package org.guess880.sonar_l10n_ja_plugin_tools.pmd;

import java.io.IOException;
import java.util.List;

import net.sourceforge.pmd.Rule;

import org.guess880.sonar_l10n_ja_plugin_tools.pmd.SonarPmdPropertiesLoader.SonarPmdNameProperty;
import org.guess880.sonar_l10n_ja_plugin_tools.utils.PluginResourceHanlder;
import org.guess880.sonar_l10n_ja_plugin_tools.utils.SonarLocalizeException;
import org.guess880.sonar_l10n_ja_plugin_tools.utils.SonarLocalizer;
import org.guess880.sonar_l10n_ja_plugin_tools.utils.SonarPropertiesLoader;
import org.guess880.sonar_l10n_ja_plugin_tools.utils.SonarProperty;

/**
 * Localize sonar's pmd files.
 *
 * @author guess880
 */
public class SonarPmdLocalizer extends SonarLocalizer {

    /**
     * for cli arguments.
     */
    private static final PmdArguments ARGUMENTS = new PmdArguments();

    /**
     * Space.
     */
    private static final String SPACE = " ";

    /**
     * Carrige return.
     */
    private static final String CR = "\r";

    /**
     * Line feed.
     */
    private static final String LF = "\n";

    /**
     * Pmd ruleset filepaths.
     */
    private String[] ruleSetFiles;

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
            final SonarPmdLocalizer localizer = new SonarPmdLocalizer();
            localizer.setRuleSets(ARGUMENTS.getRuleSetXmls())
                .setLanguage(ARGUMENTS.getLanguage())
                .setProperties(ARGUMENTS.getSonarProperties())
                .setOutdir(ARGUMENTS.getOutdir())
                .localize();
        } else {
            ARGUMENTS.printUsage(System.out);
        }
    }

    /**
     * Setter of {@link #ruleSetFiles}
     * @param ruleSetFiles {@link #ruleSetFiles}
     * @return this
     */
    public SonarPmdLocalizer setRuleSets(final String... ruleSetFiles) {
        this.ruleSetFiles = ruleSetFiles;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected SonarPropertiesLoader createPropertiesLoader() {
        return new SonarPmdPropertiesLoader();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PluginResourceHanlder createResouceHandler() {
        return new PmdXmlHandler().setRuleSetFiles(ruleSetFiles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean writesHtml(final SonarProperty property) {
        return property instanceof SonarPmdNameProperty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPropertyValue(final SonarProperty property, final Object resource) {
        return property.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getHtmlValue(final SonarProperty property, final Object resource) {
        final Rule rule = (Rule) resource;
        final String description = prettyDescription(rule.getDescription());
        final String examples = prettyExamples(rule.getExamples());
        return description + examples;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPluginKey() {
        return "pmd";
    }

    /**
     * Pretty print description.
     *
     * @param description description of pmd's rule
     * @return pretty printted description
     */
    private String prettyDescription(final String description) {
        return description
                .replaceAll(CR + LF, SPACE)
                .replaceAll(LF + CR, SPACE)
                .replaceAll(CR, SPACE)
                .replaceAll(LF, SPACE)
                .replaceAll("\\s+", SPACE)
                .trim();
    }

    /**
     * Pretty print examples.
     *
     * @param examples examples of pmd's rule
     * @return pretty printted examples
     */
    private String prettyExamples(final List<String> examples) {
        final StringBuilder sb = new StringBuilder();
        for (String example : examples) {
            sb.append(LF);
            sb.append("<p>Example:</p>");
            sb.append(LF);
            sb.append("<pre>");
            sb.append(LF);
            sb.append(example.trim());
            sb.append(LF);
            sb.append("</pre>");
        }
        return sb.toString();
    }

}
