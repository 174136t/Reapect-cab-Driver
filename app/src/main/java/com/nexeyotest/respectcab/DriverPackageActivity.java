package com.nexeyotest.respectcab;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nexeyo.respectcab.R;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.mail.Session;

public class DriverPackageActivity<callStateListener> extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener, NavigationView.OnNavigationItemSelectedListener {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private DatabaseReference fDDatabase;
    private DatabaseReference fDatabase;
    private DatabaseReference dDatabase;
    public DatabaseReference track_Database;
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
    TextView mDistanceVin, Ttotalcost, mWaitingCost, mTripCost, mTripDate;
    public TextView total_earn;

    public String str_vehicle_type;
    public String str_vehicle_plate;

    private DrawerLayout drawer;
    private long startTime = 0L;
    private long startTime2 = 0L;
    private long startTime3 = 0L;
    private Handler customHandler = new Handler();
    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(startTime), TimeUnit.MILLISECONDS.toMinutes(startTime) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(startTime)), TimeUnit.MILLISECONDS.toSeconds(startTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(startTime)));
    long timeInMilliseconds = 0L;

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

    static Double p = 3600.00;
    static Double x = 0.00;
    static Double y = 0.00;
    static Double z = 0.00;

    long maxid = 0;
    int maxidstatus = 0;
    public Integer str;
    public String value;
    public String value2;
    public String value3;
    public String tripstarttime;
    public String str_d_name;

    //    public String str_vehicle_type;
//    public String str_vehicle_plate;
//    public String str_vehicle_charge;
    public String str_package_initial_charge;
    public String str_package_initial_distance;
    public String str_package_waiting_cost;

    public double package_charge;
    public double package_initial_charge;
    public double package_initial_distance;
    public double package_waiting_cost;

    public String str_package_initialhours;
    public String str_package_nexthourcost;
    public String str_package_nexthours;
    public String str_package_waitingcost;
    public String str_package_cost_nextdistance;
    public String str_package_customer_email;
    public String str_package_id;

   // long package_initialhours = 0L;
    public double package_initialhours;
    public double package_nexthourcost;
    public double package_nexthours;
    public double package_waitingcost;
    public double package_cost_nextdistance;

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


    public String str_package_type;
    public String str_package_plate;

    public String str_package_charge;
    public double result;

    String track_status = "No";
    String track_status2 = "Yes";
    SharedPreferences sp;
    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    String rec, subject, textmessage;
    String cus_email;

    String packageDetails;
    String status_waiting = "null";

    DatabaseReference mRef;
    String StatusCost = "1";
    int flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        startForegroundService();
        super.onCreate(savedInstanceState);
        //Configuration config = getResources().getConfiguration();
        //        Display display = getWindowManager().getDefaultDisplay();
        //        int width = display.getWidth();
        //        int height = display.getHeight();

        //set layout for each screen size
        setContentView(R.layout.activity_driver_package);

        context = this;


        //mRef =  FirebaseDatabase.getInstance().getReference("Driver_Earning_And_Commission2").child(String.valueOf(str));


        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPlateno", MODE_APPEND);
        @SuppressLint("WrongConstant") SharedPreferences sh2 = getSharedPreferences("MySharedvehicletype", MODE_APPEND);


        final String plateno = sh.getString("plate_no", "");
        final String vehicletype = sh2.getString("vehicletype", "");

//        name.setText(s1);




        SharedPreferences mPrefs = getSharedPreferences("IDvalue",0);
        str = mPrefs.getInt("DriverIDValue", 0);
        str_d_name = mPrefs.getString("drivername", "Unknown");

        SharedPreferences mPrefsvehicle = getSharedPreferences("vehicledetails", 0);
        //str_vehicle_type = mPrefsvehicle.getString("cartype", "Unknown");
        str_package_plate = mPrefsvehicle.getString("carplate", "Unknown");

        SharedPreferences mPrefsvehiclecharge = getSharedPreferences("driverPackageDetails", 0);
        str_package_initial_charge = mPrefsvehiclecharge.getString("initialcost", "0.00");
        str_package_initial_distance = mPrefsvehiclecharge.getString("initialdistance", "0.00");
        str_package_initialhours = mPrefsvehiclecharge.getString("initialhours", "0.00");
        str_package_nexthourcost = mPrefsvehiclecharge.getString("nexthourcost", "0.00");
        str_package_nexthours = mPrefsvehiclecharge.getString("nexthours", "0.00");
        str_package_waitingcost = mPrefsvehiclecharge.getString("waitingcost", "0.00");
        str_package_cost_nextdistance = mPrefsvehiclecharge.getString("nextcostfordistance", "0.00");
       // str_vehicle_type = mPrefsvehiclecharge.getString("vehicletype", "Unknown");

        SharedPreferences mPref_customer_email = getSharedPreferences("customer_email_Package", 0);
        str_package_customer_email = mPref_customer_email.getString("customer_email_Package", "");


        SharedPreferences mPref_packageid = getSharedPreferences("driverPackageid", 0);
        str_package_id = mPref_packageid.getString("pkgid", "");



        package_initial_charge = Double.valueOf(str_package_initial_charge);
        package_initial_distance = Double.valueOf(str_package_initial_distance);
        package_initialhours = Double.valueOf(str_package_initialhours);
        package_nexthourcost = Double.valueOf(str_package_nexthourcost);
        package_nexthours = Double.valueOf(str_package_nexthours);
        package_waitingcost = Double.valueOf(str_package_waitingcost);
        package_cost_nextdistance = Double.valueOf(str_package_cost_nextdistance);

        final TextView driver_id = (TextView) findViewById(R.id.driver_id_trip);
        driver_id.setText(""+ str);

       //SharedPreferences mPrefsvehicle = getSharedPreferences("vehicledetails", 0);
        //str_vehicle_plate = mPrefsvehicle.getString("carplate", "Unknown");
        //str_package_plate = mPrefsvehicle.getString("carplate", "Unknown");
        //str_vehicle_type = mPrefsvehiclecharge.getString("cartype", "unknown");
        SharedPreferences mPrefs2 = getSharedPreferences("Customervalue",0);
        final String cus_mobile = mPrefs2.getString("CustomermobileValue", "No Value Mobile");
        cus_email = mPrefs2.getString("CustomeremailValue", "No Value Email");
        final TextView customer_mobile = (TextView) findViewById(R.id.cus_mobile);
        final TextView customer_email = (TextView) findViewById(R.id.cus_email);
        customer_mobile.setText(cus_mobile);
        customer_email.setText(cus_email);
