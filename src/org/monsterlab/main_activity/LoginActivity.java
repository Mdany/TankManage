package org.monsterlab.main_activity;

import java.util.ArrayList;

import org.monsterlab.database.LoginDbManager;
import org.monsterlab.database.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private EditText editView;
	private Button button;
	private LoginDbManager ldb;
	private ArrayList<User> mylist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		editView = (EditText) findViewById(R.id.pwd_edit);
		button = (Button) findViewById(R.id.button);

		ldb = new LoginDbManager(getApplicationContext());
		mylist = (ArrayList<User>) ldb.query();
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String pwd = editView.getText().toString();
				System.out.println(pwd + "........" + mylist.size());
				if (!"".equals(pwd)) {
					if (pwd.length() >= 6 && pwd.length() <= 8) {
						if (pwd.equals(mylist.get(0).getPwd())) {
							Intent intent = new Intent(LoginActivity.this,
									MainActivity.class);
							LoginActivity.this.finish();
							startActivity(intent);
						} else {
							Toast.makeText(getApplicationContext(), "密码错误",
									Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(getApplicationContext(), "密码长度为6~8位",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "密码不能为空",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
