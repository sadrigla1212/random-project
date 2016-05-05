/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalcenter.Appointments;

import java.util.ArrayList;
import medicalcenter.Time.Time;
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

/**
 *
 * @author Algirdas
 */
public class Appointments {
 
    // variable in which all the appointments are saved
    private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    
   
    /**
     * Loads partial appointment data from build\data\appointment\appointment.txt
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void loadAppointments() throws FileNotFoundException, IOException{
        
        try(BufferedReader br = new BufferedReader(new FileReader("data/appointment/appointment.txt"))) {
            //Splits every line in a file into 8 parts and assigns them to appointments object
            for(String line; (line = br.readLine()) != null; ) {
                String[] values = line.split("\\s+", 8);
                Appointment item = new Appointment();
                appointments.add(item);
                int index = appointments.size() - 1;
                appointments.get(index).setId(Integer.parseInt(values[0]));
                appointments.get(index).setDoctorId(Integer.parseInt(values[1]));
                appointments.get(index).setClientId(Integer.parseInt(values[2]));
                appointments.get(index).setDate(values[3]);
                appointments.get(index).setTime(values[4]);
                appointments.get(index).setVisited(Integer.parseInt(values[5])!= 0);
                appointments.get(index).setPayment(values[6]);
                appointments.get(index).setReason(values[7]);
            }
        }
        
    }
    
    /**
     * Loads all doctor notes from build\data\appointment\notes. Data is taken
     * from a file named n(appointment_id).txt
     * 
     * @throws IOException
     */
    public void loadNotes() throws IOException{
        
        for(int i = 0; i<appointments.size(); i++){
            //note is added only after client visits a doctor
            if(appointments.get(i).isVisited()){
                
                String path = "data/appointment/notes/n" + appointments.get(i).getId() + ".txt";
                appointments.get(i).setNotes(readFile(path));
                
            }
            
        }
        
    }
    
    /**
     * Loads all doctor diagnosis from build\data\appointment\diagnosis. Data is taken
     * from a file named d(appointment_id).txt
     * 
     * @throws IOException
     */
    public void loadDiagnosis() throws IOException{
        
        for(int i = 0; i<appointments.size(); i++){
            
            //diagnosis is added only after client visits a doctor
            if(appointments.get(i).isVisited()){
                
                String path = "data/appointment/diagnosis/d" + appointments.get(i).getId() + ".txt";
                appointments.get(i).setDiagnosis(readFile(path));
                
            }
            
        }
        
    }
    
    /**
     * Loads all doctor prescriptions from build\data\appointment\prescriptions. Data is taken
     * from a file named p(appointment_id).txt
     * 
     * @throws IOException
     */
    public void loadPrescriptions() throws IOException{
        
        for(int i = 0; i<appointments.size(); i++){
            //prescriptions are added only after client visits a doctor
            if(appointments.get(i).isVisited()){
                // every line in a file is split into three parts and a prescription is added to a selected appointment
                String path = "data/appointment/prescriptions/p" + appointments.get(i).getId() + ".txt";
                try(BufferedReader br = new BufferedReader(new FileReader(path))) {
                    for(String line; (line = br.readLine()) != null; ) {
                        String[] values = line.split("\\s+", 3);
                        Appointment item = new Appointment();
                        appointments.get(i).addPrescription(values[0], values[1], values[2]);
                    }
                }
                
            }
            
        }
        
    }
    
    /**
     * Loads all appointment data from build\data\appointment\ folder
     * @throws IOException
     */
    public void loadAppData() throws IOException{
        
        loadAppointments();
        loadNotes();
        loadDiagnosis();
        loadPrescriptions();
        
        
    }
    
    /**
     * Adds given appointment to a complete appointment list
     * @param app appointment to add
     */
    public void addAppointment(Appointment app){
        
        //Appointment list is always sorted. When the new appointment added it is inserted in an appropriate position
        for (int i = 0; i < appointments.size(); i++)
        {
            // goes through all the current appointments. if appointment's, that is being added, date becomes smaller than current appointment's in that
            // position, it is inserted in that position. Otherwise it is added to an end.
            app.setId(appointments.size()+1);
            if(compareAppointmentsByDate(app, appointments.get(i)) <= 0){
                
                appointments.add(i, app);
                break;
                
            }
            else if ((i + 1 == appointments.size())&&(compareAppointmentsByDate(app, appointments.get(i)) > 0))
            {
                
                appointments.add(i+1, app);
                break;
                
            }
               
        }
        
    }
    
    /**
     *  Replaces existing appointment with a given appointment 
     * @param app appointment to replace with
     */
    public void editAppointment(Appointment app){
        
        for (int i = 0; i < appointments.size(); i++)
        {
            
            if (appointments.get(i).getId() == app.getId())
                appointments.get(i).copyAppointment(app);
                
                
        }
        
    }
    
