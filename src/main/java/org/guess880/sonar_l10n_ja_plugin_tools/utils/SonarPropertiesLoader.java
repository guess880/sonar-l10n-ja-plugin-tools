package org.guess880.sonar_l10n_ja_plugin_tools.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Load property file of sonar.
 *
 * @author guess880
 */
public abstract class SonarPropertiesLoader {

    private static final String HTTPS = "https://";

    /**
     * Properties.
     */
    private Properties props;

    /**
     * All the keys in property.
     */
    private Enumeration<?> keys;

    /**
     * Load properties from file.
     *
     * @param fileName
     *            Properties file as 'findbugs.properties'.
     * @throws IOException
     */
    public void load(final String fileName) throws IOException {
        props = new LinkedProperties();
        final InputStream is = getInputStream(fileName);
        try {
            props.load(is);
        } finally {
            is.close();
        }
        keys = props.propertyNames();
    }

    /**
     * Return {@link InputStream}. If uri is url then download from url else
     * read local file.
     *
     * @param uri
     *            Uri of "findbugs.properties".
     * @return {@link InputStream} of uri.
     * @throws IOException
     */
    private InputStream getInputStream(final String uri) throws IOException {
        InputStream ret = null;
        if (uri.startsWith(HTTPS)) {
            final URL url = new URL(uri);
            final HttpURLConnection http = (HttpURLConnection) url
                    .openConnection();
            http.connect();
            ret = http.getInputStream();
        } else {
            ret = new FileInputStream(new File(uri));
        }
        return ret;
    }

    /**
     * Tests if this properties contains more keys.
     *
     * @return <code>true</code> if and only if this properties contains at
     *         least one more key to provide; <code>false</code> otherwise.
     */
    public boolean hasNext() {
        return keys.hasMoreElements();
    }

    /**
     * Return the next KeySet in loader properties file.
     *
     * @return next KeySet
     */
    public SonarProperty next() {
        final String key = (String) keys.nextElement();
        return innerNext(key, props.getProperty(key));
    }

    /**
     * Return the next KeySet in loader properties file.
     *
     * @param key key of record
     * @param value value of record
     * @return keySet
     */
    protected abstract SonarProperty innerNext(final String key, final String value);

}
