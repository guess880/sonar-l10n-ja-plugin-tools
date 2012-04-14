package org.guess880.sonar_l10n_ja_plugin_tools.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * LinkedProperties
 *
 * @author guess880
 */
public final class LinkedProperties extends Properties {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** LinkedHashMap */
    private Map<Object, Object> props = new LinkedHashMap<Object, Object>();

    /**
     * Constructor.
     */
    public LinkedProperties() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object get(Object key) {
        return props.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProperty(String key) {
        Object oval = get(key);
        return oval instanceof String ? (String) oval : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Object value) {
        return props.containsValue(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(Object key) {
        return props.containsKey(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(Object value) {
        return props.containsValue(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Enumeration<Object> elements() {
        return Collections.enumeration(props.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Map.Entry<Object, Object>> entrySet() {
        return props.entrySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return props.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Enumeration<?> propertyNames() {
        return keys();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Enumeration<Object> keys() {
        return Collections.enumeration(props.keySet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Object> keySet() {
        return props.keySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object put(Object key, Object value) {
        return props.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void putAll(Map<?, ?> t) {
        props.putAll(t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object remove(Object key) {
        return props.remove(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void rehash() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Object> values() {
        return props.values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return props.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        props.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * super.hashCode() + props.hashCode();
    }

}
