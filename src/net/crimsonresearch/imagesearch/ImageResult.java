package net.crimsonresearch.imagesearch;

import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ImageResult implements Serializable {
	private static final long serialVersionUID = 5340437577536405649L;
	private String fullUrl;
	private String thumbUrl;
	private String title;
	private String formattedTitle;
	
	public ImageResult(JSONObject json) {
		try {
			this.setFullUrl(json.getString("url"));
			this.setThumbUrl(json.getString("tbUrl"));
		} catch (JSONException e) {
			this.setFullUrl(null);
			this.setThumbUrl(null);
		}
		try {
			this.setFormattedTitle(json.getString("title"));
			this.setTitle(json.getString("titleNoFormatting"));
		} catch (JSONException e) {
			this.setFormattedTitle(null);
			this.setTitle(null);
		}
	}
	
	public String toString() {
		return this.thumbUrl;
	}

	public String getFormattedTitle() {
		return formattedTitle;
	}

	public void setFormattedTitle(String formattedTitle) {
		this.formattedTitle = formattedTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public static ArrayList<ImageResult> fromJSONArray(JSONArray imageJsonResult) {
		ArrayList<ImageResult> results = new ArrayList<ImageResult>();
		for(int x = 0; x < imageJsonResult.length(); x++) {
			try {
				JSONObject json = imageJsonResult.getJSONObject(x);
				Log.d("JSON", json.toString());
				results.add(new ImageResult(json));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return results;
	}
}
