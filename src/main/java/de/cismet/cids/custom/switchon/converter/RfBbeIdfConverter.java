/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.converter;

import org.apache.log4j.Logger;

import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.text.NumberFormat;

import java.util.Collection;
import java.util.Locale;
import java.util.SortedMap;

import de.cismet.cids.custom.switchon.IDFCurve;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
@ServiceProvider(service = IDFConverter.class)
public final class RfBbeIdfConverter implements IDFConverter, FormatHint {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient Logger LOG = Logger.getLogger(RfBbeIdfConverter.class);

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   from    DOCUMENT ME!
     * @param   params  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  ConversionException  DOCUMENT ME!
     */
    @Override
    public IDFCurve convertForward(final InputStream from, final String... params) throws ConversionException {
        try {
            final IDFCurve curve = new IDFCurve();
            final BufferedReader r = new BufferedReader(new InputStreamReader(from)); // NOI18N
            String line;
            // ignore first line
            r.readLine();

            final NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
            while ((line = r.readLine()) != null) {
                final String[] split = line.trim().split(";");         // NOI18N
                if (split.length != 3) {
                    throw new IllegalStateException("illegal format"); // NOI18N
                }

                curve.add(Integer.valueOf(split[1]), Integer.valueOf(split[0]), nf.parse(split[2]).doubleValue());

                if (Thread.currentThread().isInterrupted()) {
                    LOG.warn("conversion was interrupted"); // NOI18N

                    return null;
                }
            }

            // the file does not contain the target year nor its extend

            return curve;
        } catch (final Exception ex) {
            final String message = "cannot convert idf curve"; // NOI18N
            LOG.error(message, ex);

            throw new ConversionException(message, ex);
        }

        // we don't close the given stream as the caller must take care of this
    }

    /**
     * DOCUMENT ME!
     *
     * @param   to      DOCUMENT ME!
     * @param   params  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  ConversionException  DOCUMENT ME!
     */
    @Override
    public InputStream convertBackward(final IDFCurve to, final String... params) throws ConversionException {
        final NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
        final String lineSep = System.getProperty("line.separator"); // NOI18N
        final char sep = ';';

        final StringBuilder sb = new StringBuilder("Return period [a];Duration [min];Intensity [mm/h]"); // NOI18N
        sb.append(lineSep);

        try {
            final SortedMap<Integer, SortedMap<Integer, Double>> data = to.getData();
            final Collection<Integer> durations = data.keySet();
            final Collection<Integer> frequencies = to.getFrequencies();

            for (final Integer freq : frequencies) {
                for (final Integer dura : durations) {
                    sb.append(freq).append(sep);
                    sb.append(dura).append(sep);
                    sb.append(nf.format(data.get(dura).get(freq))).append(lineSep);
                }
            }

            // there may be encoding issues
            return new ByteArrayInputStream(sb.toString().getBytes());
        } catch (final Exception e) {
            final String message = "cannot convert idf data"; // NOI18N
            LOG.error(message, e);
            throw new ConversionException(message, e);
        }
    }

    @Override
    public String toString() {
        return getFormatDisplayName();
    }

    @Override
    public String getFormatName() {
        return "rf-bbe-idf-converter"; // NOI18N
    }

    @Override
    public String getFormatDisplayName() {
        return NbBundle.getMessage(
                RfBbeIdfConverter.class,
                "RfBbeIdfConverter.getFormatDisplayName().description"); // NOI18N
    }

    @Override
    public String getFormatHtmlName() {
        return null;
    }

    @Override
    public String getFormatDescription() {
        return NbBundle.getMessage(
                RfBbeIdfConverter.class,
                "RfBbeIdfConverter.getFormatDescription().description"); // NOI18N
    }

    @Override
    public String getFormatHtmlDescription() {
        return NbBundle.getMessage(
                RfBbeIdfConverter.class,
                "RfBbeIdfConverter.getFormatHtmlDescription().description"); // NOI18N
    }

    @Override
    public Object getFormatExample() {
        return NbBundle.getMessage(RfBbeIdfConverter.class, "RfBbeIdfConverter.getFormatExample().description"); // NOI18N
    }
}
