package com.nexeyotest.respectcab

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
//import android.support.design.widget.NavigationView
//import android.support.design.widget.Snackbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
//import androidx.core.widget.DrawerLayout
//import android.support.v7.app.ActionBarDrawerToggle
//import android.support.v7.app.AlertDialog
//import android.support.v7.app.AppCompatActivity
//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.RecyclerView
//import android.support.v7.widget.SwitchCompat
//import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.iid.FirebaseInstanceId
import com.nexeyo.respectcab.R
import com.nexeyotest.respectcab.ConnectivityReceiver.ConnectivityReceiverListener
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ConnectivityReceiverListener {
    private var mDriver: Button? = null
    private var mLogout: Button? = null
    var mAvailability: ToggleButton? = null
    var mCus_mobile: EditText? = null
    lateinit var sp: SharedPreferences
    lateinit private var drawer: DrawerLayout
    var drivername: String? = null
    var drivermonile: String? = null
    private val spinner: Spinner? = null
    private val mDatabase: DatabaseReference? = null
    private var mImageView: ImageView? = null
    var charge: Double? = null
    var distance: String? = null
    var button: Button? = null
    var context: Context? = null
    var intent1: Intent? = null
    var textview: TextView? = null
    var locationManager: LocationManager? = null
    var GpsStatus = false
    private var mRecyclerViewC: RecyclerView? = null
    private var mRecyclerViewB: RecyclerView? = null
    var mLayoutManagerB: LinearLayoutManager? = null
    private var mRecyclerView: RecyclerView? = null
    var mLayoutManager: LinearLayoutManager? = null
    var key: Any? = null
    var keytype: Any? = null
    var button_next: Button? = null
    var button_next_next: Button? = null
    lateinit var sptype: SharedPreferences
    lateinit var sptcharge: SharedPreferences
   // private lateinit var googleMap: GoogleMap
    private lateinit var locationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    private var locationFlag = true
    public var driverOnlineFlag = false
    //private var currentPositionMarker: Marker? = null
   // private val googleMapHelper = GoogleMapHelper()
    var str: Int? = null

    //mRef =  FirebaseDatabase.getInstance().getReference("Driver_Earning_And_Commission2").child(String.valueOf(str));


    private var firebaseHelper = FirebaseHelper("0000")
   // private val markerAnimationHelper = MarkerAnimationHelper()
    private val uiHelper = UiHelper()
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
  //  @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainold)
        mInstance = this
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        button = findViewById<View>(R.id.button1) as Button
        textview = findViewById<View>(R.id.textView1) as TextView
        context = applicationContext
        val mPref = getSharedPreferences("IDvalue", 0)
        val st = mPref.getInt("DriverIDValue", 0)
        firebaseHelper = FirebaseHelper(st.toString())

        CheckGpsStatus()
        //createLocationCallback()
        locationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = uiHelper.getLocationRequest()
        if (!uiHelper.isPlayServicesAvailable(this)) {
            Toast.makeText(this, "Play Services did not installed!", Toast.LENGTH_SHORT).show()
            finish()
        } else {}
//            requestLocationUpdate()

        val driverStatusTextView = findViewById<TextView>(R.id.driverStatusTextView)
        findViewById<SwitchCompat>(R.id.driverStatusSwitch).setOnCheckedChangeListener { _, b ->
            driverOnlineFlag = b
            if (driverOnlineFlag){
                val tok = FirebaseInstanceId.getInstance().token
                Log.e("token", tok.toString())
                Log.d("aaaaaaaaaaaa", driverOnlineFlag.toString())
                createLocationCallback()
                driverStatusTextView.text = resources.getString(R.string.online_driver)
                requestLocationUpdate()
            }
            else {
                driverOnlineFlag = false
                Log.d("bbbbbbbbbbbbbbbbbbbbbbb", driverOnlineFlag.toString())
               driverStatusTextView.text = resources.getString(R.string.offline)
                firebaseHelper.deleteDriver()
            }
        }



        //createLocationCallback()// new
        button!!.setOnClickListener { // TODO Auto-generated method stub
            intent1 = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent1)
        }
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        //window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        mDriver = findViewById<View>(R.id.driver) as Button
        mLogout = findViewById<View>(R.id.logout) as Button
        mAvailability = findViewById<View>(R.id.switch1) as ToggleButton
        mCus_email = findViewById<View>(R.id.cus_email) as EditText
        mCus_mobile = findViewById<View>(R.id.cus_mobile) as EditText
        button_next = findViewById<View>(R.id.button_next) as Button
        button_next_next = findViewById<View>(R.id.button_next_next) as Button
        button_next_next!!.visibility = View.INVISIBLE
        button_next!!.visibility = View.INVISIBLE
        mRecyclerViewC = findViewById<View>(R.id.customer_email_rv) as RecyclerView
        mRecyclerViewC!!.setHasFixedSize(true)
        mRecyclerViewC!!.layoutManager = LinearLayoutManager(this)
        mLayoutManager = LinearLayoutManager(this)
        mLayoutManager!!.reverseLayout = true
        mLayoutManager!!.stackFromEnd = true
        mRecyclerView = findViewById<View>(R.id.vehicle_list3) as RecyclerView
        mRecyclerView!!.setHasFixedSize(true)
        mRecyclerView!!.layoutManager = mLayoutManager
        mLayoutManagerB = LinearLayoutManager(this)
        mLayoutManagerB!!.reverseLayout = true
        mLayoutManagerB!!.stackFromEnd = true
        mRecyclerViewB = findViewById<View>(R.id.vehicle_type_list3) as RecyclerView
        mRecyclerViewB!!.setHasFixedSize(true)
        mRecyclerViewB!!.layoutManager = mLayoutManagerB
        mCus_mobile!!.visibility = View.INVISIBLE
        mCus_email!!.visibility = View.INVISIBLE
        mDriver!!.isEnabled = false
        mImageView = findViewById<View>(R.id.imageView3) as ImageView
        val url = "https://firebasestorage.googleapis.com/v0/b/respectcab-c47d9.appspot.com/o/driverimages%2Fdownload.png?alt=media&token=460f2dca-b87f-4b04-852f-1e1dfbb8b145"

