package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class WatchService1 {

	public static void main(String[] args) throws IOException,
			InterruptedException {

		
		Path faxFolder = Paths.get("c://java//gaurav//");
		WatchService watchService = FileSystems.getDefault().newWatchService();
		faxFolder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
		String emailAddress= "";
		String heartRate = "";
		HashMap<String, String> map = new HashMap<String,String>();

		boolean valid = true;
		do {
			WatchKey watchKey = watchService.take();

			for (WatchEvent event : watchKey.pollEvents()) {
				WatchEvent.Kind kind = event.kind();
				if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
					String fileName = event.context().toString();
					System.out.println("File Created:" + fileName);
					 readTextFileUsingScanner(faxFolder+"//"+fileName);
					  } 
				}
			
			valid = watchKey.reset();

		} while (valid);

	}
	
	public static void readTextFileUsingScanner(String fileName) {
	    try {
	    	Integer counter = 0;
	    	EmailTest javaEmail = new EmailTest();
			javaEmail.setMailServerProperties();
	      Scanner sc = new Scanner(new File(fileName));
	     // String str = sc.nextLine();
	      while (sc.hasNext()) {
	    	  String str1 = sc.nextLine();
	       // System.out.println(str);
	        try{	        	String[] array = str1.split(" ");

Integer heartBeatval =Integer.parseInt( array[1].toString());
if(heartBeatval >=100) {
	System.out.println("values" +array[0].toString()+array[1].toString());
	counter++;
	if(counter<=5) {
	javaEmail.createEmailMessage(array[0],array[1]);
	javaEmail.sendEmail();
	}
	else {
		javaEmail.createEmailMessage("gauravkanyal21@gmail.com",array[1]);
		javaEmail.sendEmail();
		counter = 0;
	}
}
				  
			  }catch(Exception e) {
				  e.printStackTrace();
			  }
	      }
	      sc.close();
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	  }
}

