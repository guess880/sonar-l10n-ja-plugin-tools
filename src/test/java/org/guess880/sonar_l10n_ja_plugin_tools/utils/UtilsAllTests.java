package org.guess880.sonar_l10n_ja_plugin_tools.utils;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    ArgumentsTest.class,
    LinkedPropertiesTest.class,
    HtmlWriterTest.class,
    SonarPropertiesLoaderTest.class,
    SonarPropertiesWriterTest.class,
    SonarLocalizerTest.class
})
public class UtilsAllTests {
}
