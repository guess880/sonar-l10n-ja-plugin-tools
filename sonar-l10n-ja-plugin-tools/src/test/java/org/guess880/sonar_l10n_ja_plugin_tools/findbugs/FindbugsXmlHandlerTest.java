package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import static org.junit.Assert.*;

import org.guess880.sonar_l10n_ja_plugin_tools.findbugs.FindbugsXmlHandler;
import org.guess880.sonar_l10n_ja_plugin_tools.findbugs.FindbugsXmlHandler.MessageSet;
import org.junit.Test;

public class FindbugsXmlHandlerTest extends TestBase {

    @Test
    public void testExist() throws Exception {
        final FindbugsXmlHandler handler = new FindbugsXmlHandler();
        handler.load(getPathName("findbugs-2.0.0.xml"),
                getPathName("findbugs-2.0.0-messages_ja.xml"));
        MessageSet msgSet = handler.getMessage("SKIPPED_CLASS_TOO_BIG");
        assertEquals("実験用 - 解析するにはあまりにも大きいクラス", msgSet.getShortDescription());
        assertEquals(
                "\n<p>\nこのクラスは有効に処理することができないほど大きいです。また、エラーのために完全に解析されませんでした。\n</p>\n",
                msgSet.getDetails());
        assertEquals(
                "shortDescription: 実験用 - 解析するにはあまりにも大きいクラス; details: \n<p>\nこのクラスは有効に処理することができないほど大きいです。また、エラーのために完全に解析されませんでした。\n</p>\n",
                msgSet.toString());
    }

    @Test
    public void testNotExist() throws Exception {
        final FindbugsXmlHandler handler = new FindbugsXmlHandler();
        handler.load(getPathName("findbugs-2.0.0.xml"),
                getPathName("findbugs-2.0.0-messages_ja.xml"));
        MessageSet msgSet = handler.getMessage("NOT_EXISTS");
        assertNull(msgSet);
    }

    @Test
    public void testHttp() throws Exception {
        final FindbugsXmlHandler handler = new FindbugsXmlHandler();
        handler.load("http://findbugs.googlecode.com/svn/trunk/findbugs/etc/findbugs.xml",
                "http://findbugs.googlecode.com/svn/trunk/findbugs/etc/messages_ja.xml");
        MessageSet msgSet = handler.getMessage("SKIPPED_CLASS_TOO_BIG");
        assertEquals("実験用 - 解析するにはあまりにも大きいクラス", msgSet.getShortDescription());
        assertEquals(
                "\n<p>\nこのクラスは有効に処理することができないほど大きいです。また、エラーのために完全に解析されませんでした。\n</p>\n",
                msgSet.getDetails());
        assertEquals(
                "shortDescription: 実験用 - 解析するにはあまりにも大きいクラス; details: \n<p>\nこのクラスは有効に処理することができないほど大きいです。また、エラーのために完全に解析されませんでした。\n</p>\n",
                msgSet.toString());
    }

}
