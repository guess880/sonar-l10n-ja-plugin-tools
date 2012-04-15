package org.guess880.sonar_l10n_ja_plugin_tools;

import org.guess880.sonar_l10n_ja_plugin_tools.findbugs.FindbugsAllTests;
import org.guess880.sonar_l10n_ja_plugin_tools.pmd.PmdAllTests;
import org.guess880.sonar_l10n_ja_plugin_tools.utils.UtilsAllTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    UtilsAllTests.class,
    FindbugsAllTests.class,
    PmdAllTests.class
})
public class AllTests {
}
