package com.nexeyotest.respectcab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nexeyo.respectcab.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.mail.Session;

public class TripStatusNo extends Activity {

    private static TextView mDriver_id_history, mtripdateandtime;
    private RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    TextView mPaymentMethod;
    SharedPreferences sp;




    private DatabaseReference mDatabase;
    private DatabaseReference fDDatabase;
    private DatabaseReference fDatabase;
    private DatabaseReference dDatabase;
    public DatabaseReference track_Database;
    public DatabaseReference unFinished_Database;
    private  DatabaseReference rDatabase;
    private DatabaseReference cusDatabase;
    private static final String TAG = "debug1";
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    Button mLogout, mStart, mWaiting, mPause, mForceStop, mForceStart;
    Button mEnd;
    EditText mDistance;
    TextView textfrom;
    TextView mlat1, mTimer1, mTimer2;
    TextView mlat2;
    TextView mlon1;
    TextView mlon2;
    TextView mSpeed, mEmail;
    EditText mDistanceold;
    EditText mDistanceold1;
    TextView mDistanceVin, Ttotalcost, mWaitingCost, mTripCost;
    public TextView total_earn;
//    String mTripDate;
    private DrawerLayout drawer;
    private long startTime = 0L;
    private long startTime2 = 0L;
    private long startTime3 = 0L;
    private Handler customHandler = new Handler();
    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(startTime), TimeUnit.MILLISECONDS.toMinutes(startTime) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(startTime)), TimeUnit.MILLISECONDS.toSeconds(startTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(startTime)));
    long timeInMilliseconds = 0L;

    long prevTime = 0L;
    long prevWaitingtime = 0L;
    long timeSwapBuff = 0L;
    long timeSwapBuff3 = 0L;
    long updatedTime = 0L;
    long updatedTime3 = 0L;
    boolean stopTimer = false;
    boolean stopTimer3 = false;
    int milliseconds2 = 0;
    int minutes = 0;
    int startTime2status = 0;
    int speed = 0;
    Object key;

    private String st1;

    String showtext = null;


    //private Handler customHandler = new Handler();
    private Handler customHandler2 = new Handler();
    private Handler customHandler3 = new Handler();

    long timeInMilliseconds2 = 0L;
    long timeInMilliseconds3 = 0L;
    long timeSwapBuff2 = 0L;
    long ptimeSwapBuff2 = 0L;
    long updatedTime2 = 0L;
    boolean stopTimer2 = false;
    int pspeed1 = 0;
    long pupdatetime2 = 0L;

    static Double distance = 0.00;
    static Double distanceV = 0.00;
    static Double distanceOld = 0.00;
    static Double distanceOld1 = 0.00;
    static Double distanceVincenty = 00.00;
    static Double costtotalinit = 0.00;
    static Double costtotalfunc = 0.00;
    static Double costTotalLast = 0.00;
    static Double tripCommission1 = 0.00;
    static Double tripCommission = 0.00;
    static Double tripCost = 0.00;
    static Double waitingCost = 0.00;
    static Double mWaitingCostV = 0.00;
    static Double mTripCostV = 0.00;
    static Double dTotalTripCost = 0.00;
    static Double dTripCommission = 0.00;
    static Double pTotal_earn = 0.00;
    static Double pTotal_commission = 0.00;
    static Double pPaid_commission;
    static Double pPaid_commission_f = 0.00;
    static Double pBalance = 0.00;
    static double sum = 0.00;
    static double sumCommission = 0.00;

    static double previousCost = 0.00;

    static Double pTotal_earn_f = 0.00;
    static Double pTotal_commission_f = 0.00;
    static double sum_f = 0.00;
    static double sumCommission_f = 0.00;


    //static Double speed =   0.0;
    static int s1 = 0;
    static int w1 = 0;

    static int status_did = 0;


    static int status = 0;
    static Double lat1 = 0.0;
    static Double lon1 = 0.0;
    static Double lat2 = 0.0;
    static Double lon2 = 0.0;
    static LatLng latLng1 = null;
    static LatLng latLng2 = null;
    static LatLng latLng3 = null;
    long maxid = 0;
    int maxidstatus = 0;
    public Integer str;
    public String value;
    public String value2;
    public String value3;
    public String tripstarttime;
    public String str_d_name;
    public String str_d_mobile;
    //    public String str_vehicle_type;
//    public String str_vehicle_plate;
//    public String str_vehicle_charge;
    public String str_vehicle_initial_charge;
    public String str_vehicle_initial_distance;
    public String str_vehicle_waiting_cost;

    public double vehicle_charge;
    public double vehicle_initial_charge;
    public double vehicle_initial_distance;
    public double vehicle_waiting_cost;

    private static DecimalFormat df = new DecimalFormat("000.00");
    private static final double EQUATORIAL_EARTH_RADIUS = 6378.1370;
    private static final double DEG_TO_RAD = Math.PI / 180;

    private Switch mWorkingDriver;
    Geocoder geocoder;
    List<Address> addresses = new ArrayList<>();
    String address = "0";
    String address2 = "0";
    String address3 = "0";
    String payment_method;


    public String str_vehicle_type;
    public String str_vehicle_plate;

    public String str_vehicle_charge;
    public double result;

    String track_status = "No";
    String track_status2 = "Yes";
