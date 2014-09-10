/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.swing.SwingUtilities;

import de.cismet.cids.dynamics.CidsBean;

import de.cismet.commons.concurrency.CismetExecutors;

/**
 * DOCUMENT ME!
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public class CidsBeanUtils {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CidsBeanUtils.class);
    private static final ExecutorService executor = CismetExecutors.newCachedThreadPool();

    //~ Methods ----------------------------------------------------------------

    /**
     * Sets a property in a CidsBean, if this property is not set. The value is taken from a future. If the future is
     * not done yet, the method will return and a new Runnable will be started which waits for the Future. After the
     * value is available, the property is set, if is still empty.
     *
     * @param  future    DOCUMENT ME!
     * @param  cidsBean  DOCUMENT ME!
     * @param  property  DOCUMENT ME!
     */
    public static void setPropertyFromFutureIfStillEmpty(final Future future,
            final CidsBean cidsBean,
            final String property) {
        final Object propertyObject = cidsBean.getProperty(property);
        if (propertyObject == null) {
            if (future.isDone()) {
                try {
                    cidsBean.setProperty(property, future.get());
                } catch (Exception ex) {
                    LOG.error(ex, ex);
                }
            } else {
                executor.submit(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                final Object propertyObject = cidsBean.getProperty(property);
                                if (propertyObject == null) {
                                    SwingUtilities.invokeLater(new Runnable() {

                                            @Override
                                            public void run() {
                                                try {
                                                    cidsBean.setProperty(property, future.get());
                                                } catch (Exception ex) {
                                                    LOG.error(ex, ex);
                                                }
                                            }
                                        });
                                }
                            } catch (Exception ex) {
                                LOG.error(ex, ex);
                            }
                        }
                    });
            }
        }
    }
}
