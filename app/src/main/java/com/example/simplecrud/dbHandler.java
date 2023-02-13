package com.example.simplecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "marks.db";
    private static final String TABLE_NAME = "marks";


    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ROLLNUM = "rollnum";
    private static final String COLUMN_MARKS = "marks";

    public dbHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_ROLLNUM + " INTEGER,"
                + COLUMN_MARKS + " INTEGER"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
    public boolean insertStudent(String id,String Name,String RollNum,String Marks) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, Name);
        values.put(COLUMN_ROLLNUM, RollNum);
        values.put(COLUMN_MARKS, Marks);

        long result=db.insert(TABLE_NAME, null, values);
        if(result==-1)
            return false;
        else
            return true;
        //db.close();
    }
    public Cursor getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery(" SELECT * FROM "+TABLE_NAME,null);
        return res;
    }
    public boolean Update(String iid,String Name,String Rollnum,String Marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_ID,iid);
        values.put(COLUMN_NAME,Name);
        values.put(COLUMN_ROLLNUM,Rollnum);
        values.put(COLUMN_MARKS,Marks);
        db.update(TABLE_NAME,values,"ID = ?",new String[] {iid});
        return true;
    }
    public Integer Delete(String ID)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{ID});
    }
}
