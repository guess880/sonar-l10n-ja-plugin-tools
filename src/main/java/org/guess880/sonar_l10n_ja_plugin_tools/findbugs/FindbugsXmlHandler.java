package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import java.text.MessageFormat;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.guess880.sonar_l10n_ja_plugin_tools.utils.PluginResourceHanlder;
import org.guess880.sonar_l10n_ja_plugin_tools.utils.SonarLocalizeException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Handle the findbugs l10n messages file as 'messages_ja.xml'.
 *
 * @author guess880
 */
final class FindbugsXmlHandler implements PluginResourceHanlder {

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
     * Document for "findbugs.xml".
     */
    private Document doc;

    /**
     * Document for "findbugs-messages.xml".
     */
    private Document msgDoc;

    /**
     * Filepath of "findbugs.xml".
     */
    private String findbugsXml;

    /**
     * Filepath of "findbugs-messages.xml".
     */
    private String messagesXml;

    /**
     * Setter of {@link #findbugsXml}.
     *
     * @param findbugsXml
     *            Filepath of "findbugs.xml".
     * @return this.
     */
    FindbugsXmlHandler setFindbugsXml(final String findbugsXml) {
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
    FindbugsXmlHandler setMessagesXml(final String messagesXml) {
        this.messagesXml = messagesXml;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load() throws SonarLocalizeException {
        try {
            final DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            doc = domFactory.newDocumentBuilder().parse(findbugsXml);
            msgDoc = domFactory.newDocumentBuilder().parse(messagesXml);
        } catch (Exception e) {
            throw new SonarLocalizeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getLocalizedResource(final String key) throws SonarLocalizeException {
        try {
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
        } catch (Exception e) {
            throw new SonarLocalizeException(e);
        }
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
        private final String shortDescription;

        /**
         * Localized Details.
         */
        private final String details;

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

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "shortDescription: " + shortDescription + "; details: "
                    + details;
        }

    }

}
