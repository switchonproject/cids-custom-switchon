/***************************************************
*
* cismet GmbH, Saarbruecken, Germany
*
*              ... and it just works.
*
****************************************************/
package de.cismet.cids.custom.switchon.wizards;

import org.apache.commons.lang.StringUtils;

import org.openide.WizardDescriptor;
import org.openide.util.Utilities;

import java.awt.Component;

import java.text.BreakIterator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

import de.cismet.cids.custom.switchon.gui.InfoProvider;
import de.cismet.cids.custom.switchon.gui.InfoReceiver;
import de.cismet.cids.custom.switchon.gui.MarkMandtoryFieldsStrong;

import de.cismet.commons.concurrency.CismetExecutors;

import de.cismet.commons.gui.wizard.AbstractWizardPanel;

/**
 * A AbstractWizardPanel which knows the class of its VisualPanel and which can generate the VisualPanel itself. This
 * avoids some code, especially the cast after a call of <code>getComponent()</code>
 *
 * @param    <T>  The class of the VisualPanel
 *
 * @author   Gilles Baatz
 * @version  $Revision$, $Date$
 */
public abstract class GenericAbstractWizardPanel<T extends Component> extends AbstractWizardPanel
        implements InfoProvider {

    //~ Static fields/initializers ---------------------------------------------

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(
            GenericAbstractWizardPanel.class);

    private static final ExecutorService executorService = CismetExecutors.newFixedThreadPool(5);

    //~ Instance fields --------------------------------------------------------

    private Future waitRunnable;

    private String generalInformation = "";

    private final Class<T> clazz;

    /**
     * This boolean is needed for a small hack to avoid that the warning is shown to early. After the settings were
     * read, the warning can not be shown for a half second. This has to be done as the warning will be shown when the
     * first component, which contains a mandatory value, receives the focus. Due to the binding on the components the
     * isValid()-method will be fired.
     */
    private final AtomicBoolean allowedToShowWarning = new AtomicBoolean(false);

    //~ Constructors -----------------------------------------------------------

    /**
     * The actual class of the generic has to be known, as otherwise a new instance of the class can not be created
     * automatically.
     *
     * @param  clazz  DOCUMENT ME!
     */
    public GenericAbstractWizardPanel(final Class<T> clazz) {
        this.clazz = clazz;
    }

    //~ Methods ----------------------------------------------------------------

    @Override
    public void readSettings(final Object settings) {
        allowedToShowWarning.set(false);
        super.readSettings(settings);
        showGeneralInformation();
        waitRunnable = executorService.submit(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            LOG.error(ex, ex);
                        } finally {
                            allowedToShowWarning.set(true);
                        }
                    }
                });
    }

    @Override
    public void storeSettings(final Object settings) {
        super.storeSettings(settings); // To change body of generated methods, choose Tools | Templates.
        if (waitRunnable != null) {
            waitRunnable.cancel(true);
        }
    }

    @Override
    protected Component createComponent() {
        Component createdComponent = null;
        try {
            createdComponent = clazz.newInstance();
        } catch (InstantiationException ex) {
            LOG.error(ex, ex);
        } catch (IllegalAccessException ex) {
            LOG.error(ex, ex);
        }
        if (createdComponent instanceof MarkMandtoryFieldsStrong) {
            ((MarkMandtoryFieldsStrong)createdComponent).markMandatoryFieldsStrong();
        }
        return createdComponent;
    }

    @Override
    public T getComponent() {
        return (T)super.getComponent();
    }

    /**
     * DOCUMENT ME!
     *
     * @return  DOCUMENT ME!
     */
    public String getGeneralInformation() {
        return generalInformation;
    }

    /**
     * DOCUMENT ME!
     *
     * @param  generalInformation  DOCUMENT ME!
     */
    public void setGeneralInformation(final String generalInformation) {
        if (generalInformation != null) {
            this.generalInformation = generalInformation;
        } else {
            this.generalInformation = "";
        }
    }

    /**
     * DOCUMENT ME!
     */
    public void showGeneralInformation() {
        provideInformation(generalInformation);
    }

    /**
     * Shows a warning. If the text is blank, the general information will be shown.
     *
     * @param  text  DOCUMENT ME!
     */
    public void showWarning(final String text) {
        if (allowedToShowWarning.get()) {
            if (StringUtils.isNotBlank(text)) {
                String stringToShow = Utilities.wrapString(
                        text,
                        100,
                        BreakIterator.getWordInstance(),
                        true);
                stringToShow = "<html>" + stringToShow.replaceAll("\n", "<br>") + "</html>";
                wizard.putProperty(WizardDescriptor.PROP_WARNING_MESSAGE, stringToShow);
            } else {
                showGeneralInformation();
            }
        }
    }

    /**
     * Tries to show the information through a InforReceiver, if no InfoReceiver exists, then the information is shown
     * in the info field of the wizard. The general information must be wrapped inside html and the line break must be
     * explicitely set, as otherwise the newline will not be shown. This is the case with the openIde version
     * RELEASE701.
     *
     * @param  information  DOCUMENT ME!
     */
    @Override
    public void provideInformation(final String information) {
        wizard.putProperty(WizardDescriptor.PROP_INFO_MESSAGE, null);
        if (getInfoReceiver() != null) {
            getInfoReceiver().setInformation(information);
        } else {
            String stringToShow = Utilities.wrapString(
                    generalInformation,
                    100,
                    BreakIterator.getWordInstance(),
                    true);
            stringToShow = "<html>" + stringToShow.replaceAll("\n", "<br>") + "</html>";
            wizard.putProperty(WizardDescriptor.PROP_INFO_MESSAGE, stringToShow);
        }
    }

    /**
     * Checks if its component is an InfoReceiver, if this is the case the component is returned. Otherwise null is
     * returned.
     *
     * @return  DOCUMENT ME!
     */
    @Override
    public InfoReceiver getInfoReceiver() {
        if (getComponent() instanceof InfoReceiver) {
            return (InfoReceiver)getComponent();
        } else {
            return null;
        }
    }

    @Override
    public void setInfoReceiver(final InfoReceiver infoReceiver) {
        LOG.fatal("GenericAbstractWizardPanel.setInfoReceiver: Not supported yet.", new Exception()); // NOI18N
    }

    @Override
    public void provideError(final String error) {
        if (getInfoReceiver() != null) {
            getInfoReceiver().setError(error);
        }
    }
}
