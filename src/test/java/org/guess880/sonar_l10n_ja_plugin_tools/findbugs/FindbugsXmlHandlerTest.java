package org.guess880.sonar_l10n_ja_plugin_tools.findbugs;

import static org.junit.Assert.*;

import org.guess880.sonar_l10n_ja_plugin_tools.findbugs.FindbugsXmlHandler;
import org.guess880.sonar_l10n_ja_plugin_tools.findbugs.FindbugsXmlHandler.MessageSet;
import org.guess880.sonar_l10n_ja_plugin_tools.utils.SonarLocalizeException;
import org.junit.Test;

public class FindbugsXmlHandlerTest extends TestBase {

    @Test
    public void testExist() throws Exception {
        final FindbugsXmlHandler handler = new FindbugsXmlHandler()
            .setFindbugsXml(getPathName("findbugs-2.0.0.xml"))
            .setMessagesXml(getPathName("findbugs-2.0.0-messages_ja.xml"));
        handler.load();
        MessageSet msgSet = (MessageSet) handler.getLocalizedResource("SKIPPED_CLASS_TOO_BIG");
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
        final FindbugsXmlHandler handler = new FindbugsXmlHandler()
            .setFindbugsXml(getPathName("findbugs-2.0.0.xml"))
            .setMessagesXml(getPathName("findbugs-2.0.0-messages_ja.xml"));
        handler.load();
        Object msgSet = handler.getLocalizedResource("NOT_EXISTS");
        assertNull(msgSet);
    }

    @Test
    public void testHttp() throws Exception {
        final FindbugsXmlHandler handler = new FindbugsXmlHandler()
            .setFindbugsXml("http://findbugs.googlecode.com/svn/trunk/findbugs/etc/findbugs.xml")
            .setMessagesXml("http://findbugs.googlecode.com/svn/trunk/findbugs/etc/messages_ja.xml");
        handler.load();
        MessageSet msgSet = (MessageSet) handler.getLocalizedResource("SKIPPED_CLASS_TOO_BIG");
        assertEquals("実験用 - 解析するにはあまりにも大きいクラス", msgSet.getShortDescription());
        assertEquals(
                "\n<p>\nこのクラスは有効に処理することができないほど大きいです。また、エラーのために完全に解析されませんでした。\n</p>\n",
                msgSet.getDetails());
        assertEquals(
                "shortDescription: 実験用 - 解析するにはあまりにも大きいクラス; details: \n<p>\nこのクラスは有効に処理することができないほど大きいです。また、エラーのために完全に解析されませんでした。\n</p>\n",
                msgSet.toString());
    }

    @Test(expected = SonarLocalizeException.class)
    public void testLoadException() throws Exception {
        final FindbugsXmlHandler handler = new FindbugsXmlHandler()
                .setFindbugsXml(getPathName("notexists.xml"))
                .setMessagesXml(getPathName("notexists_messages.xml"));
        handler.load();
    }

//    @Test(expected = SonarLocalizeException.class)
//    public void testGetLocalizedResourceException() throws Exception {
//        final FindbugsXmlHandler handler = new FindbugsXmlHandler()
//            .setFindbugsXml(getPathName("findbugs-2.0.0.xml"))
//            .setMessagesXml(getPathName("findbugs-2.0.0-messages_ja.xml"));
//        handler.load();
//        handler.getLocalizedResource(null);
//    }

}
