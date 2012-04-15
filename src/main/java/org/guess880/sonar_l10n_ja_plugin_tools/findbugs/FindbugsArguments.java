package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import java.util.Properties;

import org.guess880.sonar_l10n_ja_plugin_tools.utils.Arguments;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.Option;

/**
 * Findbugs arguments Handler.
 *
 * @author guess880
 */
final class FindbugsArguments extends Arguments {

    private static final String ALIAS_FINDBUGSXML = "findbugsxml";

    private static final String ALIAS_MESSAGESXML = "messagesxml";

    static final String ARG_NM_FINDBUGSXML = "-f";

    static final String ARG_NM_MESSAGESXML = "-m";

    @Option(required = false, name = ARG_NM_FINDBUGSXML, aliases = ALIAS_PREFIX
            + ALIAS_FINDBUGSXML, metaVar = "FILE or URL", usage = "File path of \"findbugs.xml\" of findbugs.")
    private String findbugsXml;

    @Option(required = false, name = ARG_NM_MESSAGESXML, aliases = ALIAS_PREFIX
            + ALIAS_MESSAGESXML, metaVar = "FILE or URL", usage = "File path of \"message_[language].xml\" of findbugs.")
    private String messagesXml;

    /**
     * @return {@link #findbugsXml}
     */
    String getFindbugsXml() {
        return findbugsXml;
    }

    /**
     * @return {@link #messagesXml}
     */
    String getMessagesXml() {
        return messagesXml;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setFromProperties(final Properties props) {
        findbugsXml = props.getProperty(ALIAS_FINDBUGSXML);
        messagesXml = props.getProperty(ALIAS_MESSAGESXML);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validate() throws CmdLineException {
        super.validate();
        if (findbugsXml == null) {
            throw new CmdLineException(getParser(), "findbugsXml is empty.");
        }
        if (messagesXml == null) {
            throw new CmdLineException(getParser(), "messagesXml is empty.");
        }
    }

}
