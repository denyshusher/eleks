package com.elrain.downloadtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.elrain.downloadtest.news.classes.ParseXML;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BashActivity extends Activity {

	private TextView textView;
	private ParseXML xmLtest = null;
	private DownloadATask aTask;
	private List<Map<String, String>> newsList;
	private ProgressBar progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bash_view);
		
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
			progressBar = (ProgressBar) findViewById(R.id.progressBar1);			
			newsList = new ArrayList<Map<String,String>>();
		};
		
		@Override
		protected Void doInBackground(Void... params) {
			xmLtest = new ParseXML(Variables.bashURL);
			newsList = xmLtest.getNews();
				
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			progressBar.setVisibility(View.INVISIBLE);
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
