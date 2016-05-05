/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalcenter.Time;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
/**
 *
 * @author Algirdas
 */
public class Time {
    
    private int year;
    private int month;
    private int day;
    private int dayOfWeek;
    private int hour;
    private int minute;

    /**
     * This method sets object date to the given value
     * @param year
     * @param month
     * @param day
     * @param dayOfWeek
     * @param hour
     * @param minute
     */
    public void setTime(int year, int month, int day, int dayOfWeek, int hour, int minute){
        
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
        this.setDayOfWeek(dayOfWeek);
        this.setHour(hour);
        this.setMinute(minute);
        
        
    }
    
    @Override
    public String toString(){
        
        String date = getDate() + ", " + getTime() + ", " +getDayOfWeekWord() ;
        return date;
        
    }
    
    /**
     *
     * @return the day of the week in a word format
     */
    public String getDayOfWeekWord(){
        
        switch(getDayOfWeek()){
            
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";
            default: return "Sunday";
        }
        
    }
    
    /**
     *
     * Set time to 2016-03-29 12:00
     */
    public void setTime(){
        
        this.setYear(2016);
        this.setMonth(3);
        this.setDay(29);
        this.setDayOfWeek(2);
        this.setHour(12);
        this.setMinute(0);
        
        
    }
    
    /**
     *
     * @return returns ArrayList of year, month, day and day of week
     */
    public ArrayList getDateInt(){
        
        ArrayList date = new ArrayList();
        date.add(getYear());
        date.add(getMonth());
        date.add(getDay());
        date.add(getMonth());
        
        return date;
        
    }
    
    /**
     *
     * @return returns ArrayList of hour and minute
     */
    public ArrayList getTimeInt(){
        
        ArrayList time = new ArrayList();
        time.add(getHour());
        time.add(getMinute());
        return time;
        
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * @return the dayOfWeek
     */
    public int getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * @param dayOfWeek the dayOfWeek to set
     */
    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * @return the hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * @param hour the hour to set
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * @return the minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * @param minute the minute to set
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }
    
    /**
     * This method returns current time in a "hh:mm" format
     * @return current time as a string
     */
    public String getTime(){
        
        String time;
        if(getMinute()<10)
            time = getHour()+ ":0" + getMinute();
        else
            time = getHour()+ ":" + getMinute();
        return time;
    }
    
    /**
     * This method returns current time in a "yyyy:mm:dd day of the week" format  
     * @return current date as a string
     */
    public String getDate(){
        
        String date;
        if(getMonth()<10)
            date = getYear() + "-0" + getMonth();
        else
            date = getYear() + "-" + getMonth();
        
        
        if(getDay()<10)
            date = date + "-0" + getDay();
        else
            date = date + "-" + getDay();
        return date;
        
    }
    
    /**
     * This method adds given number to a current year
     * @param num number to add to a year
     */
    public void addYear(int num){
        
        int year = getYear();
        year += num;
        setYear(year);
        
    }
    
    /**
     * This method adds given number to a current month. Year is readjusted 
     * automatically.
     * 
     * @param num number to add to a month
     */
    public void addMonth(int num){
        
        int month = getMonth();
        month += num;
        while(month>12){
            
            month-=12;
            addYear(1);
            
        }
        setMonth(month);
        
    }
    
    /**
     * This method adds given number to a current day. Year and month are
     * readjusted automatically.
     * @param num  number to add to a day
     */
    public void addDay(int num){
        
        int day = getDay();
        day+=num;
         
        boolean nextMonth = false;
        int dayInMonth = 0;
        switch(getMonth()){
            
            case 1: case 3: case 5: case 7:
            case 8: case 10: case 12:
                if(day>31)
                    nextMonth = true;
                dayInMonth = 31;
                break;
            case 4: case 6: case 9: case 11:
                if(day>30)
                    nextMonth = true;
                dayInMonth = 31;
                break;
            case 2:
                if(leapYear() && day>29){
                    nextMonth = true;
                    dayInMonth = 29;}
                else if(day>28){
                    nextMonth = true;
                    dayInMonth = 28;}
                break;
              
        }
        
        while(nextMonth)
        {
            
            day -= dayInMonth;
            addMonth(1);
            nextMonth = false;
            switch(getMonth()){
            
                case 1: case 3: case 5: case 7:
                case 8: case 10: case 12:
                    if(day>31)
                        nextMonth = true;
                    dayInMonth = 31;
                    break;
                case 4: case 6: case 9: case 11:
                    if(day>30)
                        nextMonth = true;
                    dayInMonth = 31;
                    break;
                case 2:
                    if(leapYear() && day>29){
                        nextMonth = true;
                        dayInMonth = 29;}
                    else if(day>28){
                        nextMonth = true;
                        dayInMonth = 28;}
                    break;
            }
            
        }
        
        setDay(day);
        addDayOfWeek(num);    
        
    }
    
