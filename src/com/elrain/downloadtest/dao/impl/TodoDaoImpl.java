package com.elrain.downloadtest.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.elrain.downloadtest.Variables;
import com.elrain.downloadtest.dao.TodoDao;

public class TodoDaoImpl extends SQLiteOpenHelper implements TodoDao{

	public TodoDaoImpl(Context context) {
		super(context, Variables.dbName, null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(Variables.logCreateTable, Variables.logCreateTableValue);
	    db.execSQL("create table " + Variables.tableName + " ("
	    	+ Variables.idCol + " integer primary key autoincrement," 
	        + Variables.headCol + " text,"
	        + Variables.descriptionCol + " text," 
	        + Variables.dateCol + " text "+");");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int addTodo(ContentValues contentValues, SQLiteDatabase database) {
		if("".equals(contentValues.get(Variables.headCol))){
			return 1;
		}
		else if("".equals(contentValues.get(Variables.descriptionCol))){
			return 2;
		}
		else{
			Log.d(Variables.logInsert, Variables.logInsertValue);
			try{
				long rowId = database.insert(Variables.tableName, null, contentValues);
				Log.d(Variables.logInsert, Variables.logInsertValue + " " + rowId + " " + contentValues.toString());
			}
			catch(SQLiteException ex){
				ex.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public List<Map<String, String>> getAllTodo(SQLiteDatabase database) {
		Cursor cursor = database.query(Variables.tableName, null, null, null, null, null, null);
		List<Map<String, String>> todoList = new ArrayList<Map<String,String>>();
		Map<String,String> mapTodoList = new HashMap<String, String>();
		
		
		if (cursor.moveToFirst()) {
			int idColIndex = cursor.getColumnIndex(Variables.idCol);
	        int headColIndex = cursor.getColumnIndex(Variables.headCol);
	        int descriptionColIndex = cursor.getColumnIndex(Variables.descriptionCol);
	        int dateColIndex = cursor.getColumnIndex(Variables.dateCol);

	        do {
	        	mapTodoList.put(Variables.headCol, cursor.getString(headColIndex));
	        	mapTodoList.put(Variables.descriptionCol, cursor.getString(descriptionColIndex));
	        	mapTodoList.put(Variables.dateCol, cursor.getString(dateColIndex));
	        	
	        	// получаем значения по номерам столбцов и пишем все в лог
	        	Log.d(Variables.logSelect,
	        			 Variables.idCol + " = " + cursor.getInt(idColIndex) + 
	        			", " + Variables.headCol + " = " + cursor.getString(headColIndex) + 
	        			", " + Variables.descriptionCol + " = " + cursor.getString(descriptionColIndex));
	        	// переход на следующую строку 
	        	// а если следующей нет (текущая - последняя), то false - выходим из цикла
	        	
	        	todoList.add(mapTodoList);
	        	mapTodoList = new HashMap<String, String>();
	        } while (cursor.moveToNext());
		}
		
        else
	    	Log.d(Variables.logSelect, "0 rows");
	    cursor.close();	
	    
		return todoList;
	}
	
	

}
