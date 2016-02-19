package com.example.lo_856348.contactsapp;

import android.app.Activity;

/**
 * Created by lo_856348 on 2/9/2016.
 */
public class Contact extends Activity {

    //These variables store the properties of the contact
    int _id;                 //store a primary key for database
    String _name;
    String _phone_number;

    //Empty Constructor: do nothing default
    public Contact() {
    }

    //Constructor to set a contact's id, name and phone number
    public Contact(int id, String name, String phone_number) {
        _id = id;
        _name = name;
        _phone_number = phone_number;
    }

    //method to get the ID of the contact
    public int get_id() {
        return _id;
    }

    //method to set the ID of the contact
    public void set_id(int _id) {
        this._id = _id;
    }

    //method to get the name of the contact
    public String get_name() {
        return _name;
    }

    //method to set the name of the contact
    public void set_name(String _name) {
        this._name = _name;
    }

    //method to get the phone number of the contact
    public String get_phone_number() {
        return _phone_number;
    }

    //method to set the phone number of the contact
    public void set_phone_number(String _phone_number) {
        this._phone_number = _phone_number;
    }


}
