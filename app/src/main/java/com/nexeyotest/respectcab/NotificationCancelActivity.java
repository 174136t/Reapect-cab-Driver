package com.nexeyotest.respectcab;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nexeyo.respectcab.R;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationCancelActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_notification);
        Button button1 = (Button) findViewById(R.id.accept);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Log.i("woooow", "mekath vada horay!!1");
//                Intent intent = new Intent(NotificationCancelActivity.this, MainActivity.class);
//                startActivity(intent);
                finish();
               // finish();
            }
        });
    }
}
