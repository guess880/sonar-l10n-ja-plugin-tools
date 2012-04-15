package org.guess880.sonar_l10n_ja_plugin_tools.utils;

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
 * Arguments parse.
 *
 * @author guess880
 */
public abstract class Arguments {

    private static final String ALIAS_LANGUAGE = "language";

    private static final String ALIAS_SONARPROPERTIES = "sonarproperties";

    public static final String ARG_NM_LANGUAGE = "-l";

    public static final String ARG_NM_SONAR_PROPERTIES = "-s";

    public static final String ARG_NM_PROPERTIES = "-p";

    public static final String ARG_NM_OUTDIR = "-o";

    protected static final String ALIAS_PREFIX = "--";

    @Option(required = false, name = ARG_NM_LANGUAGE, aliases = ALIAS_PREFIX
            + ALIAS_LANGUAGE, usage = "Localize language. (e.g. Japanese: ja)")
    private String language;

    @Option(required = false, name = ARG_NM_SONAR_PROPERTIES, aliases = ALIAS_PREFIX
            + ALIAS_SONARPROPERTIES, metaVar = "FILE or URL", usage = "File path of \"findbugs.properties\" of sonar.")
    private String sonarProperties;

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
    public Arguments() {
        parser = new CmdLineParser(this);
    }

    /**
     * Parse arguments.
     *
     * @param args
     *            Arguments.
     * @return If illegal arguments exist then return <code>true</code>,
     *         otherwise <code>false</code>.
     * @throws IOException
     */
    public boolean parse(final String[] args) throws IOException {
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
                setFromProperties(props);
            }
            validate();
            ret = true;
        } catch (CmdLineException e) {
            lastError = e;
        }
        return ret;
    }

    protected abstract void setFromProperties(Properties props);

    /**
     * Validate arguments after parsing.
     *
     * @throws CmdLineException
     *             if any error occur
     */
    protected void validate() throws CmdLineException {
        if (language == null) {
            throw new CmdLineException(parser, "language is empty.");
        }
        if (sonarProperties == null) {
            throw new CmdLineException(parser, "sonarProperties is empty.");
        }
    }

    /**
     * Printing usage.
     *
     * @param out
     *            Destination.
     */
    public void printUsage(final PrintStream out) {
        out.println(lastError.getMessage());
        out.printf("USAGE:%n%s%n",
                parser.printExample(ExampleMode.ALL)
                );
        parser.printUsage(out);
    }

    /**
     * @return {@link #language}
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @return {@link #sonarProperties}
     */
    public String getSonarProperties() {
        return sonarProperties;
    }

    /**
     * @return {@link #outdir}
     */
    public File getOutdir() {
        return outdir;
    }

    /**
     * @return {@link #parser}
     */
    protected CmdLineParser getParser() {
        return parser;
    }

}
