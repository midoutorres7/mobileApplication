package com.playstore.sqlite1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class dbContact extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE = "myPhone_bd";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String TABLE_CONTACTS = "contacts";

    public dbContact(@Nullable Context context) {
        super(context,DATABASE , null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String createTable = "create table "+TABLE_CONTACTS+"("+KEY_ID+" integer primary key , "+KEY_NAME+" varchar(30) , "+KEY_PHONE+" integer)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteTable = "DROP table "+TABLE_CONTACTS;
        db.execSQL(deleteTable);

        onCreate(db);
    }



//ajouter Contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE, contact.getPhone());

        db.insert(TABLE_CONTACTS, null, values);
    }



//afficher tout les contacts
    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> contacts = new ArrayList<>();
        String select_query = "select * from "+TABLE_CONTACTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query , null);     //le 2eme champs est de la condition

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                int phone = cursor.getInt(cursor.getColumnIndex(KEY_PHONE));

                Contact contact = new Contact(id ,name ,phone);
                contacts.add(contact);
            }while(cursor.moveToNext());
        }

    return contacts;
    }



//get Contact By id
    public Contact getContactById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String  query = "select * from "+TABLE_CONTACTS+" where id="+id ;
        Cursor cursor = db.rawQuery(query , null);
    //create a contact
        Contact contact = null;
    //get contact information
        if(cursor.moveToFirst()){
            int contactId = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            int phone = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));

        //set database contact info
            contact = new Contact(contactId, name , phone);
        }
        
        return contact;
    }



//2eme methode to getContact by id
    public Contact getContactByID2(int id){
        Contact contact = null;
        SQLiteDatabase db = this.getReadableDatabase();

        //nomde table   //columns   //where   //?=String[]{....}  //groupBy  //having  //orderBy

        Cursor cursor = db.query(TABLE_CONTACTS , new String[]{"id","name","phone"} , "id=?" ,
                                new String[]{String.valueOf(id)} , null,null,null );

        if(cursor.moveToFirst()){
            int idContact = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            int phone = cursor.getInt(cursor.getColumnIndex(KEY_PHONE));

            contact = new Contact(idContact,name,phone);
        }

        return contact;
    }



//Update a contact
    public void contactUpdate(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,contact.getName());
        values.put(KEY_PHONE,contact.getPhone());

        db.update(TABLE_CONTACTS , values , "id=?" , new String[]{String.valueOf(contact.getId())});
    }



//Delete a contact
    public void contactDelete(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CONTACTS, "id=?", new String[]{String.valueOf(id)});
    }

}
