package net.crimsonresearch.imagesearch;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Settings extends Activity implements OnItemSelectedListener {
	Spinner spImageSize;
	Spinner spColorFilter;
	Spinner spImageType;
	EditText etSiteFilter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		spImageSize = (Spinner)findViewById(R.id.spImageSize);
		spImageSize.setOnItemSelectedListener((OnItemSelectedListener) this);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.imageSizes , android.R.layout.simple_spinner_item); 
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spImageSize.setAdapter(adapter);
		
		spColorFilter = (Spinner)findViewById(R.id.spColorFilter);
		spColorFilter.setOnItemSelectedListener((OnItemSelectedListener) this);
		adapter = ArrayAdapter.createFromResource(this, R.array.colors , android.R.layout.simple_spinner_item); 
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spColorFilter.setAdapter(adapter);
		
		spImageType = (Spinner)findViewById(R.id.spImageType);
		spImageType.setOnItemSelectedListener((OnItemSelectedListener) this);
		adapter = ArrayAdapter.createFromResource(this, R.array.imageTypes , android.R.layout.simple_spinner_item); 
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spImageType.setAdapter(adapter);
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		if(view == spImageSize) {
			String item = (String) parent.getItemAtPosition(pos);
		}
		
		if(view == spColorFilter) {
			
		}
		
		if(view == spImageType ) {
			
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}
}
