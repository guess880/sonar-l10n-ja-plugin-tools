package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.guess880.sonar_l10n_ja_plugin_tools.findbugs.SonarFindbugsPropertiesLoader.SonarFindbugsProperty;
import org.junit.Test;

public class SonarFindbugsPropertiesLoaderTest extends TestBase {

    @Test
    public void testLoad() throws Exception {
        final SonarFindbugsPropertiesLoader loader = new SonarFindbugsPropertiesLoader();
        loader.load(getPathName("sonar-findbugs-1.properties"));
        assertTrue(loader.hasNext());
        final SonarFindbugsProperty property = (SonarFindbugsProperty) loader.next();
        assertEquals("rule.findbugs.IMSE_DONT_CATCH_IMSE.name", property.getKey());
        assertEquals("Bad practice - Dubious catching of IllegalMonitorStateException", property.getValue());
        assertEquals("IMSE_DONT_CATCH_IMSE", property.getResourceKey());
        assertEquals(
                "key: rule.findbugs.IMSE_DONT_CATCH_IMSE.name; value: Bad practice - Dubious catching of IllegalMonitorStateException; bugPattern: IMSE_DONT_CATCH_IMSE",
                property.toString());
    }

}
