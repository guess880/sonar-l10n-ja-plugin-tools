package org.guess880.sonar_l10n_ja_plugin_tools.pmd;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class SonarPmdLocalizerTest extends TestBase {

    @Test
    public void testLocalize() throws Exception {
        final String resultDir = "target/test-result/pmd";
        final File resultDirFile = new File(resultDir);
        if (resultDirFile.exists()) {
            FileUtils.forceDelete(resultDirFile);
            Thread.sleep(2000);
        }
        FileUtils.forceMkdir(resultDirFile);
        final SonarPmdLocalizer localizer = new SonarPmdLocalizer();
        localizer.setRuleSets(
                getPathName("android.xml"),
                getPathName("basic-jsf.xml"),
                getPathName("basic.xml"),
                getPathName("braces.xml"),
                getPathName("clone.xml"),
                getPathName("codesize.xml"),
                getPathName("controversial.xml"),
                getPathName("coupling.xml"),
                getPathName("design.xml"),
                getPathName("finalizers.xml"),
                getPathName("imports.xml"),
                getPathName("j2ee.xml"),
                getPathName("javabeans.xml"),
                getPathName("junit.xml"),
                getPathName("logging-jakarta-commons.xml"),
                getPathName("logging-java.xml"),
                getPathName("migrating.xml"),
                getPathName("naming.xml"),
                getPathName("optimizations.xml"),
                getPathName("strictexception.xml"),
                getPathName("strings.xml"),
                getPathName("sunsecure.xml"),
                getPathName("typeresolution.xml"),
                getPathName("unusedcode.xml")
                ).setLanguage("ja")
                .setProperties(getPathName("sonar-pmd.properties"))
                .setOutdir(resultDirFile)
                .localize();
        assertEquals(
                278,
                FileUtils.readLines(
                        new File(resultDir, "pmd_ja.properties"),
                        "UTF-8").size());
        assertEquals(
                231,
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
        SonarPmdLocalizer.main(new String[] {
                PmdArguments.ARG_NM_LANGUAGE, "ja",
                PmdArguments.ARG_NM_SONAR_PROPERTIES, getPathName("sonar-pmd.properties"),
                PmdArguments.ARG_NM_RULESETXMLS,
                getPathName("android.xml"),
                getPathName("basic-jsf.xml"),
                getPathName("basic.xml"),
                getPathName("braces.xml"),
                getPathName("clone.xml"),
                getPathName("codesize.xml"),
                getPathName("controversial.xml"),
                getPathName("coupling.xml"),
                getPathName("design.xml"),
                getPathName("finalizers.xml"),
                getPathName("imports.xml"),
                getPathName("j2ee.xml"),
                getPathName("javabeans.xml"),
                getPathName("junit.xml"),
                getPathName("logging-jakarta-commons.xml"),
                getPathName("logging-java.xml"),
                getPathName("migrating.xml"),
                getPathName("naming.xml"),
                getPathName("optimizations.xml"),
                getPathName("strictexception.xml"),
                getPathName("strings.xml"),
                getPathName("sunsecure.xml"),
                getPathName("typeresolution.xml"),
                getPathName("unusedcode.xml"),
                PmdArguments.ARG_NM_OUTDIR, resultDir
        });
        assertEquals(
                278,
                FileUtils.readLines(
                        new File(resultDir, "pmd_ja.properties"),
                        "UTF-8").size());
        assertEquals(
                231,
                FileUtils.listFiles(resultDirFile, new String[] { "html" },
                        false).size());
    }

    @Test
    public void testMainParseError() throws Exception {
        SonarPmdLocalizer.main(new String[] {
        });
    }

}
