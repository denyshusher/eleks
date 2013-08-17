package com.elrain.downloadtest.news.classes;

import java.util.List;
import java.util.Map;

import com.elrain.downloadtest.R;
import com.elrain.downloadtest.Variables;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<Map<String, String>>{

	private List<Map<String, String>> newsList;
	private final Context context;
	
	public MyArrayAdapter(Context context, int textViewResourceId,
			List<Map<String, String>> newsList) {
		super(context, textViewResourceId, newsList);
		this.newsList = newsList;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 
			View rowView = inflater.inflate(R.layout.activity_bbc_list, parent, false);
			
			TextView textView = (TextView) rowView.findViewById(R.id.newsHead);
			textView.setText(newsList.get(position).get(Variables.title).toString());
			ImageView imageView = (ImageView) rowView.findViewById(R.id.newsImage);
			
			try{
				imageView.setImageDrawable(DownloadImageFile.getImageFromUrl(newsList.get(position).get(Variables.mediaUrl)));
			}
			catch(Exception ex){
				ex.printStackTrace();
			}			
			
			return rowView;
	}
}
