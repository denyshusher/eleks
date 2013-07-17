package com.elrain.downloadtest.news.classes;

import org.apache.http.util.ByteArrayBuffer;

import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadXML {	
	
	private File file = null;
	private URL url = null;
	private File root = Environment.getExternalStorageDirectory();
	
	public DownloadXML(String _url, String _pathToFile){
		try{
			this.file = new File(root, _pathToFile);
			this.url = new URL(_url);
		}
		catch(MalformedURLException ex){
			ex.printStackTrace();
		}
	}
	
	public void downloadFile(){
		try{
			
			URLConnection urlConn = url.openConnection();
			
			InputStream is = urlConn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            
            writeFile(bis);
		}
		catch(FileNotFoundException ex){
			System.out.println("File not found");
		}
        catch(IOException ex){
        	ex.printStackTrace();
        }
	}
	
	private void writeFile(BufferedInputStream bis){
		try{
			ByteArrayBuffer buf = new ByteArrayBuffer(50);
	        int current = 0;
	        while ((current = bis.read()) != -1) {
	        	buf.append((byte) current);
	        }  
	        
	        FileOutputStream fos = new FileOutputStream(file);
	        fos.write(buf.toByteArray());
	        fos.close();
		}
		catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
