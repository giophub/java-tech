package com.giophub.commons.utils.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class FileDownloader {
    private static final Logger LOG = LoggerFactory.getLogger(FileDownloader.class);

    public FileDownloader(String sUrl, String resource) {
        sUrl = sUrl.endsWith("/") ? sUrl : sUrl + "/" ;
        String uri = sUrl + resource;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            URL url = new URL(uri);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            is = connection.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            String inputLine;
            if (LOG.isDebugEnabled()) {
                while ((inputLine = br.readLine()) != null) {
                    System.out.println(inputLine);
                }
            }
        } catch (IOException e) {
            LOG.error("Cannot download the file: {}. Exception: {}", uri, e);
        } finally {
            try { if (br != null)   br.close();     } catch (IOException e) {/* cannot do nothing */}
            try { if (isr != null)  isr.close();    } catch (IOException e) {/* cannot do nothing */}
            try { if (is != null)   is.close();     } catch (IOException e) {/* cannot do nothing */}
        }
    }
}
