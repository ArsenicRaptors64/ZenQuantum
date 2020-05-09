package API.Download;

import java.io.*;
import API.*;
import API.Download.*;

/**
 * Program to help the user to download the files and encrypt/decrypt them
 *
 * <br>
 * @author Deepak Anil Kumar (DAK404)
 * @version 1.0.0
 * @since 08-December-2019
 * <p>
 * *** Technical Details ***<br>
 * - Module Name       : ZQ2: API_S02<BR>
 * - Module Version    : 1.0.0<BR>
 * - Module Author     : Deepak Anil Kumar (DAK404), Bebhin Mathew<BR></p>
 */
public class DownloadInterface
{
	private static String User;
	Console console=System.console();
	
	/**
     * This constructor has little use in this program.
     *
     * This constructor is a stub. It doesnt have any usable part of the program.
     */
	public DownloadInterface()	{}
	
	/**
     * This method delegates the tasks to the subfunctions to download a file
	 *
	 * @param SB : Used to get the SecureBoot variable from the program
	 * @param U  : Used to get the name of the user who is downloading the file
	 * @throws Exception Used to catch general exceptions and error states in program 
     */
	public void DInterfaceScript(boolean SB,String U)throws Exception
	{
	if(SB==false)
	{
		System.exit(0);
	}
	
	User=U;
	API.Information a=new API.Information();
	a.AboutProgram();
	System.out.println("SYSTEM> Type HELP to show help for download. Else press enter to continue");
	if(console.readLine().equalsIgnoreCase("Help"))
	{
		API.HelpViewer h=new API.HelpViewer();
		h.ShowHelp("Help/Download.manual");
	}
		API.Download.Download aa=new API.Download.Download(User);
		if(aa.downloadScript()==false)
		{
			System.out.println("Download Failed. Please Check the URL and Try Again.");
			return;
		}
		else
		{
			System.out.println("Download Successful. Do you want to encrypt the file? [Y/N]");
			if(console.readLine().equals("Y"))
			{
				Encrypt();
			}
			else
			{
				return;
			}
		}
	}
	
	/**
     * This method delegates the tasks to the subfunctions to encrypt a file
	 *
	 * @throws Exception Used to catch general exceptions and error states in program 
     */
	public void Encrypt()throws Exception
	{
		API.Download.Encryptor en=new API.Download.Encryptor();
		en.encr(User);
	}
	
	/**
     * This method delegates the tasks to the subfunctions to decrypt a file
	 *
	 * @throws Exception Used to catch general exceptions and error states in program 
     */
	public void Decrypt()throws Exception
	{
		API.Download.Decryptor en=new API.Download.Decryptor();
		en.dcr(User);		
	}
}