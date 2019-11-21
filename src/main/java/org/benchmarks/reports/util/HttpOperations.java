package org.benchmarks.reports.util;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpOperations {

    public static Object getFileObjectFromWeb(String url) throws MalformedURLException {
        URL urlFile = new URL(url);
        Object input = null;

        if ("https".equals(urlFile.getProtocol())) {
            input = getFileObjectFromHTTPS(urlFile);
        } else if ("http".equals(urlFile.getProtocol())) {
            input = getFileObjectFromHTTP(urlFile);
        }

        return input;
    }

    public static Reader getFileReaderFromWeb(String url) throws MalformedURLException {
        URL urlFile = new URL(url);
        Reader input = null;

        if ("http".equals(urlFile.getProtocol())) {
            input = getFileReaderFromHTTP(urlFile);
        }

        return input;
    }

    private static Reader getFileReaderFromHTTP(URL url) {
        Reader input = null;

        try {
            URLConnection urlConn = url.openConnection();
            InputStreamReader in = new InputStreamReader((urlConn).getInputStream());
            input = new BufferedReader(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return input;
    }

    private static Object getFileObjectFromHTTPS(URL url) {
        Object input = null;

        try {
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            input = new BufferedReader(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return input;
    }

    private static Object getFileObjectFromHTTP(URL url) {
        Object input = null;

        try {
            URLConnection urlConn = url.openConnection();
            InputStreamReader in = new InputStreamReader((urlConn).getInputStream());
            input = new BufferedReader(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return input;
    }
}
