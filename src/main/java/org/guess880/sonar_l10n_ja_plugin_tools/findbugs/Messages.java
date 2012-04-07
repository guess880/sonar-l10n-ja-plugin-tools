package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Messages.
 *
 * @author guess880
 */
enum Messages {

    BUGPATTERN_NOT_EXIST;

    private static ResourceBundle rb;

    /**
     * Get message formatted string.
     *
     * @param args
     * @return Formatted string.
     */
    public String format(Object... args) {
        synchronized (Messages.class) {
            if (rb == null) {
                rb = ResourceBundle.getBundle(Messages.class.getName());
            }
            return MessageFormat.format(rb.getString(name()), args);
        }
    }
}
