package com.example.swotit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//https://www.youtube.com/watch?v=9t8VVWebRFM
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "SWOT_Database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String createDB = "CREATE TABLE SWOT_Database (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "D1 TEXT, " +
                "Strength TEXT," +
                "Weakness TEXT," +
                "Opportunities TEXT," +
                "Threats TEXT," +
                "D2 TEXT," +
                "Main_Reason TEXT)";
        //DB.execSQL("create Table SWOT_Database(id int primary key, D1 TEXT, Strength TEXT, Weakness TEXT, Opportunities TEXT, Threats TEXT, D2 TEXT, Main_Reason TEXT)");
        DB.execSQL(createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists SOT_Database");

    }



    public Boolean insertData(String D1)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("D1", D1);

        long result = DB.insert("SWOT_Database", null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Boolean insertData(String D1, String Strength, String Weakness, String Opportunities, String Threats, String D2, String Main_Reason)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("D1", D1);
        contentValues.put("Strength", Strength);
        contentValues.put("Weakness", Weakness);
        contentValues.put("Opportunities", Opportunities);
        contentValues.put("Threats", Threats);
        contentValues.put("D2", D2);
        contentValues.put("Main_Reason", Main_Reason);

        long result = DB.insert("SWOT_Database", null, contentValues );

        if (result == -1) return false;
        else return true;
    }

    public Boolean updateData(int id, String D1, String Strength, String Weakness, String Opportunities, String Threats, String D2, String Main_Reason)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Strength", Strength);
        contentValues.put("Weakness", Weakness);
        contentValues.put("Opportunities", Opportunities);
        contentValues.put("Threats", Threats);
        contentValues.put("D2", D2);
        contentValues.put("Main_Reason", Main_Reason);

        Cursor cursor = DB.rawQuery("Select * from SWOT_Database where id = ?", new String[] {Integer.toString(id)});

        if (cursor.getCount() > 0)
        {
            long result = DB.update("SWOT_Database",  contentValues, "id", new String [] {Integer.toString(id)} );

            if (result == -1) return false;
            else return true;
        }
        else return false;

    }





    public Boolean deleteData(int id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        Cursor cursor = DB.rawQuery("Select * from SWOT_Database where id = ?", new String[] {Integer.toString(id)});

        if (cursor.getCount() > 0)
        {
            long result = DB.delete("SWOT_Database", "id", new String [] {Integer.toString(id)} );

            if (result == -1) return false;
            else return true;
        }
        else return false;

    }




    public Cursor readData(int id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        Cursor cursor = DB.rawQuery("Select * from SWOT_Database where id = ?", new String[] {Integer.toString(id)});

        return cursor;

    }

    public Cursor getAllData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String getAllData = "SELECT * FROM SWOT_Database";
        Cursor rows = DB.rawQuery(getAllData, null);
        return rows;
    }


}
