package com.elrain.downloadtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.elrain.downloadtest.news.classes.ParseXML;
import com.elrain.downloadtest.news.classes.DownloadXML;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SecondActivity extends Activity {

	private TextView textView;
	private ParseXML xmLtest = new ParseXML();
	private String line;
	DownloadATask aTask;
	List<Map<String, String>> newsList = new ArrayList<Map<String,String>>();
	ProgressBar progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		startATask();
		
	}

	private void startATask(){
		aTask = new DownloadATask();
		aTask.execute();
	}
	
	class DownloadATask extends AsyncTask<Void, Void, Void>{

		@Override
		protected void onPreExecute() {
			textView = (TextView) findViewById(R.id.outputView);
			Intent intent = getIntent();
			line = intent.getStringExtra(Variables.path);
			progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		};
		
		@Override
		protected Void doInBackground(Void... params) {
			if(line.equals(Variables.bash)){
				DownloadXML downloadXML = new DownloadXML(Variables.bashURL, Variables.filePath);
				downloadXML.downloadFile();
			}
			else if(line.equals(Variables.bbc)){
				DownloadXML downloadXML = new DownloadXML(Variables.bbcURL, Variables.filePath);
				downloadXML.downloadFile();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			progressBar.setVisibility(View.INVISIBLE);
			newsList = xmLtest.getNews();
			StringBuilder strBuilder = new StringBuilder();
			for(int i=0; i<newsList.size(); ++i){
				strBuilder.append("|" + newsList.get(i).get(Variables.title) + "|\n");
				strBuilder.append(newsList.get(i).get(Variables.description) + "\n");
				strBuilder.append(newsList.get(i).get(Variables.link) + "\n\n");
			}
			textView.setText(strBuilder);
			super.onPostExecute(result);
		}
		
	}
	
}
