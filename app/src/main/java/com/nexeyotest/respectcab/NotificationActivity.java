package com.nexeyotest.respectcab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.nexeyo.respectcab.R;

import java.util.Locale;

public class NotificationActivity extends AppCompatActivity {
    public Integer str;
    SharedPreferences sp;
    String uid;
    String startAddress;
    String endAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        SharedPreferences mPrefs = getSharedPreferences("IDvalue",0);
        str = mPrefs.getInt("DriverIDValue", 0);
        uid = mPrefs.getString("uid", "a");
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String cusPhone = mPrefs.getString("CustomerPhone","DEFAULT");
        startAddress = mPrefs.getString("start","default");
        endAddress = mPrefs.getString("destination", "default");

        Button button1 = (Button) findViewById(R.id.accept);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Log.i("yeeeeee", "meka vada horay");
                Log.e("aneeeeee", "cusPhone"+cusPhone);
                Log.e("aneeeeee", "start"+startAddress);
               // DatabaseReference ref = FirebaseDatabase.getInstance().getReference("CustomerRideAssign");
                DatabaseReference rideRef = FirebaseDatabase.getInstance().getReference("NewRideAvailable");

                rideRef.orderByChild("phoneNo").equalTo(cusPhone).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){

                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("CustomerRideAssign");
                            ref.child(String.valueOf(str)).child("assignid").setValue("One");
                            ref.child(String.valueOf(str)).child("customerphone").setValue(cusPhone);
                            ref.child(String.valueOf(str)).child("driverid").setValue(str.toString());
                            ref.child(String.valueOf(str)).child("pkgid").setValue("PKG001");
                            ref.child(String.valueOf(str)).child("tripstatus").setValue("pending");
//                            Intent intent = new Intent(NotificationActivity.this,
//                                    CustomerRideRequest.class);
//                            startActivity(intent);
                            String uri = String.format(Locale.ENGLISH,"http://maps.google.com/maps?daddr= %s",startAddress);

                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(uri));

                            startActivity(intent);
                            finish();

                        }else{
                            Toast.makeText( NotificationActivity.this, "This ride has been cancelled!",
                                    Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


              //  finish();
            }
        });


        Button button2 = (Button) findViewById(R.id.reject);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Log.i("woooow", "mekath vada horay!!1");

                sp = getSharedPreferences("IDvalue", 0);
                SharedPreferences.Editor editor = sp.edit();
               String stat = "unavailable";
                editor.putString("driverStatus",stat);
                editor.commit();


                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("allOnlineDrivers");
                DatabaseReference tokenref = FirebaseDatabase.getInstance().getReference("PushTokens");
                ref.child(String.valueOf(str)).child("status").setValue("unavailable");
                tokenref.child(uid).child(String.valueOf(str)).child("status").setValue("unavailable");

                finish();
            }
        });

    }
}