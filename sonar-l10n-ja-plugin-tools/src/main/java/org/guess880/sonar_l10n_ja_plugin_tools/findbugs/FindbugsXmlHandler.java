package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import java.io.IOException;
import java.text.MessageFormat;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Handle the findbugs l10n messages file as 'messages_ja.xml'.
 *
 * @author guess880
 */
final class FindbugsXmlHandler {

    /**
     * XPath of BugPattern/ShortDescription .
     */
    private static final String XPATH_SHORT_DESCRIPTION = "//BugPattern[@type=''{0}'']/ShortDescription/text()";

    /**
     * XPath of BugPattern/category .
     */
    private static final String XPATH_CATEGORY_KEY = "//BugPattern[@type=''{0}'']/@category";

    /**
     * XPath of BugCategory/Description .
     */
    private static final String XPATH_CATEGORY_DESCRIPTION = "//BugCategory[@category=''{0}'']/Description/text()";

    /**
     * XPath of BugPattern/Details .
     */
    private static final String XPATH_DETAILS = "//BugPattern[@type=''{0}'']/Details/text()";

    private static final XPath XPATH = XPathFactory
            .newInstance()
            .newXPath();

    /**
     * Document for findbugs.xml
     */
    private Document doc;

    /**
     * Document for findbugs-messages.xml
     */
    private Document msgDoc;

    /**
     * Load the findbugs l10n messages file.
     *
     * @param msgFileName
     *            findbugs-messages.xml
     * @param fileName
     *            findbugs.xml
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    void load(final String fileName, final String msgFileName)
            throws ParserConfigurationException,
            SAXException, IOException {
        final DocumentBuilderFactory domFactory = DocumentBuilderFactory
                .newInstance();
        domFactory.setNamespaceAware(true);
        doc = domFactory.newDocumentBuilder().parse(fileName);
        msgDoc = domFactory.newDocumentBuilder().parse(msgFileName);
    }

    /**
     * Get localized message.
     *
     * @param key
     *            BugPattern type.
     * @return localized messages. ShortDescription and Details.
     * @throws XPathExpressionException
     */
    MessageSet getMessage(final String key) throws XPathExpressionException {
        MessageSet ret = null;
        final Node shortDescription = ((NodeList) XPATH
                .compile(MessageFormat.format(XPATH_SHORT_DESCRIPTION, key))
                .evaluate(msgDoc, XPathConstants.NODESET)).item(0);
        if (shortDescription != null) {
            final Node categoryKey = ((NodeList) XPATH
                    .compile(MessageFormat.format(XPATH_CATEGORY_KEY, key))
                    .evaluate(doc, XPathConstants.NODESET)).item(0);
            final Node categoryDescription = ((NodeList) XPATH
                    .compile(
                            MessageFormat.format(
                                    XPATH_CATEGORY_DESCRIPTION,
                                    categoryKey.getTextContent()))
                    .evaluate(msgDoc, XPathConstants.NODESET)).item(0);
            final Node details = ((NodeList) XPATH
                    .compile(MessageFormat.format(XPATH_DETAILS, key))
                    .evaluate(msgDoc, XPathConstants.NODESET)).item(0);
            ret = new MessageSet(categoryDescription.getTextContent() + " - "
                    + shortDescription.getTextContent(),
                    details.getNextSibling().getTextContent());
        }
        return ret;
    }

    /**
     * Localized message set. ShortDescription and Details.
     *
     * @author guess880
     */
    static final class MessageSet {

        /**
         * Localized ShortDescription.
         */
        private String shortDescription;

        /**
         * Localized Details.
         */
        private String details;

        /**
         * Constructor.
         *
         * @param shortDescription
         *            Localized ShortDescription.
         * @param details
         *            Localized Details.
         */
        private MessageSet(final String shortDescription, final String details) {
            this.shortDescription = shortDescription;
            this.details = details;
        }

        /**
         * @return ShortDescription.
         */
        String getShortDescription() {
            return shortDescription;
        }

        /**
         * @return Details.
         */
        String getDetails() {
            return details;
        }

        @Override
        public String toString() {
            return "shortDescription: " + shortDescription + "; details: "
                    + details;
        }
    }
}
