package com.elrain.downloadtest.dao;

import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public interface TodoDao {
	public int addTodo(ContentValues contentValues, SQLiteDatabase database);
	public List<Map<String, String>> getAllTodo(SQLiteDatabase database);

}
