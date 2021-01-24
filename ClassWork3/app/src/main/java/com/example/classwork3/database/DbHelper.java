package com.example.classwork3.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.classwork3.models.Students;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private  static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;
    private static final String TAG = "DbHelper";
    Context context;

    String tbl_create = "CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT , email TEXT, phone TEXT) ";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tbl_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addStudents(Students students){
        try{
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("INSERT INTO students (name, email, phone) values ('"+students.getName()+"', '"+students.getEmail()+"', '"+students.getPhone()+"')");
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();

            return  true;
        }catch (Exception e){
            Log.d(TAG, "addStudents: " + e.toString());
            return false;
        }

    }


    public List<Students> getStudents(){
        List<Students> studentList = new ArrayList<>();
        try{
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor =db.rawQuery("SELECT * from students", null);
          if(cursor !=null){
              while(cursor.moveToNext()){
                  studentList.add(new Students( cursor.getString(0), cursor.getString(1), cursor.getString(2)));
              }

          }
            return studentList;

        }catch(Exception e){

        }
        return null;
    }


    public void deleteStudent(int id) {
        try {
            SQLiteDatabase db = getReadableDatabase();
            db.delete("students", "id=?", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d("delete_error", e.toString());
        }
    }

    public boolean updateStudentDetails(int id, String name, String email, String phone) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("name", name);
            cv.put("email", email);
            cv.put("phone", phone);

            db.update("students", cv, "id=?", new String[]{String.valueOf(id)});
            return true;
        } catch (Exception e) {
            Log.d("update_error", e.toString());
            return false;
        }

    }

}
