package medicalcenter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import medicalcenter.user.doctor.Doctor;
import medicalcenter.user.patient.Patient;

/**
 * UserManager - Responsible for user registration, login, saving and loading user information from files to runtime.
 * @author Linas Ramanauskas, IT-2
 */

public class UserManager {
    private String DOCTOR_KEY;
    private ArrayList<Doctor> Doctors;
    private ArrayList<Patient> Patients;
    
    /**
    * UserManager constructor - used to create a new user manager 
    */ 
    public UserManager() {
        this.DOCTOR_KEY = "DOC123"; // Key used to register a new doctor account
        this.Patients = new ArrayList<>(); // Runtime array of all patients
        this.Doctors = new ArrayList<>(); // Runtime array of all doctors
    }
    
    /**
    * Register function - responsible for doctor or patient registration
    * Function receives user input and stores it to: "data/users/doctors/username.txt" or "data/users/patients/username.txt"
    * @param name - String value from a text field containing the name of the user.
    * @param surname - String value from a text field containing the surname of the user.
    * @param username - String value from a text field containing the username of the user.
    * @param pass1 - String value from a text field containing the first password of the user.
    * @param pass2 - String value from a text field containing the repeated password of the user.
    * @param Age - String value from a text field containing the age of the user.
    * @param Insurance - String value from a text field containing the insurance type of the user.
    * @param speciality - String value from a text field containing the specialty of the doctor (only if registering as a doctor).
    * @param isDoctor - Boolean value from a check box field containing the true/false value if the user is registering as a doctor.
    * @param docKey - String value from a text field containing the doctor key used for doctor registration.
    * @throws java.io.IOException
    * @return Returns String feedback to GUI guiding the registration process.
    */ 
    public String Register(String name, String surname, String username, String pass1, String pass2, String Age, String Insurance, String speciality, boolean isDoctor, String docKey) throws IOException {
        if(!name.equals("") && !surname.equals("") && !username.equals("") && !pass1.equals("") && !pass2.equals("")) { // Checking weather the input boxes are not empty
            if(pass1.equals(pass2)) { // Checking if both entered passwords are equal
                if(isDoctor) { // Checking if user is registering as a doctor
                    if(docKey.equals(getDOCTOR_KEY())&& !speciality.equals("")) { // Checking if the doctor key value is allright
                        String id = generateId(true); // Generating a new id for the user based on last id+1
                        File file = new File("data/users/doctors/"+username+".txt");
                        if(file.createNewFile()) {
                            try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
                                writer.println(id); writer.println(pass1); writer.println(name); writer.println(surname);writer.println(speciality); writer.close();
                            }
                            return "Success! A new doctor account has been created.";
                        } else return "Error! Doctor with the same username already exists, please try again.";
                    } else return "Error! Incorrect doctor key, please try again.";
                } else {
                    String id = generateId(false);
                    File file = new File("data/users/patients/"+username+".txt");
                    if(file.createNewFile()) {
                        try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
                            writer.println(id); writer.println(pass1); writer.println(name); writer.println(surname); writer.println(Age); writer.println(Insurance); writer.close();
                        }
                        return "Success! A new patient account has been created.";
                    } else return "Error! Patient with the same username already exists, please try again.";
                }
            } else return "Error! Passwords did not match, please try again."; 
        } else return "Error! Some fields were left empty, please try again.";
    }
    
    /**
    * Login function - responsible for the user login process
    * Function receives user input and compares it with a text file
    * @param username - String value from a text field containing the username
    * @param password - String value from a text field containing the password
    * @param isDoctor - Boolean value from a check box containing a true/false value to check weather user is logging in as a doctor or a patient
    * @throws java.io.FileNotFoundException 
    * @throws java.io.IOException
    * @return Returns String feedback to GUI guiding the login process.
    */ 
    public String LogIn(String username, String password, boolean isDoctor) throws FileNotFoundException, IOException {
        if(!username.equals("") && !password.equals("")) { // Checking if the text boxes are not empty
            if(isDoctor) { // Checking if user is logging in as a doctor or a patient
                File file = new File("data/users/doctors/"+username+".txt");
                if(file.exists()) {
                    FileReader _reader = new FileReader(file);
                    BufferedReader reader = new BufferedReader(_reader);
                    reader.readLine(); // Ommiting the first line of the file which is the id of the user
                    String userPass = reader.readLine();
                    if(userPass.equals(password)) // If the password is correct we log the user in
                        return "Success! You have loged in.";
                    else return "Error! Invalid password, please try again.";
                } else return "Error! Doctor with this username does not exist, please try again.";
            } else {
                File file = new File("data/users/patients/"+username+".txt");
                if(file.exists()) {
                    FileReader _reader = new FileReader(file);
                    BufferedReader reader = new BufferedReader(_reader);
                    reader.readLine();
                    String userPass = reader.readLine();
                    if(userPass.equals(password))
                        return "Success! You have loged in.";
                    else return "Error! Invalid password, please try again.";
                } else return "Error! Patient with this username does not exist, please try again.";
            }   
        } else return "Error! Please enter your username and password.";
    }
    
    /**
    * EditUser function - responsible for the user edit process
    * Function receives user input and replaces the one saved in the text file
    * @param Name - String value from a text field containing the name of the user.
    * @param Surname - String value from a text field containing the surname of the user.
    * @param username - String value from a text field containing the username of the user.
    * @param Age - String value from a text field containing the age of the user.
    * @param Insurance - String value from a text field containing the insurance type of the user.
    * @param oldPass - String value from a text field containing the users old password.
    * @param newPass1 - String value from a text field containing the users new password.
    * @param newPass2 - String value from a text field containing the users repeated new password.
    * @param isDoctor - Boolean value from a check box containing a true/false value to check weather user is logging in as a doctor or a patient
    * @throws java.io.FileNotFoundException 
    * @throws java.io.IOException
    * @return Returns String feedback to GUI guiding the edit user process.
    */ 
    public String EditUser(String username, String Name, String Surname, String Age, String Insurance, String oldPass, String newPass1, String newPass2, boolean isDoctor) throws FileNotFoundException, IOException {
        if (isDoctor) {
            File file = new File("data/users/doctors/"+username+".txt");
            FileReader _reader = new FileReader(file);
            BufferedReader reader = new BufferedReader(_reader);
            String id = reader.readLine();
            String userPass = reader.readLine();
            if (oldPass.equals(userPass)) {
                if (newPass1.equals(newPass2)) {
                    try (PrintWriter writer = new PrintWriter(file, "UTF-8")) { // Replacing the whole text file with new data
                        writer.println(id);
                        if(!newPass1.equals("")) writer.println(newPass1); else writer.println(oldPass); writer.println(Name); writer.println(Surname); writer.close();
                    }
                    for (Doctor d: getDoctors()) {
                        if(d.getUsername().equals(username)) { // Looping through doctors array until we find one with the same username and replacing the whole class with new data
                            d.setName(Name);
                            d.setSurname(Surname);
                            if(!newPass1.equals(""))
                                d.setPassword(newPass1);
                            else
                                d.setPassword(oldPass);
                        }
                    }
                    return "Success! Your data has been changed.";
                } else return "Error! Your new passwords do not match, please try again.";
            } else return "Error! Your current password is invalid, please try again.";
        }
        else { 
            File file = new File("data/users/patients/"+username+".txt");
            FileReader _reader = new FileReader(file);
            BufferedReader reader = new BufferedReader(_reader);
            String id = reader.readLine();
            String userPass = reader.readLine();
            if (oldPass.equals(userPass)) {
                if (newPass1.equals(newPass2)) {
                    try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
                        writer.println(id);if(!newPass1.equals("")) writer.println(newPass1); else writer.println(oldPass); writer.println(Name); writer.println(Surname); writer.println(Age); writer.println(Insurance); writer.close();
                    }
                    for (Patient p: getPatients()) {
                        if(p.getUsername().equals(username)) {
                            p.setName(Name);
                            p.setSurname(Surname);
                            if(!newPass1.equals(""))
                            p.setPassword(newPass1);
                            else p.setPassword(oldPass);
                            p.setAge(Age);
                            p.setInsurance(Insurance);
                        }
                    }
                    return "Success! Your data has been changed.";
                } else return "Error! Your new passwords do not match, please try again.";
            } else return "Error! Your current password is invalid, please try again.";
        }
    }
    
    /**
    * LoadUsers function - responsible for loading all user data from text files to runtime array list
    * @throws java.io.FileNotFoundException 
    * @throws java.io.IOException
    */ 
    public void LoadUsers() throws FileNotFoundException, IOException {
        getDoctors().clear();
        getPatients().clear();
        File file = new File("data/users/doctors/");
        File[] files = file.listFiles();
        for(File f: files) { // Looping throug all text files, creating new classes for each user and storing them to an array list
            FileReader _reader = new FileReader(f);
            BufferedReader reader = new BufferedReader(_reader);
            getDoctors().add(new Doctor(reader.readLine(), f.getName().substring(0, f.getName().length()-4), reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine()));
        }
        
        // Reading patient accounts and storing them into an array
        file = new File("data/users/patients/");
        files = file.listFiles();
        for(File f: files) {
            FileReader _reader = new FileReader(f);
            BufferedReader reader = new BufferedReader(_reader);
            getPatients().add(new Patient(reader.readLine(), f.getName().substring(0, f.getName().length()-4), reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine()));
        }
    }
    
    /**
    * SaveSchedule function - saves doctors schedule to a text file
    * @param username - String value from a text field containing the doctors username.
    * @param days - integer array containing the doctors available days.
    * @param hours - integer array containing the doctors available hours.
    * @throws java.io.FileNotFoundException 
    * @throws java.io.IOException
    */ 
    public void SaveSchedule(String username, int[] days, int[] hours) throws FileNotFoundException, IOException {
        File file = new File("data/users/schedules/"+username+".txt");
        try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
            writer.println(Arrays.toString(days)); writer.println(Arrays.toString(hours)); writer.close();
        }
    }

    /**
    * DoesScheduleExist function - checks if a schedule file exists
    * @param username - String value from a text field containing the doctors username.
    * @return - boolean value weather the file exists or not. 
    */ 
    public boolean DoesScheduleExist(String username) {
        File file = new File("data/users/schedules/"+username+".txt");
        return file.exists();
    }

    /**
    * DoesScheduleExist function - checks if a schedule file exists
    * @param username - String value from a text field containing the doctors username.
    * @throws java.io.FileNotFoundException 
    * @return integer array containing doctors available days.
    */ 
    public int[] LoadScheduleDays(String username) throws FileNotFoundException, IOException {
        File file = new File("data/users/schedules/"+username+".txt");
        FileReader _reader = new FileReader(file);
        BufferedReader reader = new BufferedReader(_reader);
        String days = reader.readLine();
        String hours = reader.readLine(); 
        String[] parts = days.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").split(","); // Replacing all array-like values to leave just plain integers
        int[] daysint = new int[parts.length];
        for (int i=0; i < parts.length; i++) 
            daysint[i] = Integer.parseInt(parts[i]); // Converting string to integer and adding it to the final array
        return daysint;
    }
    
    /**
    * DoesScheduleExist function - checks if a schedule file exists
    * @param username - String value from a text field containing the doctors username.
    * @throws java.io.FileNotFoundException 
    * @return integer array containing doctors available hours.
    */ 
    public int[] LoadScheduleHours(String username) throws FileNotFoundException, IOException {
        File file = new File("data/users/schedules/"+username+".txt");
        FileReader _reader = new FileReader(file);
        BufferedReader reader = new BufferedReader(_reader);
        String days = reader.readLine();
        String hours = reader.readLine();
        String[] parts = hours.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").split(",");  
        int[] hoursint = new int[parts.length];
        for (int i=0; i < parts.length; i++) 
            hoursint[i] = Integer.parseInt(parts[i]);
        return hoursint;
    }
    
    /**
    * getDoctor function - returns the whole doctor class from a given id
     * @param id - receives users id
     * @return - returns whole doctor class with all its data 
    */ 
    public Doctor getDoctor(int id) {
        Doctor returnD = null;
        
        for (Doctor d: getDoctors()) {
            if(d.getId().equals(Integer.toString(id))) {
                returnD = d;
            }
        }
        return returnD;
    } 
    
    /**
    * getPatient function - returns the whole patient class from a given id
    * @param id - receives users id
    * @return - returns whole patient class with all its data 
    */ 
    public Patient getPatient(int id) {
        Patient returnP = null;
        
        for (Patient p: getPatients()) {
            if(p.getId().equals(Integer.toString(id))) {
                returnP = p;
            }
        }
        return returnP;
    } 

    /**
     * @return the DOCTOR_KEY
     */
    public String getDOCTOR_KEY() {
        return DOCTOR_KEY;
    }

    /**
     * @param DOCTOR_KEY the DOCTOR_KEY to set
     */
    public void setDOCTOR_KEY(String DOCTOR_KEY) {
        this.DOCTOR_KEY = DOCTOR_KEY;
    }

    /**
     * @return the Doctors
     */
    public ArrayList<Doctor> getDoctors() {
        return Doctors;
    }

    /**
     * @param Doctors the Doctors to set
     */
    public void setDoctors(ArrayList<Doctor> Doctors) {
        this.Doctors = Doctors;
    }

    /**
     * @return the Patients
     */
    public ArrayList<Patient> getPatients() {
        return Patients;
    }

    /**
     * @param Patients the Patients to set
     */
    public void setPatients(ArrayList<Patient> Patients) {
        this.Patients = Patients;
    }
    
    /**
    * generateId function - generates a unique id when registering a new user
    * @param isDoctor - Boolean value from a check box field containing the true/false value if the user is registering as a doctor.
    * @throws java.io.FileNotFoundException 
    * @return id - returns a new unique id
    */ 
    private String generateId(boolean isDoctor) throws FileNotFoundException, IOException {
        String id;
        File folder;
        int currentId = 0;
        if (isDoctor) { // Check weather its a doctor or not
            id = "1"; // We could differentiate the id, but we chose not to.
            folder = new File("data/users/doctors/");
        }
        else {
            id = "1"; // We could differentiate the id, but we chose not to.
            folder = new File("data/users/patients/");
        }
        
        File[] listOfFiles = folder.listFiles(); // Storing all files from folder to an array
        for (File file : listOfFiles) { // Looping through all files
            if (file.isFile()) { // If the file exists
                FileReader _reader = new FileReader(file);
                BufferedReader reader = new BufferedReader(_reader);
                String readId = reader.readLine(); // Read the first line to check the id
                if(currentId < Integer.parseInt(readId.substring(0, readId.length()))) // If the current id is smaller than the id that we are reading
                    currentId = Integer.parseInt(readId.substring(0, readId.length())); // Replace the current id with the bigger one
                
                if(isDoctor) // If we would want to differentiate the doctor id from patient we could ad a letter to the string, however we chose not to.
                    id =  Integer.toString(currentId+1);
                else 
                    id =  Integer.toString(currentId+1);  
            } 
        }
        return id;
    }
}