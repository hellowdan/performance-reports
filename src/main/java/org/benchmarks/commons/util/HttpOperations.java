package org.benchmarks.commons.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpOperations {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpOperations.class);

    public static Object getFileObjectFromWeb(String url) throws IOException {
        Object input = null;

        try {
            URL urlFile = new URL(url);
            if ("https".equals(urlFile.getProtocol())) {
                input = getFileObjectFromHTTPS(urlFile);
            } else if ("http".equals(urlFile.getProtocol())) {
                input = getFileObjectFromHTTP(urlFile);
            }
        } catch (MalformedURLException e) {
            LOGGER.debug("Invalid URL: " + url, e);
            throw new MalformedURLException("Invalid URL: " + url);
        }

        return input;
    }

    public static Reader getFileReaderFromWeb(String url) throws IOException {
        Reader input = null;

        try {
            URL urlFile = new URL(url);
            if ("http".equals(urlFile.getProtocol())) {
                input = getFileReaderFromHTTP(urlFile);
            }
        } catch (MalformedURLException e) {
            LOGGER.debug("Invalid URL: " + url, e);
            throw new MalformedURLException("Invalid URL: " + url);
        }
        return input;
    }

    private static Reader getFileReaderFromHTTP(URL url) throws IOException {
        Reader input = null;

        try {
            URLConnection urlConn = url.openConnection();
            InputStreamReader in = new InputStreamReader((urlConn).getInputStream());
            input = new BufferedReader(in);
        } catch (IOException e) {
            LOGGER.debug("Content from URL cannot be read: " + url.getPath(), e);
            throw new IOException("Content from URL cannot be read: " + url.getPath(), e);
        }

        return input;
    }

    private static Object getFileObjectFromHTTPS(URL url) throws IOException {
        Object input = null;

        try {
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            input = new BufferedReader(in);
        } catch (IOException e) {
            LOGGER.debug("Content from URL cannot be read: " + url.getPath(), e);
            throw new IOException("Content from URL cannot be read: " + url.getPath(), e);
        }

        return input;
    }

    private static Object getFileObjectFromHTTP(URL url) throws IOException {
        Object input = null;

        try {
            URLConnection urlConn = url.openConnection();
            InputStreamReader in = new InputStreamReader((urlConn).getInputStream());
            input = new BufferedReader(in);
        } catch (IOException e) {
            LOGGER.debug("Content from URL cannot be read: " + url.getPath(), e);
            throw new IOException("Content from URL cannot be read: " + url.getPath(), e);
        }

        return input;
    }
}
