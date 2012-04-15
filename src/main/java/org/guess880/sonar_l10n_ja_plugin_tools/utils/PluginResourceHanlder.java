package org.guess880.sonar_l10n_ja_plugin_tools.utils;

/**
 * Plugin's resource files handler.
 *
 * @author guess880
 */
public interface PluginResourceHanlder {

    /**
     * Load from resource file.
     *
     * @throws SonarLocalizeException
     *             if any error occurs.
     */
    void load() throws SonarLocalizeException;

    /**
     * Get localized messages from resouce files.
     *
     * @param key
     *            message key.
     * @return localized messages
     * @throws SonarLocalizeException
     *             if any error occurs.
     */
    Object getLocalizedResource(final String key) throws SonarLocalizeException;
}
