package org.guess880.sonar_l10n_ja_plugin_tools.pmd;

import net.sourceforge.pmd.RuleSetFactory;
import net.sourceforge.pmd.RuleSetNotFoundException;
import net.sourceforge.pmd.RuleSets;

import org.guess880.sonar_l10n_ja_plugin_tools.utils.PluginResourceHanlder;
import org.guess880.sonar_l10n_ja_plugin_tools.utils.SonarLocalizeException;

/**
 * Handle the pmd l10n messages file as '<ruleset>.xml'.
 *
 * @author guess880
 */
public class PmdXmlHandler implements PluginResourceHanlder {

    /**
     * Pmd rulesets.
     */
    private RuleSets ruleSets;

    /**
     * Pmd ruleset filepaths.
     */
    private String[] ruleSetFiles;

    /**
     * Setter of {@link #ruleSetFiles}
     * @param ruleSetFiles {@link #ruleSetFiles}
     * @return this
     */
    PmdXmlHandler setRuleSetFiles(final String... ruleSetFiles) {
        this.ruleSetFiles = ruleSetFiles;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() throws SonarLocalizeException {
        try {
            final RuleSetFactory factory = new RuleSetFactory();
            final StringBuilder sb = new StringBuilder();
            for (String file : ruleSetFiles) {
                sb.append(file);
                sb.append(',');
            }
            sb.delete(sb.length() - 1, sb.length());
            ruleSets = factory.createRuleSets(sb.toString());
        } catch (RuleSetNotFoundException e) {
            throw new SonarLocalizeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getLocalizedResource(final String key) {
        return ruleSets.getRuleByName(key);
    }

}
