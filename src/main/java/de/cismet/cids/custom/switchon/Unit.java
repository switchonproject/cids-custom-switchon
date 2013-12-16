/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

import org.openide.util.NbBundle;

import java.io.Serializable;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
// TODO: refactor as soon as abstract enums are possible
public final class Unit extends LocalisedEnum<Unit> implements Serializable {

    //~ Static fields/initializers ---------------------------------------------

    public static final Unit M = new Unit("urn:ogc:def:uom:OGC:m", "Meter");                                            // NOI18N
    public static final Unit CM = new Unit("urn:ogc:def:uom:OGC:cm", "Centimeter");                                     // NOI18N
    public static final Unit MM = new Unit(
            "urn:ogc:def:uom:OGC:mm",                                                                                   // NOI18N
            NbBundle.getMessage(Unit.class, "Unit.MILLIMETERS.localisedName"));                                         // NOI18N
    public static final Unit PPM = new Unit(
            "urn:ogc:def:uom:OGC:ppm",                                                                                  // NOI18N
            NbBundle.getMessage(Unit.class, "Unit.PARTS_PER_MILLION.localisedName"));                                   // NOI18N
    public static final Unit PPB = new Unit(
            "urn:ogc:def:uom:OGC:ppb",                                                                                  // NOI18N
            NbBundle.getMessage(Unit.class, "Unit.PARTS_PER_BILLION.localisedName"));                                   // NOI18N
    public static final Unit KELVIN = new Unit(
            "urn:ogc:def:uom:OGC:K",                                                                                    // NOI18N
            NbBundle.getMessage(
                Unit.class,
                "Unit.KELVIN.localisedName"));                                                                          // NOI18N
    public static final Unit M3S = new Unit(
            "urn:org:def:uom:OGC:m3s",                                                                                  // NOI18N
            NbBundle.getMessage(Unit.class, "Unit.M3S.localisedName"));                                                 // NOI18N
    public static final Unit LS = new Unit(
            "l/s",                                                                                                      // NOI18N
            NbBundle.getMessage(Unit.class, "Unit.LS.localisedName"));                                                  // NOI18N
    public static final Unit M3 = new Unit(
            "m^3",                                                                                                      // NOI18N
            NbBundle.getMessage(Unit.class, "Unit.M3.localisedName"));                                                  // NOI18N
    public static final Unit MM_MIN = new Unit(
            "mm/min",                                                                                                   // NOI18N
            NbBundle.getMessage(Unit.class, "Unit.MM_MIN.localisedName"));                                              // NOI18N
    public static final Unit MM_DAY = new Unit(
            "mm/day",                                                                                                   // NOI18N
            NbBundle.getMessage(Unit.class, "Unit.MM_DAY.localisedName"));                                              // NOI18N
    public static final Unit MG_L = new Unit(
            "mg/l",                                                                                                     // NOI18N
            NbBundle.getMessage(Unit.class, "Unit.MG_L.localisedName"));                                                // NOI18N
    public static final Unit METERS = new Unit(
            "m",                                                                                                        // NOI18N
            NbBundle.getMessage(Unit.class, "Unit.METERS.localisedName"));                                              // NOI18N
    public static final Unit KG = new Unit(
            "kg",                                                                                                       // NOI18N
            NbBundle.getMessage(Unit.class, "Unit.KG.localisedName"));                                                  // NOI18N
    public static final Unit MM_H = new Unit(
            "mm/h",                                                                                                     // NOI18N
            org.openide.util.NbBundle.getMessage(Unit.class, "Unit.MM_H.localisedName"));                               // NOI18N
    public static final Unit L_S_HA = new Unit("l/s*ha", NbBundle.getMessage(Unit.class, "Unit.L_S_HA.localisedName")); // NOI18N
    public static final Unit CELSIUS = new Unit(
            "urn:org:def:uom:OGC:Celcius",                                                                              // NOI18N
            NbBundle.getMessage(Unit.class, "Unit.CELSIUS.localisedName"));                                             // NOI18N

    //~ Instance fields --------------------------------------------------------

    private final transient String propertyKey;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new Unit object.
     */
    private Unit() {
        this(null, null);
    }

    /**
     * Creates a new Unit object.
     *
     * @param  propertyKey  DOCUMENT ME!
     */
    private Unit(final String propertyKey) {
        this(propertyKey, null);
    }

    /**
     * Creates a new Unit object.
     *
     * @param  propertyKey    DOCUMENT ME!
     * @param  localisedName  DOCUMENT ME!
     */
    private Unit(final String propertyKey, final String localisedName) {
        super(localisedName);

        this.propertyKey = propertyKey;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public String getPropertyKey() {
        return propertyKey;
    }

    @Override
    protected Unit[] internalValues() {
        return values();
    }

    /**
     * DOCUMENT ME!
     *
     * @param   unit  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static Unit createCustomUnit(final String unit) {
        return new Unit(unit, unit);
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static Unit[] values() {
        return new Unit[] {
                MM, PPB, PPM, KELVIN,
                M3S, LS, M3, MM_MIN, MM_DAY,
                MG_L, METERS, KG, CELSIUS
            };
    }
}
