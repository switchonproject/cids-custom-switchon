/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.converter;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * DOCUMENT ME!
 *
 * @author   jlauter
 * @version  $Revision$, $Date$
 */
public class EulerComputationUtil {

    //~ Static fields/initializers ---------------------------------------------

    public static final String EULER_TYPE_1_COMPUTATION = "Euler Type I";
    public static final String EULER_TYPE_2_COMPUTATION = "Euler Type II";

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   raindata         DOCUMENT ME!
     * @param   interval         DOCUMENT ME!
     * @param   eulerArithmetic  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static SortedMap<Integer, Double> eulerComputation(final SortedMap<Integer, Double> raindata,
            final int interval,
            final String eulerArithmetic) {
        final SortedMap<Integer, Double> euler2DataModel = transformToEulerDataModel(raindata, interval);
        final SortedMap<Integer, Double> result = new TreeMap<Integer, Double>();

        final int size = euler2DataModel.size();

        if (eulerArithmetic.equals(EulerComputationUtil.EULER_TYPE_1_COMPUTATION)) {
            for (int i = 0; i < size; i++) {
                final int highestRain_Key = getHighestPrecipitationIntensity_Key(euler2DataModel);
                result.put(highestRain_Key, euler2DataModel.get(highestRain_Key));
                euler2DataModel.remove(highestRain_Key);
            }
        } else if (eulerArithmetic.equals(EulerComputationUtil.EULER_TYPE_2_COMPUTATION)) {
            final int max_duration = raindata.lastKey();
            final int steps = max_duration / interval;
            final int begin_duration = (steps / 3) * interval; // ((max_duration * 30 / 100) % 5) * 5;
            int t = begin_duration;

            for (int i = 0; i < size; i++) {
                final int highestRain_Key = getHighestPrecipitationIntensity_Key(euler2DataModel);
                if ((t > 0) && (t <= begin_duration)) {
                    result.put(t, euler2DataModel.get(highestRain_Key));
                    t -= interval;
                } else if (t <= 0) {
                    t = begin_duration + interval;
                    i--;
                    continue;
                } else {
                    result.put(t, euler2DataModel.get(highestRain_Key));
                    t += interval;
                }
                euler2DataModel.remove(highestRain_Key);
            }
        }
        // Transform the values from unit mm to l/(ha*s)
        final double divisor = 0.006d;
        final Iterator iterator = result.keySet().iterator();
        while (iterator.hasNext()) {
            final Integer key = (Integer)iterator.next();
            final double value = result.get(key);
            final double new_Value = value / interval / divisor;
            result.put(key, new_Value);
        }

        return result;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   raindata  DOCUMENT ME!
     * @param   interval  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private static SortedMap<Integer, Double> transformToEulerDataModel(final SortedMap<Integer, Double> raindata,
            final int interval) {
        // First: transform the values from unit mm/h to mm
        // Example 1: duration 5 min, frequency 100 a, intensity 210.34 mm/h
        // -> 210.34 mm/(60min/5min) -> 210.34 mm/12 = 17.5283 mm
        // Example 2: duration 10 min, frequency 100 a, intensity 145.76 mm/h
        // -> 145.76 mm/(60min/10min) -> 145.76 mm/6 = 24.2933 mm

        final SortedMap<Integer, Double> data = new TreeMap<Integer, Double>(raindata);
        final int minutes = 60;

        final Iterator iterator = data.keySet().iterator();
        while (iterator.hasNext()) {
            final Integer key = (Integer)iterator.next();
            final double value = data.get(key);
            final double divisor = (double)minutes / (double)key;
            final double mm_Value = value / divisor;
            data.put(key, mm_Value);
        }

        // Secound: The rainfall amounts of the individual time intervals result through subtraction from the rainfall
        // sums

        int key = interval;
        int pre_Key;
        int counter = 0;
        final int steps = (Integer)data.lastKey() / interval;
        double diff_Value = data.get(key);
        final SortedMap euler2Model = new TreeMap();
        euler2Model.put(key, diff_Value);
        key += interval;

        for (int i = 1; i < steps; i++) {
            if (data.containsKey(key)) {
                if (counter > 0) {
                    pre_Key = key - (interval * (counter + 1));
                    diff_Value = data.get(key) - data.get(pre_Key);
                    diff_Value = diff_Value / (counter + 1);
                    for (int j = 0; j <= counter; j++) {
                        pre_Key += interval;
                        euler2Model.put(pre_Key, diff_Value);
                    }
                    counter = 0;
                } else {
                    pre_Key = key - interval;
                    diff_Value = data.get(key) - data.get(pre_Key);
                    euler2Model.put(key, diff_Value);
                }
            } else {
                counter++;
            }
            key += interval;
        }

        return euler2Model;
    }

    /**
     * DOCUMENT ME!
     *
     * @param   euler2DataModel  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    private static Integer getHighestPrecipitationIntensity_Key(final SortedMap<Integer, Double> euler2DataModel) {
        double value = -1;
        int key = -1;
        final Iterator iterator = euler2DataModel.keySet().iterator();
        while (iterator.hasNext()) {
            final Integer temp_key = (Integer)iterator.next();
            final double temp_value = euler2DataModel.get(temp_key);
            if (value < temp_value) {
                key = temp_key;
                value = temp_value;
            }
        }
        return key;
    }
}
