package com.giophub.commons.utils.file;

import org.junit.Test;

public class FileDownloaderTest {

    @Test
    public void downloadFileFromHttps() {
        String url = "https://ec.europa.eu/eurostat/estat-navtree-portlet-prod/BulkDownloadListing?file=dic/en";
        String resource = "geo.dic";
        FileDownloader fd = new FileDownloader(url, resource);
    }
}
