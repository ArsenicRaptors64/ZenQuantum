/*
 *****************************************************
 *                                                   *
 * ! IMPORTANT ! DO NOT DELETE COMMENT ! IMPORTANT ! *
 *                                                   *
 *****************************************************
 *                                                   *
 *            THIS CODE IS RELEASE READY.            *
 *                                                   *
 *       THIS CODE HAS BEEN TESTED HEAVILY AND       *
 *       CONSIDERED STABLE. THIS MODULE HAS NO       *
 *       KNOWN ISSUES. CONSIDERED RELEASE READY      *
 *                                                   *
 *****************************************************
 */

package API.Update;

import java.io.*;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

class DownloadUpdate {
    protected boolean download() throws Exception {
        String curDir = System.getProperty("user.dir");
        //Download the Windows Build
        String UpdateURL = "";
        //Download the Unix Build
        //String UpdateURL = "";
        //String ChecksumURL = "";
        File file = new File(UpdateURL);
        try {            
            //downloadUsingNIO(UpdateURL, "Update.md5");
            return downloadUsingNIO(UpdateURL, curDir + "/Update.zip");
        } catch (Exception e) {
            return false;
        }
    }
    private boolean downloadUsingNIO(String urlStr, String file) throws Exception {
        try {
            URL website = new URL(urlStr);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            return true;
        } catch (Exception E) {
            //E.printStackTrace();
            return false;
        }
    }
}