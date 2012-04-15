package org.guess880.sonar_l10n_ja_plugin_tools.pmd;

import static org.junit.Assert.*;

import net.sourceforge.pmd.Rule;

import org.guess880.sonar_l10n_ja_plugin_tools.utils.SonarLocalizeException;
import org.junit.Test;


public class PmdXmlHandlerTest extends TestBase {

    @Test
    public void testGetRuleFile() throws Exception {
        final PmdXmlHandler handler = new PmdXmlHandler()
            .setRuleSetFiles(getPathName("basic.xml"));
        handler.load();
        final Rule rule = (Rule) handler.getLocalizedResource("EmptyCatchBlock");
        assertEquals("EmptyCatchBlock", rule.getName());
    }

    @Test
    public void testGetRuleHttp() throws Exception {
        final PmdXmlHandler handler = new PmdXmlHandler()
            .setRuleSetFiles("https://pmd.svn.sourceforge.net/svnroot/pmd/tags/pmd/pmd_release_4_3/rulesets/basic.xml");
        handler.load();
        final Rule rule = (Rule) handler.getLocalizedResource("EmptyCatchBlock");
        assertEquals("EmptyCatchBlock", rule.getName());
    }

    @Test(expected = SonarLocalizeException.class)
    public void testRuleSetNotFoundException() throws Exception {
        final PmdXmlHandler handler = new PmdXmlHandler()
            .setRuleSetFiles(getPathName("notfound.xml"));
        handler.load();
    }

}
