package org.monsterlab.main_activity;

import java.util.ArrayList;

import org.monsterlab.database.LoginDbManager;
import org.monsterlab.database.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePasswordActivity extends Activity {

	private EditText old;
	private EditText new_pwd;

	private LoginDbManager ldb;
	private ArrayList<User> mylist;
	private String o;
	private String n;

	private boolean ob;
	private boolean nb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_update_password);

		ldb = new LoginDbManager(getApplicationContext());
		mylist = (ArrayList<User>) ldb.query();

		old = (EditText) findViewById(R.id.old_pwd);
		new_pwd = (EditText) findViewById(R.id.new_pwd);

		Button reback = (Button) findViewById(R.id.reback_btn);
		Button change = (Button) findViewById(R.id.change);

		reback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), MainActivity.class);
				UpdatePasswordActivity.this.finish();
				startActivity(intent);

			}
		});

		change.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				o = old.getText().toString();
				n = new_pwd.getText().toString();
				
				ob=false;
				nb=false;
				
				System.out.println(o + "....." + n);
				if (!"".equals(o)) {
					if (o.length() >= 6 && o.length() <= 8) {
						if (o.equals(mylist.get(0).getPwd())) {
							ob = true;
							System.out.println("密码正确");
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

				System.out.println(ob);

				if (ob && !"".equals(n)) {
					if (n.length() >= 6 && n.length() <= 8) {
						nb = true;
						System.out.println("新密码设置完毕");
					} else {
						Toast.makeText(getApplicationContext(), "新密码长度为6~8位",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), "新密码不能为空",
							Toast.LENGTH_SHORT).show();
				}

				if (ob && nb) {
					ldb.update(o, n);
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(), MainActivity.class);
					UpdatePasswordActivity.this.finish();
					ldb.closeDB();
					startActivity(intent);
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update, menu);
		return true;
	}

}
