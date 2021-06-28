package com.playstore.sqlite1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;

public class dbContact extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE = "myPhone_bd";


    //TABLE1
    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_SEX = "sexe";
    private static final String KEY_AGE = "age";
    private static final String KEY_POIDS = "poids";
    private static final String KEY_CREATIN = "creatinine";
    private static final String KEY_BILI = "bilirubine";
    private static final String KEY_TGOTGA = "tgotga";

    //TABLE2
    private static final String TABLE_MEDICAM = "medicament";
    private static final String KEY_NAME2 = "name";
    private static final String KEY_DOSE = "dose";
    private static final String KEY_CLAIRANCE_MIN = "clairanceMin";
    private static final String KEY_CLAIRANCE_MAX = "clairanceMax";
    private static final String KEY_BILI_MIN = "bilirubineMin";
    private static final String KEY_BILI_MAX = "bilirubineMax";
    private static final String KEY_TGOTGA_MIN = "tgotgaMin";
    private static final String KEY_TGOTGA_MAX = "tgotgaMax";

    //TABLE3
    private static final String TABLE_USER = "user";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    //TABLE4
    private static final String TABLE_CONSULTATION = "consultation";
    private static final String KEY_NAME_PATIENT = "namePatient";
    private static final String KEY_NAME_MEDICAM = "nameMedicam";
    private static final String KEY_DOSE_ADMIN = "doseAdmin";


    public dbContact(@Nullable Context context) {
        super(context,DATABASE , null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table "+TABLE_CONTACTS+"("+KEY_ID+" integer primary key , "+KEY_NAME+" varchar(30) , "+KEY_PHONE+" integer , "+KEY_SEX+" varchar(10) , "+KEY_AGE+" integer, "+KEY_POIDS+" real, "+KEY_CREATIN+" real, "+KEY_BILI+" real, "+KEY_TGOTGA+" real)";
        String createTable2 = "create table "+TABLE_MEDICAM+"("+KEY_ID+" integer primary key , "+KEY_NAME2+" varchar(30) , "+KEY_DOSE+" real , "+KEY_CLAIRANCE_MIN+" real , "+KEY_CLAIRANCE_MAX+" real , "+KEY_BILI_MIN+" real , "+KEY_BILI_MAX+" real , "+KEY_TGOTGA_MIN+" real , "+KEY_TGOTGA_MAX+" real)";
        String createTable3 = "create table "+TABLE_USER+"("+KEY_ID+" integer primary key , "+KEY_NAME+" varchar(30) , "+KEY_EMAIL+" varchar(30) , "+KEY_PASSWORD+" varchar(30))";
        String createTable4 = "create table "+TABLE_CONSULTATION+"("+KEY_ID+" integer primary key , "+KEY_NAME_PATIENT+" varchar(30) , "+KEY_NAME_MEDICAM+" varchar(30) , "+KEY_DOSE_ADMIN+" real)";

        db.execSQL(createTable);
        db.execSQL(createTable2);
        db.execSQL(createTable3);
        db.execSQL(createTable4);

        db.execSQL("INSERT INTO "+ TABLE_USER  + " VALUES(1 , 'mohamed', 'mohamed@gmail.com' , '123');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteTable = "DROP table "+TABLE_CONTACTS;
        String deleteTable2 = "DROP table "+TABLE_MEDICAM;
        String deleteTable3 = "DROP table "+TABLE_USER;
        String deleteTable4 = "DROP table "+TABLE_CONSULTATION;

        db.execSQL(deleteTable);
        db.execSQL(deleteTable2);
        db.execSQL(deleteTable3);
        db.execSQL(deleteTable4);
        onCreate(db);
    }



    //****************************************************************************//
//ajouter Contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE, contact.getPhone());
        values.put(KEY_SEX, contact.getSex());
        values.put(KEY_AGE, contact.getAge());
        values.put(KEY_POIDS, contact.getPoid());
        values.put(KEY_CREATIN, contact.getCreatinine());
        values.put(KEY_BILI, contact.getBili());
        values.put(KEY_TGOTGA, contact.getTgoTga());

        db.insert(TABLE_CONTACTS, null, values);
    }


//ajouter Medicam
    public void addMedicam(Medicam medicam) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME2, medicam.getName());
        values.put(KEY_DOSE, medicam.getDoseComp());
        values.put(KEY_CLAIRANCE_MIN, medicam.getCLAIRANCE_MIN());
        values.put(KEY_CLAIRANCE_MAX, medicam.getCLAIRANCE_MAX());
        values.put(KEY_BILI_MIN, medicam.getKEY_BILI_MIN());
        values.put(KEY_BILI_MAX, medicam.getKEY_BILI_MAX());
        values.put(KEY_TGOTGA_MIN, medicam.getKEY_TGOTGA_MIN());
        values.put(KEY_TGOTGA_MAX, medicam.getKEY_TGOTGA_MAX());

        db.insert(TABLE_MEDICAM, null, values);
    }

    //add Consultation
    public void addConsultation(Consultation consultation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME_PATIENT, consultation.getContact().toString());
        values.put(KEY_NAME_MEDICAM, consultation.getMedicam().toString());
        values.put(KEY_DOSE_ADMIN, consultation.getDoseAdmin());

        db.insert(TABLE_CONSULTATION, null, values);
    }



