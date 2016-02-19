package com.example.lo_856348.contactsapp;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    //these variables hold all the information from the database
    List<Contact> contacts = new ArrayList<>();
    public DatabaseHandler dbh = new DatabaseHandler(this);
    int currID;

    //onCreate sets up the GUI and puts in data from the database into the fields if data exists
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currID = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts = dbh.getAllContacts();
        EditText updateName = (EditText) findViewById(R.id.contact_edit);
        EditText updatePhone = (EditText) findViewById(R.id.pho_no_edit);

        if (contacts.size() != 0) {
            updateName.setText("" + contacts.get(currID).get_name());
            updatePhone.setText("" + contacts.get(currID).get_phone_number());
        }
        updateID();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /* Scrolls left through the contacts and updates the fields. If the current contact is the first
     * one, it will go to the last contact
     */
    public void leftArrow(View v) {
        if (contacts.size() != 0) {
            if (currID == 0) {
                if (contacts.size() != 0) {
                }
            } else {
                currID -= 1;
            }
            EditText updateName = (EditText) findViewById(R.id.contact_edit);
            EditText updatePhone = (EditText) findViewById(R.id.pho_no_edit);

            updateName.setText("" + contacts.get(currID).get_name());
            updatePhone.setText("" + contacts.get(currID).get_phone_number());

            updateID();
        }
    }

    /* Scrolls right through the contacts and updates the fields. If the current contact is the last
     * one, it will go to the first contact
     */
    public void rightArrow(View v) {
        if (contacts.size() != 0) {
            if (currID == (contacts.size() - 1)) {
            } else {
                if (contacts.size() != 0) {
                    currID += 1;
                }
            }
            EditText updateName = (EditText) findViewById(R.id.contact_edit);
            EditText updatePhone = (EditText) findViewById(R.id.pho_no_edit);

            updateName.setText("" + contacts.get(currID).get_name());
            updatePhone.setText("" + contacts.get(currID).get_phone_number());

            updateID();
        }
    }

    //Deletes the current contact from the database and updates the fields to show the next available contact
    public void deleteBtn(View v) {
        if (contacts.size() == 1) {
            dbh.deleteContact(contacts.get(currID));

            contacts = dbh.getAllContacts();
            EditText updateName = (EditText) findViewById(R.id.contact_edit);
            EditText updatePhone = (EditText) findViewById(R.id.pho_no_edit);
            if ((currID == contacts.size()) && (contacts.size() != 0)) {
                currID--;
            }
            updateName.setText("");
            updatePhone.setText("");
        } else if (contacts.size() != 0) {
            dbh.deleteContact(contacts.get(currID));

            contacts = dbh.getAllContacts();
            EditText updateName = (EditText) findViewById(R.id.contact_edit);
            EditText updatePhone = (EditText) findViewById(R.id.pho_no_edit);
            if ((currID == contacts.size()) && (contacts.size() != 0)) {
                currID--;
            }
            updateName.setText("" + contacts.get(currID).get_name());
            updatePhone.setText("" + contacts.get(currID).get_phone_number());

            updateID();
        }
    }

    //Updates the current contact with the changed values from the fields
    public void updateBtn(View v) {
        if (contacts.size() != 0) {
            EditText updateName = (EditText) findViewById(R.id.contact_edit);
            EditText updatePhone = (EditText) findViewById(R.id.pho_no_edit);

            Contact contact = new Contact(contacts.get(currID).get_id(), updateName.getText() + "", updatePhone.getText() + "");

            dbh.updateContact(contact);

            contacts = dbh.getAllContacts();

            updateID();
        }
    }

    /* Creates a new contact with the information given in the bottom fields and adds it into the
     * database
     */
    public void addBtn(View v) {
        EditText addName = (EditText) findViewById(R.id.new_contact_edit);
        EditText addPhone = (EditText) findViewById(R.id.new_pho_no_edit);

        if ((addName.getText() + "").equals("") && (addPhone.getText() + "").equals("")) {
        } else {
            Contact contact = new Contact(contacts.size() + 1, addName.getText() + "", addPhone.getText() + "");

            dbh.addContact(contact);

            contacts = dbh.getAllContacts();
            EditText updateName = (EditText) findViewById(R.id.contact_edit);
            EditText updatePhone = (EditText) findViewById(R.id.pho_no_edit);

            updateName.setText("" + contacts.get(currID).get_name());
            updatePhone.setText("" + contacts.get(currID).get_phone_number());
            addName.setText("");
            addPhone.setText("");

            updateID();
        }
    }

    //Will update the ID at the top right whenever any action is done simply for testing purposes
    public void updateID() {
        TextView id = (TextView) findViewById(R.id.currId);
        id.setText("" + currID);
    }
}
