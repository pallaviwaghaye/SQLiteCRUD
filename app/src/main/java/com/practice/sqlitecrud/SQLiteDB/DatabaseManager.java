package com.practice.sqlitecrud.SQLiteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.practice.sqlitecrud.model.Person;

import java.util.ArrayList;
import java.util.List;


public class DatabaseManager {
    private static final String TAG = DatabaseManager.class.getSimpleName();
    // Database fields
    private Context context = null;
    private SQLiteDatabase database = null;
    private DatabaseHelper dbHelper = null;

    public DatabaseManager(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(context);
        }
        if (database == null) {
            database = dbHelper.getWritableDatabase();
        }
    }

    public void close() {
        dbHelper.close();
        database = null;
        dbHelper = null;
    }

    //Inserting/Deleting/Updating/Fetching Data From UI to DB : Methods - start

    public synchronized long insertCallEntryDetails(Person person) {
        long result = 0;
        try {
            if (database == null) {
                open();
            }

            /*
             DatabaseConstants.CallDatabaseEntry.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    DatabaseConstants.CallDatabaseEntry.COLUMN_EMAIL + TEXT_TYPE + COMMA_SEP +
                    DatabaseConstants.CallDatabaseEntry.COLUMN_PHONE_NUMBER + TEXT_TYPE + COMMA_SEP +
                    DatabaseConstants.CallDatabaseEntry.COLUMN_QUERY + TEXT_TYPE + COMMA_SEP +
                    DatabaseConstants.CallDatabaseEntry.COLUMN_TIME_STAMP + TEXT_TYPE +
             */
            ContentValues values = new ContentValues();
            values.put(DatabaseConstants.CallDatabaseEntry.COLUMN_PERSON_NAME, person.getPname());
            values.put(DatabaseConstants.CallDatabaseEntry.COLUMN_PERSON_ADDRESS, person.getPaddress());
            values.put(DatabaseConstants.CallDatabaseEntry.COLUMN_TIMEDATE, person.getTimedate());
            values.put(DatabaseConstants.CallDatabaseEntry.COLUMN_PERSON_qualification, person.getPqualification());

            result = database.insert(DatabaseConstants.CallDatabaseEntry.TABLE_NAME, null, values);
            close();
        } catch (Exception ex) {

        }
        return result;
    }

    public synchronized List<Person> getAllEntries() {
        if (database == null) {
            open();
        }
        List<Person> detailsList = new ArrayList<Person>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseConstants.CallDatabaseEntry.TABLE_NAME;
        Cursor cursor = database.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                /*
                   values.put(DatabaseConstants.CallDatabaseEntry.COLUMN_NAME, callEntry.getName());
            values.put(DatabaseConstants.CallDatabaseEntry.COLUMN_EMAIL, callEntry.getEmailId());
            values.put(DatabaseConstants.CallDatabaseEntry.COLUMN_PHONE_NUMBER, callEntry.getPhoneNumber());
            values.put(DatabaseConstants.CallDatabaseEntry.COLUMN_TIME_STAMP, callEntry.getTimeStamp());
            values.put(DatabaseConstants.CallDatabaseEntry.COLUMN_QUERY, callEntry.getQuery());
                 */
                Person category = new Person();
                category.setId(Integer.parseInt(cursor.getString(0)));
                category.setPname(cursor.getString(1));
                category.setPaddress(cursor.getString(2));
                category.setTimedate(cursor.getString(3));
                category.setPqualification(cursor.getString(4));


                detailsList.add(category);
            } while (cursor.moveToNext());
        }
        close();
        return detailsList;
    }

    public synchronized int deleteEntry(int id) {
        int result = 0;
        if (database == null) {
            open();
        }
        String tableName = DatabaseConstants.CallDatabaseEntry.TABLE_NAME;
        String where = "id='" + id + "'";

        result = database.delete(tableName, where, null);
        Log.i(TAG, " deleteTableRow Deleted rows count: " + result);
        close(); // Closing database connection
        return result;
    }


    public synchronized long updateEntry(Person person) {
        long result = 0;
        try {
            if (database == null) {
                open();
            }
            //int id = person.getId();
            //String where = "id='" + id + "'";

            ContentValues values = new ContentValues();
            values.put(DatabaseConstants.CallDatabaseEntry.COLUMN_PERSON_NAME, person.getPname());
            values.put(DatabaseConstants.CallDatabaseEntry.COLUMN_PERSON_ADDRESS, person.getPaddress());
            values.put(DatabaseConstants.CallDatabaseEntry.COLUMN_TIMEDATE, person.getTimedate());
            values.put(DatabaseConstants.CallDatabaseEntry.COLUMN_PERSON_qualification, person.getPqualification());

            result = database.update(DatabaseConstants.CallDatabaseEntry.TABLE_NAME, values, DatabaseConstants.CallDatabaseEntry.COLUMN_ID + " = ?",
                    new String[]{String.valueOf(person.getId())});
            Log.e("result=====", String.valueOf(result));
            close();
        } catch (Exception ex) {

            Log.e("exception===", String.valueOf(ex));
        }
        return result;
    }



}