/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author A C E R
 */
public class ModelTable {
    
    
    String dealerId,cmp_name,cmp_address,cmp_email, prod_type, cmp_phno,cmp_web,agt_name,agt_phno,date;

    public ModelTable(String dealerId, String cmp_name, String cmp_address, String cmp_email, String prod_type, String cmp_phno, String agt_name, String agt_phno, String date) {
       
        this.dealerId = dealerId;  
        this.cmp_address = cmp_address;
        this.cmp_name = cmp_name;
        this.cmp_email = cmp_email;
        this.prod_type = prod_type;
        this.cmp_phno = cmp_phno;
        this.agt_name = agt_name;
        this.agt_phno = agt_phno;
        this.date = date;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getCmp_name() {
        return cmp_name;
    }

    public void setCmp_name(String cmp_name) {
        this.cmp_name = cmp_name;
    }
    

    public String getCmp_address() {
        return cmp_address;
    }

    public void setCmp_address(String cmp_address) {
        this.cmp_address = cmp_address;
    }

    public String getCmp_email() {
        return cmp_email;
    }
    
    public void setCmp_email(String cmp_email) {
        this.cmp_email = cmp_email;
    }

    public String getProd_type() {
        return prod_type;
    }

    public void setProd_type(String prod_type) {
        this.prod_type = prod_type;
    }
   
    public String getCmp_phno() {
        return cmp_phno;
    }

    public void setCmp_phno(String cmp_phno) {
        this.cmp_phno = cmp_phno;
    }

    public String getAgt_name() {
        return agt_name;
    }

    public void setAgt_name(String agt_name) {
        this.agt_name = agt_name;
    }

    public String getAgt_phno() {
        return agt_phno;
    }

    public void setAgt_phno(String agt_phno) {
        this.agt_phno = agt_phno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
