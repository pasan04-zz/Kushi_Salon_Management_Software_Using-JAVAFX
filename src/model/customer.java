/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//DONE
public class customer {
    
    private String NIC;
    private String Firstname;
    private String Lastname;
    private String Address;
    private String Phonenumber;
    private String Age;

    public customer(String NIC, String firtname, String lastname, String address, String phoneNumber, String age) {
        this.NIC = NIC;
        this.Firstname = firtname;
        this.Lastname = lastname;
        this.Address = address;
        this.Phonenumber = phoneNumber;
        this.Age = age;
    }
    
    
    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public void setFirstname(String firtname) {
        this.Firstname = firtname;
    }

    public void setLastname(String lastname) {
        this.Lastname = lastname;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.Phonenumber = phoneNumber;
    }

    public void setAge(String age) {
        this.Age = age;
    }

    public String getNIC() {
        return NIC;
    }

    public String getFirstName() {
        return Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhoneNumber() {
        return Phonenumber;
    }

    public String getAge() {
        return Age;
    }
    
    
    
}
