package com.elrain.downloadtest.todo;

import com.elrain.downloadtest.Variables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{

	public DBHelper(Context context) {
		super(context, "DBTodo", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(Variables.logCreateTable, Variables.logCreateTableValue);
	    db.execSQL("create table todoTable ("
	    	+ "id integer primary key autoincrement," 
	        + "head text,"
	        + "description text," 
	        + "createDate text "+");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
