package org.guess880.sonar_l10n_ja_plugin_tools.utils;

/**
 * Set of sonar key and value.
 *
 * @author guess880
 */
public abstract class SonarProperty {

    /**
     * Sonar key.
     */
    private final String key;

    /**
     * Sonar value.
     */
    private final String value;

    /**
     * Constructor
     *
     * @param key
     * @param value
     */
    protected SonarProperty(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return {@link #key}
     */
    public String getKey() {
        return key;
    }

    /**
     * @return {@link #value}
     */
    public String getValue() {
        return value;
    }

    /**
     * @return key of plugin's resouce
     */
    public abstract String getResourceKey();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "key: " + key + "; value: " + value;
    }

}
