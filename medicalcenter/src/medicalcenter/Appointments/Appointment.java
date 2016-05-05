
package medicalcenter.Appointments;

import java.util.ArrayList;

/**
 *
 * @author Algirdas
 */
public class Appointment {
    
    private int id;
    private int doctorId;
    private int clientId;
    private boolean visited;
    private String reason;
    private String notes;
    private String diagnosis;
    private String time;
    private String date;
    private String Payment;
    private int is = 0;
    private ArrayList<Prescription> prescriptions = new ArrayList<Prescription>();
    
    /**
     *
     * @param app
     */
    public void copyAppointment (Appointment app){
        
        this.id = app.id;
        this.doctorId = app.doctorId;
        this.clientId = app.clientId;
        this.visited = app.visited;
        this.reason = app.reason;
        this.notes = app.notes;
        this.diagnosis = app.diagnosis;
        this.time = app.time;
        this.date = app.date;
        
    }
    
    /**
     * Returns the cost of the appointment as a string
     * @return cost of the appointment
     */
    public String getPayment() {
        return Payment;
    }

    /**
     * Sets appointment cost to the given value
     * @param Payment
     */
    public void setPayment(String Payment) {
        this.Payment = Payment;
    }

    /**
     * Marks appointment if it's currently used by a doctor
     * @param is
     */
    public void setisDoc (int is){
        this.is = is;
    }
    
    /**
     * Return the list of all prescriptions added to an appointment
     * @return a list of prescriptions
     */
    public ArrayList<Prescription> getPrescriptions(){
        
        return prescriptions;
        
    }
    
    /**
     * Adds given prescription to an appointment
     * @param pres prescription to add
     */
    public void addPrescription (Prescription pres){
        
        prescriptions.add(pres);
        
    }
    
    /**
     * Adds a prescription to an appointment using given data
     * @param date expiration date of a prescription
     * @param pay prescription cost
     * @param name prescription name
     */
    public void addPrescription (String date, String pay, String name){
        
        Prescription pres = new Prescription();
        pres.setExpireDate(date);
        pres.setPayment(pay);
        prescriptions.add(pres);
        
    }
    
    /**
     * Returns the id of an appointment
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets an id of an appointment
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the id of assigned doctor
     * @return the doctorId
     */
    public int getDoctorId() {
        return doctorId;
    }

    /**
     * Sets the id of assigned doctor
     * @param doctorId the doctorId to set
     */
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * Returns the client, that created an appointment, id
     * @return the clientId
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Sets client id
     * @param clientId the clientId to set
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Returns the reason of an appointment
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the reason of an appointment
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Returns the notes of the doctor
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the doctor's notes
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Returns the diagnosis/results of an appointment
     * @return the result
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Sets the results of an appointment
     * @param result the result to set
     */
    public void setDiagnosis(String result) {
        this.diagnosis = result;
    }
    
    @Override
   public String toString(){
        
        String visitedd;
        String data = null;
        
        if (isVisited()==true){
        visitedd = "Visited";
        }
        else {
            visitedd = "Not Visited";}
        
        
            if (is==0){
             data = getId() + "|Doc ID:  " +  getDoctorId() + "| " + visitedd + "| " + getReason();
        }
            else{
               data = getId() + "|Client ID:  " +  getClientId() + "| " + visitedd + "| " + getReason(); 
            }
       
        
       
        
        return data;
        
    }
    
    

    /**
     * Checks whether a client visited a doctor
     * @return the visited
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Sets the appointment state to visited/not visited
     * @param visited the visited to set
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Returns registered time of an appointment
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the time of an appointment
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Returns registered date of an appointment
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of an appointment
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
}
