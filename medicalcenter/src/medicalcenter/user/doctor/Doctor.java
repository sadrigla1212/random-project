package medicalcenter.user.doctor;

import medicalcenter.user.User;

/**
 * Patient - Doctor sub class with additional variables, their getters and setters.
 * @author Linas Ramanauskas, IT-2s
 */

public class Doctor extends User {
    private String speciality;
    
    /**
    * Doctor constructor - used to create a new doctor
    * @param id - user id (from parent class)
    * @param username - user username (from parent class)
    * @param password - user password (from parent class)
    * @param surname - user surname (from parent class)
    * @param name - user name (from parent class)
    * @param speciality - doctors specialty
    */ 
    public Doctor(String id, String username, String password, String name, String surname, String speciality) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
    } 

    /**
     * @return the speciality
     */
    public String getSpeciality() {
        return speciality;
    }

    /**
     * @param speciality the speciality to set
     */
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}