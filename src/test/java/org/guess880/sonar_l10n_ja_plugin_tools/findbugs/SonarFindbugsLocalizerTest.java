package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class SonarFindbugsLocalizerTest extends TestBase {

    @Test
    public void testLocalize() throws Exception {
        final String resultDir = "target/test-result/findbugs";
        final File resultDirFile = new File(resultDir);
        if (resultDirFile.exists()) {
            FileUtils.forceDelete(resultDirFile);
            Thread.sleep(2000);
        }
        FileUtils.forceMkdir(resultDirFile);
        final SonarFindbugsLocalizer localizer = new SonarFindbugsLocalizer();
        localizer.setFindbugsXml(getPathName("findbugs-2.0.0.xml"))
            .setFindbugsMessageXml(getPathName("findbugs-2.0.0-messages_ja.xml"))
            .setLanguage("ja")
            .setProperties(getPathName("sonar-findbugs.properties"))
            .setOutdir(resultDirFile)
            .localize();
        assertEquals(
                385,
                FileUtils.readLines(
                        new File(resultDir, "findbugs_ja.properties"),
                        "UTF-8").size());
        assertEquals(
                382,
                FileUtils.listFiles(resultDirFile, new String[] { "html" },
                        false).size());
    }

    @Test
    public void testMain() throws Exception {
        final String resultDir = "target/test-result/findbugs";
        final File resultDirFile = new File(resultDir);
        if (resultDirFile.exists()) {
            FileUtils.forceDelete(resultDirFile);
            Thread.sleep(2000);
        }
        FileUtils.forceMkdir(resultDirFile);
        SonarFindbugsLocalizer.main(new String[] {
                FindbugsArguments.ARG_NM_LANGUAGE, "ja",
                FindbugsArguments.ARG_NM_SONAR_PROPERTIES, getPathName("sonar-findbugs.properties"),
                FindbugsArguments.ARG_NM_FINDBUGSXML, getPathName("findbugs-2.0.0.xml"),
                FindbugsArguments.ARG_NM_MESSAGESXML, getPathName("findbugs-2.0.0-messages_ja.xml"),
                FindbugsArguments.ARG_NM_OUTDIR, resultDir
        });
        assertEquals(
                385,
                FileUtils.readLines(
                        new File(resultDir, "findbugs_ja.properties"),
                        "UTF-8").size());
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
