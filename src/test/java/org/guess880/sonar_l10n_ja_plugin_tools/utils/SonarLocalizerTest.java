package org.guess880.sonar_l10n_ja_plugin_tools.utils;

import org.junit.Test;

public class SonarLocalizerTest {

    private static class SonarLocalizerImpl extends SonarLocalizer {

        @Override
        protected SonarPropertiesLoader createPropertiesLoader() {
            return null;
        }

        @Override
        protected PluginResourceHanlder createResouceHandler() {
            return null;
        }

        @Override
        protected boolean writesHtml(SonarProperty property) {
            return false;
        }

        @Override
        protected String getPropertyValue(SonarProperty property,
                Object resource) {
            return null;
        }

        @Override
        protected String getHtmlValue(SonarProperty property, Object resource) {
            return null;
        }

        @Override
        protected String getPluginKey() {
            return null;
        }

    }

    @Test(expected = SonarLocalizeException.class)
    public void testLocalizeException() throws Exception {
        final SonarLocalizer localizer = new SonarLocalizerImpl();
        localizer.localize();
    }

}