    /**
     * Saves all appointment data to build\data\appointment\ folder
     * @throws FileNotFoundException
     */
    public void saveAppointments() throws FileNotFoundException{
        
        //curent appointment file is deleted and recreated
        try {
            Files.delete(Paths.get("data/appointment/appointment.txt"));
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", Paths.get("data/appointment/appointment.txt"));
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", Paths.get("data/appointment/appointment.txt"));
        } catch (IOException x) {
            System.err.println(x);
        }
        
        
        try {
    		 
	      File file = new File("data/appointment/appointment.txt");
	      
	      if (file.createNewFile()){
	        System.out.println("Appointment.txt file is created!");
	      }else{
	        System.out.println("Appointment.txt file already exists.");
	      }
	      
    	} catch (IOException e) {}

        
        try (PrintWriter appFile = new PrintWriter("data/appointment/appointment.txt")) {
            for (int i = 0; i<appointments.size();i++)  
            {
                
                int visited;
                if (appointments.get(i).isVisited() == true)
                    visited = 1;
                else
                    visited = 0;
                
                //all appointment info is joined into one string and written in the file
                String appointment = appointments.get(i).getId() + " "
                        + appointments.get(i).getDoctorId() + " "
                        + appointments.get(i).getClientId() + " "
                        + appointments.get(i).getDate() + " "
                        + appointments.get(i).getTime() + " "
                        + visited + " "
                        + appointments.get(i).getPayment() + " "
                        + appointments.get(i).getReason();
                
                appFile.println(appointment);
                
                //appointment note is saved
                String notePath = "data/appointment/notes/n" + appointments.get(i).getId() + ".txt";
                try (PrintWriter noteFile = new PrintWriter(notePath)) {
                    if (appointments.get(i).getNotes() != null)
                        noteFile.print(appointments.get(i).getNotes());
                }
                
                //appointment diagnosis is saved
                String diagnosisPath = "data/appointment/diagnosis/d" + appointments.get(i).getId() + ".txt";
                try (PrintWriter diagnosisFile = new PrintWriter(diagnosisPath)) {
                    if (appointments.get(i).getDiagnosis() != null)
                        diagnosisFile.print(appointments.get(i).getDiagnosis());
                }
                
                //all the prescription data is joined into a string and also saved
                if (appointments.get(i).getPrescriptions() != null)
                {
                    String prescriptionPath = "data/appointment/prescriptions/p" + appointments.get(i).getId() + ".txt";
                    try (PrintWriter prescriptionFile = new PrintWriter(prescriptionPath)) {
                        ArrayList<Prescription> prescr;
                        prescr = appointments.get(i).getPrescriptions();
                        for(int o = 0; o<prescr.size(); o++)
                        {
                            prescriptionFile.println(prescr.get(o).getExpireDate() + " " + prescr.get(o).getPayment() + " " + prescr.get(o).getName());
                            
                        }
                    }
                }
            }
        }
        
    }
    
