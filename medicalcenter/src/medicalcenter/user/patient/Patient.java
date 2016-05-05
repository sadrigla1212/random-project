package medicalcenter.user.patient;

import medicalcenter.user.User;

/**
 * Patient - Patient sub class with additional variables, their getters and setters.
 * @author Linas Ramanauskas, IT-2s
 */

public class Patient extends User {
    private String age, insurance;
    
    /**
    * Patient constructor - used to create a new patient
    * @param id - user id (from parent class)
    * @param username - user username (from parent class)
    * @param password - user password (from parent class)
    * @param surname - user surname (from parent class)
    * @param name - user name (from parent class)
    * @param insurance - patient patient insurance type
    * @param age - patient age
    */ 
    public Patient(String id, String username, String password, String name, String surname, String age, String insurance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.insurance = insurance;
    }    

    /**
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return the insurance
     */
    public String getInsurance() {
        return insurance;
    }

    /**
     * @param insurance the insurance to set
     */
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }
}