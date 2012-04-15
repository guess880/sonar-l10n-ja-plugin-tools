package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    FindbugsArgumentsTest.class,
    FindbugsXmlHandlerTest.class,
    SonarFindbugsPropertiesLoaderTest.class,
    SonarFindbugsLocalizerTest.class
})
public class FindbugsAllTests {
}