     /**
     * This method adds given number to a current day of the week.
     * @param num  number to add to a day of the week.
     */   
    public void addDayOfWeek (int num){
        
        int dayOfWeek = getDayOfWeek();
        dayOfWeek += num;
        
        while(dayOfWeek>7){
            
            dayOfWeek -= 7;
            
        }
        
        setDayOfWeek(dayOfWeek);
        
    }
    
        
     /**
     * This method adds given number to a current hour. Day is adjusted
     * automatically.
     * @param num  number to add to an hour.
     */
    public void addHour(int num){
        
        int hour = getHour();
        hour += num;
        
        while(hour>23){
            
            hour -= 24;
            addDay(1);
            
        }
        setHour(hour);
    }
    
        
     /**
     * This method adds given number to a current minute. Hour is readjusted
     * automatically.
     * @param num  number to add to a minute.
     */
    public void addMinute(int num){
        
        int minute = getMinute();
        minute += num;
        
        while(minute>60){
            
            minute -= 60;
            addHour(1);
            
        }
        setMinute(minute);
        
    }
    
    /**
     * Compares given date to this objects current date.
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @return 
     */
    public int compareToDate(int year, int month, int day, int hour, int minute){
        
        if(year > getYear())
            return 1;
        else if(year < getYear())
            return -1;
        
        if(month > getMonth())
            return 1;
        else if(month < getMonth())
            return -1;
        
        if(day > getDay())
            return 1;
        else if(day < getDay())
            return -1;
        
        if(hour > getHour())
            return 1;
        else if(hour < getHour())
            return -1;
        
        if(minute > getMinute())
            return 1;
        else if(minute < getMinute())
            return -1;
        
        
        return 0;
        
    }
    
    /**
     * Checks whether current year is a leap year
     * @return truth if current year is a leap year
     */
    public boolean leapYear() {

    int theYear;
    theYear = getYear();

    if (theYear < 100) {
        if (theYear > 40) {
            theYear = theYear + 1900;
        } else {
            theYear = theYear + 2000;
        }
    }

    if (theYear % 4 == 0) {
        if (theYear % 100 != 0) {
            return true;
        } else if (theYear % 400 == 0) {
            return true;
        } else {
            return false;
        }
    } else {
            return false;
    }
}
    
    /**
     * Creates file time.txt in build\data\time folder necessary for time saving
     */
    public void createFiles(){

        if (new File("data/time").mkdirs()){
            System.out.println("Time directory is created!");
        }
        else{
            System.out.println("Time directory already exists.");
        }
        
        try {
    		 
	      File file = new File("data/time/time.txt");
	      
	      if (file.createNewFile()){
	        System.out.println("Time.txt file is created!");
	      }else{
	        System.out.println("Time.txt file already exists.");
	      }
	      
    	} catch (IOException e) {}
    }
    
    /**
     * saves current time data in build\data\time\time.txt
     * @throws FileNotFoundException 
     */
    public void saveTime() throws FileNotFoundException{
        
        try {
            Files.delete(Paths.get("data/time/time.txt"));
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", Paths.get("data/time/time.txt"));
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", Paths.get("data/time/time.txt"));
        } catch (IOException x) {
            System.err.println(x);
        }
        
        try {
    		 
	      File file = new File("data/time/time.txt");
	      
	      if (file.createNewFile()){
	        System.out.println("Time.txt file is created!");
	      }else{
	        System.out.println("Time.txt file already exists.");
	      }
	      
    	} catch (IOException e) {}
        
        PrintWriter timeFile = new PrintWriter("data/time/time.txt");
        
        String time = getYear() + " " + getMonth() + " " + getDay() + " " + getDayOfWeek() + " " + getHour() + " " + getMinute();
        
        timeFile.println(time);
        
        timeFile.close();
        
    }
    
    /**
     * loads saved time from build\data\time\time.txt file
     * @throws IOException 
     */
    public void loadTime() throws IOException{
        
   
        try(BufferedReader br = new BufferedReader(new FileReader("data/time/time.txt"))) {
            
            String line = br.readLine();
            String[] values = line.split("\\s+", 6);
            setTime(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]),
                    Integer.parseInt(values[4]), Integer.parseInt(values[5]));
            
        }
        
    }
    
}