//***********************************************************************************//

//afficher tout les contacts
    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> contacts = new ArrayList<>();
        String select_query = "select * from "+TABLE_CONTACTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query , null);     //le 2eme champs est de la condition

        if(cursor.moveToFirst()){
            do{
                int contactId = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                int phone = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
                String sex = cursor.getString(cursor.getColumnIndex(KEY_SEX));
                int age = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_AGE)));
                double poids = cursor.getDouble(cursor.getColumnIndex(KEY_POIDS));
                double creatinine = cursor.getDouble(cursor.getColumnIndex(KEY_CREATIN));
                double bili = cursor.getDouble(cursor.getColumnIndex(KEY_BILI));
                double tgo = cursor.getDouble(cursor.getColumnIndex(KEY_TGOTGA));

                Contact contact = new Contact(contactId, name, phone,sex , age, poids, creatinine, bili, tgo);
                contacts.add(contact);
            }while(cursor.moveToNext());
        }

    return contacts;
    }

//afficher tout les Medicaments
    public ArrayList<Medicam> getAllMedicaments(){
        ArrayList<Medicam> medicamList = new ArrayList<>();
        String select_query = "select * from "+TABLE_MEDICAM;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query , null);     //le 2eme champs est de la condition

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME2));
                double dose = cursor.getDouble(cursor.getColumnIndex(KEY_DOSE));
                double clairanceMin = cursor.getDouble(cursor.getColumnIndex(KEY_CLAIRANCE_MIN));
                double clairanceMax = cursor.getDouble(cursor.getColumnIndex(KEY_CLAIRANCE_MAX));
                double biliMin = cursor.getDouble(cursor.getColumnIndex(KEY_BILI_MIN));
                double biliMax = cursor.getDouble(cursor.getColumnIndex(KEY_BILI_MAX));
                double tgotgaMin = cursor.getDouble(cursor.getColumnIndex(KEY_TGOTGA_MIN));
                double tgotgaMax = cursor.getDouble(cursor.getColumnIndex(KEY_TGOTGA_MAX));

                Medicam medicam = new Medicam(id,name,dose,clairanceMin,clairanceMax,biliMin,biliMax,tgotgaMin,tgotgaMax);

                medicamList.add(medicam);
            }while(cursor.moveToNext());
        }

        return medicamList;
    }


    //afficher tout les consultation
    public ArrayList<Consultation> getAllConsultations(){
        ArrayList<Consultation> consultations = new ArrayList<>();
        String select_query = "select * from "+TABLE_CONSULTATION;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query , null);     //le 2eme champs est de la condition

        if(cursor.moveToFirst()){
            do{
                int consultID = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                Contact contact = new Contact();
                contact.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_PATIENT))) ;
                Medicam medicam = new Medicam();
                medicam.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_MEDICAM)));
                double dose = cursor.getDouble(cursor.getColumnIndex(KEY_DOSE_ADMIN));

                Consultation consultation = new Consultation(consultID , contact , medicam , dose);
                consultations.add(consultation);
            }while(cursor.moveToNext());
        }

        return consultations;
    }


