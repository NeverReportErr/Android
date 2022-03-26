package com.example.loginapplication;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ActivityLogin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ProgressBar progressBar=findViewById(R.id.progressBar);//实例化进度条
        progressBar.setVisibility(View.INVISIBLE);//进度条不可见
        Button login = findViewById(R.id.login);//实例化登陆按钮
        login.setOnClickListener(view -> {//登陆事件监听
            Toast.makeText(ActivityLogin.this,"正在登陆，请稍后",Toast.LENGTH_SHORT).show();
            //获取用户输入的用户名和密码
            String admin=((EditText)findViewById(R.id.admin)).getText().toString().trim();
            String password=((EditText)findViewById(R.id.password)).getText().toString().trim();
            progressBar.setVisibility(View.VISIBLE);//显示进度条
            Handler handler=new Handler();
            handler.postDelayed(() -> {
                if(admin.equals("123")&&password.equals("123")){//判断账号密码是否正确
                    Intent isLogin=new Intent(ActivityLogin.this,MainActivity.class);
                    Toast.makeText(ActivityLogin.this,"登陆成功!",Toast.LENGTH_SHORT).show();
                    startActivity(isLogin);
                }
                else {
                    Toast.makeText(ActivityLogin.this,"用户名或密码错误!",Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
            },2000);//延迟2000毫秒后执行
        });
    }

}