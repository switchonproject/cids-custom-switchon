/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Workaround to the fact that there are no abstract enums. The downside of the usage of an abstract class instead of an
 * abstract enum is a) that it is not possible to force subclasses to provide a static method <code>T[] values()</code>
 * and b) that classes who try to take advantage of the abstract nature of the class must be initialised with a <code>
 * Class<T></code> object and will have to access the <code>values()</code> method via reflection. Moreover the <code>
 * switch</code> statement cannot be used anymore<br/>
 * <br/>
 * <b>Every implementing class must implement <code>public static T[] values()</code> to provide all available
 * objects.</b>
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
// TODO: refactor as soon as abstract enums are supported
public abstract class LocalisedEnum<T> implements Iterable<T>, Serializable, Comparable<T> {

    //~ Instance fields --------------------------------------------------------

    private final String localisedName;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new LocalisedEnum object.
     *
     * @param  localisedName  DOCUMENT ME!
     */
    public LocalisedEnum(final String localisedName) {
        assert localisedName != null : "localised name must never be null"; // NOI18N

        this.localisedName = localisedName;
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    public int compareTo(final T o) {
        return localisedName.compareTo(((LocalisedEnum)o).localisedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public String getLocalisedName() {
        return localisedName;
    }

    @Override
    public Iterator<T> iterator() {
        final T[] values = Arrays.copyOf(internalValues(), internalValues().length);

        return new Iterator<T>() {

                private int i = 0;

                @Override
                public boolean hasNext() {
                    return i < values.length;
                }

                @Override
                public T next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException("no more elements"); // NOI18N
                    }

                    return values[i++];
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException("Not supported!"); // NOI18N
                }
            };
    }

    @Override
    public String toString() {
        return localisedName + "[" + super.toString() + "]"; // NOI18N
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    protected abstract T[] internalValues();
}
