package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.guess880.sonar_l10n_ja_plugin_tools.findbugs.Arguments;
import org.junit.Test;

public class ArgumentsTest extends TestBase {

    @Test
    public void testNoArgs() throws Exception {
        final Arguments args = new Arguments();
        final boolean ret = args.parse(new String[] {
                });
        args.printUsage(System.out);
        assertFalse(ret);
    }

    @Test
    public void testWithProperties() throws Exception {
        final Arguments args = new Arguments();
        final boolean ret = args.parse(new String[] {
                Arguments.ARG_NM_OUTDIR, "target/test-result",
                Arguments.ARG_NM_PROPERTIES, getPathName("ja.properties")
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
                args.getMessaagesXml());
        assertEquals(new File("target/test-result"), args.getOutdir());
    }

    @Test
    public void testWithoutLanguage() throws Exception {
        final Arguments args = new Arguments();
        final boolean ret = args.parse(new String[] {
                Arguments.ARG_NM_OUTDIR, "target/test-result",
        });
        args.printUsage(System.out);
        assertFalse(ret);
    }

    @Test
    public void testWithoutSonarProperties() throws Exception {
        final Arguments args = new Arguments();
        final boolean ret = args.parse(new String[] {
                Arguments.ARG_NM_OUTDIR, "target/test-result",
                Arguments.ARG_NM_LANGUAGE, "ja"
        });
        args.printUsage(System.out);
        assertFalse(ret);
    }

    @Test
    public void testWithoutFindbugsXml() throws Exception {
        final Arguments args = new Arguments();
        final boolean ret = args.parse(new String[] {
                Arguments.ARG_NM_OUTDIR, "target/test-result",
                Arguments.ARG_NM_LANGUAGE, "ja",
                Arguments.ARG_NM_SONAR_PROPERTIES, "test"
        });
        args.printUsage(System.out);
        assertFalse(ret);
    }

    @Test
    public void testWithoutMessagesXml() throws Exception {
        final Arguments args = new Arguments();
        final boolean ret = args.parse(new String[] {
                Arguments.ARG_NM_OUTDIR, "target/test-result",
                Arguments.ARG_NM_LANGUAGE, "ja",
                Arguments.ARG_NM_SONAR_PROPERTIES, "findbugs.properties",
                Arguments.ARG_NM_FINDBUGSXML, "findbugs.xml"
        });
        args.printUsage(System.out);
        assertFalse(ret);
    }

    @Test(expected = IOException.class)
    public void testIOException() throws Exception {
        final String fileName = getPathName("ja.properties");
        final FileOutputStream out = new FileOutputStream(new File(fileName),
                true);
        try {
            out.getChannel().lock();
            final Arguments args = new Arguments();
            final boolean ret = args.parse(new String[] {
                    Arguments.ARG_NM_OUTDIR, "target/test-result",
                    Arguments.ARG_NM_PROPERTIES, fileName
            });
            args.printUsage(System.out);
            assertFalse(ret);
        } finally {
            out.close();
        }
    }

}
