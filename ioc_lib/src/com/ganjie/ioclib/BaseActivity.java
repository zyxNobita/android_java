package com.ganjie.ioclib;

import com.ganjie.ioclib.util.InjectUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InjectUtils.inject(this);
		TextView tv=new TextView(this);
	}

}
