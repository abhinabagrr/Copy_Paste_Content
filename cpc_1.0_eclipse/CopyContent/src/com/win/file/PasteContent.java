package com.win.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Properties;

import javax.swing.JOptionPane;

/**
 * Pastes content on a single file
 * @author abhibasu
 *
 */
public class PasteContent {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("args.length" + args.length);
			return;
		}
		
		String destination = args[0];
		File file = new File(destination);
		
		System.out.println("Destination: " + destination);
		if(file.isDirectory() || !file.exists()){
			return;
		}
		
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("cache")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String source = properties.getProperty("fName");
		System.out.println("SOURCE: " + source);
		
		File fSource = new File(source);
		File fDest = new File(destination);
		boolean start = true;
		if(!getFileExtension(fSource).equals(getFileExtension(fDest))){
			System.out.println("File extensions are not same. waiting for user input.");
			int input = JOptionPane.showOptionDialog(null, "File types are not same, do you want to proceed?", "Warning",
					JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, null, null);
			start = input == JOptionPane.OK_OPTION;
			System.out.println("User wanted to over write: " + start);
			System.out.println("User wanted to over write: " + input);
		}
		
		if(start){
			FileChannel sourceChannel = null;
		    FileChannel destChannel = null;
		    try {
		        sourceChannel = new FileInputStream(source).getChannel();
		        destChannel = new FileOutputStream(destination).getChannel();
		        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		       } catch (IOException e) {
				e.printStackTrace();
			}finally{
		           try {
					sourceChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		           try {
					destChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		       }
		}
	}

	private static String getFileExtension(File file) {
		String name = file.getName();
		try {
			return name.substring(name.lastIndexOf(".") + 1);
		} catch (Exception e) {
			return "";
		}
	}

}
