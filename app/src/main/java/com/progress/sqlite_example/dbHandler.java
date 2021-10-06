package com.progress.sqlite_example;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class dbHandler extends SQLiteOpenHelper {
	private static final String DB_NAME= "students_db";
	private static final int DB_VER= 1;
	private static final String TB_STUDENTS= "students";
	
	
	public dbHandler(Context context) {
		super(context, DB_NAME, null, DB_VER);
	}
	
	// kita akan membuat table disini
@Override
public void onCreate(SQLiteDatabase db) {
	db.execSQL("CREATE TABLE "+TB_STUDENTS+" (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR (40), student_id VARCHAR (15) )");
	// kita akan membuat data dummy
	ContentValues values= new ContentValues();
	values.put("name", "Adrian");
	values.put("student_id", "200030167");
	db.insert(TB_STUDENTS, null, values);
}

// apabila versi db dinaikan maka kita akan mengahpus db
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL("DROP TABLE IF EXISTS "+TB_STUDENTS);
	// membuat ulang db
	onCreate(db);
}

public List<students> getStudents() {
		List<students> studentList= new ArrayList<students>();
		
		SQLiteDatabase db= this.getReadableDatabase();
	  Cursor cursor= db.rawQuery("SELECT * FROM "+ TB_STUDENTS, null);
	  
	  if (cursor.moveToFirst()){
	  	do {
	  		students student= new students();
	  		student.set_id(Integer.parseInt(cursor.getString(0)));
	  		student.set_name(cursor.getString(1));
				student.set_student_id(cursor.getString(2));
				
				studentList.add(student);
			} while (cursor.moveToNext());
		}
	  
	  cursor.close();
	  
	  return  studentList;
}

public students getStudentDetail(Integer id) {
		students student= new students();
	
		SQLiteDatabase db= this.getReadableDatabase();
		Cursor cursor= db.rawQuery("SELECT * FROM "+TB_STUDENTS+" WHERE id="+id, null);
		
		if (cursor.moveToFirst()){
			student.set_id(Integer.parseInt(cursor.getString(0)));
			student.set_name(cursor.getString(1));
			student.set_student_id(cursor.getString(2));
		}
		
		cursor.close();
		
		return student;
}

public void addStudent(String name, String student_id) {
	SQLiteDatabase db= this.getWritableDatabase();
	
	ContentValues values= new ContentValues();
	values.put("name", name);
	values.put("student_id", student_id);
	db.insert(TB_STUDENTS, null, values);
}

public void deleteStudent(Integer id) {
	SQLiteDatabase db= this.getWritableDatabase();
	
	db.delete(TB_STUDENTS, "id=?", new String[]{String.valueOf(id)});
}

public void updateStudent(Integer id, String name, String student_id) {
	SQLiteDatabase db= this.getWritableDatabase();
	
	ContentValues values= new ContentValues();
	values.put("name", name);
	values.put("student_id", student_id);
	
	db.update(TB_STUDENTS, values, "id=?", new String[]{String.valueOf(id)});
}

}