//************************************************************************************//


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
            String sex = cursor.getString(cursor.getColumnIndex(KEY_SEX));
            int age = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_AGE)));
            double poids = cursor.getDouble(cursor.getColumnIndex(KEY_POIDS));
            double creatinine = cursor.getDouble(cursor.getColumnIndex(KEY_CREATIN));
            double bili = cursor.getDouble(cursor.getColumnIndex(KEY_BILI));
            double tgo = cursor.getDouble(cursor.getColumnIndex(KEY_TGOTGA));

            contact = new Contact(contactId, name, phone,sex , age, poids, creatinine, bili, tgo);
        //set database contact info
        }
        
        return contact;
    }

//get medicament by ID
    public Medicam getMedicamByID(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from "+TABLE_MEDICAM+" where id="+id ;
        Cursor cursor = db.rawQuery(query , null);

        Medicam medicam = null;
        if(cursor.moveToFirst()){
            int medicamId = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String name = cursor.getString(cursor.getColumnIndex(KEY_NAME2));
            double dose = cursor.getDouble(cursor.getColumnIndex(KEY_DOSE));
            double clrMin = cursor.getDouble(cursor.getColumnIndex(KEY_CLAIRANCE_MIN));
            double clrMax = cursor.getDouble(cursor.getColumnIndex(KEY_CLAIRANCE_MAX));
            double bilMin = cursor.getDouble(cursor.getColumnIndex(KEY_BILI_MIN));
            double bilMax = cursor.getDouble(cursor.getColumnIndex(KEY_BILI_MAX));
            double tgMin = cursor.getDouble(cursor.getColumnIndex(KEY_TGOTGA_MIN));
            double tgMax = cursor.getDouble(cursor.getColumnIndex(KEY_TGOTGA_MAX));

            medicam = new Medicam(medicamId ,name ,dose ,clrMin ,clrMax ,bilMin ,bilMax ,tgMin ,tgMax);
        }

        return medicam;
    }

//get Consultation byId
    public Consultation getConsultationById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from "+TABLE_CONSULTATION+" where id="+id ;
        Cursor cursor = db.rawQuery(query , null);

        Consultation consultation = null;
        if(cursor.moveToFirst()){
            int consultID = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            Contact contact = new Contact();
            contact.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_PATIENT))) ;
            Medicam medicam = new Medicam();
            medicam.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_MEDICAM)));
            double dose = cursor.getDouble(cursor.getColumnIndex(KEY_DOSE_ADMIN));

            consultation = new Consultation(consultID , contact , medicam , dose);
        }

        return consultation;
    }

    //get User
    public User getUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from "+TABLE_USER+" where id=1" ;
        Cursor cursor = db.rawQuery(query , null);

        User user = null;

        if(cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            String email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL));
            String password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD));

            user = new User(name, email, password);
        }

        return user;
    }


//2eme methode to getContact by id
    public Contact getContactByID2(int id){
        Contact contact = null;
        SQLiteDatabase db = this.getReadableDatabase();

        //nomde table   //columns   //where   //?=String[]{....}  //groupBy  //having  //orderBy

        Cursor cursor = db.query(TABLE_CONTACTS , new String[]{"id","name","phone"} , "id=?" ,
                                new String[]{String.valueOf(id)} , null,null,null );

        if(cursor.moveToFirst()){
            int contactId = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            int phone = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
            String sex = cursor.getString(cursor.getColumnIndex(KEY_SEX));
            int age = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_AGE)));
            double poids = cursor.getDouble(cursor.getColumnIndex(KEY_POIDS));
            double creatinine = cursor.getDouble(cursor.getColumnIndex(KEY_CREATIN));
            double bili = cursor.getDouble(cursor.getColumnIndex(KEY_BILI));
            double tgo = cursor.getDouble(cursor.getColumnIndex(KEY_TGOTGA));

            contact = new Contact(contactId, name, phone,sex , age, poids, creatinine, bili, tgo);
        }

        return contact;
    }