//    SharedPreferences sp;
    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    String rec, subject, textmessage;
    String cus_email;
    String cus_mobile;

    String packageDetails;
    String status_waiting = "null";

    DatabaseReference mRef;
    FirebaseHelper firebaseHelper = new FirebaseHelper("0000");
    public String str_package_id;

    int situation =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPlateno", MODE_APPEND);
        @SuppressLint("WrongConstant") SharedPreferences sh2 = getSharedPreferences("MySharedvehicletype", MODE_APPEND);


        final String plateno = sh.getString("plate_no", "");
        final String vehicletype = sh2.getString("vehicletype", "");

        SharedPreferences mPrefs2 = getSharedPreferences("DriverTotalEarn",0);
        Integer str2 = mPrefs2.getInt("DriverTatalEarn", 0);

        final SharedPreferences mPrefs = getSharedPreferences("IDvalue",0);
         str = mPrefs.getInt("DriverIDValue", 0);

        setContentView(R.layout.pop_up_tripstatus_no);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.history_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mPaymentMethod = (TextView) findViewById(R.id.payment_method);

        SharedPreferences mPrefs3 = getSharedPreferences("Customervalue",0);
        cus_mobile = mPrefs2.getString("CustomermobileValue", "No Value Mobile");
        cus_email = mPrefs3.getString("CustomeremailValue", "No Value Email");
        final String customer_mobile = cus_mobile;
        final String customer_email = cus_email;
