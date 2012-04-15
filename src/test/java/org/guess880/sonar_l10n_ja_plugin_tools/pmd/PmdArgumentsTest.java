package org.guess880.sonar_l10n_ja_plugin_tools.pmd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;


public class PmdArgumentsTest extends TestBase {

    @Test
    public void test() throws Exception {
        final PmdArguments args = new PmdArguments();
        final boolean ret = args.parse(new String[] {
                PmdArguments.ARG_NM_OUTDIR, "target/test-result",
                PmdArguments.ARG_NM_LANGUAGE, "ja",
                PmdArguments.ARG_NM_SONAR_PROPERTIES, "test",
                PmdArguments.ARG_NM_RULESETXMLS, "xml1", "xml2", "xml3"
        });
        assertTrue(ret);
        assertEquals("ja", args.getLanguage());
        assertEquals(
                "test",
                args.getSonarProperties());
        assertEquals(
                3,
                args.getRuleSetXmls().length);
        assertEquals(new File("target/test-result"), args.getOutdir());
    }

    @Test
    public void testWithProperties() throws Exception {
        final PmdArguments args = new PmdArguments();
        final boolean ret = args.parse(new String[] {
                PmdArguments.ARG_NM_OUTDIR, "target/test-result",
                PmdArguments.ARG_NM_PROPERTIES, getPathName("ja.properties")
        });
        assertTrue(ret);
        assertEquals("ja", args.getLanguage());
        assertEquals(
                "https://raw.github.com/SonarSource/sonar/master/plugins/sonar-l10n-en-plugin/src/main/resources/org/sonar/l10n/pmd.properties",
                args.getSonarProperties());
        assertEquals(
                24,
                args.getRuleSetXmls().length);
        assertEquals(new File("target/test-result"), args.getOutdir());
    }

    @Test
    public void testWithoutRuleSetXmls() throws Exception {
        final PmdArguments args = new PmdArguments();
        final boolean ret = args.parse(new String[] {
                PmdArguments.ARG_NM_OUTDIR, "target/test-result",
                PmdArguments.ARG_NM_LANGUAGE, "ja",
                PmdArguments.ARG_NM_SONAR_PROPERTIES, "test"
        });
        args.printUsage(System.out);
        assertFalse(ret);
    }

}
