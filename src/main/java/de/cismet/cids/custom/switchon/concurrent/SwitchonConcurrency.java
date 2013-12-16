/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import de.cismet.commons.concurrency.CismetExecutors;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public final class SwitchonConcurrency {

    //~ Static fields/initializers ---------------------------------------------

    private static final transient ThreadGroup SWITCHON_THREAD_GROUP;

    private static final transient ExecutorService MISC_POOL;

    static {
        final SecurityManager s = System.getSecurityManager();
        final ThreadGroup parent = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();

        SWITCHON_THREAD_GROUP = new ThreadGroup(parent, "SwitchOn"); // NOI18N

        MISC_POOL = CismetExecutors.newFixedThreadPool(8, createThreadFactory("general-purpose")); // NOI18N
    }

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new SwitchonConcurrency object.
     */
    private SwitchonConcurrency() {
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param   prefix  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static ThreadFactory createThreadFactory(final String prefix) {
        return createThreadFactory(prefix, null);
    }

    /**
     * DOCUMENT ME!
     *
     * @param   prefix      DOCUMENT ME!
     * @param   excHandler  DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static ThreadFactory createThreadFactory(final String prefix,
            final Thread.UncaughtExceptionHandler excHandler) {
        return new SwitchonThreadFactory(prefix, excHandler);
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public static ExecutorService getSwitchonGeneralPurposePool() {
        return MISC_POOL;
    }

    //~ Inner Classes ----------------------------------------------------------

    /**
     * Very similar to the {@link Executors#defaultThreadFactory()} implementation.
     *
     * @version  $Revision$, $Date$
     */
    private static final class SwitchonThreadFactory implements ThreadFactory {

        //~ Static fields/initializers -----------------------------------------

        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);

        //~ Instance fields ----------------------------------------------------

        private final transient String prefix;
        private final transient AtomicInteger createCount;
        private final transient Thread.UncaughtExceptionHandler excHandler;

        //~ Constructors -------------------------------------------------------

        /**
         * Creates a new SwitchonThreadFactory object.
         *
         * @param  prefix      DOCUMENT ME!
         * @param  excHandler  DOCUMENT ME!
         */
        SwitchonThreadFactory(final String prefix, final Thread.UncaughtExceptionHandler excHandler) {
            this.prefix = prefix + "-pool-" + POOL_NUMBER.getAndIncrement() + "-thread-"; // NOI18N
            this.createCount = new AtomicInteger(1);
            this.excHandler = excHandler;
        }

        //~ Methods ------------------------------------------------------------

        @Override
        public Thread newThread(final Runnable r) {
            final Thread t = new Thread(SWITCHON_THREAD_GROUP, r, prefix + createCount.getAndIncrement(), 0);

            t.setDaemon(false);
            t.setPriority(Thread.NORM_PRIORITY);
            t.setUncaughtExceptionHandler(excHandler);

            return t;
        }
    }
}
