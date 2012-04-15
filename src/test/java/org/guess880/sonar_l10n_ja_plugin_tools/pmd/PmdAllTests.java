package org.guess880.sonar_l10n_ja_plugin_tools.pmd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
    PmdArgumentsTest.class,
    PmdXmlHandlerTest.class,
    SonarPmdPropertiesLoaderTest.class,
    SonarPmdLocalizerTest.class
})
public class PmdAllTests {
}
