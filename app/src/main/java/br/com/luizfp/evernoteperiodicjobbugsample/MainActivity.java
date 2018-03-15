package br.com.luizfp.evernoteperiodicjobbugsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_syncData).setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        DemoSyncJob.runJobImmediately();
    }
}