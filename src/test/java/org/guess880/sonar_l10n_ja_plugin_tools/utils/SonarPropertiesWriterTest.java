package org.guess880.sonar_l10n_ja_plugin_tools.utils;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class SonarPropertiesWriterTest extends TestBase {

    @Test
    public void test() throws Exception {
        final String fileName = getPathName("propertes-writer-test.properties");
        final String key = "key";
        final String value = "value";
        final SonarPropertiesWriter writer = new SonarPropertiesWriter();
        writer.append(key, value);
        writer.write(new File(fileName));
        final Properties props = new Properties();
        props.load(new FileInputStream(new File(fileName)));
        assertEquals(value, props.get(key));
    }

    @Test(expected = IOException.class)
    public void testIOException() throws Exception {
        final String fileName = getPathName("propertes-writer-test.properties");
        final FileOutputStream out = new FileOutputStream(new File(fileName), true);
        try {
            out.getChannel().lock();
            final SonarPropertiesWriter writer = new SonarPropertiesWriter();
            writer.write(new File(fileName));
        } finally {
            out.close();
        }
    }

}
