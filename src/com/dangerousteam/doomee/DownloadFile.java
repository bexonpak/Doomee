/*
 * Copyright (C) 2020 Bexon Pak.
 */
package com.dangerousteam.doomee;

import com.dangerousteam.doomee.util.FileUtil;
import com.dangerousteam.doomee.util.LogFileUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DownloadFile {

    /**
     * Download files from Url.
     */
    public static void downLoadFromUrl(String urlStr, String savePath, Long timeStamp) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Set timeout.
            conn.setConnectTimeout(15 * 1000);
            // Set User-Agent.
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1 Safari/605.1.15");
            InputStream inputStream = conn.getInputStream();
            byte[] getData = readInputStream(inputStream);
            // Saved location.
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            // Get file name.
            String fileName = urlStr.substring(urlStr.lastIndexOf('/') + 1);
            fileName = fileName.replaceAll("\\?.*", "");
            File file = new File(saveDir + File.separator + fileName);
            if (file.exists()) {
                file = FileUtil.createNewFile(file);
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
            if (fos != null) {
                fos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String time = simpleDateFormat.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
            System.err.println("There is an exception in the download, please check the error_" + time + ".log file.");
            LogFileUtil.saveAsFileWriter("error_" + time + ".log", urlStr + "\r");
        }
    }

    /**
     * Get byte array from input stream.
     */
    private static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

}
