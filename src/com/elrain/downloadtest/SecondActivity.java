package com.elrain.downloadtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.elrain.downloadtest.news.classes.ParseXML;
import com.elrain.downloadtest.news.classes.DownloadXML;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

public class SecondActivity extends Activity {

	private TextView textView;
	private ParseXML xmLtest = new ParseXML();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		textView = (TextView) findViewById(R.id.outputView);
		Intent intent = getIntent();
		String line = intent.getStringExtra(Variables.path);
		List<Map<String, String>> newsList = new ArrayList<Map<String,String>>();
		
		if(line.equals("bash")){
			DownloadXML downloadXML = new DownloadXML(Variables.bashURL, Variables.filePath);
			downloadXML.downloadFile();
		}
		else if(line.equals("bbc")){
			DownloadXML downloadXML = new DownloadXML(Variables.bbcURL, Variables.filePath);
			downloadXML.downloadFile();
		}
		
		newsList = xmLtest.getNews();
		StringBuilder strBuilder = new StringBuilder();
		for(int i=0; i<newsList.size(); ++i){
			strBuilder.append("|" + newsList.get(i).get(Variables.title) + "|\n");
			strBuilder.append(newsList.get(i).get(Variables.description) + "\n");
			strBuilder.append(newsList.get(i).get(Variables.link) + "\n\n");
		}
		textView.setText(strBuilder);
	}


}
