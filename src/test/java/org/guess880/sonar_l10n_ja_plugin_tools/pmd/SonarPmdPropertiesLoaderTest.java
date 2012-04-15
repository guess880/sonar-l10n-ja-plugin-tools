package org.guess880.sonar_l10n_ja_plugin_tools.pmd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.guess880.sonar_l10n_ja_plugin_tools.pmd.SonarPmdPropertiesLoader.SonarPmdNameProperty;
import org.guess880.sonar_l10n_ja_plugin_tools.pmd.SonarPmdPropertiesLoader.SonarPmdParamProperty;
import org.junit.Test;

public class SonarPmdPropertiesLoaderTest extends TestBase {

    @Test
    public void testLoadName() throws Exception {
        final SonarPmdPropertiesLoader loader = new SonarPmdPropertiesLoader();
        loader.load(getPathName("sonar-pmd-1.properties"));
        assertTrue(loader.hasNext());
        final SonarPmdNameProperty property = (SonarPmdNameProperty) loader.next();
        assertEquals("rule.pmd.VariableNamingConventions.name", property.getKey());
        assertEquals("Naming - Variable naming conventions", property.getValue());
        assertEquals("VariableNamingConventions", property.getResourceKey());
        assertEquals(
                "key: rule.pmd.VariableNamingConventions.name; value: Naming - Variable naming conventions; name: VariableNamingConventions",
                property.toString());
    }

    @Test
    public void testLoadParam() throws Exception {
        final SonarPmdPropertiesLoader loader = new SonarPmdPropertiesLoader();
        loader.load(getPathName("sonar-pmd-2.properties"));
        assertTrue(loader.hasNext());
        final SonarPmdParamProperty property = (SonarPmdParamProperty) loader.next();
        assertEquals("rule.pmd.VariableNamingConventions.param.memberSuffix", property.getKey());
        assertEquals("A suffix for member variables", property.getValue());
        assertEquals("memberSuffix", property.getResourceKey());
        assertEquals(
                "key: rule.pmd.VariableNamingConventions.param.memberSuffix; value: A suffix for member variables; paramName: memberSuffix",
                property.toString());
    }

}
