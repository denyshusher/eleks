package com.elrain.downloadtest.todo;

import com.elrain.downloadtest.Variables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{

	public DBHelper(Context context) {
		super(context, Variables.dbName, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(Variables.logCreateTable, Variables.logCreateTableValue);
	    db.execSQL("create table " + Variables.tableName + " ("
	    	+ "id integer primary key autoincrement," 
	        + Variables.headCol + " text,"
	        + Variables.descriptionCol + " text," 
	        + Variables.dateCol + " text "+");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
