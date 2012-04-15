package org.guess880.sonar_l10n_ja_plugin_tools.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class ArgumentsTest extends TestBase {

    private static class ArgumentsImpl extends Arguments {

        @Override
        protected void setFromProperties(Properties props) {
            // do nothing
        }

    }

    @Test
    public void testNoArgs() throws Exception {
        final ArgumentsImpl args = new ArgumentsImpl();
        final boolean ret = args.parse(new String[] {
                });
        args.printUsage(System.out);
        assertFalse(ret);
    }

    @Test
    public void testWithProperties() throws Exception {
        final ArgumentsImpl args = new ArgumentsImpl();
        final boolean ret = args.parse(new String[] {
                ArgumentsImpl.ARG_NM_OUTDIR, "target/test-result",
                ArgumentsImpl.ARG_NM_PROPERTIES, getPathName("ja.properties")
        });
        assertTrue(ret);
        assertEquals("ja", args.getLanguage());
        assertEquals(
                "https://raw.github.com/SonarSource/sonar/master/plugins/sonar-l10n-en-plugin/src/main/resources/org/sonar/l10n/findbugs.properties",
                args.getSonarProperties());
        assertEquals(new File("target/test-result"), args.getOutdir());
    }

    @Test
    public void testWithoutLanguage() throws Exception {
        final ArgumentsImpl args = new ArgumentsImpl();
        final boolean ret = args.parse(new String[] {
                ArgumentsImpl.ARG_NM_OUTDIR, "target/test-result",
        });
        args.printUsage(System.out);
        assertFalse(ret);
    }

    @Test
    public void testWithoutSonarProperties() throws Exception {
        final ArgumentsImpl args = new ArgumentsImpl();
        final boolean ret = args.parse(new String[] {
                ArgumentsImpl.ARG_NM_OUTDIR, "target/test-result",
                ArgumentsImpl.ARG_NM_LANGUAGE, "ja"
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
            final ArgumentsImpl args = new ArgumentsImpl();
            final boolean ret = args.parse(new String[] {
                    ArgumentsImpl.ARG_NM_OUTDIR, "target/test-result",
                    ArgumentsImpl.ARG_NM_PROPERTIES, fileName
            });
            args.printUsage(System.out);
            assertFalse(ret);
        } finally {
            out.close();
        }
    }
}
