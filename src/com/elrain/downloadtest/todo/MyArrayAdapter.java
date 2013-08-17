package com.elrain.downloadtest.todo;

import java.util.List;
import java.util.Map;

import com.elrain.downloadtest.R;
import com.elrain.downloadtest.Variables;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<Map<String, String>>{

	private List<Map<String, String>> todoList;
	private final Context context;
	
	public MyArrayAdapter(Context context, int textViewResourceId,
			List<Map<String, String>> todoList) {
		super(context, textViewResourceId, todoList);
		this.todoList = todoList;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 
			View rowView = inflater.inflate(R.layout.activity_todo_list, parent, false);
			TextView textView = (TextView) rowView.findViewById(R.id.showAllTodo);
			textView.setText(todoList.get(position).get(Variables.headCol).toString());
	 
			return rowView;
	}

}
