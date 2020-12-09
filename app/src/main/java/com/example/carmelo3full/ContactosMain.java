package com.example.carmelo3full;

import java.util.Date;
import java.util.List;

public class ContactosMain {
    private String ContactName;
    private List<String> ContactNumber;
    private Date birthday;
    private int calls;

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public List<String> getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(List<String> contactNumber) {
        ContactNumber = contactNumber;
    }

    public void setContactCalls(){
        this.calls=0;
    }



}
