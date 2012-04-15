package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import java.io.IOException;

import org.guess880.sonar_l10n_ja_plugin_tools.findbugs.FindbugsXmlHandler.MessageSet;
import org.guess880.sonar_l10n_ja_plugin_tools.utils.PluginResourceHanlder;
import org.guess880.sonar_l10n_ja_plugin_tools.utils.SonarLocalizeException;
import org.guess880.sonar_l10n_ja_plugin_tools.utils.SonarLocalizer;
import org.guess880.sonar_l10n_ja_plugin_tools.utils.SonarPropertiesLoader;
import org.guess880.sonar_l10n_ja_plugin_tools.utils.SonarProperty;

/**
 * Localize sonar's findbugs files.
 *
 * @author guess880
 */
public final class SonarFindbugsLocalizer extends SonarLocalizer {

    /**
     * for cli arguments.
     */
    private static final FindbugsArguments ARGUMENTS = new FindbugsArguments();

    /**
     * Filepath of "findbugs.xml".
     */
    private String findbugsXml;

    /**
     * Filepath of "findbugs-messages.xml".
     */
    private String messagesXml;

    /**
     * Main method.
     *
     * @param args
     * @throws SonarLocalizeException
     * @throws IOException
     */
    public static void main(final String[] args) throws SonarLocalizeException,
            IOException {
        if (ARGUMENTS.parse(args)) {
            final SonarFindbugsLocalizer localizer = new SonarFindbugsLocalizer();
            localizer.setFindbugsXml(ARGUMENTS.getFindbugsXml())
                .setFindbugsMessageXml(ARGUMENTS.getMessagesXml())
                .setLanguage(ARGUMENTS.getLanguage())
                .setProperties(ARGUMENTS.getSonarProperties())
                .setOutdir(ARGUMENTS.getOutdir())
                .localize();
        } else {
            ARGUMENTS.printUsage(System.out);
        }
    }

    /**
     * Setter of {@link #findbugsXml}.
     *
     * @param findbugsXml
     *            Filepath of "findbugs.xml".
     * @return this.
     */
    public SonarFindbugsLocalizer setFindbugsXml(final String findbugsXml) {
        this.findbugsXml = findbugsXml;
        return this;
    }

    /**
     * Setter of {@link #messagesXml}
     *
     * @param messagesXml
     *            Filepath of "findbugs-messages.xml".
     * @return this.
     */
    public SonarFindbugsLocalizer setFindbugsMessageXml(final String messageXml) {
        this.messagesXml = messageXml;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected SonarPropertiesLoader createPropertiesLoader() {
        return new SonarFindbugsPropertiesLoader();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PluginResourceHanlder createResouceHandler() {
        return new FindbugsXmlHandler()
                .setFindbugsXml(findbugsXml)
                .setMessagesXml(messagesXml);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean writesHtml(final SonarProperty property) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPropertyValue(final SonarProperty property,
            final Object resource) {
        return ((MessageSet) resource).getShortDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getHtmlValue(final SonarProperty property,
            final Object resource) {
        return ((MessageSet) resource).getDetails();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPluginKey() {
        return "findbugs";
    }

}
