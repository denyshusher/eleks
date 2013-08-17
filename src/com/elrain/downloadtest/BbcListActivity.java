package com.elrain.downloadtest;

import java.util.List;
import java.util.Map;

import com.elrain.downloadtest.news.classes.MyArrayAdapter;
import com.elrain.downloadtest.news.classes.ParseXML;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;

public class BbcListActivity extends ListActivity{

	private List<Map<String, String>> newsList;
	private ParseXML xmLtest = null;
	private CreateNewsList createNewsList;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		startATask(this);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent bbcShowActivity = new Intent(getApplicationContext(), BbcShowActivity.class);
		bbcShowActivity.putExtra(Variables.title, newsList.get(position).get(Variables.title));
		bbcShowActivity.putExtra(Variables.description, newsList.get(position).get(Variables.description));
		bbcShowActivity.putExtra(Variables.mediaUrl, newsList.get(position).get(Variables.mediaUrl));
		bbcShowActivity.putExtra(Variables.link, newsList.get(position).get(Variables.link));
		startActivity(bbcShowActivity);
	}
	
	private void startATask(Context context){
		createNewsList = new CreateNewsList(context);
		createNewsList.execute();
	}
	
	class CreateNewsList extends AsyncTask<Void, Void, Void>{
		Context context;
		public CreateNewsList(Context context){
			this.context = context;
			
		}
		@Override
		protected void onPreExecute() {
			
		}
		
		@Override
		protected Void doInBackground(Void... arg0) {
			xmLtest = new ParseXML(Variables.bbcURL);
			newsList = xmLtest.getNews();
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			setListAdapter(new MyArrayAdapter(context, 0, newsList));
			super.onPostExecute(result);
		}
	}
}
