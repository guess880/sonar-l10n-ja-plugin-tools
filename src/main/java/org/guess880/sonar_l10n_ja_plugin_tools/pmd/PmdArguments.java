package org.guess880.sonar_l10n_ja_plugin_tools.pmd;

import java.util.Arrays;
import java.util.Properties;

import org.guess880.sonar_l10n_ja_plugin_tools.utils.Arguments;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

/**
 * Pmd arguments Handler.
 *
 * @author guess880
 */
public class PmdArguments extends Arguments {

    private static final String ALIAS_RULESETXMLS = "rulesetxmls";

    static final String ARG_NM_RULESETXMLS = "-r";

    @Option(required = false, name = ARG_NM_RULESETXMLS,
            aliases = ALIAS_PREFIX + ALIAS_RULESETXMLS,
            metaVar = "FILE or URL list",
            usage = "File pathes of \"<ruleset>.xml\" of findbugs.",
            multiValued = true, handler = StringArrayOptionHandler.class)
    private String[] ruleSetXmls;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setFromProperties(final Properties props) {
        ruleSetXmls = props.getProperty(ALIAS_RULESETXMLS).split(",");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validate() throws CmdLineException {
        super.validate();
        if (ruleSetXmls == null) {
            throw new CmdLineException(getParser(), "ruleSetXmls is empty.");
        }
    }

    /**
     * @return {@link #ruleSetXmls}
     */
    String[] getRuleSetXmls() {
        return Arrays.copyOf(ruleSetXmls, ruleSetXmls.length);
    }

}
