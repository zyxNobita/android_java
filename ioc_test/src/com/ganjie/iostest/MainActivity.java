package com.ganjie.iostest;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ganjie.ioclib.BaseActivity;
import com.ganjie.ioclib.annotations.ContentView;
import com.ganjie.ioclib.annotations.OnClick;
import com.ganjie.ioclib.annotations.ViewInject;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

	@ViewInject(R.id.btnLogin)
	private Button btnLogin;
	
	@ViewInject(R.id.btnRegister)
	private Button btnRegister;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		btnLogin.setOnClickListener(l)
	}
	
	public void btnOnClick(View v){
		Toast.makeText(this, "btnOnClick"+v.getId(), 500).show();
	}
	
	@OnClick({R.id.btnLogin,R.id.btnRegister})
	public void toast(View btn){
		switch (btn.getId()) {
		case R.id.btnLogin:
			Toast.makeText(this, "登录........", 500).show();
			break;
		case R.id.btnRegister:
			Toast.makeText(this, "注册........", 500).show();
			break;
		}
	}
	
}