//
//        SharedPreferences mPrefs3 = getSharedPreferences("TripPackage",0);
//        packageDetails = mPrefs3.getString("TripPackageValue", "123");

        //Navigation Drawer
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //mLogout = (Button) findViewById(R.id.logout);
        mStart = (Button) findViewById(R.id.start);
        mEnd = (Button) findViewById(R.id.End);
        mWaiting = (Button) findViewById(R.id.waiting);
        mPause = (Button) findViewById(R.id.pause);
        //        mDistance = (EditText) findViewById(R.id.distance);
        //        mlat1 = (TextView) findViewById(R.id.twlat1);
        //        mlat2 = (TextView) findViewById(R.id.twlat2);
        //        mlon1 = (TextView) findViewById(R.id.twlon1);
        //        mlon2 = (TextView) findViewById(R.id.twlon2);
        mTimer1 = (TextView) findViewById(R.id.timerValue);
        mTimer2 = (TextView) findViewById(R.id.timerValue2);
        mSpeed = (TextView) findViewById(R.id.speed);

        mForceStop = (Button) findViewById(R.id.forcestop);
        mForceStart = (Button) findViewById(R.id.forcestart);

        mWaitingCost = (TextView) findViewById(R.id.Waitingcost);
        mTripCost = (TextView) findViewById(R.id.Tripcost);
        //        mDistanceold = (EditText) findViewById(R.id.distanceold);
        //        mDistanceold1 = (EditText) findViewById(R.id.distanceold1);
        mDistanceVin = (TextView) findViewById(R.id.distanceV);
        Ttotalcost = (TextView) findViewById(R.id.totalcost);
        mEmail = (TextView) findViewById(R.id.email);
        mTripDate = (TextView) findViewById(R.id.trip_date);
        total_earn = (TextView) findViewById(R.id.total_earn);

        mWorkingDriver = (Switch) findViewById(R.id.workingSwitch);

        mWorkingDriver.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    connectDriver();

                    startTime = SystemClock.uptimeMillis();
                    customHandler.postDelayed(updateTimerThread, 0);
                    mEnd.setEnabled(true);
                    mStart.setVisibility(View.INVISIBLE);
                    mEnd.setVisibility(View.VISIBLE);
                    //mWaiting.setVisibility(View.VISIBLE);
                    //mWaiting.setEnabled(true);
                    //mPause.setVisibility(View.INVISIBLE);
                    //mPause.setEnabled(false);

                    mForceStop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            status_waiting = "stop";
                            mSpeed.setBackgroundColor(Color.BLUE);
                            timeSwapBuff2 = timeSwapBuff2 + timeInMilliseconds2;
                            customHandler2.removeCallbacks(updateTimerThread2);
                            startTime2status = 1;
                            mForceStart.setEnabled(true);
                            mForceStop.setEnabled(false);
                            mForceStart.setVisibility(View.VISIBLE);
                            mForceStop.setVisibility(View.INVISIBLE);

                        }
                    });

                    mForceStart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            status_waiting = "null";
                            mSpeed.setBackgroundColor(Color.WHITE);
                        }
                    });


                    mWaiting.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (startTime2 == 0) {
                                startTime2 = SystemClock.uptimeMillis();
                                customHandler2.postDelayed(updateTimerThread2, 0);

                                mWaiting.setEnabled(false);
                                mPause.setEnabled(true);
                                mForceStop.setVisibility(View.VISIBLE);
                                mForceStop.setEnabled(true);
                                mForceStart.setVisibility(View.INVISIBLE);
                                mForceStart.setEnabled(false);
                                //mWaiting.setVisibility(View.INVISIBLE);
                                //mPause.setVisibility(View.VISIBLE);
//                              waitingCost = (int) ((startTime2/(60*1000))*4);
                            }

                            if (startTime2 > 0 && startTime2status == 1) {
                                startTime2 = SystemClock.uptimeMillis();
                                customHandler2.postDelayed(updateTimerThread2, 0);

                                mWaiting.setEnabled(false);
                                mPause.setEnabled(true);
                                //mWaiting.setVisibility(View.INVISIBLE);
                                //mPause.setVisibility(View.VISIBLE);
                                startTime2status = 0;
                                mForceStop.setVisibility(View.VISIBLE);
                                mForceStop.setEnabled(true);
                                mForceStart.setVisibility(View.INVISIBLE);
                                mForceStart.setEnabled(false);

//                        waitingCost = (int) ((startTime2/(60*1000))*4);
                            }
                        }
                    });
                    mPause.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            status_waiting = "null";
                            timeSwapBuff2 = timeSwapBuff2 + timeInMilliseconds2;
                            customHandler2.removeCallbacks(updateTimerThread2);
                            startTime2status = 1;
                            mWaiting.setEnabled(true);
                            mPause.setEnabled(false);
                            //mWaiting.setVisibility(View.VISIBLE);
                            //mPause.setVisibility(View.INVISIBLE);
                            mForceStop.setVisibility(View.INVISIBLE);
                            mForceStart.setVisibility(View.VISIBLE);
                            mForceStop.setEnabled(false);
                            mForceStart.setEnabled(true);
                        }
                    });

                }else{

                    mEnd.performClick();
                }
            }
        });

        Date d = new Date();

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        final String formattedDate = df.format(c.getTime());
        mTripDate.setText(formattedDate);
        tripstarttime = d.getHours() + ":" + d.getMinutes();

        mDatabase = FirebaseDatabase.getInstance().getReference("DriverHistory_Model");
        fDDatabase = FirebaseDatabase.getInstance().getReference("DriverHistory_Model Date Wise");
        fDatabase = FirebaseDatabase.getInstance().getReference("Date wise Driver Report");
        dDatabase = FirebaseDatabase.getInstance().getReference("Driver_Earning_And_Commission2");
        track_Database = FirebaseDatabase.getInstance().getReference("DriverTracking");



        track_Database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()&& maxidstatus==0){
                    maxid = dataSnapshot.getChildrenCount();
                    maxidstatus++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        Query query = mDatabase.orderByChild("driverid").equalTo(driver_id.getText().toString());
//
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()){
//                    //maxid = dataSnapshot.getChildrenCount();
//
//                    sum = 0.0;
//                    sumCommission = 0.0;
//                    for (DataSnapshot ds: dataSnapshot.getChildren()){
//                        Map<String, Object> hMap = (Map<String, Object>) ds.getValue();
//                        Object total_earn_1 = hMap.get("triptotalcost");
//                        Object total_commission = hMap.get("tripcommission");
//                        pTotal_earn = Double.parseDouble(String.valueOf(total_earn_1));
//                        pTotal_commission = Double.parseDouble(String.valueOf(total_commission));
//                        sum = Math.round((sum+pTotal_earn) * 100.0) / 100.0;
//                        sumCommission = Math.round((sumCommission+pTotal_commission) * 100.0) / 100.0;
//
//                        total_earn.setText(String.valueOf(sum));
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


//        Query query2 = fDDatabase.child(mTripDate.getText().toString()).child(driver_id.getText().toString()).orderByChild("driverid").equalTo(driver_id.getText().toString());
//
//        query2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()){
//                    //maxid = dataSnapshot.getChildrenCount();
//
//                    sum_f = 0.0;
//                    sumCommission_f = 0.0;
//                    for (DataSnapshot ds: dataSnapshot.getChildren()){
//                        Map<String, Object> hMap = (Map<String, Object>) ds.getValue();
//                        Object total_earn_1_f = hMap.get("triptotalcost");
//                        Object total_commission_f = hMap.get("tripcommission");
//                        pTotal_earn_f = Double.parseDouble(String.valueOf(total_earn_1_f));
//                        pTotal_commission_f = Double.parseDouble(String.valueOf(total_commission_f));
//                        sum_f = Math.round((sum_f+pTotal_earn_f) * 100.0) / 100.0;
//                        sumCommission_f = Math.round((sumCommission_f+pTotal_commission_f) * 100.0) / 100.0;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        FirebaseDatabase.getInstance().getReference("Driver_Earning_And_Commission2").child(String.valueOf(str)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                FinanceBalance_Model fin = snapshot.getValue(FinanceBalance_Model.class);
                pPaid_commission = Math.round((Double.valueOf(fin.getPaidcommission())) * 100.0) / 100.0;

                //System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        mEnd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                final String[] listItems = {"Cash", "Credit card", "Vouchers"};

                final AlertDialog.Builder builder = new AlertDialog.Builder(DriverPackageActivity.this,R.style.MaterialThemeDialog);
                final EditText input = new EditText(DriverPackageActivity.this);
                builder.setView(input);
                input.setVisibility(View.INVISIBLE);
                input.setHint("Enter voucher code here");

                final TextView myMsg = new TextView(DriverPackageActivity.this);
                myMsg.setText("Total fare -"+String.valueOf(costTotalLast));
                myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
                myMsg.setTextSize(30);
                myMsg.setTextColor(Color.BLUE);

                final TextView confirm_title = new TextView(DriverPackageActivity.this);
                confirm_title.setText("     Respect cab service");
                confirm_title.setTextSize(20);
                confirm_title.setTextColor(Color.BLUE);


                builder.setCustomTitle(myMsg);

//                builder.setTitle(myMsg+"Choose Payment Method");

                int checkedItem = 10; //this will checked the item when user open the dialog
                builder.setSingleChoiceItems(listItems, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DriverPackageActivity.this, "Position: " + which + " Value: " + listItems[which], Toast.LENGTH_LONG).show();
                        payment_method = listItems[which];

                        if (payment_method==listItems[2]){
                            input.setVisibility(View.VISIBLE);
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

                        final AlertDialog.Builder alert = new AlertDialog.Builder(DriverPackageActivity.this,R.style.MaterialThemeDialog);
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


                                        //value = input.getText().toString().trim();
                                        value2 = mTimer1.getText().toString().trim();
                                        value3 = mTimer2.getText().toString().trim();
                                        String id = mDatabase.push().getKey();

                                        tripCommission1 = costTotalLast * 10 / 100;
                                        tripCommission = Math.round(tripCommission1 * 100.0) / 100.0;
                                        dTripCommission = dTripCommission + tripCommission;

                                        HashMap<String, Object> dataMap = new HashMap<String, Object>();
                                        //HashMap<String, Object> dataMap2 = new HashMap<String, Object>();

                                        dataMap.put("tripid", String.valueOf(maxid));
                                        dataMap.put("driverid", driver_id.getText().toString());
                                        dataMap.put("distancecarmeter", value);
                                        dataMap.put("distanceapplication", String.valueOf(distanceV));
                                        dataMap.put("tripcommission", tripCommission);
                                        dataMap.put("triptotalcost", costTotalLast);
                                        dataMap.put("triptime", value2);
                                        dataMap.put("tripcost", String.valueOf(mTripCostV));
                                        dataMap.put("waitingtime", value3);
                                        dataMap.put("waitingcost", String.valueOf(mWaitingCostV));
                                        dataMap.put("customermobile",customer_mobile.getText().toString().trim());
                                        dataMap.put("customeremail",customer_email.getText().toString().trim());
                                        dataMap.put("tripstartdate",mTripDate.getText().toString());
                                        dataMap.put("tripenddate", tripendDate);
                                        dataMap.put("tripstarttime", tripstarttime);
                                        dataMap.put("tripendtime", tripendtime);
                                        dataMap.put("startaddress", address);
                                        dataMap.put("endaddress", address2);
                                        dataMap.put("endaddress", address3);
                                        dataMap.put("paymentmethod", payment_method);
                                        dataMap.put("vouchercode", input.getText().toString());

                                        mDatabase.child(String.valueOf(maxid)).setValue(dataMap);

                                        fDDatabase.child(mTripDate.getText().toString()).child(driver_id.getText().toString()).child(String.valueOf(maxid)).setValue(dataMap);



                                        Query query2 = fDDatabase.child(mTripDate.getText().toString()).child(driver_id.getText().toString()).orderByChild("driverid").equalTo(driver_id.getText().toString());

                                        query2.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()){
                                                    //maxid = dataSnapshot.getChildrenCount();

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


                                                    dataMap3.put("driverid", (driver_id.getText().toString()));
                                                    dataMap3.put("tripstartdate", (mTripDate.getText().toString()));
                                                    dataMap3.put("totalearning", sum_f);
                                                    dataMap3.put("totalcommission", sumCommission_f);
                                                    dataMap3.put("balance", Double.parseDouble(String.valueOf((sumCommission_f) - pPaid_commission_f)));



                                                    fDatabase.child(driver_id.getText().toString()).child(mTripDate.getText().toString()).setValue(dataMap3);

                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

                                        Query query = mDatabase.orderByChild("driverid").equalTo(driver_id.getText().toString());

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

                                                        total_earn.setText(String.valueOf(sum));
                                                    }
                                                    HashMap<String, Object> dataMap2 = new HashMap<String, Object>();

                                                    dataMap2.put("totalearning", Double.parseDouble(String.valueOf(sum + pTotal_earn)));
                                                    dataMap2.put("totalcommission", Double.parseDouble(String.valueOf(sumCommission)));
                                                    dataMap2.put("date", tripendDate);
                                                    dataMap2.put("driverid", driver_id.getText().toString());
                                                    dataMap2.put("paidcommission", pPaid_commission);
                                                    dataMap2.put("balance", Math.round((Double.parseDouble(String.valueOf((sumCommission) - pPaid_commission))) * 100.0) / 100.0);

                                                    dDatabase.child(driver_id.getText().toString()).setValue(dataMap2);
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


                                            String to = str_package_customer_email;
                                            String subject = "Respect Cab Service Trip Details";
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
                                                            mTripDate.getText().toString()+
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
                                                            "<div style=\"background-color:#0040FF;\">"+
                                                            "      <hr class=\"style-two\">\n" +
                                                            "\n" +
                                                            "      <h1><b><font color=\"#FFFFFF\">Total\n<br>LKR <span style=\"float:right;\">"+String.valueOf(costTotalLast)+" </span>\n</h1>\"\n<br><font style=\"font-size:15px;\" color=\"#FFFFFF\">Pay by "+payment_method +"-"+input.getText().toString()+"</font>"+
                                                            "      </font> " +
                                                            "<br><br>  \n" +
                                                            "<font color=\"#FFFFFF\">"+
                                                            "        1st "+package_initial_distance+"KM--------LKR <span style=\"float:right;\"> "+package_initial_charge+" </span><br>\n" +
                                                            "        Next 1KM -------LKR<span style=\"float:right;\"> "+str_package_cost_nextdistance+"</span><br>\n" +
                                                            "        Waiting 1minute-LKR<span style=\"float:right;\"> "+package_waitingcost+"</span>"+
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
                                                            "              Driver ID - "+ driver_id.getText().toString() +"\n" +
                                                            "              <br>  \n" +
                                                            "              Vehicle_Model type - "+vehicletype+"\n" +
                                                            "              <br>  \n" +
                                                            "             Vehicle_Model number - "+plateno+"\n" +
                                                            "          </font>       \n" +
                                                            "        \n" +
                                                            "         \n" +
                                                            "<hr class=\"style-eight\" />"+
                                                            "        <h3><b>Respect Cab Service<b></h3>\n" +
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
                                                Toast.makeText(DriverPackageActivity.this, "You must enter a recipient email", Toast.LENGTH_LONG).show();
                                            }else if(subject.isEmpty()){
                                                Toast.makeText(DriverPackageActivity.this, "You must enter a Subject", Toast.LENGTH_LONG).show();
                                            }else if(message.isEmpty()){
                                                Toast.makeText(DriverPackageActivity.this, "You must enter a message", Toast.LENGTH_LONG).show();
                                            }else {
                                                //everything is filled out
                                                //send email
                                                new GMailSender().sendEmail(to, subject, message);
                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();

                                        }


