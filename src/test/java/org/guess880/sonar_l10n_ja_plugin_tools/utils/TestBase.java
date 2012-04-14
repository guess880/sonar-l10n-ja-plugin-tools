package org.guess880.sonar_l10n_ja_plugin_tools.utils;

abstract class TestBase {

    protected String getPathName(final String fileName) {
        return "target/test-classes/org/guess880/sonar_l10n_ja_plugin_tools/utils/" + fileName;
    }

}
