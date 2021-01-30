package autoTestFramework.downloadUtil;

import autoTestFramework.stringsUtils.StringsReader;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The class is responsible for download file and checking the download
 */

public class DownloadFileWithURL {
    private static final String DOWNLOAD_DIRECTORY_PATH = "downloadDirectoryPath";
    private static final String WAITING_TIME = "waitingTime";

    private String settingsLink;

    private static String downloadPath;

    private static final Logger logger = Logger.getLogger(DownloadFileWithURL.class);

    public DownloadFileWithURL(String settingsLink) {
        this.settingsLink = settingsLink;
    }

    public void download(String link) {
        try {
            logger.info("Try to download.");
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            InputStream content = connection.getInputStream();

            File file = new File(downloadPath);
            OutputStream out = new FileOutputStream(file);
            IOUtils.copy(content, out);
            content.close();
            out.close();
        } catch (Exception ex) {
            logger.warn(ex);
        }
    }

    public boolean isDownload() {
        StringsReader stringsReader = new StringsReader(settingsLink);
        try {
            Thread.sleep(Integer.parseInt(stringsReader.getCustomElement(WAITING_TIME)));
            downloadPath = stringsReader.getCustomElement(DOWNLOAD_DIRECTORY_PATH);
            logger.info("File was downloaded: " + new File(downloadPath).exists());
            return new File(downloadPath).exists();
        } catch (InterruptedException ex) {
            logger.warn(ex);
            return false;
        }
    }

}
