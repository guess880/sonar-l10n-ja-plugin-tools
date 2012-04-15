package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class FindbugsArgumentsTest extends TestBase {

    @Test
    public void testWithProperties() throws Exception {
        final FindbugsArguments args = new FindbugsArguments();
        final boolean ret = args.parse(new String[] {
                FindbugsArguments.ARG_NM_OUTDIR, "target/test-result",
                FindbugsArguments.ARG_NM_PROPERTIES, getPathName("ja.properties")
        });
        assertTrue(ret);
        assertEquals("ja", args.getLanguage());
        assertEquals(
                "https://raw.github.com/SonarSource/sonar/master/plugins/sonar-l10n-en-plugin/src/main/resources/org/sonar/l10n/findbugs.properties",
                args.getSonarProperties());
        assertEquals(
                "http://findbugs.googlecode.com/svn/trunk/findbugs/etc/findbugs.xml",
                args.getFindbugsXml());
        assertEquals(
                "http://findbugs.googlecode.com/svn/trunk/findbugs/etc/messages_ja.xml",
                args.getMessagesXml());
        assertEquals(new File("target/test-result"), args.getOutdir());
    }

    @Test
    public void testWithoutFindbugsXml() throws Exception {
        final FindbugsArguments args = new FindbugsArguments();
        final boolean ret = args.parse(new String[] {
                FindbugsArguments.ARG_NM_OUTDIR, "target/test-result",
                FindbugsArguments.ARG_NM_LANGUAGE, "ja",
                FindbugsArguments.ARG_NM_SONAR_PROPERTIES, "test"
        });
        args.printUsage(System.out);
        assertFalse(ret);
    }

    @Test
    public void testWithoutMessagesXml() throws Exception {
        final FindbugsArguments args = new FindbugsArguments();
        final boolean ret = args.parse(new String[] {
                FindbugsArguments.ARG_NM_OUTDIR, "target/test-result",
                FindbugsArguments.ARG_NM_LANGUAGE, "ja",
                FindbugsArguments.ARG_NM_SONAR_PROPERTIES, "findbugs.properties",
                FindbugsArguments.ARG_NM_FINDBUGSXML, "findbugs.xml"
        });
        args.printUsage(System.out);
        assertFalse(ret);
    }

}
