package net.crimsonresearch.imagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class ImageSearch extends Activity {
	public static final int IMAGE_DISPLAY_ACTIVITY = 0;
	public static final int SETTINGS_ACTIVITY = 1;
	
	EditText etQuery;
    GridView gvResults;
    Button btnSearch;
    SearchSettings searchSettings = new SearchSettings();
    String queryString;
    
    ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
    ImageResultArrayAdapter imageAdapter;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_search);
		setupViews();
		imageAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);
		gvResults.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position, long rowId) {
				Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
				ImageResult imageResult = imageResults.get(position);
				i.putExtra(ImageDisplayActivity.KEY_NAME, imageResult);
				startActivityForResult(i, IMAGE_DISPLAY_ACTIVITY);
			}
		});
		
		gvResults.setOnScrollListener(new EndlessScrollListener() {
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				loadImagesForPage(page, queryString);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	    	case R.id.action_settings:
	            openSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private void openSettings() {
		Intent i = new Intent(getApplicationContext(), Settings.class);
		i.putExtra(Settings.KEY_NAME, searchSettings);
		startActivityForResult(i, SETTINGS_ACTIVITY);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == SETTINGS_ACTIVITY && resultCode == Activity.RESULT_OK) {	
			searchSettings = (SearchSettings) data.getSerializableExtra(Settings.KEY_NAME);
		}
	}

	private void setupViews() {
		etQuery = (EditText) findViewById(R.id.etQuery);
		gvResults = (GridView) findViewById(R.id.gvResults);
		btnSearch = (Button) findViewById(R.id.btnSearch);
	}
	
	public void onImageSearch(View v) {
			queryString = etQuery.getText().toString();
		Toast.makeText(this, "Searching for " + queryString, Toast.LENGTH_LONG).show();
		imageResults.clear();
		loadImagesForPage(0, queryString);
	}
	
    private void loadImagesForPage(final int page, final String queryString) {
		AsyncHttpClient client = new AsyncHttpClient();
		String urlString = "";
		String value = searchSettings.getColorFilter();
		if(value != null && value.length() > 0)
		{
			urlString += "&imgcolor=" + Uri.encode(value);
		}
		value = searchSettings.getImageSize();
		if(value != null && value.length() > 0)
		{
			urlString += "&imgsz="+ Uri.encode(value);
		}
		
		value = searchSettings.getImageType();
		if(value != null && value.length() > 0)
		{
			urlString += "&imgtype="+Uri.encode(value);
		}
		
		value = searchSettings.getSiteFilter();
		if(value != null && value.length() > 0)
		{
			urlString += "&as_sitesearch=" + Uri.encode(value);
		}
		
		urlString += "&v=1.0&q=" + Uri.encode(queryString);
		// https://ajax.googleapis.com/ajax/services/search/images?q=Android&v=1.0
		client.get("http://ajax.googleapis.com/ajax/services/search/images?rsz=8&start=" + page + urlString, new JsonHttpResponseHandler() {
			public void onSuccess(JSONObject response) {
				JSONArray imageJsonResult = null;
				try {
					imageJsonResult = response.getJSONObject("responseData").getJSONArray("results");
					imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResult));
					Log.d("DEBUG", imageResults.toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		});
    }
}
