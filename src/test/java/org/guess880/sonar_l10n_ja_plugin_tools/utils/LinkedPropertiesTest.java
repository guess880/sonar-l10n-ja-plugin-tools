package org.guess880.sonar_l10n_ja_plugin_tools.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class LinkedPropertiesTest extends TestBase {

    private LinkedProperties props;

    private void load() throws Exception {
        props.load(new FileInputStream(new File(
                getPathName("linked.properties"))));
    }

    @Before
    public void setUp() {
        props = new LinkedProperties();
    }

    @Test
    public void testContainsObject() throws Exception {
        load();
        assertTrue(props.contains("value2"));
        assertFalse(props.contains("value4"));
    }

    @Test
    public void testContainsKeyObject() throws Exception {
        load();
        assertTrue(props.containsKey("key2"));
        assertFalse(props.containsKey("key4"));
    }

    @Test
    public void testContainsValueObject() throws Exception {
        load();
        assertTrue(props.containsValue("value2"));
        assertFalse(props.containsValue("value4"));
    }

    @Test
    public void testElements() throws Exception {
        load();
        final Enumeration<Object> e = props.elements();
        int i = 1;
        while (e.hasMoreElements()) {
            assertEquals("value" + Integer.toString(i), e.nextElement());
            i++;
        }
        assertEquals(4, i);
    }

    @Test
    public void testEntrySet() throws Exception {
        load();
        final Set<Map.Entry<Object, Object>> set = props.entrySet();
        int i = 1;
        for (Iterator<Map.Entry<Object, Object>> iterator = set.iterator(); iterator.hasNext();) {
            Map.Entry<Object, Object> entry = iterator.next();
            assertEquals("key" + Integer.toString(i), entry.getKey());
            assertEquals("value" + Integer.toString(i), entry.getValue());
            i++;
        }
        assertEquals(4, i);
    }

    @Test
    public void testGetProperty() throws Exception {
        load();
        assertEquals("value2", props.getProperty("key2"));
        assertNull(props.getProperty("key4"));
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(props.isEmpty());
        load();
        assertFalse(props.isEmpty());
    }

    @Test
    public void testPropertyNames() throws Exception {
        load();
        final Enumeration<?> e = props.propertyNames();
        int i = 1;
        while (e.hasMoreElements()) {
            assertEquals("key" + Integer.toString(i), e.nextElement());
            i++;
        }
        assertEquals(4, i);
    }

    @Test
    public void testKeys() throws Exception {
        load();
        final Enumeration<?> e = props.keys();
        int i = 1;
        while (e.hasMoreElements()) {
            assertEquals("key" + Integer.toString(i), e.nextElement());
            i++;
        }
        assertEquals(4, i);
    }

    @Test
    public void testKeySet() throws Exception {
        load();
        final Set<Object> set = props.keySet();
        int i = 1;
        for (Iterator<Object> iterator = set.iterator(); iterator.hasNext();) {
            assertEquals("key" + Integer.toString(i), iterator.next());
            i++;
        }
        assertEquals(4, i);
    }

    @Test
    public void testPutObjectObject() throws Exception {
        props.put("key1", "value1");
        props.put("key2", "value2");
        props.put("key3", "value3");
        final Set<Map.Entry<Object, Object>> set = props.entrySet();
        int i = 1;
        for (Iterator<Map.Entry<Object, Object>> iterator = set.iterator(); iterator.hasNext();) {
            Map.Entry<Object, Object> entry = iterator.next();
            assertEquals("key" + Integer.toString(i), entry.getKey());
            assertEquals("value" + Integer.toString(i), entry.getValue());
            i++;
        }
        assertEquals(4, i);
    }

    @Test
    public void testPutAllMapOfQQ() throws Exception {
        final LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(3);
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        props.putAll(map);
        final Set<Map.Entry<Object, Object>> set = props.entrySet();
        int i = 1;
        for (Iterator<Map.Entry<Object, Object>> iterator = set.iterator(); iterator.hasNext();) {
            Map.Entry<Object, Object> entry = iterator.next();
            assertEquals("key" + Integer.toString(i), entry.getKey());
            assertEquals("value" + Integer.toString(i), entry.getValue());
            i++;
        }
        assertEquals(4, i);
    }

    @Test
    public void testRemoveObject() throws Exception {
        load();
        assertTrue(props.containsKey("key2"));
        props.remove("key2");
        assertFalse(props.containsKey("key2"));
    }

    @Test
    public void testRehash() throws Exception {
        load();
        final Object clone = props.clone();
        props.rehash();
        assertEquals(clone, props);
    }

    @Test
    public void testValues() throws Exception {
        load();
        final Collection<Object> values = props.values();
        int i = 1;
        for (Iterator<Object> iterator = values.iterator(); iterator.hasNext();) {
            assertEquals("value" + Integer.toString(i), iterator.next());
            i++;
        }
        assertEquals(4, i);
    }

    @Test
    public void testSize() throws Exception {
        load();
        assertEquals(3, props.size());
    }

    @Test
    public void testClear() throws Exception {
        load();
        props.clear();
        assertTrue(props.isEmpty());
    }

    @Test
    public void testHashCode() {
        props.hashCode();
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(props.equals(props));
    }

}
