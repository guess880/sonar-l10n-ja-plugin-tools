package org.guess880.sonar_l10n_ja_plugin_tools.utils;

/**
 * Exception wrapper for sonar-l10n-ja-plugin-tools
 *
 * @author guess880
 */
public class SonarLocalizeException extends Exception {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     *
     * @param cause the cause.
     */
    public SonarLocalizeException(final Throwable cause) {
        super(cause);
    }

}
