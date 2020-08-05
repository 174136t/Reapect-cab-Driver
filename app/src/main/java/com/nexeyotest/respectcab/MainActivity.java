package com.nexeyotest.respectcab;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;

import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.nexeyo.respectcab.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;



import java.util.HashMap;


import java.util.Map;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ConnectivityReceiver.ConnectivityReceiverListener {
    private static MainActivity mInstance;
    private Button mDriver, mLogout;
    public ToggleButton mAvailability;
    public static EditText mCus_email;
    public EditText mCus_mobile;
    public SharedPreferences sp;
    private DrawerLayout drawer;
    String drivername;
    String drivermonile;
    private Spinner spinner;
    private DatabaseReference mDatabase;
    private ImageView mImageView;

    Double charge;
    String distance;

    Button button;
    Context context;
    Intent intent1;
    TextView textview;
    LocationManager locationManager ;
    boolean GpsStatus ;

    private RecyclerView mRecyclerViewC;
    private RecyclerView mRecyclerViewB;
    LinearLayoutManager mLayoutManagerB;

    private RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;

    private static TextView driver_id_text;
    private static TextView plateno_text;
    private static TextView vehicle_type_T;
    private static TextView vehicle_charge;

    private static TextView vehiclecharge_f;
    private static TextView vehicletype_f;
    private static TextView initialcharge_f;
    private static TextView initialdistance_f;
    private static TextView waitingcost_f;

    Object key;
    Object keytype;

    Button button_next;
    Button button_next_next;

    SharedPreferences sptype;
    SharedPreferences sptcharge;


    private static final String TAG2 = "debugpackage";

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final String TAG = "MainActivity";

//    String status_waiting = "null";
//    int speed = 0;
//    TextView mSpeed, mEmail;
//    Location mLastLocation;
//    public Integer str;
//    static Double lat1 = 0.0;
//    static Double lon1 = 0.0;
//    static Double lat2 = 0.0;
//    static Double lon2 = 0.0;
//    static int status = 0;
//    Geocoder geocoder;
//    List<Address> addresses = new ArrayList<>();
//    String address = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mainold);

        mInstance = this;

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        button = (Button)findViewById(R.id.button1);
        textview = (TextView)findViewById(R.id.textView1);
         context = getApplicationContext();
        CheckGpsStatus();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent1);
            }
        });



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        mDriver = (Button) findViewById(R.id.driver);
        mLogout = (Button) findViewById(R.id.logout);
        mAvailability = (ToggleButton) findViewById(R.id.switch1);
        mCus_email = (EditText) findViewById(R.id.cus_email);
        mCus_mobile = (EditText) findViewById(R.id.cus_mobile);
        button_next = (Button) findViewById(R.id.button_next);
        button_next_next = (Button) findViewById(R.id.button_next_next);
        button_next_next.setVisibility(View.INVISIBLE);
        button_next.setVisibility(View.INVISIBLE);

        mRecyclerViewC = (RecyclerView) findViewById(R.id.customer_email_rv);
        mRecyclerViewC.setHasFixedSize(true);
        mRecyclerViewC.setLayoutManager(new LinearLayoutManager(this));


        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.vehicle_list3);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mLayoutManagerB = new LinearLayoutManager(this);
        mLayoutManagerB.setReverseLayout(true);
        mLayoutManagerB.setStackFromEnd(true);
        mRecyclerViewB = (RecyclerView) findViewById(R.id.vehicle_type_list3);
        mRecyclerViewB.setHasFixedSize(true);
        mRecyclerViewB.setLayoutManager(mLayoutManagerB);

        mCus_mobile.setVisibility(View.INVISIBLE);
        mCus_email.setVisibility(View.INVISIBLE);
        mDriver.setEnabled(false);

        mImageView = (ImageView) findViewById(R.id.imageView3);
        String url = "https://firebasestorage.googleapis.com/v0/b/respectcab-c47d9.appspot.com/o/driverimages%2Fdownload.png?alt=media&token=460f2dca-b87f-4b04-852f-1e1dfbb8b145";

