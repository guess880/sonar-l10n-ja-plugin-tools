package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.guess880.sonar_l10n_ja_plugin_tools.findbugs.SonarPropertiesLoader;
import org.guess880.sonar_l10n_ja_plugin_tools.findbugs.SonarPropertiesLoader.SonarProperty;
import org.junit.Test;

public class SonarPropertiesLoaderTest extends TestBase {

    @Test
    public void testFile() throws Exception {
        final SonarPropertiesLoader loader = new SonarPropertiesLoader();
        loader.load(getPathName("sonar-findbugs-1.properties"));
        assertTrue(loader.hasNext());
        final SonarProperty property = loader.next();
        assertEquals("rule.findbugs.IMSE_DONT_CATCH_IMSE.name", property.getKey());
        assertEquals("IMSE_DONT_CATCH_IMSE", property.getBugPattern());
        assertEquals("Bad practice - Dubious catching of IllegalMonitorStateException", property.getValue());
        assertEquals(
                "key: rule.findbugs.IMSE_DONT_CATCH_IMSE.name; bugPattern: IMSE_DONT_CATCH_IMSE; value: Bad practice - Dubious catching of IllegalMonitorStateException",
                property.toString());
    }

    @Test
    public void testHttp() throws Exception {
        final SonarPropertiesLoader loader = new SonarPropertiesLoader();
        loader.load("https://raw.github.com/SonarSource/sonar/master/plugins/sonar-l10n-en-plugin/src/main/resources/org/sonar/l10n/findbugs.properties");
        assertTrue(loader.hasNext());
        final SonarProperty property = loader.next();
        assertEquals("rule.findbugs.IMSE_DONT_CATCH_IMSE.name", property.getKey());
        assertEquals("IMSE_DONT_CATCH_IMSE", property.getBugPattern());
        assertEquals("Bad practice - Dubious catching of IllegalMonitorStateException", property.getValue());
        assertEquals(
                "key: rule.findbugs.IMSE_DONT_CATCH_IMSE.name; bugPattern: IMSE_DONT_CATCH_IMSE; value: Bad practice - Dubious catching of IllegalMonitorStateException",
                property.toString());
    }

    @Test(expected = IOException.class)
    public void testIOException() throws Exception {
        final String fileName = getPathName("sonar-findbugs-1.properties");
        final FileOutputStream out = new FileOutputStream(new File(fileName), true);
        try {
            out.getChannel().lock();
            final SonarPropertiesLoader loader = new SonarPropertiesLoader();
            loader.load(fileName);
        } finally {
            out.close();
        }
    }

}
