package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.guess880.sonar_l10n_ja_plugin_tools.findbugs.Arguments;
import org.guess880.sonar_l10n_ja_plugin_tools.findbugs.SonarFindbugsLocalizer;
import org.junit.Test;

public class SonarFindbugsLocalizerTest extends TestBase {

    @Test
    public void testMain() throws Exception {
        final String resultDir = "target/test-result";
        final File resultDirFile = new File(resultDir);
        if (resultDirFile.exists()) {
            FileUtils.forceDelete(resultDirFile);
            Thread.sleep(2000);
        }
        resultDirFile.mkdir();
        SonarFindbugsLocalizer.main(new String[] {
                Arguments.ARG_NM_LANGUAGE, "ja",
                Arguments.ARG_NM_SONAR_PROPERTIES, getPathName("sonar-findbugs.properties"),
                Arguments.ARG_NM_FINDBUGSXML, getPathName("findbugs-2.0.0.xml"),
                Arguments.ARG_NM_MESSAGESXML, getPathName("findbugs-2.0.0-messages_ja.xml"),
                Arguments.ARG_NM_OUTDIR, resultDir
        });
        assertEquals(
                385,
                FileUtils.readLines(
                        new File(resultDir, "findbugs_ja.properties"),
                        "ISO8859-1").size());
        assertEquals(
                382,
                FileUtils.listFiles(resultDirFile, new String[] { "html" },
                        false).size());
    }

    @Test
    public void testMainParseError() throws Exception {
        SonarFindbugsLocalizer.main(new String[] {
        });
    }

}
