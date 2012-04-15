package org.guess880.sonar_l10n_ja_plugin_tools.utils;

import java.io.File;

/**
 * Localize sonar's resouce files.
 *
 * @author guess880
 */
public abstract class SonarLocalizer {

    /**
     * Html file extension.
     */
    private static final String EXT_HTML = ".html";

    /**
     * Properties file extension.
     */
    private static final String EXT_PROPS = ".properties";

    /**
     * Localize language.
     */
    private String language;

    /**
     * Filepath of "<plugin>.properties".
     */
    private String properties;

    /**
     * Output destination.
     */
    private File outdir;

    /**
     * @see SonarLocalizer#language
     */
    public SonarLocalizer setLanguage(final String language) {
        this.language = language;
        return this;
    }

    /**
     * @see SonarLocalizer#properties
     */
    public SonarLocalizer setProperties(final String properties) {
        this.properties = properties;
        return this;
    }

    /**
     * @see SonarLocalizer#outdir
     */
    public SonarLocalizer setOutdir(final File outdir) {
        this.outdir = outdir;
        return this;
    }

    /**
     * Localize.
     *
     * @throws SonarLocalizeException
     *             if any error occur.
     */
    public void localize() throws SonarLocalizeException {
        final HtmlWriter htmlWriter = new HtmlWriter();
        final SonarPropertiesLoader propLoader = createPropertiesLoader();
        final SonarPropertiesWriter propWriter = new SonarPropertiesWriter();
        final PluginResourceHanlder resouceHandler = createResouceHandler();
        try {
            resouceHandler.load();
            propLoader.load(properties);
            while (propLoader.hasNext()) {
                final SonarProperty property = propLoader.next();
                if (writesHtml(property)) {
                    final Object resource = resouceHandler
                            .getLocalizedResource(property
                                    .getResourceKey());
                    if (resource == null) {
                        System.out.println(
                                Messages.NOT_EXIST.format(property
                                        .getResourceKey()));
                        propWriter.append(property.getKey(),
                                property.getValue());
                    } else {
                        propWriter.append(property.getKey(),
                                getPropertyValue(property, resource));
                        htmlWriter.write(
                                new File(outdir, property.getResourceKey()
                                        + EXT_HTML),
                                getHtmlValue(property, resource));
                    }
                } else {
                    propWriter.append(property.getKey(), property.getValue());
                }
            }
            propWriter.write(new File(outdir, getPluginKey() + "_" + language
                    + EXT_PROPS));
        } catch (Exception e) {
            throw new SonarLocalizeException(e);
        }
    }

    /**
     * @return instance of {@link SonarPropertiesLoader}.
     */
    protected abstract SonarPropertiesLoader createPropertiesLoader();

    /**
     * @return instance of {@link PluginResourceHanlder}.
     */
    protected abstract PluginResourceHanlder createResouceHandler();

    /**
     * @param property
     *            {@link SonarProperty}
     * @return if property write html then <code>true</code>, otherwise
     *         <code>false</code>.
     */
    protected abstract boolean writesHtml(final SonarProperty property);

    /**
     * @param property
     *            {@link SonarProperty}
     * @param resource
     *            Localized resouce.
     * @return value of "<plugin>_<language>.properties".
     */
    protected abstract String getPropertyValue(final SonarProperty property,
            final Object resource);

    /**
     * @param property
     *            {@link SonarProperty}
     * @param resource
     *            Localized resouce.
     * @return value of html file.
     */
    protected abstract String getHtmlValue(final SonarProperty property,
            final Object resource);

    /**
     * @return plugin key.
     */
    protected abstract String getPluginKey();
}
