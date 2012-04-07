package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.guess880.sonar_l10n_ja_plugin_tools.findbugs.HtmlWriter;
import org.junit.Test;

public class HtmlWriterTest extends TestBase {

    @Test
    public void test() throws IOException {
        final String html = "<p>test</p>";
        final String fileName = getPathName("test.html");
        final File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        final HtmlWriter writer = new HtmlWriter();
        writer.write(new File(fileName), html);
        final BufferedReader reader = new BufferedReader(new FileReader(
                file));
        try {
            assertEquals(html, reader.readLine());
        } finally {
            reader.close();
        }
    }

    @Test(expected = IOException.class)
    public void testIOException() throws Exception {
        final String fileName = getPathName("test.html");
        final FileOutputStream out = new FileOutputStream(new File(fileName), true);
        try {
            out.getChannel().lock();
            final HtmlWriter writer = new HtmlWriter();
            writer.write(new File(fileName), "<p>testIOException</p>");
        } finally {
            out.close();
        }
    }

}