//        mCus_phone_no = (EditText) findViewById(R.id.cus_mobile);
//        mCus_email = (EditText) findViewById(R.id.cus_email);
        val mPrefs = getSharedPreferences("IDvalue", 0)
        var str = mPrefs.getInt("DriverIDValue", 0)
        drivername = mPrefs.getString("drivername", "123")
        drivermonile = mPrefs.getString("drivermobile", "456")
        val driverfullname = mPrefs.getString("driverfullname", "456")
        val driver_id = findViewById<View>(R.id.driver_id_main) as TextView
        val driver_name = findViewById<View>(R.id.driver_name) as TextView
        val driver_mobile = findViewById<View>(R.id.driver_mobile) as TextView
        val driver_id_display = findViewById<View>(R.id.driver_id_display) as TextView
        driver_id.text = "" + str
        driver_name.text = "" + driverfullname
        driver_mobile.text = "" + drivermonile
        driver_id_display.text = "Driver Id -$str"
        mDriver!!.setOnClickListener(View.OnClickListener {
            val alert = AlertDialog.Builder(this@MainActivity, R.style.MaterialThemeDialog)
            val confirm_title = TextView(this@MainActivity)
            confirm_title.text = "     Respect cab service"
            confirm_title.textSize = 20f
            confirm_title.setTextColor(Color.BLUE)
            alert
                    .setCustomTitle(confirm_title)
                    .setMessage("Are you Sure you want to start a trip?")
                    .setPositiveButton(android.R.string.yes, DialogInterface.OnClickListener { dialog, whichButton ->
                        val intent = Intent(this@MainActivity, DriverMapActivity::class.java)
                        startActivity(intent)
                        val mCus_phone = findViewById<View>(R.id.cus_mobile) as EditText
                        //              mCus_email = (EditText) findViewById(R.id.cus_email);
                        val cus_phone = mCus_phone.text.toString().trim { it <= ' ' }
                        val cus_email = mCus_email!!.text.toString().trim { it <= ' ' }
                        sp = getSharedPreferences("Customervalue", 0)
                        val editor = sp.edit()
                        editor.putString("CustomermobileValue", cus_phone)
                        editor.putString("CustomeremailValue", cus_email)
                        editor.commit()
                        finish()
                        del()
//                        driverOnlineFlag = false
//                        Log.e("dddddd", driverOnlineFlag.toString())
//                        val mPrefs = getSharedPreferences("IDvalue", 0)
//                        str = mPrefs.getInt("DriverIDValue", 0)
//                        firebaseHelper = FirebaseHelper(str.toString())
//                        firebaseHelper.deleteDriver()
////                        createHiringLocationCallback()
//                        Log.e("ppppppppppppp", driverOnlineFlag.toString())
                        return@OnClickListener

                    })
                    .setNegativeButton(android.R.string.no, null).show()


//                sp = getSharedPreferences("TripPackage", 0);
//                SharedPreferences.Editor editorPackage = sp.edit();
//                editorPackage.putString("TripPackageValue", spinner.getSelectedItem().toString());
//                editorPackage.commit();
        })
        button_next!!.setOnClickListener { v ->
            val mCus_phone = mCus_mobile!!.text.toString()
            firebaseUserSearch(mCus_phone)
            hideKeyboard(v as Button)

            //button_next_next.setVisibility(View.VISIBLE);
        }
        button_next_next!!.setOnClickListener { v -> hideKeyboard(v as Button) }
        mLogout!!.setOnClickListener {
            val alert = AlertDialog.Builder(this@MainActivity, R.style.MaterialThemeDialog)
            val confirm_title = TextView(this@MainActivity)
            confirm_title.text = "     Respect cab service"
            confirm_title.textSize = 20f
            confirm_title.setTextColor(Color.BLUE)
            alert
                    .setCustomTitle(confirm_title)
                    .setMessage("Are you Sure you want to logout?")
                    .setPositiveButton(android.R.string.yes) { dialog, whichButton ->
                        val sp = getSharedPreferences("IDvalue", Context.MODE_PRIVATE)
                        val e = sp.edit()
                        e.clear()
                        e.commit()
                        FirebaseAuth.getInstance().signOut()
                        val intent = Intent(this@MainActivity, DriverLoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .setNegativeButton(android.R.string.no, null).show()
        }
        mAvailability!!.setOnClickListener {
            checkConnection()
            val isConnected = ConnectivityReceiver.isConnected()
            showSnack(isConnected)
            if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "Location permission is already granted", Toast.LENGTH_SHORT).show()

                // TODO Auto-generated method stub
                if (mAvailability!!.isChecked && drivername != "123" && drivername != "" && GpsStatus == true && isConnected == true) {
                    mDriver!!.isEnabled = true
                    mLogout!!.isEnabled = false
                    mCus_email!!.visibility = View.VISIBLE
                    mCus_mobile!!.visibility = View.VISIBLE
                    button_next!!.visibility = View.VISIBLE
                } else {
                    mDriver!!.isEnabled = false
                    mLogout!!.isEnabled = true
                    mCus_email!!.visibility = View.INVISIBLE
                    mCus_mobile!!.visibility = View.INVISIBLE
                    button_next!!.visibility = View.INVISIBLE
                    button_next_next!!.visibility = View.INVISIBLE
                }
            } else {
                // Request Location Permission
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            }
        }
        val DriversRef = FirebaseDatabase.getInstance()
                .getReference("vehiclefordrivers")
                .orderByChild("driverid")
                .equalTo(str.toString())
                .addValueEventListener(
                        object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                for (child in dataSnapshot.children) {
                                    val valuesMap: Map<String, Any>? = child.value as HashMap<String, Any>?
                                    key = valuesMap!!["vehicleid"]
                                    firebaseUserSearch5()
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {}
                        }
                )
        val VehicletypeRef = FirebaseDatabase.getInstance()
                .getReference("vehicle")
                .orderByChild("vdidname")
                .equalTo(str.toString())
                .addValueEventListener(
                        object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                for (child in dataSnapshot.children) {
                                    val valuesMap: Map<String, Any>? = child.value as HashMap<String, Any>?
                                    keytype = valuesMap!!["vehicletype"]
                                    firebaseUserSearch4()
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {}
                        }
                )
    }

    fun hideKeyboard(view: View) {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        } catch (ignored: Exception) {
        }
    }

    fun firebaseUserSearch5() {
        Toast.makeText(this@MainActivity, "Started Search", Toast.LENGTH_LONG).show()

        //final Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference("drivers").orderByChild("mobile").equalTo(searchText);
        val firebaseSearchQuery4 = FirebaseDatabase.getInstance().getReference("vehicle").orderByChild("vdidname").equalTo(key.toString())
        val reference = FirebaseDatabase.getInstance().getReference("vehicle")
        reference.orderByChild("vdidname").equalTo(key.toString()).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (datas in dataSnapshot.children) {
                    val plate_no = datas.child("plateno").value.toString()
                    val vehicletype = datas.child("vehicletype").value.toString()
                    val sharedPreferences = getSharedPreferences("MySharedPlateno", Context.MODE_PRIVATE)
                    val myEdit = sharedPreferences.edit()
                    val sharedPreferences2 = getSharedPreferences("MySharedvehicletype", Context.MODE_PRIVATE)
                    val myEdit2 = sharedPreferences2.edit()
                    myEdit.putString("plate_no", plate_no)
                    myEdit2.putString("vehicletype", vehicletype)
                    myEdit.commit()
                    myEdit2.commit()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        val firebaseRecyclerAdapter4: FirebaseRecyclerAdapter<Vehicle_Model, UsersViewHolder2> = object : FirebaseRecyclerAdapter<Vehicle_Model, UsersViewHolder2>(
                Vehicle_Model::class.java,
                R.layout.vehicle_select_tabold,
                UsersViewHolder2::class.java,
                firebaseSearchQuery4
        ) {
            override fun populateViewHolder(viewHolder: UsersViewHolder2, model: Vehicle_Model, position: Int) {
                viewHolder.setDetails(model.name2, model.getPlateno(), model.driverid, model.getVehicletype(), model.getCharges())
                sptype = getSharedPreferences("vehicledetails", 0)
                val editortype = sptype.edit()
                //editortype.putString("cartype", vehicle_type_T.getText().toString());
                editortype.putString("carplate", plateno_text!!.text.toString())
                //                editortype.putString("carcharge", vehiclecharge_f.getText().toString());
                editortype.apply()
            }
        }
        mRecyclerView!!.adapter = firebaseRecyclerAdapter4
    }

    class UsersViewHolder2(var mViewM: View) : RecyclerView.ViewHolder(mViewM) {
        fun setDetails(userName: String?, plateno: String?, driverid: String?, vehicle_type: String?, charges: String?) {
            vehicle_type_T = mViewM.findViewById<View>(R.id.vehicleid) as TextView
            vehicle_type_T!!.text = vehicle_type
            plateno_text = mViewM.findViewById<View>(R.id.driveridselect) as TextView
            plateno_text!!.text = plateno
            driver_id_text = mViewM.findViewById<View>(R.id.driverid) as TextView
            driver_id_text!!.text = driverid
            vehicle_charge = mViewM.findViewById<View>(R.id.textView7) as TextView
            vehicle_charge!!.text = charges
        }

    }

    fun firebaseUserSearch4() {

//        final Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference("DriverHistory_Model").child("1");
        val firebaseSearchQuery5 = FirebaseDatabase.getInstance().getReference("vehicletype").orderByChild("vehicletype").equalTo(keytype.toString())
        val firebaseRecyclerAdapter5: FirebaseRecyclerAdapter<VehicleType_Model, VehicleTypeViewHolder> = object : FirebaseRecyclerAdapter<VehicleType_Model, VehicleTypeViewHolder>(
                VehicleType_Model::class.java,
                R.layout.vehicle_type_select,
                VehicleTypeViewHolder::class.java,
                firebaseSearchQuery5
        ) {
            override fun populateViewHolder(viewHolder: VehicleTypeViewHolder, model: VehicleType_Model, position: Int) {
                //viewHolder.setDetails(String.valueOf(model.getDriverid()));
                viewHolder.setDetails4(model.getVehicletype(), model.getCharges(), model.getInitialcharge(), model.getInitialdistance(), model.getWaitingcost())
                sptcharge = getSharedPreferences("vehiclecharges", 0)
                val editorcharge = sptcharge.edit()
                editorcharge.putString("carcharge", vehiclecharge_f!!.text.toString())
                editorcharge.putString("cartype", vehicletype_f!!.text.toString())
                editorcharge.putString("initialcharge", initialcharge_f!!.text.toString())
                editorcharge.putString("initialdistance", initialdistance_f!!.text.toString())
                editorcharge.putString("waitingcost", waitingcost_f!!.text.toString())
                editorcharge.apply()
            }
        }
        mRecyclerViewB!!.adapter = firebaseRecyclerAdapter5
        Toast.makeText(this@MainActivity, "Started setAdapter", Toast.LENGTH_LONG).show()
    }

    class VehicleTypeViewHolder(var mViewT: View) : RecyclerView.ViewHolder(mViewT) {
        @SuppressLint("SetTextI18n")
        fun setDetails4(vehicletype: String, charge: String, initialcharge: String?, initialdistance: String?, waitingcost: String?) {
            vehicletype_f = mViewT.findViewById<View>(R.id.vehicle_type) as TextView
            vehicletype_f!!.text = vehicletype
            vehiclecharge_f = mViewT.findViewById<View>(R.id.vehicle_charge_f) as TextView
            vehiclecharge_f!!.text = charge
            initialcharge_f = mViewT.findViewById<View>(R.id.initialcharge) as TextView
            initialcharge_f!!.text = initialcharge
            initialdistance_f = mViewT.findViewById<View>(R.id.initialdistance) as TextView
            initialdistance_f!!.text = initialdistance
            waitingcost_f = mViewT.findViewById<View>(R.id.Waitingcost) as TextView
            waitingcost_f!!.text = waitingcost
        }

    }

    private fun firebaseUserSearch(searchText: String) {
        Toast.makeText(this@MainActivity, "Started Search", Toast.LENGTH_LONG).show()
        val firebaseSearchQuery = FirebaseDatabase.getInstance().getReference("customers").orderByChild("mobile").equalTo(searchText)
        val firebaseRecyclerAdapter: FirebaseRecyclerAdapter<CustomerEmail_Model, UsersViewHolder> = object : FirebaseRecyclerAdapter<CustomerEmail_Model, UsersViewHolder>(
                CustomerEmail_Model::class.java,
                R.layout.cus_email_list,
                UsersViewHolder::class.java,
                firebaseSearchQuery
        ) {
            override fun populateViewHolder(viewHolder: UsersViewHolder, model: CustomerEmail_Model, position: Int) {
                viewHolder.setDetails2(model.getEmail())
            }
        }
        mRecyclerViewC!!.adapter = firebaseRecyclerAdapter
    }

    class UsersViewHolder(var mView: View) : RecyclerView.ViewHolder(mView) {
        @SuppressLint("SetTextI18n")
        fun setDetails2(email: String) {
            mCus_email!!.setText(email)
        }

    }
    fun del(){
        driverOnlineFlag = false
        Log.e("dddddd", driverOnlineFlag.toString())
        val mPrefs = getSharedPreferences("IDvalue", 0)
        str = mPrefs.getInt("DriverIDValue", 0)
        firebaseHelper = FirebaseHelper(str.toString())
        firebaseHelper.deleteDriver()
//                        createHiringLocationCallback()
        Log.e("ppppppppppppp", driverOnlineFlag.toString())
    }

    fun CheckGpsStatus() {
        locationManager = context!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        assert(locationManager != null)
        GpsStatus = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (GpsStatus == true) {
            button!!.visibility = View.INVISIBLE
            textview!!.text = ""
            button!!.isEnabled = false
            //createLocationCallback()
        } else {
            textview!!.text = "GPS Is Disabled"
            button!!.visibility = View.INVISIBLE
            button!!.isEnabled = false
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE ->
                // Check Location permission is granted or not
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this@MainActivity, "Location  permission granted", Toast.LENGTH_SHORT).show()
                    requestLocationUpdate()
                    val alert = AlertDialog.Builder(this@MainActivity)
                    alert
                            .setTitle("Respect Cab")
                            .setMessage("Are you Sure you want to start a trip?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, DialogInterface.OnClickListener { dialog, whichButton ->
                                val intent = Intent(this@MainActivity, DriverMapActivity::class.java)
                                startActivity(intent)
                                finish()
                                driverOnlineFlag = false
                                return@OnClickListener
                            })
                            .setNegativeButton(android.R.string.no, null).show()
                } else {
                    Toast.makeText(this@MainActivity, "Location  permission denied", Toast.LENGTH_SHORT).show()
                }
        }
    }



    private fun createLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)
                if (locationResult!!.lastLocation == null) return
                val latLng = LatLng(locationResult.lastLocation.latitude, locationResult.lastLocation.longitude)
                Log.e("Location", latLng.latitude.toString() + " , " + latLng.longitude)
                val tok = FirebaseInstanceId.getInstance().token
                Log.e("token", tok.toString())
                if (locationFlag) {
                    locationFlag = false
                    //animateCamera(latLng)
                }
                if (driverOnlineFlag) {
                    Log.d("cccccccccccccccc", driverOnlineFlag.toString())
                    firebaseHelper.updateDriver(Driver(lat = latLng.latitude, lng = latLng.longitude, token = tok.toString()))
                }
                //showOrAnimateMarker(latLng)
            }
        }
    }

    private fun createHiringLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)
                if (locationResult!!.lastLocation == null) return
                val latLng = LatLng(locationResult.lastLocation.latitude, locationResult.lastLocation.longitude)
                Log.e("Location", latLng.latitude.toString() + " , " + latLng.longitude)
                val tok = FirebaseInstanceId.getInstance().token
                Log.e("token when hiring", tok.toString())


                val mPrefs = getSharedPreferences("IDvalue", 0)
                str = mPrefs.getInt("DriverIDValue", 0)
                firebaseHelper = FirebaseHelper(str.toString())


                if (!driverOnlineFlag) {
                    Log.e("kkkkkkk", driverOnlineFlag.toString())
                    firebaseHelper.updateHiringDriver(Driver(lat = latLng.latitude, lng = latLng.longitude, token = tok.toString()))
                }
                //showOrAnimateMarker(latLng)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdate() {
        if (!uiHelper.isHaveLocationPermission(this)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
            return
        }
        if (uiHelper.isLocationProviderEnabled(this))
            uiHelper.showPositiveDialogWithListener(this, resources.getString(R.string.need_location), resources.getString(R.string.location_content), object : IPositiveNegativeListener {
                override fun onPositive() {
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                }
            }, "Turn On", false)
        locationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
    }



