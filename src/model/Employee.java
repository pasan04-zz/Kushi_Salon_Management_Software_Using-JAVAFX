/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Employee {
    private final StringProperty eid;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty NIC;
    private final StringProperty email;
    private final StringProperty phone;
    private final StringProperty address;
    private final StringProperty gender;


    public Employee(String eid,String firstName,String lastName,String NIC,String email,String phone,String address,String gender) {
        
        this.eid = new SimpleStringProperty(eid);
        this.firstName = new SimpleStringProperty(firstName);//firstName
        this.lastName = new SimpleStringProperty(lastName);//lastName
        this.NIC = new SimpleStringProperty(NIC);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
        this.gender = new SimpleStringProperty(gender);

    }

    public String getEid() {
        return eid.get();
    }
    public void setEid(String eid){
        this.eid.set(eid);
    }
    public StringProperty eidProperty(){
        return eid;
    }
    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String FirstName){
        this.firstName.set(FirstName);
    }
    public StringProperty firstNameProperty(){
        return firstName;
    }
    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String LastName){
        this.firstName.set(LastName);
    }
    public StringProperty lastNameProperty(){
        return lastName;
    }
    public String getNIC() {
        return NIC.get();
    }
    public void setNIC(String nic){
        this.NIC.set(nic);
    }
    public StringProperty NICProperty(){
        return NIC;
    }
    
    public String getEmail() {
        return email.get();
    }
     public void setEmail(String Email){
        this.email.set(Email);
    }
    public StringProperty EmailProperty(){
        return email;
    }
    public String getAddress() {
        return address.get();
    }
     public void setAddress(String Address){
        this.address.set(Address);
    }
    public StringProperty AddressProperty(){
        return address;
    }

    public String getPhone() {
        return phone.get();
    }
    public void setPhone(String Phone){
        this.phone.set(Phone);
    }
    public StringProperty phoneProperty(){
        return phone;
    }
    
    public String getGender() {
        return gender.get();
    }
    public void setGender(String Gender){
        this.gender.set(Gender);
    }
    public StringProperty genderProperty(){
        return gender;
    }
    
   /* public Object getHireDate() {
        return hireDate.get();
    }
    public void setHireDate(Date HireDate){
        this.hireDate.set(HireDate);
    }
    public SimpleObjectProperty<Date> hireDateProperty(){
        return hireDate;
    }
   */
}
