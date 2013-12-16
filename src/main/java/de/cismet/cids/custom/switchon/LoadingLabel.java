/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

/**
 * DOCUMENT ME!
 *
 * @author   martin.scholl@cismet.de
 * @version  $Revision$, $Date$
 */
public final class LoadingLabel extends JLabel {

    //~ Instance fields --------------------------------------------------------

    private final transient String loading;

    private final transient Timer timer;
    private final transient TimerTask task;

    private transient int noOfDots;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new LoadingLabel object.
     */
    public LoadingLabel() {
        loading = "LOADING ";
        noOfDots = 0;
        timer = new Timer(true);
        task = new TimerTask() {

                @Override
                public void run() {
                    final StringBuilder sb = new StringBuilder(loading);
                    for (int i = 0; i < (noOfDots % 4); ++i) {
                        sb.append('.');
                    }

                    noOfDots++;

                    LoadingLabel.this.internalSetText(sb.toString());
                }
            };

        timer.scheduleAtFixedRate(task, 0, 300);
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * DOCUMENT ME!
     */
    public void dispose() {
        timer.cancel();
    }

    /**
     * DOCUMENT ME!
     *
     * @param  text  DOCUMENT ME!
     */
    private void internalSetText(final String text) {
        super.setText(text);
    }

    @Override
    public void setText(final String text) {
        // we ignore settext, we don't want the label's text to be overridden
    }
}
