package com.practice.sqlitecrud.SQLiteDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "personinfo.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ", ";
    private static final String BLOB_TYPE = " BLOB";

    // Create/Delete Table
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + DatabaseConstants.CallDatabaseEntry.TABLE_NAME + " (" +
                    DatabaseConstants.CallDatabaseEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseConstants.CallDatabaseEntry.COLUMN_PERSON_NAME + TEXT_TYPE + COMMA_SEP +
                    DatabaseConstants.CallDatabaseEntry.COLUMN_PERSON_ADDRESS + TEXT_TYPE + COMMA_SEP +
                    DatabaseConstants.CallDatabaseEntry.COLUMN_TIMEDATE + TEXT_TYPE + COMMA_SEP +
                    DatabaseConstants.CallDatabaseEntry.COLUMN_PERSON_qualification + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + DatabaseConstants.CallDatabaseEntry.TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);

    }
}