//    override fun onRequestPermissionsResul(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResul(requestCode, permissions, grantResults)
//        if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
//            val value = grantResults[0]
//            if (value == PERMISSION_DENIED) {
//                Toast.makeText(this, "Location Permission denied", Toast.LENGTH_SHORT).show()
//                finish()
//            } else if (value == PERMISSION_GRANTED) requestLocationUpdate()
//        }
//    }



    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_logout -> {
                val alert = AlertDialog.Builder(this@MainActivity, R.style.MaterialThemeDialog)
                val confirm_title = TextView(this@MainActivity)
                confirm_title.text = "     Respect cab service"
                confirm_title.textSize = 20f
                confirm_title.setTextColor(Color.BLUE)
                alert
                        .setCustomTitle(confirm_title)
                        .setMessage("Are you Sure you want to logout?")
                        .setPositiveButton(android.R.string.yes) { dialog, whichButton ->
                            val sp = getSharedPreferences("IDvalue", Context.MODE_PRIVATE)
                            val e = sp.edit()
                            e.clear()
                            e.commit()
                            FirebaseAuth.getInstance().signOut()
                            val intent = Intent(this@MainActivity, DriverLoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        .setNegativeButton(android.R.string.no, null).show()
            }
            R.id.nav_history -> {
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
                val intent = Intent(this@MainActivity, DriverHistory::class.java)
                startActivity(intent)
            }
            R.id.nav_customer -> {
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
                val intent5 = Intent(this@MainActivity, CustomerRideRequest::class.java)
                startActivity(intent5)
            }
            R.id.nav_m_notify -> {
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
                val intent2 = Intent(this@MainActivity, PackageAssign::class.java)
                startActivity(intent2)
            }
            R.id.nav_finance -> {
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
                val intent3 = Intent(this@MainActivity, Finance_Activity::class.java)
                startActivity(intent3)
            }
            R.id.nav_payment -> {
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
                val intent4 = Intent(this@MainActivity, PaymentHistoryActivity::class.java)
                startActivity(intent4)
            }
        }
        drawer!!.closeDrawer(GravityCompat.START)
        return true
    }

    //    public boolean internetIsConnected() {
    //        try {
    //            String command = "ping -c 1 google.com";
    //            return (Runtime.getRuntime().exec(command).waitFor() == 0);
    //        } catch (Exception e) {
    //            return false;
    //        }
    //    }
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showSnack(isConnected)
    }

    private fun checkConnection() {
        val isConnected = ConnectivityReceiver.isConnected()
        showSnack(isConnected)
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
    private fun showSnack(isConnected: Boolean) {
        val message: String
        val color: Int
        if (isConnected) {
            message = "Good! Connected to Internet"
            color = Color.WHITE
        } else {
            message = "Sorry! Not connected to internet"
            color = Color.RED
        }
        val snackbar = Snackbar
                .make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG)
        val sbView = snackbar.view
        val textView = sbView.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(color)
        snackbar.show()
    }

    override fun onStart() {
        super.onStart()
        CheckGpsStatus()
        Log.i(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        CheckGpsStatus()
        Log.i(TAG, "onResume")
        MyApplication.getInstance().setConnectivityListener(this)
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        CheckGpsStatus()
        Log.i(TAG, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }

    companion object {
        private var mInstance: MainActivity? = null
        var mCus_email: EditText? = null
        private var driver_id_text: TextView? = null
        private var plateno_text: TextView? = null
        private var vehicle_type_T: TextView? = null
        private var vehicle_charge: TextView? = null
        private var vehiclecharge_f: TextView? = null
        private var vehicletype_f: TextView? = null
        private var initialcharge_f: TextView? = null
        private var initialdistance_f: TextView? = null
        private var waitingcost_f: TextView? = null
        private const val TAG2 = "debugpackage"
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val TAG = "MainActivity"
        private const val MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 2200
    }
}

interface IPositiveNegativeListener {
    fun onPositive()

    fun onNegative() {

    }
}
