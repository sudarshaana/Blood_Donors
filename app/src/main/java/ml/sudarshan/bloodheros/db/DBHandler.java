package ml.sudarshan.bloodheros.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ml.sudarshan.bloodheros.Person;

/**
 * Created by Sudarshan on 4/18/2017.
 */

public class DBHandler  extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "blood";
    private static int DATABASE_VERSION = 1;

    private static final String TIPS_TABLE_NAME = "data";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String BLOODGROUP = "bloodGroup";
    private static final String PHONE_NO = "phoneNo";

    private static final String CREATE_TIPS_TABLE = "CREATE TABLE " + TIPS_TABLE_NAME +
            " ("
            + ID + " INTEGER PRIMARY KEY,"
            + NAME + " TEXT, "
            + BLOODGROUP + " TEXT, "
            + PHONE_NO + " TEXT "
            + ")";
    
    

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TIPS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addTipsData(Person person) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, person.getName());
        values.put(BLOODGROUP, person.getBloodgroup());
        values.put(PHONE_NO, person.getPhoneNo());

        // Inserting Row
        db.insert(TIPS_TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
    public void updateData(Person person) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, person.getName());
        values.put(BLOODGROUP, person.getBloodgroup());
        values.put(PHONE_NO, person.getPhoneNo());

        // Inserting Row
        //db.update(TIPS_TABLE_NAME, null, values);

        int updateStatus = db.update(TIPS_TABLE_NAME, values, ID + " = ?",
                new String[]{String.valueOf(person.getID())});

        db.close(); // Closing database connection
    }

    public List<Person> getAllData() {
        List<Person> personList = new ArrayList<>();

        String QUERY = "SELECT * FROM " + TIPS_TABLE_NAME +  " ORDER BY name ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(QUERY, null);

        if (cursor.moveToFirst()) {
            do {

                Person person = new Person();
                person.setID(cursor.getString(0));
                person.setName(cursor.getString(1));
                person.setBloodgroup(cursor.getString(2));
                person.setPhoneNo(cursor.getString(3));

                // Adding data to the ArrayList
                personList.add(person);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return personList;
    }

}