//                                        FirebaseDatabase.getInstance().getReference("Driver_Earning_And_Commission2").child(String.valueOf(str)).child("latitude1").removeValue();
//                                        FirebaseDatabase.getInstance().getReference("Driver_Earning_And_Commission2").child(String.valueOf(str)).child("longitude1").removeValue();
//                                        FirebaseDatabase.getInstance().getReference("Driver_Earning_And_Commission2").child(String.valueOf(str)).child("latitude2").removeValue();
//                                        FirebaseDatabase.getInstance().getReference("Driver_Earning_And_Commission2").child(String.valueOf(str)).child("longitude2").removeValue();

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

                                        Toast.makeText(DriverPackageActivity.this, "Inserted", Toast.LENGTH_SHORT).show();

                                        disconnectDriver();

                                        Intent intent = new Intent(DriverPackageActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .setNegativeButton(android.R.string.no, null).show();




                    }
                });

                AlertDialog dialoglast = builder.create();
                dialoglast.show();
            }
        });

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStart.setVisibility(View.INVISIBLE);
                mEnd.setVisibility(View.VISIBLE);
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);
                mStart.setEnabled(false);
                mEnd.setEnabled(true);
                mEnd.setVisibility(View.VISIBLE);


            }
        });
//
//        mEnd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
//
//    private void startForegroundService() {
//        startService (new Intent(DriverMapActivity.this, onAppKilled.class));
//    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        buildGoogleApiClient();
        mMap.setMyLocationEnabled(true);

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(location.getLatitude(), location.getLongitude()))
//                .title("Spider Man")
//                .icon(BitmapDescriptorFactory.defaultMarker()));

        if (status_waiting.equals("null")) {
            if (location.hasSpeed() && location.getSpeed() > 0.1) {

                speed = (int) ((location.getSpeed() * 18) / 5);
                mSpeed.setText(String.valueOf(speed));

                if (startTime2status == 0) {
                    if (mPause.performClick() == false) {
                        mSpeed.setTextColor(Color.parseColor("#d6d6c2"));
                        mPause.performClick();
                        pspeed1 = speed;
                    }
                }

            } else {

                speed = 0;
                mSpeed.setText(String.valueOf(speed));
                mSpeed.setTextColor(Color.parseColor("#FF0000"));

                if (mWaiting.performClick() == false) {
                    mWaiting.performClick();
                    pspeed1 = speed;
                }
            }
        }else{
            if (location.hasSpeed() && location.getSpeed() > 0.1) {

                speed = (int) ((location.getSpeed() * 18) / 5);
                mSpeed.setText(String.valueOf(speed));

                if (startTime2status == 0) {
                    if (mPause.performClick() == false) {
                        mSpeed.setTextColor(Color.parseColor("#d6d6c2"));
                        mPause.performClick();
                        pspeed1 = speed;
                    }
                }

            } else {

                speed = 0;
                mSpeed.setText(String.valueOf(speed));
                mSpeed.setTextColor(Color.parseColor("#FF0000"));

                if (startTime2status == 0) {
                    if (mPause.performClick() == false) {
                        mSpeed.setTextColor(Color.parseColor("#d6d6c2"));
                        mPause.performClick();
                        pspeed1 = speed;
                    }
                }
            }
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));


        String userId = FirebaseAuth.getInstance().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("DriverAvailabilityNew");

