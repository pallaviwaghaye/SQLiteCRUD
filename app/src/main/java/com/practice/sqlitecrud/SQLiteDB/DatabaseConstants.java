package com.practice.sqlitecrud.SQLiteDB;

import android.provider.BaseColumns;

public class DatabaseConstants {
    private DatabaseConstants() {
    }

    public static abstract class CallDatabaseEntry implements BaseColumns {
        // Table ProductCategory
        public static final String TABLE_NAME = "PersonInfo";
        //Column names
        public static final String COLUMN_ID = "id";//pk
        public static final String COLUMN_PERSON_NAME = "pname";
        public static final String COLUMN_PERSON_ADDRESS = "paddress";
        public static final String COLUMN_TIMEDATE = "timedate";
        public static final String COLUMN_PERSON_qualification = "pquali";


    }
}