//        mCus_phone_no = (EditText) findViewById(R.id.cus_mobile);
//        mCus_email = (EditText) findViewById(R.id.cus_email);

        SharedPreferences mPrefs = getSharedPreferences("IDvalue", 0);
        Integer str = mPrefs.getInt("DriverIDValue", 0);
        drivername = mPrefs.getString("drivername", "123");
        drivermonile = mPrefs.getString("drivermobile", "456");
        String driverfullname = mPrefs.getString("driverfullname", "456");
        final TextView driver_id = (TextView) findViewById(R.id.driver_id_main);
        final TextView driver_name = (TextView) findViewById(R.id.driver_name);
        final TextView driver_mobile = (TextView) findViewById(R.id.driver_mobile);
        final TextView driver_id_display = (TextView) findViewById(R.id.driver_id_display);
        driver_id.setText("" + str);
        driver_name.setText("" + driverfullname);
        driver_mobile.setText("" + drivermonile);
        driver_id_display.setText("Driver Id -" +str);

        mDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this,R.style.MaterialThemeDialog);
                final TextView confirm_title = new TextView(MainActivity.this);

                confirm_title.setText("     Respect cab service");
                confirm_title.setTextSize(20);
                confirm_title.setTextColor(Color.BLUE);
                alert
                        .setCustomTitle(confirm_title)
                        .setMessage("Are you Sure you want to start a trip?")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent intent = new Intent(MainActivity.this, DriverMapActivity.class);
                                startActivity(intent);

                                EditText mCus_phone = (EditText) findViewById(R.id.cus_mobile);
                                //              mCus_email = (EditText) findViewById(R.id.cus_email);

                                String cus_phone = mCus_phone.getText().toString().trim();
                                String cus_email = mCus_email.getText().toString().trim();


                                sp = getSharedPreferences("Customervalue", 0);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("CustomermobileValue", cus_phone);
                                editor.putString("CustomeremailValue", cus_email);
                                editor.commit();

                                finish();
                                return;
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();






