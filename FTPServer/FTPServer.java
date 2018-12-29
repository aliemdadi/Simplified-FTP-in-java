import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.*;




public class FTPServer
{
	public static void main(String args[]) throws Exception
	{
		ServerSocket soc=new ServerSocket(20);
		System.out.println("FTP Server Started on Port Number 20");
		while(true)
		{
			System.out.println("Waiting for Connection ...");
			transferfile t=new transferfile(soc.accept());
			
		}
	}
}

class transferfile extends Thread
{
	Socket ClientSoc;

	DataInputStream din;
	DataOutputStream dout;
	File currdir = new File(".");
	String dir = currdir.getAbsolutePath();
	String  cd;
	
	transferfile(Socket soc)
	{
		try
		{
		
		
			ClientSoc=soc;						
			din=new DataInputStream(ClientSoc.getInputStream());
			dout=new DataOutputStream(ClientSoc.getOutputStream());
			System.out.println("FTP Client Connected ...");
			start();
			  cd= dir.substring(0, dir.length() - currdir.getCanonicalPath().length());
			  

			
		}
		catch(Exception ex)
		{
		}		
	}
	void SendFile() throws Exception
	{		
		String filename=din.readUTF();
		//File f=new File(filename);
		File f=new File(cd+filename);
		System.out.println(f);
		if(!f.exists())
		{
			dout.writeUTF("File Not Found");
			return;
		}
		else
		{
			dout.writeUTF("READY");
			FileInputStream fin=new FileInputStream(f);
			int ch;
			do
			{
				ch=fin.read();
				dout.writeUTF(String.valueOf(ch));
			}
			while(ch!=-1);	
			fin.close();	
			dout.writeUTF("File Receive Successfully");							
		}
	}
	
	void CD() throws Exception
	{
		
		String rinput=din.readUTF();
		if(rinput.equals("BACK")) {

			Path newdir = Paths.get(cd);
			newdir=newdir.getParent();
			cd=newdir.toString();
		}
		
		else {
		String directory=rinput+"/";
	
		Path newdir = Paths.get(directory);
	
		
		if(Files.exists(newdir))
		{
			
			cd=cd+directory;
			dout.writeUTF("DCH");
		}
		else
		{
			Files.createDirectory(newdir);
			cd=cd+directory;
			dout.writeUTF("DC");
		}
			
    	}
	}
	 void LIST() throws IOException {
			
			File dir = new File(cd);
			String[] files = dir.list();
			String fl="";
			if (files.length == 0) {
			    System.out.println("The directory is empty");
			} else 
			{
				  for(String afile :files) {
					  fl=fl+afile+"\n";
				}
			}
				
				System.out.println(fl);
			    dout.writeUTF(fl);
			}

	public void run()
	{
		while(true)
		{
			try
			{
			System.out.println("Waiting for Command ...");
			String Command=din.readUTF();
			if(Command.compareTo("GET")==0)
			{
				System.out.println("\tGET Command Received ...");
				SendFile();
				continue;
			}
			else if(Command.compareTo("CD")==0)
			{
				System.out.println("\tCD Command Receiced ...");				
				CD();
				continue;
			}
			else if(Command.compareTo("LIST")==0)
			{
				System.out.println("\tLIST Command Receiced ...");				
				LIST();
				continue;
			}
			else if(Command.compareTo("DISCONNECT")==0)
			{
				System.out.println("\tDisconnect Command Received ...");
				this.ClientSoc.close();
				System.exit(1);
			}
			}
			catch(Exception ex)
			{
			}
		}
	}
}