//        customer_mobile.setText(cus_mobile);
//        customer_email.setText(cus_email);

        SharedPreferences mPrefsvehiclecharge = getSharedPreferences("vehiclecharges", 0);
        str_vehicle_charge = mPrefsvehiclecharge.getString("carcharge", "123");
        str_vehicle_type = mPrefsvehiclecharge.getString("cartype", "123");
        str_vehicle_initial_charge = mPrefsvehiclecharge.getString("initialcharge", "123");
        str_vehicle_initial_distance = mPrefsvehiclecharge.getString("initialdistance", "123");
        str_vehicle_waiting_cost = mPrefsvehiclecharge.getString("waitingcost", "123");

        vehicle_charge = Double.valueOf(str_vehicle_charge);
        vehicle_initial_charge = Double.valueOf(str_vehicle_initial_charge);
        vehicle_waiting_cost = Double.valueOf(str_vehicle_waiting_cost);
        vehicle_initial_distance = Double.valueOf(str_vehicle_initial_distance);

        // firebaseUserSearch(String.valueOf(str));
        firebaseUserSearch(str);
        mDatabase = FirebaseDatabase.getInstance().getReference("DriverHistory_Model");
        fDDatabase = FirebaseDatabase.getInstance().getReference("DriverHistory_Model Date Wise");
        fDatabase = FirebaseDatabase.getInstance().getReference("Date wise Driver Report");
        dDatabase = FirebaseDatabase.getInstance().getReference("Driver_Earning_And_Commission2");
        track_Database = FirebaseDatabase.getInstance().getReference("DriverTracking");
        rDatabase = FirebaseDatabase.getInstance().getReference("RideTracking");
        unFinished_Database = FirebaseDatabase.getInstance().getReference("UnFinishedRides");
        cusDatabase = FirebaseDatabase.getInstance().getReference("CustomerRideHistory");

        Button button1 = (Button) findViewById(R.id.continueTrip);
        Button button2 = (Button) findViewById(R.id.stopTrip);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Log.i("addeeeeeeeeeeeeee", "mekath vada horay!!1");

               sp = getSharedPreferences("IDvalue", 0);
                String tripid = sp.getString("tripcost","DEFAULT");
                String mTripDate = sp.getString("date","DEFAULT");
                Log.i("addeeeeeeeeeeeeee", tripid);
                Log.e("addeeeeeeeeeeeeee", mTripDate);


                DatabaseReference failedTripRef = FirebaseDatabase.getInstance().getReference("UnFinishedRides");

                failedTripRef.orderByChild("driverid").equalTo(str).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){

                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("packageassign");

                            reference.orderByChild("driverid").equalTo(String.valueOf(str)).addListenerForSingleValueEvent(new ValueEventListener() {
                                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Log.e("addadddaadddaa", dataSnapshot.toString());
                                    if(dataSnapshot.exists()){

                                        for(DataSnapshot datas: dataSnapshot.getChildren()){
                                            String status= Objects.requireNonNull(datas.child("tripstatus").getValue()).toString();
                                            if(status.equals("Running")){
                                                situation =1;
                                                Intent intent = new Intent(TripStatusNo.this, FailedDriverPackageActivity.class);
                                                startActivity(intent);
                                                finish();
//                                            Toast.makeText( TripStatusNo.this, "haamooooooo yantam athi", Toast.LENGTH_LONG).show();
                                            }else {
                                                situation =0;
                                                Intent intent = new Intent(TripStatusNo.this, FailedMapActivity.class);
                                                startActivity(intent);
                                                finish();
//                                            Toast.makeText( TripStatusNo.this, "hari yanne", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }else{
                                        Intent intent = new Intent(TripStatusNo.this, FailedMapActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });


//                            if(situation==0){
//
//                            }else{
//
//                            }


                        }else{
                            Toast.makeText( TripStatusNo.this, "There are no any failed trips!",
                                    Toast.LENGTH_LONG).show();
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        FirebaseDatabase.getInstance().getReference("Driver_Earning_And_Commission2").child(String.valueOf(str)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d("aanna", snapshot.toString());
                if(snapshot.getValue() != null){
                    FinanceBalance_Model fin = snapshot.getValue(FinanceBalance_Model.class);
                    pPaid_commission = Math.round((Double.valueOf(fin.getPaidcommission())) * 100.0) / 100.0;
                }else{
//                    FinanceBalance_Model fin = snapshot.getValue(FinanceBalance_Model.class);
                    pPaid_commission = Math.round(((double) 0) * 100.0) / 100.0;
                }


                //System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                sp = getSharedPreferences("IDvalue", 0);
//                String tripid = sp.getString("tripcost","DEFAULT");
                String mTripDate = sp.getString("date","DEFAULT");
//                Log.i("addeeeeeeeeeeeeee", tripid);
                Log.e("addeeeeeeeeeeeeee", mTripDate);
                Log.d("addeeeeeeeeeeeeee", mTripDate);


                DatabaseReference failedTripRef = FirebaseDatabase.getInstance().getReference("UnFinishedRides");

                failedTripRef.orderByChild("driverid").equalTo(str).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){


                final String[] listItems = {"Cash", "Credit", "Vouchers"};

                final AlertDialog.Builder builder = new AlertDialog.Builder(TripStatusNo.this,R.style.MaterialThemeDialog);
                final EditText input = new EditText(TripStatusNo.this);
                builder.setView(input);
                input.setVisibility(View.INVISIBLE);
                input.setHint("Enter voucher code here");

                final TextView myMsg = new TextView(TripStatusNo.this);
                myMsg.setText("Total fare -"+String.valueOf(costTotalLast));
                myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
                myMsg.setTextSize(30);
                myMsg.setTextColor(Color.BLUE);


                final TextView confirm_title = new TextView(TripStatusNo.this);
                confirm_title.setText("     NexRide Service");
                confirm_title.setTextSize(20);
                confirm_title.setTextColor(Color.BLACK);


                builder.setCustomTitle(myMsg);

//                builder.setTitle(myMsg+"Choose Payment Method");

                int checkedItem = 10; //this will checked the item when user open the dialog
                builder.setSingleChoiceItems(listItems, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(TripStatusNo.this, "Position: " + which + " Value: " + listItems[which], Toast.LENGTH_LONG).show();
                        payment_method = listItems[which];

                        if (payment_method==listItems[2]){
                            input.setVisibility(View.VISIBLE);
                            input.requestFocus();
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);
                        }else if (payment_method==listItems[1]){
                            input.setVisibility(View.INVISIBLE);
                        }else{
                            input.setVisibility(View.INVISIBLE);
                        }
                    }
                });

                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog2, int which) {
                        dialog2.dismiss();

                        final AlertDialog.Builder alert = new AlertDialog.Builder(TripStatusNo.this,R.style.MaterialThemeDialog);
                        alert
                                .setCustomTitle(confirm_title)
                                .setMessage("Are you Sure you want to end the trip?")

                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Date d2 = new Date();

                                        Calendar c2 = Calendar.getInstance();

                                        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
                                        String formattedDate2 = df2.format(c2.getTime());
                                        final String tripendDate = formattedDate2;
                                        final String tripendtime = d2.getHours() + ":" + d2.getMinutes();
                                        sp = getSharedPreferences("IDvalue", 0);
                                        String tripid = sp.getString("tripcost","DEFAULT");
                                        String mTripDate = sp.getString("date","DEFAULT");
                                        costTotalLast = Double.valueOf(sp.getString("tripcost","DEFAULT"));
                                        value2 = sp.getString("tripTime","DEFAULT");
                                        mWaitingCostV =  Double.valueOf(sp.getString("waitingcost","DEFAULT"));
                                        tripstarttime = sp.getString("starttime","DEFAULT");
                                        value3 = sp.getString("waitingtime","DEFAULT");
                                        address = sp.getString("tripstartaddress","DEFAULT");
                                        address2 = sp.getString("tripendaddress","DEFAULT");
                                        maxid =Long.valueOf( sp.getString("tripid","DEFAULT"));
                                        distanceV = Double.valueOf(sp.getString("totaldistance","DEFAULT"));
                                        mTripCostV = costTotalLast - mWaitingCostV;
                                        str_d_name = sp.getString("drivername", "Unknown");
                                        str_d_mobile = sp.getString("drivermobile","");

                                        Log.i("addeeeeeeeeeeeeee", tripid);
                                        Log.e("addeeeeeeeeeeeeee", mTripDate);

                                        //value = input.getText().toString().trim();
//                                        value2 = mTimer1.getText().toString().trim();
//                                        value3 = mTimer2.getText().toString().trim();
//                                        String id = mDatabase.push().getKey();

                                        tripCommission1 = costTotalLast * 10 / 100;
                                        tripCommission = Math.round(tripCommission1 * 100.0) / 100.0;
                                        dTripCommission = dTripCommission + tripCommission;

                                        HashMap<String, Object> dataMap = new HashMap<String, Object>();
                                        //HashMap<String, Object> dataMap2 = new HashMap<String, Object>();

                                        dataMap.put("tripid", String.valueOf(maxid));
                                        dataMap.put("drivername",str_d_name);
                                        dataMap.put("drivermobile",str_d_mobile);
                                        dataMap.put("driverid", str.toString());
                                        dataMap.put("distancecarmeter", value);
                                        dataMap.put("distanceapplication", String.valueOf(distanceV));
                                        dataMap.put("tripcommission", tripCommission);
                                        dataMap.put("triptotalcost", costTotalLast);
                                        dataMap.put("triptime", value2);
                                        dataMap.put("tripcost", String.valueOf(mTripCostV));
                                        dataMap.put("waitingtime", value3);
                                        dataMap.put("waitingcost", String.valueOf(mWaitingCostV));
                                        dataMap.put("customermobile",customer_mobile.toString().trim());
                                        dataMap.put("customeremail",customer_email.toString().trim());
                                        dataMap.put("tripstartdate",mTripDate.toString());
                                        dataMap.put("tripenddate", tripendDate);
                                        dataMap.put("tripstarttime", tripstarttime);
                                        dataMap.put("tripendtime", tripendtime);
                                        dataMap.put("startaddress", address);
                                        dataMap.put("endaddress", address2);
//                                        dataMap.put("endaddress", address3);
                                        dataMap.put("paymentmethod", payment_method);
                                        dataMap.put("vouchercode", input.getText().toString());

                                        mDatabase.child(String.valueOf(maxid)).setValue(dataMap);

                                        fDDatabase.child(mTripDate.toString()).child(str.toString()).child(String.valueOf(maxid)).setValue(dataMap);
                                        SharedPreferences mmPrefs = getSharedPreferences("IDvalue",0);

                                        String uid = mmPrefs.getString("uid", "a");
                                        cusDatabase.child(uid).push().setValue(dataMap);


                                        Query query2 = fDDatabase.child(mTripDate.toString()).child(str.toString()).orderByChild("driverid").equalTo(str.toString());

                                        query2.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()){
                                                    //maxid = dataSnapshot.getChildrenCount();
                                                    sp = getSharedPreferences("IDvalue", 0);
//                                                    String tripid = sp.getString("tripcost","DEFAULT");
                                                    String mTripDate = sp.getString("date","DEFAULT");
//                                                    Log.i("addeeeeeeeeeeeeee", tripid);
                                                    Log.e("addeeeeeeeeeeeeee", mTripDate);
                                                    sum_f = 0.0;
                                                    sumCommission_f = 0.0;
                                                    pTotal_earn_f = 0.0;
                                                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                                                        Map<String, Object> hMap = (Map<String, Object>) ds.getValue();
                                                        Object total_earn_1_f = hMap.get("triptotalcost");
                                                        Object total_commission_f = hMap.get("tripcommission");
                                                        pTotal_earn_f = Double.parseDouble(String.valueOf(total_earn_1_f));
                                                        pTotal_commission_f = Double.parseDouble(String.valueOf(total_commission_f));
                                                        sum_f = Math.round((sum_f+pTotal_earn_f) * 100.0) / 100.0;
                                                        sumCommission_f = Math.round((sumCommission_f+pTotal_commission_f) * 100.0) / 100.0;
                                                    }
                                                    HashMap<String, Object> dataMap3 = new HashMap<String, Object>();


                                                    dataMap3.put("driverid", (str.toString()));
                                                    dataMap3.put("tripstartdate", (mTripDate.toString()));
                                                    dataMap3.put("totalearning", sum_f);
                                                    dataMap3.put("totalcommission", sumCommission_f);
                                                    dataMap3.put("balance", Double.parseDouble(String.valueOf((sumCommission_f) - pPaid_commission_f)));



                                                    fDatabase.child(str.toString()).child(mTripDate.toString()).setValue(dataMap3);

                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

                                        Query query = mDatabase.orderByChild("driverid").equalTo(str.toString());

                                        query.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()){
                                                    //maxid = dataSnapshot.getChildrenCount();

                                                    sum = 0.0;
                                                    sumCommission = 0.0;
                                                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                                                        Map<String, Object> hMap = (Map<String, Object>) ds.getValue();
                                                        Object total_earn_1 = hMap.get("triptotalcost");
                                                        Object total_commission = hMap.get("tripcommission");
                                                        pTotal_earn = Double.parseDouble(String.valueOf(total_earn_1));
                                                        pTotal_commission = Double.parseDouble(String.valueOf(total_commission));
                                                        sum = Math.round((sum+pTotal_earn) * 100.0) / 100.0;
                                                        sumCommission = Math.round((sumCommission+pTotal_commission) * 100.0) / 100.0;

//                                                        total_earn.setText(String.valueOf(sum));
                                                    }
                                                    HashMap<String, Object> dataMap2 = new HashMap<String, Object>();

                                                    dataMap2.put("totalearning", Double.parseDouble(String.valueOf(sum )));
                                                    dataMap2.put("totalcommission", Double.parseDouble(String.valueOf(sumCommission)));
                                                    dataMap2.put("date", tripendDate);
                                                    dataMap2.put("driverid", str.toString());
                                                    dataMap2.put("paidcommission", pPaid_commission);
                                                    dataMap2.put("balance", Math.round((Double.parseDouble(String.valueOf((sumCommission) - pPaid_commission))) * 100.0) / 100.0);

                                                    dDatabase.child(str.toString()).setValue(dataMap2);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

//
//                                pPaid_commission_f = 0.00;
//                                pTotal_earn_f = 0.00;
//                                pTotal_commission_f = 0.00;
//                                sum_f = 0.00;
//                                sumCommission_f = 0.00;


                                        try {
                                            //ImageView image=new ImageView(DriverMapActivity.this);

//                                            WebView WebView1 = (WebView) findViewById(R.id.webview);
//                                            WebView1.loadUrl("file:///android_asset/sample.html");


                                            String to = cus_email;
                                            String subject = "NexRide Trip Details";
                                            String message =
                                                    //html template for receipt
                                                    "<!DOCTYPE html>\n" +
                                                            "<html lang=\"en\">\n" +
                                                            "<head>\n" +
                                                            "  <title>Bootstrap Example</title>\n" +
                                                            "  <meta charset=\"utf-8\">\n" +
                                                            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                                                            "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css\">\n" +
                                                            "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\n" +
                                                            "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>\n" +
                                                            "\n" +
                                                            "  <style type=\"text/css\">\n" +
                                                            "  @media only screen and (max-width: 400px) {\n" +
                                                            "\n" +
                                                            "  hr.style-eight {\n" +
                                                            "    height: 5px;\n" +
                                                            "    border: 1;\n" +
                                                            "    box-shadow: inset 0 9px 9px -3px #3498DB;\n" +
                                                            "    -webkit-border-radius: 5px;\n" +
                                                            "    -moz-border-radius: 5px;\n" +
                                                            "    -ms-border-radius: 5px;\n" +
                                                            "    -o-border-radius: 5px;\n" +
                                                            "    border-radius: 5px;\n" +
                                                            "  } \n" +
                                                            "\n" +
                                                            "  hr.style-two {\n" +
                                                            "    border: 0;\n" +
                                                            "    height: 0;\n" +
                                                            "    border-top: 1px solid rgba(0, 0, 0, 0.1);\n" +
                                                            "    border-bottom: 1px solid rgba(255, 255, 255, 0.3);\n" +
                                                            "  }\n" +
                                                            "}\n" +
                                                            "\n" +
                                                            " @media only screen and (min-width: 961px) {\n" +
                                                            "\n" +
                                                            "  hr.style-eight {\n" +
                                                            "    height: 5px;\n" +
                                                            "    border: 1;\n" +
                                                            "    box-shadow: inset 0 9px 9px -3px #3498DB;\n" +
                                                            "    -webkit-border-radius: 5px;\n" +
                                                            "    -moz-border-radius: 5px;\n" +
                                                            "    -ms-border-radius: 5px;\n" +
                                                            "    -o-border-radius: 5px;\n" +
                                                            "    border-radius: 5px;\n" +
                                                            "  } \n" +
                                                            "\n" +
                                                            "  hr.style-two {\n" +
                                                            "    border: 0;\n" +
                                                            "    height: 0;\n" +
                                                            "    border-top: 1px solid rgba(0, 0, 0, 0.1);\n" +
                                                            "    border-bottom: 1px solid rgba(255, 255, 255, 0.3);\n" +
                                                            "  }\n" +
                                                            "}\n" +
                                                            "\n" +
                                                            " @media only screen and (min-width: 401px) {\n" +
                                                            "\n" +
                                                            "  hr.style-eight {\n" +
                                                            "    height: 5px;\n" +
                                                            "    border: 1;\n" +
                                                            "    box-shadow: inset 0 9px 9px -3px #3498DB;\n" +
                                                            "    -webkit-border-radius: 5px;\n" +
                                                            "    -moz-border-radius: 5px;\n" +
                                                            "    -ms-border-radius: 5px;\n" +
                                                            "    -o-border-radius: 5px;\n" +
                                                            "    border-radius: 5px;\n" +
                                                            "  } \n" +
                                                            "\n" +
                                                            "  hr.style-two {\n" +
                                                            "    border: 0;\n" +
                                                            "    height: 0;\n" +
                                                            "    border-top: 1px solid rgba(0, 0, 0, 0.1);\n" +
                                                            "    border-bottom: 1px solid rgba(255, 255, 255, 0.3);\n" +
                                                            "  }\n" +
                                                            "}\n" +
                                                            "\n" +
                                                            "\n" +
                                                            "\n" +
                                                            "  </style>\n" +
                                                            "\n" +
                                                            "</head>\n" +
                                                            "<body>\n" +
                                                            "\n" +
                                                            "<div class=\"container\">\n" +
                                                            "  \n" +
                                                            "  <div class=\"row\">\n" +
                                                            "    <div class=\"col-xs-3 col-md-3\" style=\"background-color:#E4E9E9;\">\n" +
                                                            "    \n" +
                                                            "    </div>\n" +
                                                            "    <div class=\"col-xs-6 col-md-6\" style=\"background-color:#FDFEFE;\">\n" +
                                                            "<img src=\"https://i.ibb.co/VTCTdr7/Vehicle-Sticker-7-X-21-Inches-Selected-1-1.png\" alt=\"pageNo\" width=\"100%\" height=\"100%\"/>"+
                                                            "\n" +
                                                            "      <div class=\"col-xs-12 col-md-12 col-lg-12\" style=\"background-color:#3498DB;\">\n" +
                                                            "      </div>\n" +
                                                            "      \n" +
                                                            "      <blockquote>\n" +
                                                            "\n" +
                                                            "      <font style=\"font-size:15px;\">Trip ID\n" +
                                                            "        <span style=\"float:right;\">\n" +
                                                            String.valueOf(maxid) +
                                                            "        </span>\n" +
                                                            "      </font></br>\n" +
                                                            "      <hr class=\"style-two\">\n" +
                                                            "\n" +
                                                            "      <font style=\"font-size:15px;\">Date\n" +
                                                            "        <span style=\"float:right;\">\n" +
                                                            mTripDate.toString()+
                                                            "        </span>\n" +
                                                            "      </font></br>\n" +
                                                            "      <hr class=\"style-two\">\n" +
                                                            "\n" +
                                                            "      <font style=\"font-size:15px;\">Start time\n" +
                                                            "        <span style=\"float:right;\">\n" +
                                                            tripstarttime+
                                                            "        </span>\n" +
                                                            "      </font></br>\n" +
                                                            "      <hr class=\"style-two\">\n" +
                                                            "\n" +
                                                            "      <font style=\"font-size:15px;\">Finish time\n" +
                                                            "        <span style=\"float:right;\">\n" +
                                                            tripendtime+
                                                            "        </span>\n" +
                                                            "      </font></br>\n" +
                                                            "      <hr class=\"style-two\">\n" +

                                                            "\n" +
                                                            "      <font style=\"font-size:15px;\">Duration\n" +
                                                            "        <span style=\"float:right;\">\n" +
                                                            value2 +
                                                            "        </span>\n" +
                                                            "      </font></br>\n" +
                                                            "      <hr class=\"style-two\">\n" +
                                                            "\n" +
                                                            "      <font style=\"font-size:15px;\">From\n" +
                                                            "        <span style=\"float:right;\">\n" +
                                                            address+
                                                            "        </span>  \n" +
                                                            "      </font><br><br>\n" +
                                                            "      <hr class=\"style-eight\">\n" +
                                                            "\n" +
                                                            "      <font style=\"font-size:15px;\">To\n" +
                                                            "        <span style=\"float:right;\">\n" +
                                                            address2+
                                                            "        </span>\n" +
                                                            "      </font><br><br>\n" +
                                                            "      <hr class=\"style-eight\">\n" +
                                                            "    </br>"+
                                                            "\n" +
                                                            "      <font style=\"font-size:15px;\">Distance\n" +
                                                            "        <span style=\"float:right;\">\n" +
                                                            String.valueOf(distanceV)+
                                                            "        </span>\n" +
                                                            "      </font></br>\n" +
                                                            "      <hr class=\"style-two\">\n" +
                                                            "\n" +
                                                            "\n" +
                                                            "      <font style=\"font-size:15px;\">Distance Fare\n" +
                                                            "        <span style=\"float:right;\">\n" +
                                                            String.valueOf(mTripCostV)+
                                                            "        </span>\n" +
                                                            "      </font></br>\n" +
                                                            "      <hr class=\"style-two\">\n" +
                                                            "\n" +
                                                            "      <font style=\"font-size:15px;\">Waiting time\n" +
                                                            "         <span style=\"float:right;\">\n" +
                                                            value3+
                                                            "        </span> \n" +
                                                            "      </font></br>\n" +
                                                            "      <hr class=\"style-two\">\n" +
                                                            "\n" +
                                                            "      <font style=\"font-size:15px;\">Waiting Fare\n" +
                                                            "        <span style=\"float:right;\">\n" +
                                                            String.valueOf(mWaitingCostV)+
                                                            "        </span>\n" +
                                                            "      </font></br>\n" +
                                                            "      <hr class=\"style-two\">\n" +
                                                            "\n" +
                                                            "<div style=\"background-color:#018D09;\">"+
                                                            "      <hr class=\"style-two\">\n" +
                                                            "\n" +
                                                            "      <h1><b><font color=\"#FFFFFF\">Total\n<br>LKR <span style=\"float:right;\">"+String.valueOf(costTotalLast)+" </span>\n</h1>\"\n<br><font style=\"font-size:15px;\" color=\"#FFFFFF\">Pay by "+payment_method +"-"+input.getText().toString()+"</font>"+
                                                            "      </font> " +
                                                            "<br><br>  \n" +
                                                            "<font color=\"#FFFFFF\">"+
                                                            "        1st "+str_vehicle_initial_distance+"KM--------LKR <span style=\"float:right;\"> "+vehicle_initial_charge+" </span><br>\n" +
                                                            "        Next 1KM -------LKR<span style=\"float:right;\"> "+vehicle_charge+"</span><br>\n" +
                                                            "        Waiting 1minute-LKR<span style=\"float:right;\"> "+vehicle_waiting_cost+"</span>"+
                                                            "</font>"+
                                                            "<hr class=\"style-eight\" />\n" +
                                                            "\n" +
                                                            "        \n" +
                                                            "</div>"+
                                                            "        <h3><b><b></h3>\n" +
                                                            "          <font style=\"font-size:15px;\" color=\"black\"> \n" +
                                                            "<center><img src=\"https://carlisletheacarlisletheatre.org/images/avatar-logo-user.jpg\" alt=\"pageNo\" width=\"30%\" height=\"30%\"/></center>"+
                                                            "              Driver Name - "+ str_d_name +"\n" +
                                                            "              <br>  \n" +
                                                            "              Driver ID - "+ str.toString() +"\n" +
                                                            "              <br>  \n" +
                                                            "              Vehicle Type - "+vehicletype+"\n" +
                                                            "              <br>  \n" +
                                                            "              Vehicle Plate Number - "+plateno+"\n" +
                                                            "          </font>       \n" +
                                                            "        \n" +
                                                            "         \n" +
                                                            "<hr class=\"style-eight\" />"+
                                                            "        <h3><b>NexRide Service<b></h3>\n" +
                                                            "          <font style=\"font-size:15px;\" color=\"black\"> \n" +
                                                            "        <h1><b>Thanks for riding, </b></h1>\n" +
                                                            "        We hope you enjoyed your ride</br>\n" +
                                                            "\n" +
                                                            "    </div>\n" +
                                                            "    <div class=\"col-xs-3 col-md-3\" style=\"background-color:#E4E9E9;\">\n" +
                                                            "\n" +
                                                            "    </div>\n" +
                                                            "  </div>\n" +
                                                            "\n" +
                                                            "\n" +
                                                            "</div>\n" +
                                                            "\n" +
                                                            "    \n" +
                                                            "</body>\n" +
                                                            "</html>";

                                            if(to.isEmpty()){
                                                Toast.makeText(TripStatusNo.this, "You must enter a recipient email", Toast.LENGTH_LONG).show();
                                            }else if(subject.isEmpty()){
                                                Toast.makeText(TripStatusNo.this, "You must enter a Subject", Toast.LENGTH_LONG).show();
                                            }else if(message.isEmpty()){
                                                Toast.makeText(TripStatusNo.this, "You must enter a message", Toast.LENGTH_LONG).show();
                                            }else {
                                                //everything is filled out
                                                //send email
                                                new GMailSender().sendEmail(to, subject, message);
                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();

                                        }

                                        distance = 0.0;
                                        distanceV = 0.0;
                                        status = 0;
                                        s1 = 0;
                                        distanceOld = 0.00;
                                        distanceOld1 = 0.00;
                                        distanceVincenty = 00.00;
                                        costtotalfunc = 0.00;
                                        costtotalinit = 0.00;
                                        costTotalLast = 0.00;
                                        tripCost = 0.00;
                                        waitingCost = 0.00;

                                        tripCommission = 0.00;

                                        mTripCostV = 0.00;
                                        mWaitingCostV = 0.00;

//                                        Toast.makeText(DriverMapActivity.this, "Inserted", Toast.LENGTH_SHORT).show();

                                        disconnectDriver();
                                        // deleting current hiring drivers data
//                                        SharedPreferences mPrefs = getSharedPreferences("IDvalue",0);
//                                        str = mPrefs.getInt("DriverIDValue", 0);
                                        firebaseHelper = new FirebaseHelper(str.toString());
                                        firebaseHelper.deleteHiringDriver();
                                        Intent intent = new Intent(TripStatusNo.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, null).show();




                    }
                });

                AlertDialog dialoglast = builder.create();
                            dialoglast.getWindow().setGravity(Gravity.TOP);
                dialoglast.show();

                        }else{
                            Toast.makeText( TripStatusNo.this, "There are no any failed trips!",
                                    Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });

    }
    public void disconnectDriver(){
        SharedPreferences mPrefs = getSharedPreferences("IDvalue",0);
        String tripid = mPrefs.getString("tripid","DEFAULT");
        track_Database.child(tripid).child("status").setValue(track_status2);

        unFinished_Database.child(String.valueOf(str)).removeValue();
        maxidstatus = 0;
//        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("DriverAvailabilityNew");

//        GeoFire geoFire = new GeoFire(ref);
//        geoFire.removeLocation(userId);
        SharedPreferences mPref_packageid = getSharedPreferences("driverPackageid", 0);
        str_package_id = mPref_packageid.getString("pkgid", "");
        ref.child(String.valueOf(str)).child("latitude1").removeValue();
        ref.child(String.valueOf(str)).child("longitude1").removeValue();
        ref.child(String.valueOf(str)).child("latitude2").removeValue();
        ref.child(String.valueOf(str)).child("longitude2").removeValue();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("packageassign");

        reference.orderByChild("driverid").equalTo(String.valueOf(str)).addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    String status= Objects.requireNonNull(datas.child("tripstatus").getValue()).toString();
                    if(status.equals("Running")){
                        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("assignedpackagecompleted");
                        DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference("packageassign");
                        if(!str_package_id.equals("PKG001")){
                            ref2.child(String.valueOf(maxid)).child("pkgid").setValue(str_package_id+" - Completed");
                            ref2.child(String.valueOf(maxid)).child("tripstatus").setValue("Completed");
                            ref2.child(String.valueOf(maxid)).child("driverid").setValue(str+"-Completed");

                            ref4.child(String.valueOf(str)).child("tripstatus").setValue("Completed");
                            ref4.child(String.valueOf(str)).child("pkgid").setValue(str_package_id+" - Completed");
                        }

                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("CustomerRideAssign");
        ref3.child(String.valueOf(str)).child("tripstatus").setValue("Completed");
        ref3.child(String.valueOf(str)).child("pkgid").setValue(str_package_id+" - Completed");

    }

    private void firebaseUserSearch(int s) {

//        final Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference("DriverHistory_Model").child("1");
        final Query firebaseSearchQuery2 = FirebaseDatabase.getInstance().getReference("UnFinishedRides").orderByChild("driverid").equalTo(s);


        FirebaseRecyclerAdapter<TripStatusNo_Model, TripStatusViewHolder> firebaseRecyclerAdapter2 = new FirebaseRecyclerAdapter<TripStatusNo_Model, TripStatusViewHolder>(
                TripStatusNo_Model.class,
                R.layout.tripstatus_no,
                TripStatusViewHolder.class,
                firebaseSearchQuery2
        ) {
            @Override
            protected void populateViewHolder(TripStatusViewHolder viewHolder, TripStatusNo_Model model, int position) {


                viewHolder.setDetails(model.getDriverid(),model.getTripid(),
                        model.getStartaddress(), model.getEndaddress(), model.getTripcost(),
                        model.getdate(),model.getTripstarttime() , model.getTriptime()
                        , model.getTotaldistance(), model.getWaitingtime()
                        ,  model.getWaitingcost()
                );

                SharedPreferences sp;
                sp = getSharedPreferences("IDvalue", 0);
                SharedPreferences.Editor editor = sp.edit();
                String tripId = String.valueOf(model.getTripid());
                editor.putString("tripid",tripId);
                editor.putString("tripstartaddress",model.getStartaddress());
                editor.putString("tripendaddress",model.getEndaddress());
                editor.putString("tripcost", String.valueOf(model.getTripcost()));
                editor.putString("date",model.getdate());
                editor.putString("totaldistance",model.getTotaldistance());
                editor.putString("waitingtime",model.getWaitingtime());
                editor.putString("waitingcost",model.getWaitingcost());
                editor.putString("tripTime",model.getTriptime());
                editor.putString("starttime",model.getTripstarttime());
                editor.apply();
            }
        };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter2);

//        Toast.makeText(TripStatusNo.this, "Started setAdapter", Toast.LENGTH_LONG).show();
    }

    public static class TripStatusViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public TripStatusViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        @SuppressLint("SetTextI18n")
        public void setDetails(int driverid, int tripid,String tripstartaddress,
                               String tripendaddress, double tripcost, String date,
                               String tripstarttime, String triptime
                , String totaldistance, String waitingtime
                , String waitingcost
        )
        {
//            SharedPreferences sp;
//            sp = getSharedPreferences("IDvalue", 0);
//                SharedPreferences.Editor editor = sp.edit();
//                String tripId = String.valueOf(tripid);
//                editor.putString("tripid",tripId);
//                editor.putString("tripstartaddress",tripstartaddress);
//                editor.putString("tripendaddress",tripendaddress);
//                editor.putString("tripcost", String.valueOf(tripcost));
//                editor.putString("totaldistance",totaldistance);
//                editor.putString("waitingtime",waitingtime);
//                editor.putString("waitingcost",waitingcost);
//                editor.apply();
            TextView user_name5 = (TextView) mView.findViewById(R.id.trip_date);
            user_name5.setText(date);

            TextView user_name = (TextView) mView.findViewById(R.id.history_text);
            String text = " "+ tripstartaddress
//                    "<font color=#FF0000><br> To:- </font>" + tripendaddress
                    ;
            user_name.setText((text)
            );
//              user_name.setText("From " + tripstartaddress + "\nTo " + tripendaddress);



            TextView user_name2 = (TextView) mView.findViewById(R.id.history_time);
            user_name2.setText("" + tripstarttime + "");
//
            TextView user_name3 = (TextView) mView.findViewById(R.id.trip_id);
            user_name3.setText("Trip ID: " + tripid);
//
            TextView user_name6 = (TextView) mView.findViewById(R.id.trip_total_cost);
            user_name6.setText("Trip Cost Rs: " + tripcost+"0");

//            TextView user_name7 = (TextView) mView.findViewById(R.id.driver_id);
//            user_name7.setText("Driver ID " + driverid);

            TextView user_name9 = (TextView) mView.findViewById(R.id.payment_method);
            user_name9.setText("Trip time: "+triptime);

            TextView user_name8 = (TextView) mView.findViewById(R.id.total_distance);
            user_name8.setText("Total distance: "+totaldistance +" km");

            TextView user_name10 = (TextView) mView.findViewById(R.id.waiting_cost);
            user_name10.setText("Waiting cost: Rs: "+waitingcost+"0");

            TextView user_name11 = (TextView) mView.findViewById(R.id.waiting_time);
            user_name11.setText("Waiting time: "+waitingtime);

            TextView user_name12 = (TextView) mView.findViewById(R.id.endAddress);
            user_name12.setText(""+tripendaddress);

//            if (user_name8.getText().toString().equals("Cash")){
//                user_name8.setTextColor(Color.parseColor("#2B9E08"));
////                user_name8.setText(paymentmethod);
//            }else if (user_name8.getText().toString().equals("Credit")){
//                user_name8.setTextColor(Color.parseColor("#0000FF"));
////                user_name8.setText(paymentmethod);
//            }else {
//                user_name8.setTextColor(Color.parseColor("#FF0000"));
////                user_name8.setText(paymentmethod);
//            }
        }
    }
}

