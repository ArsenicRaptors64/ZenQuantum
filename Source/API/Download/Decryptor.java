package API.Download;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.spec.KeySpec;
import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

class Decryptor
{
  private String nm, Pass, pw, Key;
  private static String Username="";
  Console console=System.console();
	protected void dcr(String U)throws Exception
	{
		Username=U;
		Decryptor de=new Decryptor();
		de.welcome();
	}

	private void welcome()throws Exception
	{
      	System.out.println("DISCLAIMER: FILE CANNOT BE RECOVERED IF THE CREDENTIALS ARE LOST.\n");
		System.out.print("Enter the name of the file to be Decrypted (with extension): ");
		nm=console.readLine();
		System.out.println("Enter the password and key to encrypt the file.");
		System.out.print("Password: ");
		pw=String.valueOf(console.readPassword());
		System.out.print("Security Key: ");
		Key=String.valueOf(console.readPassword());
	    Pass=pw+Key;
		System.out.println("Attempting to decrypt file....");
		Decr();
	}
      
	void Decr() throws Exception
	{
		try
		{
			String curDir = System.getProperty("user.dir");
			String newDir=curDir+"/Users/"+Username+"/Files/"+nm+"/";
			/* Description:
			* Read the salt
			* User must transfer salt, iv and password to the recipient securely
			*/
			FileInputStream saltFis = new FileInputStream(newDir+"salt.enc");
			byte[] salt = new byte[8];
			saltFis.read(salt);
			saltFis.close();

			// Reading the iv
			FileInputStream ivFis = new FileInputStream(newDir+"iv.enc");
			byte[] iv = new byte[16];
			ivFis.read(iv);
			ivFis.close();

			SecretKeyFactory factory = SecretKeyFactory
			.getInstance("PBKDF2WithHmacSHA1");
			KeySpec keySpec = new PBEKeySpec(Pass.toCharArray(), salt, 65536,
			256);
			SecretKey tmp = factory.generateSecret(keySpec);
			SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

			// File decryption, Decrypt and save the file
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
			FileInputStream fis = new FileInputStream(newDir+"/"+nm+".LOCK");
			FileOutputStream fos = new FileOutputStream(newDir+"/"+nm);
			byte[] in = new byte[64];
			int read;
			while ((read = fis.read(in)) != -1) {
				byte[] output = cipher.update(in, 0, read);
				if (output != null)
				fos.write(output);
			}

			byte[] output = cipher.doFinal();
			if (output != null)
			fos.write(output);
			fis.close();
			fos.flush();
			fos.close();
		}
		catch(Exception e)
		{
			System.out.println("The entered password is incorrect. Please try again.");
			console.readLine();
		}
	}
}
