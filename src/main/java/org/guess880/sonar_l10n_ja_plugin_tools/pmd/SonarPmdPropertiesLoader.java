package org.guess880.sonar_l10n_ja_plugin_tools.pmd;

import org.guess880.sonar_l10n_ja_plugin_tools.utils.SonarPropertiesLoader;
import org.guess880.sonar_l10n_ja_plugin_tools.utils.SonarProperty;

/**
 * Load 'pmd.properties' of sonar.
 *
 * @author guess880
 */
final class SonarPmdPropertiesLoader extends SonarPropertiesLoader {

    private static final String REGEX_SEPARATER = "[.]";

    /**
     * {@inheritDoc}
     */
    @Override
    protected SonarProperty innerNext(final String key, final String value) {
        SonarProperty ret;
        if (key.endsWith(".name")) {
            ret = new SonarPmdNameProperty(key, value);
        } else {
            ret = new SonarPmdParamProperty(key, value);
        }
        return ret;
    }

    /**
     * Set of sonar key and pmd key(name) and value.
     *
     * @author guess880
     */
    static final class SonarPmdNameProperty extends SonarProperty {

        /**
         * Index of name.
         */
        private static final int NAME_INDEX = 2;

        /**
         * pmd key(name).
         */
        private final String name;

        /**
         * Constructor
         *
         * @param key
         *            sonar key
         * @param value
         *            sonar value
         */
        private SonarPmdNameProperty(final String key, final String value) {
            super(key, value);
            this.name = key.split(REGEX_SEPARATER)[NAME_INDEX];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getResourceKey() {
            return name;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return super.toString() + "; name: " + name;
        }
    }

    /**
     * Set of sonar key and pmd key(parameter name) and value.
     *
     * @author guess880
     */
    static final class SonarPmdParamProperty extends SonarProperty {

        /**
         * Index of parameter name.
         */
        private static final int PARAM_NAME_INDEX = 4;

        /**
         * pmd parameter name.
         */
        private final String paramName;

        /**
         * Constructor.
         *
         * @param key
         *            sonar key
         * @param value
         *            sonar value
         */
        private SonarPmdParamProperty(final String key, final String value) {
            super(key, value);
            this.paramName = key.split(REGEX_SEPARATER)[PARAM_NAME_INDEX];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getResourceKey() {
            return paramName;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return super.toString() + "; paramName: " + paramName;
        }

    }

}
