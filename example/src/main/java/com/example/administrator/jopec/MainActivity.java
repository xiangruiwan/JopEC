package com.example.administrator.jopec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add();
    }
    public void add(){
        System.out.print("你好");
    }
    public void remove(){
        System.out.print("下载");
    }
}