//            GeoFire geoFire = new GeoFire(ref);
//            geoFire.setLocation(userId, new GeoLocation(location.getLatitude(), location.getLongitude()));

        ref.child(String.valueOf(str)).child("latitude1").setValue(lat1);
        ref.child(String.valueOf(str)).child("longitude1").setValue(lon1);
        ref.child(String.valueOf(str)).child("latitude2").setValue(lat2);
        ref.child(String.valueOf(str)).child("longitude2").setValue(lon2);

        track_Database.child(String.valueOf(maxid)).child("Location").child("Driver ID").setValue(str);
        track_Database.child(String.valueOf(maxid)).child("Location").child("lat1").setValue(lat1);
        track_Database.child(String.valueOf(maxid)).child("Location").child("lon1").setValue(lon1);
        track_Database.child(String.valueOf(maxid)).child("Location").child("lat2").setValue(lat2);
        track_Database.child(String.valueOf(maxid)).child("Location").child("lon2").setValue(lon2);
        track_Database.child(String.valueOf(maxid)).child("Location").child("startaddress").setValue(address);
        track_Database.child(String.valueOf(maxid)).child("Location").child("endaddress").setValue(address2);
        track_Database.child(String.valueOf(maxid)).child("Location").child("endaddress").setValue(address3);
        track_Database.child(String.valueOf(maxid)).child("Location").child("waitingcost").setValue(String.valueOf(mWaitingCostV));
        track_Database.child(String.valueOf(maxid)).child("Location").child("waitingtime").setValue(mTimer2.getText().toString().trim());
        track_Database.child(String.valueOf(maxid)).child("Location").child("triptime").setValue(mTimer1.getText().toString().trim());
        track_Database.child(String.valueOf(maxid)).child("Location").child("totaldistance").setValue(String.valueOf(distanceV));
        track_Database.child(String.valueOf(maxid)).child("Location").child("date").setValue(mTripDate.getText().toString());
        track_Database.child(String.valueOf(maxid)).child("Location").child("starttime").setValue(tripstarttime);
        track_Database.child(String.valueOf(maxid)).child("Location").child("tripcost").setValue(costTotalLast = Math.round(costTotalLast * 100.0) / 100.0);
        track_Database.child(String.valueOf(maxid)).child("Location").child("status").setValue(track_status);


        ////
        if (status == 0) {

            if((package_initialhours*3600) <= (updatedTime / 1000)){
                if (mForceStart.performClick() == false) {
                    mForceStart.performClick();
                }
            }else {
                mForceStop.performClick();
            }

            lat1 = location.getLatitude();
            lon1 = location.getLongitude();

            geocoder = new Geocoder(this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(lat1, lon1, 2); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                e.printStackTrace();
            }

            address = addresses.get(0).getAddressLine(0);


            //speed = (double) roundToNDigits(((location.getSpeed() * 3600) / 1000), 2);

        } else if ((status % 2) != 0) {

            if ((distanceV * 1000) > package_initial_distance*1000 && (package_initialhours*3600) <= (updatedTime / 1000)){
                StatusCost = "0";
            }else if ((distanceV * 1000) > package_initial_distance*1000 || (package_initialhours*3600) <= (updatedTime / 1000)){
                StatusCost = "1";
            }

            if((package_initialhours*3600) <= (updatedTime / 1000)){

                               //
                if (mForceStart.performClick() == false) {
                    mForceStart.performClick();
                }
                //

            }else {
                mForceStop.performClick();
            }
            lat2 = location.getLatitude();
            lon2 = location.getLongitude();

            //waitingCost = Double.valueOf((int) ((startTime2/(60*1000))*4));
            //latLng2 = new LatLng(lat2, lon2);
            distanceVincenty = distanceVincenty(lat1, lon1, lat2, lon2);
            //distanceOld1 = distanceBetweenTwoPoint2(lat1,lon1,lat2,lon2);
            if (location.hasSpeed() && location.getSpeed() > 0) {
                distanceOld1 = distanceBetweenTwoPoint2(lat1, lon1, lat2, lon2);
            } else {
                distanceOld1 = 0.0;
            }
            distance = distance + distanceOld1;
            distance = Math.round(distance * 1000.0) / 1000.0;
            distanceV = distanceV + distanceVincenty;

            ////

            int dischk1 = Integer.valueOf(distanceV.intValue());
            if (dischk1 > s1) {
                distanceV = distanceV + 0.032;
                s1 = s1 + 1;
            }


            //distanceV = 2.5;
            distanceV = Math.round(distanceV * 1000.0) / 1000.0;

            costTotalLast = costTotal(costTotalLast);
            costTotalLast = Math.round(costTotalLast * 100.0) / 100.0;

            geocoder = new Geocoder(this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(lat1, lon1, 2); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                e.printStackTrace();
            }

            address2 = addresses.get(0).getAddressLine(0);

        } else if ((status % 2) == 0) {
            lat1 = location.getLatitude();
            lon1 = location.getLongitude();

            geocoder = new Geocoder(this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(lat1, lon1, 2); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                e.printStackTrace();
            }

            address3 = addresses.get(0).getAddressLine(0);


            //latLng3 = new LatLng(lat1, lon1);
            distanceVincenty = distanceVincenty(lat1, lon1, lat2, lon2);
            //distanceOld1 = distanceBetweenTwoPoint2(lat1,lon1,lat2,lon2);
            if (location.hasSpeed() && location.getSpeed() > 0) {
                distanceOld1 = distanceBetweenTwoPoint2(lat1, lon1, lat2, lon2);
            } else {
                distanceOld1 = 0.0;
            }
            //distance = roundToNDigits(distance + distanceOld1,4) ;
            distance = distance + distanceOld1;
            distance = Math.round(distance * 1000.0) / 1000.0;
            distanceV = distanceV + distanceVincenty;

            if ((distanceV * 1000) > 1000) {
                int dischk1 = Integer.valueOf(distanceV.intValue());
                if (dischk1 > s1) {
                    distanceV = distanceV + 0.032;
                    s1 = s1 + 1;
                }
            }

            //distanceV = 1.9;
            distanceV = Math.round(distanceV * 1000.0) / 1000.0;
            //distance += SphericalUtil.computeDistanceBetween(latLng1,latLng3);
            //speed = (double) roundToNDigits(((location.getSpeed() * 3600) / 1000), 2);
            //costtotalfunc = costTotal(costtotalinit);
            costTotalLast = costTotal(costTotalLast);
            costTotalLast = Math.round(costTotalLast * 100.0) / 100.0;
        }


        mDistanceVin.setText(distanceV.toString());

        Ttotalcost.setText(costTotalLast.toString());
        Ttotalcost.setGravity(Gravity.CENTER_HORIZONTAL);

        mTripCost.setText(mTripCostV.toString());
        mTripCost.setGravity(Gravity.CENTER_HORIZONTAL);

        mWaitingCost.setText(String.valueOf(mWaitingCostV));
        mWaitingCost.setGravity(Gravity.CENTER_HORIZONTAL);

        status++;
    }

    public double distanceBetweenTwoPoint2(double lat1, double lon1, double lat2, double lon2) {


        double dlong = (lon2 - lon1) * DEG_TO_RAD;
        double dlat = (lat2 - lat1) * DEG_TO_RAD;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1 * DEG_TO_RAD) * Math.cos(lat2 * DEG_TO_RAD) * Math.pow(Math.sin(dlong / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = EQUATORIAL_EARTH_RADIUS * c;
        //distanceOld = roundToNDigits(d,4);
        distanceOld = d;
        return distanceOld;

    }

    public double distanceVincenty(double lat1, double lon1, double lat2, double lon2) {
        double a = 6378137, b = 6356752.314245, f = 1 / 298.257223563; // WGS-84 ellipsoid params
        double L = Math.toRadians(lon2 - lon1);
        double U1 = Math.atan((1 - f) * Math.tan(Math.toRadians(lat1)));
        double U2 = Math.atan((1 - f) * Math.tan(Math.toRadians(lat2)));
        double sinU1 = Math.sin(U1), cosU1 = Math.cos(U1);
        double sinU2 = Math.sin(U2), cosU2 = Math.cos(U2);

        double sinLambda, cosLambda, sinSigma, cosSigma, sigma, sinAlpha, cosSqAlpha, cos2SigmaM;
        double lambda = L, lambdaP, iterLimit = 100;
        do {
            sinLambda = Math.sin(lambda);
            cosLambda = Math.cos(lambda);
            sinSigma = Math.sqrt((cosU2 * sinLambda) * (cosU2 * sinLambda)
                    + (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda) * (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda));
            if (sinSigma == 0)
                return 0; // co-incident points
            cosSigma = sinU1 * sinU2 + cosU1 * cosU2 * cosLambda;
            sigma = Math.atan2(sinSigma, cosSigma);
            sinAlpha = cosU1 * cosU2 * sinLambda / sinSigma;
            cosSqAlpha = 1 - sinAlpha * sinAlpha;
            cos2SigmaM = cosSigma - 2 * sinU1 * sinU2 / cosSqAlpha;
            if (Double.isNaN(cos2SigmaM))
                cos2SigmaM = 0; // equatorial line: cosSqAlpha=0 (§6)
            double C = f / 16 * cosSqAlpha * (4 + f * (4 - 3 * cosSqAlpha));
            lambdaP = lambda;
            lambda = L + (1 - C) * f * sinAlpha
                    * (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM)));
        } while (Math.abs(lambda - lambdaP) > 1e-12 && --iterLimit > 0);

        if (iterLimit == 0)
            return Double.NaN; // formula failed to converge

        double uSq = cosSqAlpha * (a * a - b * b) / (b * b);
        double A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
        double B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));
        double deltaSigma = B
                * sinSigma
                * (cos2SigmaM + B
                / 4
                * (cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM) - B / 6 * cos2SigmaM
                * (-3 + 4 * sinSigma * sinSigma) * (-3 + 4 * cos2SigmaM * cos2SigmaM)));
        double dist = b * A * (sigma - deltaSigma);
        //return roundToNDigits(dist/1000, 2);
        dist = dist / 1000;
        if (speed == 0) {
            dist = 0;
        }

        return dist;
    }


    public double costTotal(double cost) {




        //public double package_nexthourcost;
        //public double package_nexthours;
        if (StatusCost=="1"){
            if ((package_initialhours*3600) <= (updatedTime / 1000)) {
                if (package_waitingcost > 0){
                    waitingCost = Double.valueOf((int) (((updatedTime2 / 1000) / 1 / 15) * package_waitingcost/4));
                }

                //            costTotalLast = waitingCost + 150 + ((distanceV * 1000) - 2000) * 48 / 1000;
               //New waiting cost calculation
                if (flag != 1) {
                   p = package_initialhours*3600;
                }
                 x = 1/package_nexthours;
                y = 3600/x;
                 z =  (updatedTime/1000)-p;
                if (y <z){
                    waitingCost = waitingCost + package_nexthourcost;
                    p = p + y;
                    flag = 1;

                }


                //new waiting cost calculation

                costTotalLast = waitingCost + package_initial_charge ;
//            mTripCostV = 150 + ((distanceV * 1000) - 2000) * 48 / 1000;
                mTripCostV = package_initial_charge;
                mTripCostV = Math.round(mTripCostV * 100.0) / 100.0;
//            mWaitingCostV = waitingCost;
                mWaitingCostV = Math.round(waitingCost * 100.0) / 100.0;

            }

            else if ((distanceV * 1000) > package_initial_distance*1000) {

                waitingCost = 0.00;
                //            costTotalLast = waitingCost + 150 + ((distanceV * 1000) - 2000) * 48 / 1000;
                costTotalLast = waitingCost + package_initial_charge + ((distanceV * 1000) - package_initial_distance * 1000) * package_cost_nextdistance / 1000;
//            mTripCostV = 150 + ((distanceV * 1000) - 2000) * 48 / 1000;
                mTripCostV = package_initial_charge + ((distanceV * 1000) - package_initial_distance * 1000) * package_cost_nextdistance / 1000;
                mTripCostV = Math.round(mTripCostV * 100.0) / 100.0;
//            mWaitingCostV = waitingCost;
                mWaitingCostV = Math.round(waitingCost * 100.0) / 100.0;

            }



            else{
                waitingCost = 0.00;
                //            costTotalLast = waitingCost + 150 + ((distanceV * 1000) - 2000) * 48 / 1000;
                costTotalLast = waitingCost + package_initial_charge;
                mTripCostV = 150 + ((distanceV * 1000) - 2000) * 48 / 1000;
                mTripCostV = package_initial_charge;
                mTripCostV = Math.round(mTripCostV * 100.0) / 100.0;
//           mWaitingCostV = waitingCost;
                mWaitingCostV = Math.round(waitingCost * 100.0) / 100.0;


            }
        }else{
            if ((distanceV * 1000) > package_initial_distance*1000 && (package_initialhours*3600) <= (updatedTime / 1000)) {

               // waitingCost = Double.valueOf((int) (((updatedTime2 / 1000) / 1 / 15) * package_waitingcost/4));
                //            costTotalLast = waitingCost + 150 + ((distanceV * 1000) - 2000) * 48 / 1000;
                //            costTotalLast = waitingCost + 150 + ((distanceV * 1000) - 2000) * 48 / 1000;

                if (package_waitingcost > 0){
                    waitingCost = Double.valueOf((int) (((updatedTime2 / 1000) / 1 / 15) * package_waitingcost/4));
                }

                //            costTotalLast = waitingCost + 150 + ((distanceV * 1000) - 2000) * 48 / 1000;
                //New waiting cost calculation
                if (flag != 1) {
                    p = package_initialhours*3600;
                }
                x = 1/package_nexthours;
                y = 3600/x;
                z =  (updatedTime/1000)-p;
                if (y <z){
                    waitingCost = waitingCost + package_nexthourcost;
                    p = p + y;
                    flag = 1;

                }


                //new waiting cost calculation




                costTotalLast = waitingCost + package_initial_charge + ((distanceV * 1000) - package_initial_distance * 1000) * package_cost_nextdistance / 1000;
//            mTripCostV = 150 + ((distanceV * 1000) - 2000) * 48 / 1000;
                mTripCostV = package_initial_charge + ((distanceV * 1000) - package_initial_distance * 1000) * package_cost_nextdistance / 1000;
                mTripCostV = Math.round(mTripCostV * 100.0) / 100.0;
//            mWaitingCostV = waitingCost;
                mWaitingCostV = Math.round(waitingCost * 100.0) / 100.0;

            }
        }



        ////


//        if ((distanceV * 1000) > package_initial_distance*1000) {
//
//            waitingCost = 0.00;
//            //            costTotalLast = waitingCost + 150 + ((distanceV * 1000) - 2000) * 48 / 1000;
//            costTotalLast = waitingCost + package_initial_charge + ((distanceV * 1000) - package_initial_distance * 1000) * package_cost_nextdistance / 1000;
////            mTripCostV = 150 + ((distanceV * 1000) - 2000) * 48 / 1000;
//            mTripCostV = package_initial_charge + ((distanceV * 1000) - package_initial_distance * 1000) * package_cost_nextdistance / 1000;
//            mTripCostV = Math.round(mTripCostV * 100.0) / 100.0;
////            mWaitingCostV = waitingCost;
//            mWaitingCostV = Math.round(waitingCost * 100.0) / 100.0;
//
//        }else if ((package_initialhours*3600000) <= (updatedTime / 1000)) {
//
//
//            waitingCost = Double.valueOf((int) (((updatedTime2 / 1000) / 1/15) * 1));
//            //            costTotalLast = waitingCost + 150 + ((distanceV * 1000) - 2000) * 48 / 1000;
//            costTotalLast = waitingCost + package_initial_charge;
////            mTripCostV = 150 + ((distanceV * 1000) - 2000) * 48 / 1000;
//            mTripCostV = package_initial_charge;
//            mTripCostV = Math.round(mTripCostV * 100.0) / 100.0;
////            mWaitingCostV = waitingCost;
//            mWaitingCostV = Math.round(waitingCost * 100.0) / 100.0;
//
//        }
//        else{
//            waitingCost = 0.00;
//            //            costTotalLast = waitingCost + 150 + ((distanceV * 1000) - 2000) * 48 / 1000;
//            costTotalLast = waitingCost + package_initial_charge;
////            mTripCostV = 150 + ((distanceV * 1000) - 2000) * 48 / 1000;
//            mTripCostV = package_initial_charge;
//            mTripCostV = Math.round(mTripCostV * 100.0) / 100.0;
////            mWaitingCostV = waitingCost;
//            mWaitingCostV = Math.round(waitingCost * 100.0) / 100.0;
//
//        }

   return costTotalLast;
    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //timer interval set

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(4000);
        mLocationRequest.setFastestInterval(4000);
        //Avoid erroneous distance updates - Helitha
        mLocationRequest.setSmallestDisplacement(0.0f);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        mWorkingDriver.setChecked(true);
        mWorkingDriver.setEnabled(false);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void connectDriver(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;

        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    private boolean isConnect(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }




    public void disconnectDriver(){
        track_Database.child(String.valueOf(maxid)).child("Location").child("status").setValue(track_status2);
        maxidstatus = 0;

        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("DriverAvailabilityNew");

//        GeoFire geoFire = new GeoFire(ref);
//        geoFire.removeLocation(userId);

        ref.child(String.valueOf(str)).child("latitude1").removeValue();
        ref.child(String.valueOf(str)).child("longitude1").removeValue();
        ref.child(String.valueOf(str)).child("latitude2").removeValue();
        ref.child(String.valueOf(str)).child("longitude2").removeValue();


        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("assignedpackagecompleted");
        ref2.child(String.valueOf(maxid)).child("pkgid").setValue(str_package_id+" - Completed");
        ref2.child(String.valueOf(maxid)).child("tripstatus").setValue("Completed");
        ref2.child(String.valueOf(maxid)).child("driverid").setValue(str+"-Completed");

        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("packageassign");
        ref3.child(String.valueOf(str)).child("tripstatus").setValue("Completed");
        ref3.child(String.valueOf(str)).child("pkgid").setValue(str_package_id+" - Completed");
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            int hrs = mins / 60;
            secs = secs % 60;
            mins = mins % 60;
            int milliseconds = (int) (updatedTime % 1000);
            String localtime = String.format("%02d", hrs)
                    + ":" + String.format("%02d", mins)
                    + ":" + String.format("%02d", secs);
            mTimer1.setText(localtime);
            if (hrs == 48) {
                stopTimer = true;
                Toast.makeText(DriverPackageActivity.this, "You guys are awesome",
                        Toast.LENGTH_SHORT).show();
            }
            if (!stopTimer)
                customHandler.postDelayed(this, 0);
        }

    };

    private Runnable updateTimerThread2 = new Runnable() {

        public void run() {


            timeInMilliseconds2 = SystemClock.uptimeMillis() - startTime2;
            updatedTime2 = timeSwapBuff2 + timeInMilliseconds2;


            int secs2 = (int) (updatedTime2 / 1000);
            int mins2 = secs2 / 60;
            int hrs2 = mins2 / 60;
            secs2 = secs2 % 60;
            mins2 = mins2 % 60;
            milliseconds2 = (int) (updatedTime2 % 1000);
            String localtime2 = String.format("%02d", hrs2)
                    + ":" + String.format("%02d", mins2)
                    + ":" + String.format("%02d", secs2);
            mTimer2.setText(localtime2);

            int sectomin = secs2 / 60;
            int hrstomin = hrs2 * 60;

            minutes = sectomin + hrstomin + mins2;

            if (!stopTimer2)
                customHandler2.postDelayed(this, 0);
        }


    };


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_logout:
                final AlertDialog.Builder alert2 = new AlertDialog.Builder(DriverPackageActivity.this);
                alert2
                        .setTitle("NEXTAXI")
                        .setMessage("Are you sure you want to LOGOUT?")
                        .setIcon(android.R.drawable.ic_dialog_alert)

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                SharedPreferences sp=getSharedPreferences("IDvalue",MODE_PRIVATE);
                                SharedPreferences.Editor e=sp.edit();
                                e.clear();
                                e.commit();

                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(DriverPackageActivity.this, DriverLoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
                break;
            case R.id.nav_history:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
                Intent intent = new Intent(DriverPackageActivity.this, DriverHistory.class);
                startActivity(intent);
                break;
//            case R.id.nav_settings:
//                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
//                Intent intent2 = new Intent(DriverMapActivity.this, DriverVehicle.class);
//                startActivity(intent2);
//                break;
            case R.id.nav_googlemap:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.maps");
                if (launchIntent != null) {

                    startActivity(launchIntent);
                } else {
                    Toast.makeText(DriverPackageActivity.this, "There is no package available in android", Toast.LENGTH_LONG).show();
                }
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void startService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        serviceIntent.putExtra("inputExtra", "Respect Cab Service is running in background");

        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        stopService(serviceIntent);
    }


    @Override
    public void onBackPressed () {

    }

    @Override
    protected void onStart() {
        super.onStart();


        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        startService();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i(TAG, "onSaveInstance");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(TAG, "onStop");
    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        Log.i(TAG, "onRestoreInstance");
//    }


    @Override
    protected void onRestart() {
        super.onRestart();
        stopService();

        Log.i(TAG, "onRestart");
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService();
        Log.i(TAG, "onDestroy");
    }


}
