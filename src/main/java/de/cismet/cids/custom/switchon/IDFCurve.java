/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.vividsolutions.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public final class IDFCurve {

    //~ Instance fields --------------------------------------------------------

    // duration, frequency, intensity
    private transient SortedMap<Integer, SortedMap<Integer, Double>> data;
    private transient Geometry geom;
    private transient Integer centerYear;
    private transient Boolean forecast;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new IDFCurve object.
     */
    public IDFCurve() {
        data = new TreeMap<Integer, SortedMap<Integer, Double>>();
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * Add new IDF data.
     *
     * @param   duration   in minutes
     * @param   frequency  in years
     * @param   intensity  in mm/h
     *
     * @return  true if the entry was successfully inserted, false if the entry was not inserted because there already
     *          is a corresponding entry
     */
    @JsonIgnore
    public boolean add(final int duration, final int frequency, final double intensity) {
        synchronized (this) {
            if (!data.containsKey(duration)) {
                data.put(duration, new TreeMap<Integer, Double>());
            }

            final Map<Integer, Double> freqToIntensity = data.get(duration);
            if (freqToIntensity.containsKey(frequency)) {
                return false;
            } else {
                freqToIntensity.put(frequency, intensity);

                return true;
            }
        }
    }

    /**
     * Creates a Timeseries Toolbox conformant string from the IDF data.
     *
     * @return  a TSTB conformat string to be used for IDF DS
     */
    @JsonIgnore
    public String toTSTBFormat() {
        final StringBuilder dura = new StringBuilder();
        final StringBuilder freq = new StringBuilder();
        final StringBuilder inte = new StringBuilder();

        synchronized (this) {
            final Iterator<Integer> itDurations = data.keySet().iterator();
            while (itDurations.hasNext()) {
                final Integer duration = itDurations.next();
                final SortedMap<Integer, Double> freqToIntensity = data.get(duration);
                final Iterator<Integer> itFrequencies = freqToIntensity.keySet().iterator();
                while (itFrequencies.hasNext()) {
                    final Integer frequency = itFrequencies.next();
                    final Double intensity = freqToIntensity.get(frequency);

                    dura.append(duration).append(':');
                    freq.append(frequency).append(':');
                    inte.append(intensity).append(':');
                }
            }
        }

        dura.deleteCharAt(dura.length() - 1);
        freq.deleteCharAt(freq.length() - 1);
        inte.deleteCharAt(inte.length() - 1);

        return dura.toString() + " " + freq.toString() + " " + inte.toString(); // NOI18N
    }

    /**
     * Returns the internal data storage of this <code>IDFCurve</code> instance. The keys of the map are the durations
     * in ascending order. The values of the map are another map whose keys are the frequencies in ascending order and
     * the values are the intensities.
     *
     * @return  the internal data storage
     */
    public SortedMap<Integer, SortedMap<Integer, Double>> getData() {
        return data;
    }

    /**
     * Sets the internal data storage of this <code>IDFCurve</code> instance. The keys of the given map are assumed to
     * be the durations in ascending order. The values of the map are assumed to be another map whose keys are assumed
     * to be the frequencies in ascending order and the values are assumed to be the intensities.
     *
     * @param  data  the new internal data for this <code>IDFCurve</code>
     */
    public void setData(final SortedMap<Integer, SortedMap<Integer, Double>> data) {
        this.data = data;
    }

    /**
     * Retrieves a list of the frequencies in ascending order.
     *
     * @return  the frequencies in ascending order
     */
    @JsonIgnore
    public List<Integer> getFrequencies() {
        // assumes same frequencies for all durations, will be sorted
        return new ArrayList<Integer>(data.get(data.firstKey()).keySet());
    }

    /**
     * Provides the actual data as rows which are intended to be used in a JTable. The <code>Object[][]</code> will
     * contain all the durations in ascending order with all their intensities, sorted by the ascending order of the
     * frequencies.<br/>
     * <br/>
     * Example:<br/>
     * o[1] = {5 48.3 51.5 27.3} o[2] = {10 37.2 47.2 58.3} o[3] = {20 27.57 23.5 21.456} ...<br/>
     * whereas the first value is the duration and the subsequent values are the intensities.
     *
     * @return  an array containing the actual data as rows which are intended to be used in a JTable.
     */
    @JsonIgnore
    public Object[][] getDurationIntensityRows() {
        final Set<Integer> durations = data.keySet();
        final Iterator<Integer> itDurations = durations.iterator();

        final Object[][] rows = new Object[durations.size()][];
        int i = 0;
        while (itDurations.hasNext()) {
            final Integer duration = itDurations.next();
            final Collection<Double> intensities = data.get(duration).values();
            final Object[] row = new Object[intensities.size() + 1];
            row[0] = duration;
            int j = 1;
            for (final Double intensity : intensities) {
                row[j++] = intensity;
            }

            rows[i++] = row;
        }

        return rows;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    @JsonIgnore
    public Integer getCenterYear() {
        return centerYear;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  centerYear  DOCUMENT ME!
     */
    public void setCenterYear(final Integer centerYear) {
        this.centerYear = centerYear;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    @JsonIgnore
    public Geometry getGeom() {
        return geom;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  geom  DOCUMENT ME!
     */
    public void setGeom(final Geometry geom) {
        this.geom = geom;
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public Boolean getForecast() {
        return forecast;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  forecast  DOCUMENT ME!
     */
    public void setForecast(final Boolean forecast) {
        this.forecast = forecast;
    }
}
