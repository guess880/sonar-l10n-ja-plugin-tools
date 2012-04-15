package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import org.guess880.sonar_l10n_ja_plugin_tools.utils.SonarPropertiesLoader;
import org.guess880.sonar_l10n_ja_plugin_tools.utils.SonarProperty;

/**
 * Load 'findbugs.properties' of sonar.
 *
 * @author guess880
 */
final class SonarFindbugsPropertiesLoader extends SonarPropertiesLoader {

    /**
     * {@inheritDoc}
     */
    @Override
    protected SonarProperty innerNext(
            final String key, final String value) {
        return new SonarFindbugsProperty(key, value);
    }

    /**
     * Set of sonar key and findbugs key(BugPattern) and value.
     *
     * @author guess880
     */
    static final class SonarFindbugsProperty extends SonarProperty {

        /**
         * Index of BugPattern.
         */
        private static final int BUGPATTERN_INDEX = 2;

        /**
         * Findbugs key(BugPattern).
         */
        private final String bugPattern;

        /**
         * Constructor
         *
         * @param key
         *            Sonar key.
         * @param value
         *            Sonar value.
         */
        private SonarFindbugsProperty(final String key, final String value) {
            super(key, value);
            this.bugPattern = key.split("[.]")[BUGPATTERN_INDEX];
        }

        /**
         * @return {@link #bugPattern}.
         */
        @Override
        public String getResourceKey() {
            return bugPattern;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return super.toString() + "; bugPattern: " + bugPattern;
        }

    }

}
