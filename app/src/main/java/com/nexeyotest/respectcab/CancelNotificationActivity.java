package com.nexeyotest.respectcab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nexeyo.respectcab.R;

public class CancelNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_notification);
        Button button1 = (Button) findViewById(R.id.accept);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Log.i("woooow", "mekath vada horay!!1");
                finish();
            }
        });
    }
}