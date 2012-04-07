package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.guess880.sonar_l10n_ja_plugin_tools.findbugs.FindbugsXmlHandler.MessageSet;
import org.guess880.sonar_l10n_ja_plugin_tools.findbugs.SonarPropertiesLoader.SonarProperty;
import org.xml.sax.SAXException;

/**
 * Localize sonar's findbugs files.
 *
 * @author guess880
 */
public final class SonarFindbugsLocalizer {

    private static final String EXT_HTML = ".html";

    private static final String FILENAME_FINDBUGS = "findbugs_";

    private static final String EXT_PROPS = ".properties";

    /**
     * Constructor.
     */
    public SonarFindbugsLocalizer() {
        super();
    }

    /**
     * Main method.
     *
     * @param args
     * @throws XPathExpressionException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static void main(String[] args) throws XPathExpressionException,
            IOException, ParserConfigurationException, SAXException {
        final Arguments arguments = new Arguments();
        if (arguments.parse(args)) {
            new SonarFindbugsLocalizer().localize(
                    arguments.getLanguage(),
                    arguments.getSonarProperties(),
                    arguments.getFindbugsXml(),
                    arguments.getMessaagesXml(),
                    arguments.getOutdir());
        } else {
            arguments.printUsage(System.out);
        }
    }

    /**
     * Localize sonar's findbugs files.
     *
     * @param language
     *            Localize language.
     * @param properties
     *            Sonar's findbugs.properties.
     * @param findbugs
     *            Findbugs's findbugs.xml
     * @param messages
     *            Findbugs's messages.xml
     * @param outdir
     *            Output destination.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws XPathExpressionException
     */
    public void localize(final String language, final String properties,
            final String findbugs, final String messages, final File outdir)
            throws ParserConfigurationException, SAXException, IOException,
            XPathExpressionException {
        final HtmlWriter htmlWriter = new HtmlWriter();
        final SonarPropertiesWriter propWriter = new SonarPropertiesWriter();
        final FindbugsXmlHandler xmlHandler = new FindbugsXmlHandler();
        xmlHandler.load(findbugs, messages);
        final SonarPropertiesLoader propLoader = new SonarPropertiesLoader();
        propLoader.load(properties);
        while (propLoader.hasNext()) {
            final SonarProperty property = propLoader.next();
            final MessageSet msgSet = xmlHandler.getMessage(property
                    .getBugPattern());
            if (msgSet == null) {
                System.out.println(
                        Messages.BUGPATTERN_NOT_EXIST.format(property
                                .getBugPattern()));
                propWriter.append(property.getKey(), property.getValue());
            } else {
                propWriter.append(property.getKey(),
                        msgSet.getShortDescription());
                htmlWriter.write(new File(outdir, property.getBugPattern()
                        + EXT_HTML), msgSet.getDetails());
            }
        }
        propWriter.write(new File(outdir, FILENAME_FINDBUGS + language
                + EXT_PROPS));
    }
}