//                sp = getSharedPreferences("TripPackage", 0);
//                SharedPreferences.Editor editorPackage = sp.edit();
//                editorPackage.putString("TripPackageValue", spinner.getSelectedItem().toString());
//                editorPackage.commit();

            }
        });

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mCus_phone = mCus_mobile.getText().toString();

                firebaseUserSearch(mCus_phone);


                hideKeyboard((Button)v);

                //button_next_next.setVisibility(View.VISIBLE);

            }
        });

        button_next_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard((Button)v);
            }
        });


        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this,R.style.MaterialThemeDialog);
                final TextView confirm_title = new TextView(MainActivity.this);

                confirm_title.setText("     Respect cab service");
                confirm_title.setTextSize(20);
                confirm_title.setTextColor(Color.BLUE);
                alert
                        .setCustomTitle(confirm_title)
                        .setMessage("Are you Sure you want to logout?")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                SharedPreferences sp=getSharedPreferences("IDvalue",MODE_PRIVATE);
                                SharedPreferences.Editor e=sp.edit();
                                e.clear();
                                e.commit();

                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(MainActivity.this, DriverLoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });


        mAvailability.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                checkConnection();
                boolean isConnected = ConnectivityReceiver.isConnected();
                showSnack(isConnected);





                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Location permission is already granted", Toast.LENGTH_SHORT).show();

                    // TODO Auto-generated method stub

                    if (mAvailability.isChecked() && !drivername.equals("123") && !drivername.equals("") && GpsStatus==true && isConnected==true  ) {
                        mDriver.setEnabled(true);
                        mLogout.setEnabled(false);
                        mCus_email.setVisibility(View.VISIBLE);
                        mCus_mobile.setVisibility(View.VISIBLE);
                        button_next.setVisibility(View.VISIBLE);
                    } else {
                        mDriver.setEnabled(false);
                        mLogout.setEnabled(true);
                        mCus_email.setVisibility(View.INVISIBLE);
                        mCus_mobile.setVisibility(View.INVISIBLE);
                        button_next.setVisibility(View.INVISIBLE);
                        button_next_next.setVisibility(View.INVISIBLE);
                    }
                } else {
                    // Request Location Permission
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
                }
            }
        });

        ValueEventListener DriversRef = FirebaseDatabase.getInstance()
                .getReference("vehiclefordrivers")
                .orderByChild("driverid")
                .equalTo(String.valueOf(str))
                .addValueEventListener(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot child : dataSnapshot.getChildren()) {
                                    Map<String, Object> valuesMap = (HashMap<String, Object>) child.getValue();

                                    key = valuesMap.get("vehicleid");
                                    firebaseUserSearch5();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }

                        }
                );

        ValueEventListener VehicletypeRef = FirebaseDatabase.getInstance()
                .getReference("vehicle")
                .orderByChild("vdidname")
                .equalTo(String.valueOf(str))
                .addValueEventListener(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot child : dataSnapshot.getChildren()) {
                                    Map<String, Object> valuesMap = (HashMap<String, Object>) child.getValue();

                                    keytype = valuesMap.get("vehicletype");
                                    firebaseUserSearch4();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }

                        }
                );


    }





















    public void hideKeyboard(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch(Exception ignored) {
        }
    }

    public void firebaseUserSearch5() {
        Toast.makeText(MainActivity.this, "Started Search", Toast.LENGTH_LONG).show();

        //final Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference("drivers").orderByChild("mobile").equalTo(searchText);

        final Query firebaseSearchQuery4 = FirebaseDatabase.getInstance().getReference("vehicle").orderByChild("vdidname").equalTo(String.valueOf(key));

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("vehicle");

        reference.orderByChild("vdidname").equalTo(String.valueOf(key)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    String plate_no = datas.child("plateno").getValue().toString();
                    String vehicletype = datas.child("vehicletype").getValue().toString();

                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPlateno", MODE_PRIVATE);

                    SharedPreferences.Editor myEdit = sharedPreferences.edit();

                    SharedPreferences sharedPreferences2 = getSharedPreferences("MySharedvehicletype", MODE_PRIVATE);

                    SharedPreferences.Editor myEdit2 = sharedPreferences2.edit();

                    myEdit.putString("plate_no", plate_no);
                    myEdit2.putString("vehicletype", vehicletype);

                    myEdit.commit();
                    myEdit2.commit();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        FirebaseRecyclerAdapter<Vehicle_Model, MainActivity.UsersViewHolder2> firebaseRecyclerAdapter4 = new FirebaseRecyclerAdapter<Vehicle_Model, MainActivity.UsersViewHolder2>(
                Vehicle_Model.class,
                R.layout.vehicle_select_tabold,
                MainActivity.UsersViewHolder2.class,
                firebaseSearchQuery4

        ) {
            @Override
            protected void populateViewHolder(MainActivity.UsersViewHolder2 viewHolder, Vehicle_Model model, int position) {
                viewHolder.setDetails(model.getName2(), model.getPlateno(), model.getDriverid(), model.getVehicletype(), model.getCharges());

                sptype = getSharedPreferences("vehicledetails", 0);
                SharedPreferences.Editor editortype = sptype.edit();
                //editortype.putString("cartype", vehicle_type_T.getText().toString());
                editortype.putString("carplate", plateno_text.getText().toString());
//                editortype.putString("carcharge", vehiclecharge_f.getText().toString());
                editortype.apply();
            }
        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter4);
    }

    public static class UsersViewHolder2 extends RecyclerView.ViewHolder {

        View mViewM;

        public UsersViewHolder2(@NonNull View itemView) {
            super(itemView);
            mViewM = itemView;
        }

        public void setDetails(String userName, String plateno, String driverid, String vehicle_type, String charges) {

            vehicle_type_T = (TextView) mViewM.findViewById(R.id.vehicleid);
            vehicle_type_T.setText(vehicle_type);

            plateno_text = (TextView) mViewM.findViewById(R.id.driveridselect);
            plateno_text.setText(plateno);

            driver_id_text = (TextView) mViewM.findViewById(R.id.driverid);
            driver_id_text.setText(driverid);

            vehicle_charge = (TextView) mViewM.findViewById(R.id.textView7);
            vehicle_charge.setText(charges);

        }
    }



    public void firebaseUserSearch4() {

//        final Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference("DriverHistory_Model").child("1");
        final Query firebaseSearchQuery5 = FirebaseDatabase.getInstance().getReference("vehicletype").orderByChild("vehicletype").equalTo(String.valueOf(keytype));


        FirebaseRecyclerAdapter<VehicleType_Model, MainActivity.VehicleTypeViewHolder> firebaseRecyclerAdapter5 = new FirebaseRecyclerAdapter<VehicleType_Model, MainActivity.VehicleTypeViewHolder>(
                VehicleType_Model.class,
                R.layout.vehicle_type_select,
                MainActivity.VehicleTypeViewHolder.class,
                firebaseSearchQuery5
        ) {
            @Override
            protected void populateViewHolder(MainActivity.VehicleTypeViewHolder viewHolder, VehicleType_Model model, int position) {
                //viewHolder.setDetails(String.valueOf(model.getDriverid()));
                viewHolder.setDetails4(model.getVehicletype(), model.getCharges(), model.getInitialcharge(), model.getInitialdistance(), model.getWaitingcost());

                sptcharge = getSharedPreferences("vehiclecharges", 0);
                SharedPreferences.Editor editorcharge = sptcharge.edit();

                editorcharge.putString("carcharge", vehiclecharge_f.getText().toString());
                editorcharge.putString("cartype", vehicletype_f.getText().toString());
                editorcharge.putString("initialcharge", initialcharge_f.getText().toString());
                editorcharge.putString("initialdistance", initialdistance_f.getText().toString());
                editorcharge.putString("waitingcost", waitingcost_f.getText().toString());

                editorcharge.apply();
            }
        };

        mRecyclerViewB.setAdapter(firebaseRecyclerAdapter5);

        Toast.makeText(MainActivity.this, "Started setAdapter", Toast.LENGTH_LONG).show();
    }


    public static class VehicleTypeViewHolder extends RecyclerView.ViewHolder {

        View mViewT;

        public VehicleTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            mViewT = itemView;
        }

        @SuppressLint("SetTextI18n")
        public void setDetails4(String vehicletype, String charge, String initialcharge, String initialdistance, String waitingcost) {
            vehicletype_f = (TextView) mViewT.findViewById(R.id.vehicle_type);
            vehicletype_f.setText(String.valueOf(vehicletype));

            vehiclecharge_f = (TextView) mViewT.findViewById(R.id.vehicle_charge_f);
            vehiclecharge_f.setText(String.valueOf(charge));

            initialcharge_f = (TextView) mViewT.findViewById(R.id.initialcharge);
            initialcharge_f.setText(initialcharge);

            initialdistance_f = (TextView) mViewT.findViewById(R.id.initialdistance);
            initialdistance_f.setText(initialdistance);

            waitingcost_f = (TextView) mViewT.findViewById(R.id.Waitingcost);
            waitingcost_f.setText(waitingcost);

        }
    }


    private void firebaseUserSearch(String searchText) {
        Toast.makeText(MainActivity.this, "Started Search", Toast.LENGTH_LONG).show();

        final Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference("customers").orderByChild("mobile").equalTo(searchText);

        FirebaseRecyclerAdapter<CustomerEmail_Model, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<CustomerEmail_Model, MainActivity.UsersViewHolder>(
                CustomerEmail_Model.class,
                R.layout.cus_email_list,
                MainActivity.UsersViewHolder.class,
                firebaseSearchQuery
        ) {
            @Override
            protected void populateViewHolder(MainActivity.UsersViewHolder viewHolder, CustomerEmail_Model model, int position) {
                viewHolder.setDetails2(model.getEmail());
            }
        };

        mRecyclerViewC.setAdapter(firebaseRecyclerAdapter);
    }


    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        @SuppressLint("SetTextI18n")
        private void setDetails2(String email) {
            mCus_email.setText(email);
        }

    }

    public void CheckGpsStatus(){
        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(GpsStatus == true) {
            button.setVisibility(View.INVISIBLE);
            textview.setText("");
            button.setEnabled(false);
        } else {
            textview.setText("GPS Is Disabled");
            button.setVisibility(View.INVISIBLE);
            button.setEnabled(false);
        }
    }



    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE:

                // Check Location permission is granted or not
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Location  permission granted", Toast.LENGTH_SHORT).show();


                    final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert
                            .setTitle("Respect Cab")
                            .setMessage("Are you Sure you want to start a trip?")
                            .setIcon(android.R.drawable.ic_dialog_alert)

                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intent = new Intent(MainActivity.this, DriverMapActivity.class);
                                    startActivity(intent);
                                    finish();
                                    return;
                                }
                            })
                            .setNegativeButton(android.R.string.no, null).show();

                } else {
                    Toast.makeText(MainActivity.this, "Location  permission denied", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_logout:
                final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this,R.style.MaterialThemeDialog);
                final TextView confirm_title = new TextView(MainActivity.this);

                confirm_title.setText("     Respect cab service");
                confirm_title.setTextSize(20);
                confirm_title.setTextColor(Color.BLUE);
                alert
                        .setCustomTitle(confirm_title)
                        .setMessage("Are you Sure you want to logout?")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                SharedPreferences sp=getSharedPreferences("IDvalue",MODE_PRIVATE);
                                SharedPreferences.Editor e=sp.edit();
                                e.clear();
                                e.commit();

                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(MainActivity.this, DriverLoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
                break;
            case R.id.nav_history:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
                Intent intent = new Intent(MainActivity.this, DriverHistory.class);
                startActivity(intent);
                break;


                //new part start
            case R.id.nav_customer:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
                Intent intent5 = new Intent(MainActivity.this, CustomerRideRequest.class);
                startActivity(intent5);
                break;
                //new part end


                
            case R.id.nav_m_notify:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
                Intent intent2 = new Intent(MainActivity.this, PackageAssign.class);
                startActivity(intent2);
                break;
            case R.id.nav_finance:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
                Intent intent3 = new Intent(MainActivity.this, Finance_Activity.class);
                startActivity(intent3);
                break;
            case R.id.nav_payment:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
                Intent intent4 = new Intent(MainActivity.this, PaymentHistoryActivity.class);
                startActivity(intent4);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public boolean internetIsConnected() {
//        try {
//            String command = "ping -c 1 google.com";
//            return (Runtime.getRuntime().exec(command).waitFor() == 0);
//        } catch (Exception e) {
//            return false;
//        }
//    }




    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

//    public boolean isOnline() {
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
//            int     exitValue = ipProcess.waitFor();
//            return (exitValue == 0);
//        }
//        catch (IOException e)          { e.printStackTrace(); }
//        catch (InterruptedException e) { e.printStackTrace(); }
//
//        return false;
//    }







    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;

        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;


        }

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        CheckGpsStatus();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        CheckGpsStatus();
        Log.i(TAG, "onResume");
        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        CheckGpsStatus();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "onDestroy");
    }
}
