package org.guess880.sonar_l10n_ja_plugin_tools.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Write html file.
 *
 * @author guess880
 */
public final class HtmlWriter {

    /**
     * Sonar resource encoding.
     */
    private static final String ENCODING = "UTF-8";

    /**
     * Write html file.
     *
     * @param file
     *            html file.
     * @param html
     *            html contents.
     * @throws IOException
     */
    public void write(final File file, final String html) throws IOException {
        final OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(file), ENCODING);
        try {
            writer.write(html);
            writer.flush();
        } finally {
            writer.close();
        }
    }

}
