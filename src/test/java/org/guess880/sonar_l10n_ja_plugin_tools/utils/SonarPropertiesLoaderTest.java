package org.guess880.sonar_l10n_ja_plugin_tools.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class SonarPropertiesLoaderTest extends TestBase {

    private static class SonarPropertiesLoaderImpl extends SonarPropertiesLoader {

        @Override
        protected SonarProperty innerNext(String key, String value) {
            return new SonarProperty(key, value) {
                @Override
                public String getResourceKey() {
                    return "resoucekey";
                }
            };
        }

    }

    @Test
    public void testLoadFile() throws Exception {
        final SonarPropertiesLoader loader = new SonarPropertiesLoaderImpl();
        loader.load(getPathName("linked.properties"));
        assertTrue(loader.hasNext());
        final SonarProperty property = loader.next();
        assertEquals("key1", property.getKey());
        assertEquals("value1", property.getValue());
        assertEquals("resoucekey", property.getResourceKey());
        assertEquals("key: key1; value: value1", property.toString());
    }

    @Test
    public void testLoadHttp() throws Exception {
        final SonarPropertiesLoader loader = new SonarPropertiesLoaderImpl();
        loader.load("https://raw.github.com/guess880/sonar-l10n-ja-plugin-tools/master/src/test/resources/org/guess880/sonar_l10n_ja_plugin_tools/utils/linked.properties");
        assertTrue(loader.hasNext());
        final SonarProperty property = loader.next();
        assertEquals("key1", property.getKey());
        assertEquals("value1", property.getValue());
        assertEquals("resoucekey", property.getResourceKey());
        assertEquals("key: key1; value: value1", property.toString());
    }

    @Test(expected = IOException.class)
    public void testIOException() throws Exception {
        final String fileName = getPathName("linked.properties");
        final FileOutputStream out = new FileOutputStream(new File(fileName), true);
        try {
            out.getChannel().lock();
            final SonarPropertiesLoader loader = new SonarPropertiesLoaderImpl();
            loader.load(fileName);
        } finally {
            out.close();
        }
    }

}
