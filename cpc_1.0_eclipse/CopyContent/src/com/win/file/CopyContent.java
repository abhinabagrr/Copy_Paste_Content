package com.win.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Copies content of a single file
 * @author abhibasu
 *
 */
public class CopyContent {

	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("args.length" + args.length);
			return;
		}
		
		String fileName = args[0];
		File file = new File(fileName);
		
		System.out.println("file name: " + fileName);
		if(file.isDirectory() || !file.exists()){
			return;
		}
		
		Properties properties = new Properties();
		properties.setProperty("fName", fileName);
		try {
			properties.store(new FileOutputStream(new File("cache")), "");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
//		FileChannel sourceChannel = null;
//	    FileChannel destChannel = null;
//	    try {
//	        sourceChannel = new FileInputStream(fileName).getChannel();
//	        destChannel = new FileOutputStream("tmp").getChannel();
//	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
//	       } catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//	           try {
//				sourceChannel.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	           try {
//				destChannel.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	       }
	}

}
