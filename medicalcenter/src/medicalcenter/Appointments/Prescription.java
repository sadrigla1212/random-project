/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalcenter.Appointments;

/**
 *
 * @author Algirdas
 */
public class Prescription {
    
    private String expireDate;
    private String payment;
    private String name;

    /**
     * @return the expiration date of a prescription
     */
    public String getExpireDate() {
        return expireDate;
    }

    /**
     * Sets expiration date to the given value
     * @param expireDate the new expiration date
     */
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * @return the payment value
     */
    public String getPayment() {
        return payment;
    }

    /**
     * Sets payment to the given value
     * @param payment the new payment size
     */
    public void setPayment(String payment) {
        this.payment = payment;
    }

    /**
     * @return the name of a prescription
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new name to the prescription
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
}