//Update a contact
    public void contactUpdate(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,contact.getName());
        values.put(KEY_PHONE,contact.getPhone());
        values.put(KEY_SEX,contact.getSex());
        values.put(KEY_AGE,contact.getAge());
        values.put(KEY_POIDS,contact.getPoid());
        values.put(KEY_CREATIN,contact.getCreatinine());
        values.put(KEY_BILI,contact.getBili());
        values.put(KEY_TGOTGA,contact.getTgoTga());

        db.update(TABLE_CONTACTS , values , "id=?" , new String[]{String.valueOf(contact.getId())});
    }

//Update Medicament
    public void medicamUpdate(Medicam medicam){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME2 , medicam.getName());
        values.put(KEY_DOSE , medicam.getDoseComp());
        values.put(KEY_CLAIRANCE_MIN , medicam.getCLAIRANCE_MIN());
        values.put(KEY_CLAIRANCE_MAX , medicam.getCLAIRANCE_MAX());
        values.put(KEY_BILI_MIN , medicam.getKEY_BILI_MIN());
        values.put(KEY_BILI_MAX , medicam.getKEY_BILI_MAX());
        values.put(KEY_TGOTGA_MIN , medicam.getKEY_TGOTGA_MIN());
        values.put(KEY_TGOTGA_MAX , medicam.getKEY_TGOTGA_MAX());

        db.update(TABLE_MEDICAM , values , "id=?" , new String[]{String.valueOf(medicam.getId())});
    }

//Update a user
    public void userUpdate(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,user.getName());
        values.put(KEY_PASSWORD,user.getPassword());
        values.put(KEY_EMAIL,user.getEmail());

        db.update(TABLE_USER , values , "id=?" , new String[]{"1"});
    }


//Delete a contact
    public void contactDelete(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CONTACTS, "id=?", new String[]{String.valueOf(id)});
    }

//Delete a medicament
    public void deleteMedicam(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_MEDICAM , "id=?" , new String[]{String.valueOf(id)});
    }

//Delete a Consult
    public void deleteConsultation(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CONSULTATION , "id=?" , new String[]{String.valueOf(id)});
    }


//Search a contact
public ArrayList<Contact> search(String keyword) {
    ArrayList<Contact> contacts = null;

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_CONTACTS + " where " + KEY_NAME + " like ?",
                new String[] { "%" + keyword + "%" });
        if (cursor.moveToFirst()) {
            contacts = new ArrayList<Contact>();
            do {
                int contactId = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                int phone = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
                String sex = cursor.getString(cursor.getColumnIndex(KEY_SEX));
                int age = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_AGE)));
                double poids = cursor.getDouble(cursor.getColumnIndex(KEY_POIDS));
                double creatinine = cursor.getDouble(cursor.getColumnIndex(KEY_CREATIN));
                double bili = cursor.getDouble(cursor.getColumnIndex(KEY_BILI));
                double tgo = cursor.getDouble(cursor.getColumnIndex(KEY_TGOTGA));

                Contact contact = new Contact(contactId, name, phone,sex , age, poids, creatinine, bili, tgo);
                contacts.add(contact);
            } while (cursor.moveToNext());
        }

    return contacts;
}

