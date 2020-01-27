package org.benchmarks.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.benchmarks.exceptions.InvalidContentFromURLException;
import org.benchmarks.exceptions.InvalidURLException;

public class HttpOperations {

    private HttpOperations() {
    }

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
            throw new InvalidURLException(url, e);
        }

        return input;
    }

    public static Reader getFileReaderFromWeb(String url) throws IOException {
        Reader input = null;

        try {
            URL urlFile = new URL(url);
            input = getFileReaderFromHTTP(urlFile);
        } catch (MalformedURLException e) {
            throw new InvalidURLException(url, e);
        }
        return input;
    }

    private static Reader getFileReaderFromHTTP(URL url) throws IOException {
        Reader input;

        try {
            URLConnection urlConn = url.openConnection();
            InputStreamReader in = new InputStreamReader((urlConn).getInputStream());
            input = new BufferedReader(in);
        } catch (IOException e) {
            throw new InvalidContentFromURLException(url.getPath(), e);
        }

        return input;
    }

    private static Object getFileObjectFromHTTPS(URL url) throws IOException {
        Object input;

        try {
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            input = new BufferedReader(in);
        } catch (IOException e) {
            throw new InvalidContentFromURLException(url.getPath(), e);
        }

        return input;
    }

    private static Object getFileObjectFromHTTP(URL url) throws IOException {
        Object input;

        try {
            URLConnection urlConn = url.openConnection();
            InputStreamReader in = new InputStreamReader((urlConn).getInputStream());
            input = new BufferedReader(in);
        } catch (IOException e) {
            throw new InvalidContentFromURLException(url.getPath(), e);
        }

        return input;
    }
}
