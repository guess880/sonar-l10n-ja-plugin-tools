package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Writer of localized 'findbugs.properties' of sonar.
 *
 * @author guess880
 */
final class SonarPropertiesWriter {

    /**
     * {@link Properties}
     */
    private Properties props = new LinkedProperties();

    /**
     * Append property.
     *
     * @param key Key of property.
     * @param value Value of Property.
     */
    void append(final String key, final String value) {
        props.setProperty(key, value);
    }

    /**
     * Write properties file.
     *
     * @param file Properies file.
     * @throws IOException
     */
    void write(final File file) throws IOException {
        final FileOutputStream out = new FileOutputStream(file);
        try {
            props.store(out, null);
        } finally {
            out.close();
        }
    }

}
