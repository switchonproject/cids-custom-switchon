/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import org.openide.util.NbBundle;

import java.io.IOException;
import java.io.Serializable;

/**
 * DOCUMENT ME!
 *
 * @author   mscholl
 * @version  $Revision$, $Date$
 */
// TODO: refactor as soon as abstract enums are supported
public final class Variable extends LocalisedEnum<Variable> implements Serializable, JsonSerializable {

    //~ Static fields/initializers ---------------------------------------------

    public static final Variable TEMPERATURE = new Variable(
            "urn:ogc:def:property:OGC:temp", // NOI18N
            NbBundle.getMessage(Variable.class, "Variable.TEMPERATURE.localisedName")); // NOI18N
    public static final Variable PRECIPITATION = new Variable(
            "urn:ogc:def:property:OGC:prec", // NOI18N
            NbBundle.getMessage(Variable.class, "Variable.PRECIPITATION.localisedName")); // NOI18N
    public static final Variable NOX = new Variable(
            "urn:ogc:def:property:OGC:NOX", // NOI18N
            NbBundle.getMessage(Variable.class, "Variable.NOX.localisedName")); // NOI18N
    public static final Variable NO2 = new Variable(
            "urn:ogc:def:property:OGC:NO2", // NOI18N
            NbBundle.getMessage(Variable.class, "Variable.NO2.localisedName")); // NOI18N
    public static final Variable SO2 = new Variable(
            "urn:ogc:def:property:OGC:SO2", // NOI18N
            NbBundle.getMessage(Variable.class, "Variable.SO2.localisedName")); // NOI18N
    public static final Variable PM10 = new Variable(
            "urn:ogc:def:property:OGC:PM10", // NOI18N
            NbBundle.getMessage(Variable.class, "Variable.PM10.localisedName")); // NOI18N
    public static final Variable PM2_5 = new Variable(
            "urn:ogc:def:property:OGC:PM2.5", // NOI18N
            NbBundle.getMessage(Variable.class, "Variable.PM2_5.localisedName")); // NOI18N
    public static final Variable O3 = new Variable(
            "urn:ogc:def:property:OGC:O3", // NOI18N
            NbBundle.getMessage(Variable.class, "Variable.O3.localisedName")); // NOI18N
    public static final Variable EVAPORATION = new Variable(
            "urn:ogc:def:property:OGC:Evaporation", // NOI18N
            NbBundle.getMessage(Variable.class, "Variable.EVAPORATION.localisedName")); // NOI18N
    public static final Variable COUT = new Variable(
            "urn:ogc:def:property:OGC:cout", // NOI18N
            NbBundle.getMessage(Variable.class, "Variable.COUT.localisedName")); // NOI18N
    public static final Variable CRUN = new Variable(
            "urn:ogc:def:property:OGC:crun", // NOI18N
            NbBundle.getMessage(Variable.class, "Variable.CRUN.localisedName")); // NOI18N
    public static final Variable CPRC = new Variable(
            "urn:ogc:def:property:OGC:cprc", // NOI18N
            NbBundle.getMessage(Variable.class, "Variable.CPRC.localisedName")); // NOI18N
    public static final Variable CTMP = new Variable(
            "urn:ogc:def:property:OGC:ctmp", // NOI18N
            NbBundle.getMessage(Variable.class, "Variable.CTMP.localisedName")); // NOI18N
    public static final Variable GWAT = new Variable(
            "urn:ogc:def:property:OGC:gwat", // NOI18N
            NbBundle.getMessage(Variable.class, "Variable.GWAT.localisedName")); // NOI18N
    public static final Variable SMDF = new Variable(
            "urn:ogc:def:property:OGC:smdf", // NOI18N
            NbBundle.getMessage(Variable.class, "Variable.SMDF.localisedName")); // NOI18N
    public static final Variable UNKNOWN = new Variable(
            "urn:ogc:def:property:OGC:unknown", // NOI18N
            NbBundle.getMessage(Variable.class, "Variable.UNKNOWN.localisedName")); // NOI18N

    //~ Instance fields --------------------------------------------------------

    private final String propertyKey;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new Variable object.
     */
    private Variable() {
        this(null, null);
    }

    /**
     * Creates a new Variable object.
     *
     * @param  localisedName  DOCUMENT ME!
     */
    @JsonIgnore
    private Variable(final String localisedName) {
        this(null, localisedName);
    }

    /**
     * Creates a new Variable object.
     *
     * @param  propertyKey    DOCUMENT ME!
     * @param  localisedName  DOCUMENT ME!
     */
    private Variable(final String propertyKey, final String localisedName) {
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
    protected Variable[] internalValues() {
        return values();
    }

    /**
     * DOCUMENT ME!
     *
     * @param   variable  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     *
     * @throws  IllegalArgumentException  DOCUMENT ME!
     */
    @JsonCreator
    public static Variable getVariable(final String variable) {
        for (final Variable v : values()) {
            if (v.getPropertyKey().equals(variable)) {
                return v;
            }
        }

        throw new IllegalArgumentException("unknown variable: " + variable); // NOI18N
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static Variable[] values() {
        return new Variable[] {
                EVAPORATION,
                NOX,
                NO2,
                O3,
                PM10,
                PM2_5,
                PRECIPITATION,
                SO2,
                TEMPERATURE,
                COUT,
                CPRC,
                CRUN,
                CTMP,
                GWAT,
                SMDF,
                UNKNOWN
            };
    }

    @Override
    public void serialize(final JsonGenerator jgen, final SerializerProvider provider) throws IOException,
        JsonProcessingException {
        jgen.writeString(getPropertyKey());
    }

    @Override
    public void serializeWithType(final JsonGenerator jgen,
            final SerializerProvider provider,
            final TypeSerializer typeSer) throws IOException, JsonProcessingException {
        serialize(jgen, provider);
    }
}
