package com.example.geekshivam.i_cms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static String databasename="iCMS.db";
    private static String table_name="complaint_list";
    private static int databaseversion=1;

    private final String COL_1="ID";
    private final String COL_2="DATE";
    private final String COL_3="TIME";
    private final String COL_4="COMPLAINT_TYPE";
    private final String COL_5="COMPLAINT_ISSUE";
    private final String COL_6="COMPLAINT_DESCRIPTION";
    private final String COL_7="ADDRESS";
    private final String COL_8="CONTACT_NO";
    private final String COL_9="AVAILABLE_DATE";
    private final String COL_10="AVAILABLE_TIME";
    private final String COL_11="STATUS";

    public MySQLiteOpenHelper(Context context) {
        super(context, databasename,null, databaseversion);

    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + table_name +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,TIME TEXT,COMPLAINT_TYPE TEXT,COMPLAINT_ISSUE TEXT,COMPLAINT_DESCRIPTION TEXT, ADDRESS TEXT,CONTACT_NO TEXT,AVAILABLE_DATE TEXT,AVAILABLE_TIME TEXT,STATUS INTEGER)");
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table_name);
    }

    public boolean insertData_to_localDatabase(Complaint complaint)
    {
        Log.d("iCMS","insertData_to_localDatabase() called.");

        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, "put the complaint date here.");
        contentValues.put(COL_3, "put the complaint time here.");
        //from complaint object
        contentValues.put(COL_4, complaint.getType_of_complaint());
        contentValues.put(COL_5, complaint.getIssue());
        contentValues.put(COL_6, complaint.getDescription());
        contentValues.put(COL_7, complaint.getAddress());
        contentValues.put(COL_8, complaint.getPhoneNo());
        contentValues.put(COL_9, complaint.getAvailable_date());
        contentValues.put(COL_10, complaint.getAvailabletime());
        contentValues.put(COL_11, complaint.getStatus().toString());

        long result = db.insert(table_name, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table_name,null);
        return res;
    }

    public void fillDatabase(String email)
    {
        Log.d("iCMS","fillDatabase() called.");
        final MySQLiteOpenHelper myDB=this;

        FirebaseDatabase.
                getInstance().
                getReference().
                child("Complaints").
                orderByKey().
                startAt(email.substring(0,9)).
                endAt(email).
                addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                         String type_of_complaint="Unavailable",issue="Unavailable",description="Unavailable";
                         Boolean status=false;
                         String address="Unavailable";
                         int phoneNo=000;
                         String available_date="Unavailable",availabletime="Unavailable";
                         int i=0;

                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {

                            Object object=snapshot.getValue();
                            Log.d("iCMS",object.toString()+" being added.\n i="+i);

                            if(i==0)address=object.toString();

                            if(i==1)available_date=object.toString();

                            if(i==2)availabletime=object.toString();

                            if(i==3)description=object.toString();

                            if(i==4)issue=object.toString();

                            if(i==5)phoneNo=Integer.parseInt(object.toString());

                            if(i==6)status=Boolean.valueOf(object.toString());

                            if(i==7)
                            {
                                type_of_complaint=object.toString();
                                Log.d("iCMS","PT a:status="+status);
                                Complaint c=new Complaint(type_of_complaint,issue,description,address,phoneNo,available_date,availabletime,status);
                                Log.d("iCMS","PT b");
                                Boolean res=myDB.insertData_to_localDatabase(c);
                                Log.d("iCMS","PT c");

                                i=-1;
                                Log.d("iCMS","New complaint added with issue:"+c.getIssue()+"was Successful="+res.toString());
                                Log.d("iCMS","===========================");
                            }

                            i++;
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }
}
