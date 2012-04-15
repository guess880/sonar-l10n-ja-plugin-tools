package org.guess880.sonar_l10n_ja_plugin_tools.utils;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Messages.
 *
 * @author guess880
 */
public enum Messages {

    /**
     * Message of not exist.
     */
    NOT_EXIST;

    /**
     * messages_<language>.properties
     */
    private static final ResourceBundle RB = ResourceBundle
            .getBundle(Messages.class.getName());

    /**
     * Get message formatted string.
     *
     * @param args
     * @return Formatted string.
     */
    public String format(final Object... args) {
        return MessageFormat.format(RB.getString(name()), args);
    }

}