//Search Medicams
    public ArrayList<Medicam> searchMedicam(String keyword) {
        ArrayList<Medicam> medicams = null;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_MEDICAM + " where " + KEY_NAME + " like ?", new String[]{"%" + keyword + "%"});
        if (cursor.moveToFirst()) {
            medicams = new ArrayList<Medicam>();
            do {
                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME2));
                double dose = cursor.getDouble(cursor.getColumnIndex(KEY_DOSE));
                double clairanceMin = cursor.getDouble(cursor.getColumnIndex(KEY_CLAIRANCE_MIN));
                double clairanceMax = cursor.getDouble(cursor.getColumnIndex(KEY_CLAIRANCE_MAX));
                double biliMin = cursor.getDouble(cursor.getColumnIndex(KEY_BILI_MIN));
                double biliMax = cursor.getDouble(cursor.getColumnIndex(KEY_BILI_MAX));
                double tgotgaMin = cursor.getDouble(cursor.getColumnIndex(KEY_TGOTGA_MIN));
                double tgotgaMax = cursor.getDouble(cursor.getColumnIndex(KEY_TGOTGA_MAX));

                Medicam medicam = new Medicam(id, name, dose, clairanceMin, clairanceMax, biliMin, biliMax, tgotgaMin, tgotgaMax);
                medicams.add(medicam);
            } while (cursor.moveToNext());
        }
            return medicams;

    }

    //Search Consult
    public ArrayList<Consultation> searchConsultation(String keyword) {
        ArrayList<Consultation> consultations = null;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_CONSULTATION + " where " + KEY_NAME_PATIENT + " like ?", new String[]{"%" + keyword + "%"});
        if(cursor.moveToFirst()){
            consultations = new ArrayList<Consultation>();
            do{
                int consultID = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                Contact contact = new Contact();
                contact.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_PATIENT))) ;
                Medicam medicam = new Medicam();
                medicam.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_MEDICAM)));
                double dose = cursor.getDouble(cursor.getColumnIndex(KEY_DOSE_ADMIN));

                Consultation consultation = new Consultation(consultID , contact , medicam , dose);
                consultations.add(consultation);
            }while(cursor.moveToNext());
        }
    return  consultations;
    }

    //number of patient
    public int countPatient(){
        int all;
        String select_query = "select * from "+TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query , null);     //le 2eme champs est de la condition
        all = cursor.getCount();
        return all;
    }

    //number of medicaments
    public int countMedicams(){
        int all;
        String select_query = "select * from "+TABLE_MEDICAM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query , null);     //le 2eme champs est de la condition
        all = cursor.getCount();
        return all;
    }

    //number of consultations
    public int countConsult(){
        int all;
        String select_query = "select * from "+TABLE_CONSULTATION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query , null);     //le 2eme champs est de la condition
        all = cursor.getCount();
        return all;
    }




//Calculate Dose
public double calculeDose(int patID , int medID){
    Medicam medicam = getMedicamByID(medID);
    Contact contact = getContactById(patID);
    double clairance = 0;
    double bili = contact.getBili();
    double tgoTga = contact.getTgoTga();
    double doseAdministrer = 0;
    //String[] descriptionTable = {"contre indique" , "la dose complete" , "50% de la dose complete"};
    ArrayList<Integer> description = new ArrayList<>();

    if(contact.getSex().equals("Homme")){
        clairance = 1.23 * contact.getPoid() * (140 - contact.getAge())/contact.getCreatinine();
    }else{
        clairance = 1.04 * contact.getPoid() * (140 - contact.getAge())/contact.getCreatinine();
    }

    if(clairance <= medicam.getCLAIRANCE_MIN()){
        //contre indique
        description.add(3);
    }else if(clairance >= medicam.getCLAIRANCE_MAX()){
        //la dose complete
        description.add(1);
    }else{
        //50% de la dose complete
        description.add(2);
    }

    if(bili <= medicam.getKEY_BILI_MIN()){
        //contre indique
        description.add(3);
    }else if(bili >= medicam.getKEY_BILI_MAX()){
        //la dose complete
        description.add(1);
    }else{
        //50% de la dose complete
        description.add(2);
    }

    if(tgoTga <= medicam.getKEY_TGOTGA_MIN()){
        //contre indique
        description.add(3);
    }else if(tgoTga >= medicam.getKEY_TGOTGA_MAX()){
        //la dose complete
        description.add(1);
    }else{
        //50% de la dose complete
        description.add(2);
    }

    int max = Collections.max(description);
    switch (max){
    case 1: doseAdministrer = medicam.getDoseComp();
        break;
    case 2: doseAdministrer = medicam.getDoseComp()/2;
        break;
    case 3: doseAdministrer = 0;
        break;
    }


        return doseAdministrer;
    }



}
