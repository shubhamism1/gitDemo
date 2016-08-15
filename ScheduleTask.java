package com.scheduler;

import java.util.*;
import java.text.*;
 
public class ScheduleTask {
 
   static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   
   static boolean flag = false;
 
   static Timer timer = new Timer();
   
   static Scanner sc = new Scanner(System.in);
 
   private static class MyTimeTask extends TimerTask {
	   
      public void run() {
         System.out.println("Running Task");
         System.out.println("Current Time: " + df.format( new Date()));
         
         if(!flag)	
        	 timer.cancel();
      }
   }
 
   public static void main(String[] args) throws ParseException {
	  String currTime = df.format(new Date());
      System.out.println("Current Time: " + currTime);
 
      //Date and time at which you want to execute
//      String str = "2016-08-15 22:19:23";
      String str = "";
      String format = "yyyy-MM-dd HH:mm:ss";
      System.out.println("please enter time in this format " + format + " Ex :-->  "+ currTime);
      str = sc.nextLine();
      
      
      while(!isValidFormat(format,str)){
    	  
    	  str = sc.nextLine();
      }
      while(!isBefore(str)){
    	  System.out.println("given time is before current time" + df.format(new Date()) + " \nplease enter valid time");
    	  str = sc.nextLine();
      }
      Date date = df.parse(str);
      System.out.println("Do you want to run it on fixed interval (y/n) \n");
      char choice = sc.next().charAt(0);
      
      if(choice == 'y' || choice =='Y')
      {
    	 flag = true;
     	 System.out.println("Give interval in milliseconds\n");
     	 long interval = sc.nextLong();
     	System.out.println("It will be executed on " + date);
     	 timer.scheduleAtFixedRate(new MyTimeTask(), date, interval);
     	
      }else{
    	  System.out.println("It will be executed on " + date);
    	  timer.schedule(new MyTimeTask(), date);
      }
   }
   private static boolean isBefore(String str) {
	   String currTime = df.format(new Date());
	   if(currTime.compareTo(str) < 0){
		   return true;
	   }
	return false;
}
public static boolean isValidFormat(String format, String value) {
       Date date = null;
       try {
           SimpleDateFormat sdf = new SimpleDateFormat(format);
           date = sdf.parse(value);
           if (!value.equals(sdf.format(date))) {
               date = null;
           }
       } catch (ParseException ex) {
//           ex.printStackTrace();
           System.out.println("please enter time in this format " + format + " Ex :-->  "+ df.format(new Date()));
       }
       return date != null;
   }
}
