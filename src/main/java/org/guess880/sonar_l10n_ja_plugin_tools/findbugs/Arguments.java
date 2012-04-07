package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ExampleMode;
import org.kohsuke.args4j.Option;

/**
 * Arguments Handler.
 *
 * @author guess880
 */
final class Arguments {

    private static final String ALIAS_LANGUAGE = "language";

    private static final String ALIAS_SONARPROPERTIES = "sonarproperties";

    private static final String ALIAS_FINDBUGSXML = "findbugsxml";

    private static final String ALIAS_MESSAGESXML = "messagesxml";

    static final String ARG_NM_LANGUAGE = "-l";

    static final String ARG_NM_SONAR_PROPERTIES = "-s";

    static final String ARG_NM_FINDBUGSXML = "-f";

    static final String ARG_NM_MESSAGESXML = "-m";

    static final String ARG_NM_PROPERTIES = "-p";

    static final String ARG_NM_OUTDIR = "-o";

    private static final String ALIAS_PREFIX = "--";

    @Option(required = false, name = ARG_NM_LANGUAGE, aliases = ALIAS_PREFIX
            + ALIAS_LANGUAGE, usage = "Localize language. (e.g. Japanese: ja)")
    private String language;

    @Option(required = false, name = ARG_NM_SONAR_PROPERTIES, aliases = ALIAS_PREFIX
            + ALIAS_SONARPROPERTIES, metaVar = "FILE or URL", usage = "File path of \"findbugs.properties\" of sonar.")
    private String sonarProperties;

    @Option(required = false, name = ARG_NM_FINDBUGSXML, aliases = ALIAS_PREFIX
            + ALIAS_FINDBUGSXML, metaVar = "FILE or URL", usage = "File path of \"findbugs.xml\" of findbugs.")
    private String findbugsXml;

    @Option(required = false, name = ARG_NM_MESSAGESXML, aliases = ALIAS_PREFIX
            + ALIAS_MESSAGESXML, metaVar = "FILE or URL", usage = "File path of \"message_[language].xml\" of findbugs.")
    private String messagesXml;

    @Option(required = false, name = ARG_NM_PROPERTIES, aliases = ALIAS_PREFIX
            + "properties", usage = "File path of \"options.properties\". If use this option then ignore other options excluding \"outdir\".")
    private File properties;

    @Option(required = true, name = ARG_NM_OUTDIR, aliases = ALIAS_PREFIX
            + "outdir", usage = "Output directory path.")
    private File outdir;

    private final CmdLineParser parser;

    private CmdLineException lastError;

    /**
     * Constructor.
     */
    Arguments() {
        parser = new CmdLineParser(this);
    }

    /**
     * Parse arguments.
     *
     * @param args Arguments.
     * @return If illegal arguments exist then return <code>true</code>, otherwise <code>false</code>.
     * @throws IOException
     */
    boolean parse(String[] args) throws IOException {
        boolean ret = false;
        try {
            parser.parseArgument(args);
            if (properties != null) {
                final Properties props = new Properties();
                final FileInputStream in = new FileInputStream(properties);
                try {
                    props.load(in);
                } finally {
                    in.close();
                }
                language = props.getProperty(ALIAS_LANGUAGE);
                sonarProperties = props.getProperty(ALIAS_SONARPROPERTIES);
                findbugsXml = props.getProperty(ALIAS_FINDBUGSXML);
                messagesXml = props.getProperty(ALIAS_MESSAGESXML);
            }
            if (language == null) {
                throw new CmdLineException(parser, "language is empty.");
            }
            if (sonarProperties == null) {
                throw new CmdLineException(parser, "sonarProperties is empty.");
            }
            if (findbugsXml == null) {
                throw new CmdLineException(parser, "findbugsXml is empty.");
            }
            if (messagesXml == null) {
                throw new CmdLineException(parser, "messagesXml is empty.");
            }
            ret = true;
        } catch (CmdLineException e) {
            lastError = e;
        }
        return ret;
    }

    /**
     * Printing usage.
     *
     * @param out Destination.
     */
    void printUsage(PrintStream out) {
        out.println(lastError.getMessage());
        out.printf("USAGE:%n java SonarFindbugsLocalizer%s%n",
                parser.printExample(ExampleMode.ALL)
                );
        parser.printUsage(out);
    }

    /**
     * @return {@link #language}
     */
    String getLanguage() {
        return language;
    }

    /**
     * @return {@link #sonarProperties}
     */
    String getSonarProperties() {
        return sonarProperties;
    }

    /**
     * @return {@link #findbugsXml}
     */
    String getFindbugsXml() {
        return findbugsXml;
    }

    /**
     * @return {@link #messagesXml}
     */
    String getMessaagesXml() {
        return messagesXml;
    }

    /**
     * @return {@link #outdir}
     */
    File getOutdir() {
        return outdir;
    }

}