    /**
     * This method is used to read a file into a single string
     * @param path
     * @return
     * @throws IOException 
     */
    private String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }
    
    /**
     * Prints all the appointment data into a console
     */
    public void printAppointments(){
        
        for (int i = 0; i< appointments.size(); i++){
            
            System.out.println(appointments.get(i));
            
        }
        
    }
    
    /**
     *Prints all the notes into a console
     */
    public void printNote(){
        
        for (int i = 0; i< appointments.size(); i++){
            
            System.out.println(appointments.get(i).getNotes());
            
        }
        
    }
    
    /**
     *Prints all the diagnosis into a console
     */
    public void printDiagnosis(){
        
        for (int i = 0; i< appointments.size(); i++){
            
            System.out.println(appointments.get(i).getDiagnosis());
            
        }
        
    }
    
    /**
     *Creates necessary files and directories:
     * build\data\appointment
     * build\data\appointment\notes
     * build\data\appointment\diagnosis
     * build\data\appointment\prescriptions
     * build\data\appointment\appointment.txt
     */
    public void createFiles(){

        if (new File("data/appointment").mkdirs()){
            System.out.println("Appointment directory is created!");
        }
        else{
            System.out.println("Appointment directory already exists.");
        }
        
        if (new File("data/appointment/notes").mkdirs()){
            System.out.println("Notes directory is created!");
        }
        else{
            System.out.println("Notes directory already exists.");
        }
        
        if (new File("data/appointment/diagnosis").mkdirs()){
            System.out.println("Diagnosis directory is created!");
        }
        else{
            System.out.println("Diagnosis directory already exists.");
        }
        
        if (new File("data/appointment/prescriptions").mkdirs()){
            System.out.println("Prescriptions directory is created!");
        }
        else{
            System.out.println("Prescriptions directory already exists.");
        }
        
        try {
    		 
	      File file = new File("data/appointment/appointment.txt");
	      
	      if (file.createNewFile()){
	        System.out.println("Appointment.txt file is created!");
	      }else{
	        System.out.println("Appointment.txt file already exists.");
	      }
	      
    	} catch (IOException e) {
	      e.printStackTrace();}
    }
    
    /**
     * @return the complete list of all the appointments
     */
    public ArrayList<Appointment> getAllAppointments(){
        
        return appointments;
        
    }
    
    /**
     * Returns the list of all the future appointments
     * @param t current time
     * @return the list of appointments
     */
    @SuppressWarnings("empty-statement")
    public ArrayList<Appointment> getFutureAppointments(Time t){
        
        ArrayList<Appointment> futureAppointments = new ArrayList<>();
        for (int i = 0; i<appointments.size(); i++){
            
            String[] date = appointments.get(i).getDate().split("-");
            String[] time = appointments.get(i).getTime().split(":");
            int[] completeDate = new int[date.length+time.length];
            // date and time are split into separate numbers and parsed as Integers
            for(int o = 0; o< date.length; o++){
                
                try {
                    completeDate[o] = Integer.parseInt(date[o]);
                } catch (NumberFormatException nfe) {};
                
            }
            for(int o = date.length; o< date.length+time.length; o++){
                
                try {
                    completeDate[o] = Integer.parseInt(date[o-date.length]);
                } catch (NumberFormatException nfe) {};
                
            }
            
            // date and time are compared to the current date and time
            // if it is in the future, an appointment is added to a list
            if(t.compareToDate(completeDate[0], completeDate[1], completeDate[2], completeDate[3], completeDate[4])>=0){
                
                futureAppointments.add(appointments.get(i));
                
            }
            
            
        }
        return futureAppointments;
        
    }
    
    /**
     * Returns the list of all doctors appointments
     * @param id doctor id
     * @return the list of appointments
     */
    public ArrayList<Appointment> getAllDoctorAppointments(int id){
        
        ArrayList<Appointment> allAppointments = new ArrayList<>();
        for(int i = 0; i<appointments.size();i++){
            // if doctor matches, an appointment is added to a list
            if(appointments.get(i).getDoctorId() == id){
                
                allAppointments.add(appointments.get(i));
                
            }
            
        }
        
        return allAppointments;
        
    }
    
    /**
     * Returns the list of all client appointments
     * @param id client id
     * @return the list of appointments
     */
    public ArrayList<Appointment> getAllClientAppointments(int id){
        
        ArrayList<Appointment> allAppointments = new ArrayList<>();
        for(int i = 0; i<appointments.size();i++){
            // if client matches, an appointment is added to a list            
            if(appointments.get(i).getClientId() == id){
                
                allAppointments.add(appointments.get(i));
                
            }
            
        }
        
        return allAppointments;
        
    }
    
    /**
     * Returns the list of all doctor's future appointments
     * @param id doctor id
     * @param t current time
     * @return the list of appointments
     */
    public ArrayList<Appointment> getAllFutureDoctorAppointments(int id, Time t){
        
                
        ArrayList<Appointment> allFutureApp = getFutureAppointments(t);                
        ArrayList<Appointment> allAppointments = new ArrayList<>();
        for(int i = 0; i<allFutureApp.size();i++){
            // if doctor matches, an appointment is added to a list
            if(allFutureApp.get(i).getDoctorId() == id){
                
                allAppointments.add(allFutureApp.get(i));
                
            }
            
        }
        
        return allAppointments;
        
        
    }
     
     /**
     * Returns the list of all client's future appointments
     * @param id client id
     * @param t current time
     * @return the list of appointments
     */
    public ArrayList<Appointment> getAllFutureClientAppointments(int id, Time t){
        
                
        ArrayList<Appointment> allFutureApp = getFutureAppointments(t);                
        ArrayList<Appointment> allAppointments = new ArrayList<>();
        for(int i = 0; i<allFutureApp.size();i++){
            // if client matches, an appointment is added to a list
            if(allFutureApp.get(i).getClientId() == id){
                
                allAppointments.add(allFutureApp.get(i));
                
            }
            
        }
        
        return allAppointments;
        
        
    }
    
    /**
     * Compares two appointments by their date
     * @param app1 first appointment
     * @param app2 second appointment
     * @return 1 if app1 > app2
     *         0 if app1 = app2
     *        -1 if app1
     */
    private int compareAppointmentsByDate(Appointment app1, Appointment app2){
        
        if(app1.getDate().compareTo(app2.getDate())>0)
            return 1;
        else if (app1.getDate().compareTo(app2.getDate()) < 0)
            return -1;
        else if (app1.getTime().compareTo(app2.getTime()) > 0)
            return 1;
        else if (app1.getTime().compareTo(app2.getTime())< 0)
            return -1;
        return 0;
    }
    
}
