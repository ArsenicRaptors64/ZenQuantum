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
import API.*;

/**
 * Program to help the user to download latest version of the program
 *
 * <br>
 * @author Deepak Anil Kumar (DAK404)
 * @version 1.0.0
 * @since 08-December-2019
 * <p>
 * *** Technical Details ***<br>
 * - Module Name       : ZQ2: API_S03<BR>
 * - Module Version    : 1.0.0<BR>
 * - Module Author     : Deepak Anil Kumar (DAK404), Bebhin Mathew<BR></p>
 */
public class UpdateInterface {
    API.Information DispInfoObj = new API.Information();
    Console console = System.console();

    public UpdateInterface() {}

    public void updateInterface() throws Exception {
        DispInfoObj.AboutProgram();
        System.out.println("Updater 6.2.4");
		System.out.println("Release Builds.");
        System.out.println("SYSTEM> Initiating update.\n[ATTENTION] : THE UPDATER WILL DOWNLOAD THE LATEST RELEASE AND INSTALL IT.\n[WARNING] : DO NOT CHANGE NETWORK STATES, REMOVE DRIVES, TURN OFF DEVICE OR CLOSE THIS PROGRAM. DOING SO MAY RENDER THE PROGRAM UNUSABLE.\n[ATTENTION] : You will be logged out of your current session, and will need to restart the program and re-login to continue.\n");
        System.out.println("-------------------------");
        //for Windows
        System.out.println("SYSTEM> Downloading program from: \n");

        //for Unix
        //System.out.println("SYSTEM> Downloading program from: ");

        if (download() == true) {
            System.out.println("SYSTEM> Update downloaded successfully.");
            System.out.println("SYSTEM> Update file location: ./Update.zip\n");
            System.out.println("Installing update from ./Update.zip");
            if (install() == true)
                System.out.println("SYSTEM> Update installed successfully.");
            else {
                System.out.println("SYSTEM> Failed to install update.\n");
                System.out.println("Possible Causes:\n- The downloaded update file was partially downloaded or was corrupt.\n- The download session was interrupted by a network change.\n- The update location has been moved to a new URL.\n\n");
                System.out.println("Possible Solutions:\n- Retry the update.\n- Restart the network/routers and check the network connection stability.\n- Contact the Administrator for more information.\n\n");
            }
        } else {
            System.out.println("SYSTEM> Failed to download update. Network Error.\n");
            System.out.println("Possible Causes:\n- Limited/restricted network access, or firewall rules which prevented downloading the file.\n- The download session was interrupted by a network change.\n- The update location has been moved to a new URL.\n");
            System.out.println("Possible Solutions:\n- Retry the update.\n- Restart the network/routers and check the network connection stability.\n- Contact the Administrator for more information.\n-------------------------\n");
        }
        System.out.println("Press Enter to Restart the program.");
        console.readLine();
        //For Windows
        new ProcessBuilder("cmd", "/c", "Run_Program.bat").inheritIO().start();
        //For Unix
        //new ProcessBuilder("/bin/bash", "-c", "sh Run_Program.sh").inheritIO().start();
        System.exit(0);
    }
    private boolean download() throws Exception {
        try {
            DownloadUpdate downloadProgram = new DownloadUpdate();
            return downloadProgram.download();
        } catch (Exception E) {
            return false;
        }
    }

    private boolean install() throws Exception {
        try {
            InstallUpdate installProgram = new InstallUpdate();
            return installProgram.install();
        } catch (Exception E) {
            return false;
        }
    }
}