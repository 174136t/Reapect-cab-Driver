package com.nexeyotest.respectcab;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nexeyo.respectcab.R;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerRideRequest extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerViewB;

    LinearLayoutManager mLayoutManagerEmail;
    private RecyclerView mRecyclerViewEmail;

    LinearLayoutManager mLayoutManagerB;
    private static TextView driver_id1;
    public static TextView driver_id_f;
    private static TextView initialcharge_f;

    public static TextView pckage_name;
    private static TextView customer_phone_number_f;

    private static TextView package_name_f;
    private static TextView initialdistance_f;
    private static TextView initialhours_f;
    private static TextView initialcost_f;
    private static TextView nexthours_cost_f;
    private static TextView nexthours_f;
    private static TextView vehicle_type_f;
    private static TextView tripstatus_f;
    private static TextView waiting_cost_f;
    private static TextView customer_phone_f;
    private static TextView nextcostdistance_f;
    private static TextView customer_email_f;
    String driverid;

    private Button mStartPackage;
    private DatabaseReference packagefind;
    public Object key;
    public Object pkg_name;
    public Object d_id;

    RadioButton radioSelect;
    SharedPreferences packagesession;
    SharedPreferences packageidsession;
    SharedPreferences email_for_package;
    FirebaseHelper firebaseHelper = new FirebaseHelper("0000");
    public Integer strr;
    boolean driverOnlineFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_package_assign);
        setContentView(R.layout.customer_ride_request);

        SharedPreferences mPrefs = getSharedPreferences("IDvalue",0);
        Integer str = mPrefs.getInt("DriverIDValue", 0);


        ValueEventListener DriversRef = FirebaseDatabase.getInstance()
                .getReference("CustomerRideAssign")
                .orderByChild("driverid")
                .equalTo(str)
                .addValueEventListener(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String found="No";
                                for (DataSnapshot child : dataSnapshot.getChildren()) {
                                    Map<String, Object> valuesMap = (HashMap<String, Object>) child.getValue();

                                    key = valuesMap.get("driverid");
                                    found = "Yes";

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }

                        }
                );





        //mDatabase = FirebaseDatabase.getInstance().getReference("DriverHistory_Model");

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.package_list2);
        ((RecyclerView) mRecyclerView).setHasFixedSize(true);
        ((RecyclerView) mRecyclerView).setLayoutManager(mLayoutManager);

        mLayoutManagerEmail = new LinearLayoutManager(this);
        mLayoutManagerEmail.setReverseLayout(true);
        mLayoutManagerEmail.setStackFromEnd(true);
        mRecyclerViewEmail = (RecyclerView) findViewById(R.id.package_email_list);
        mRecyclerViewEmail.setHasFixedSize(true);
        mRecyclerViewEmail.setLayoutManager(mLayoutManagerEmail);




        mLayoutManagerB = new LinearLayoutManager(this);
        mLayoutManagerB.setReverseLayout(true);
        mLayoutManagerB.setStackFromEnd(true);
        mRecyclerViewB = (RecyclerView) findViewById(R.id.package_list);
        mRecyclerViewB.setHasFixedSize(true);
        mRecyclerViewB.setLayoutManager(mLayoutManagerB);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogrp);
        TextView show_selected = (TextView) findViewById(R.id.show_selected);
        radioSelect = (RadioButton) findViewById(R.id.radioButton);
        driver_id1 = (TextView) findViewById(R.id.driverid);


        mStartPackage = (Button) findViewById(R.id.start_package2);
        firebaseUserSearch(String.valueOf(str));




            mStartPackage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String pkg = String.valueOf(pkg_name);
                    Integer find = pkg.indexOf("-", 1);
                    //String status = pkg.substring(9);
                    //if (status.equals("Completed")){
                       // pkg = "null";
                  //  }

                    if (find == -1 && !pkg.equals("null")) {
                        final AlertDialog.Builder alert = new AlertDialog.Builder(CustomerRideRequest.this, R.style.MaterialThemeDialog);
                        final TextView confirm_title = new TextView(CustomerRideRequest.this);

                        confirm_title.setText("     Respect cab service");
                        confirm_title.setTextSize(20);
                        confirm_title.setTextColor(Color.BLUE);
                        alert
                                .setCustomTitle(confirm_title)
                                .setMessage("Are you Sure you want to start a trip?")

                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Intent intent = new Intent(CustomerRideRequest.this, ClientPackageActivity.class);
                                        startActivity(intent);
                                        finish();

//
//                                     new com.nexeyotest.respectcab.MainActivity()
                                       // com.nexeyotest.respectcab.MainActivityKt.
                                       // new MainActivity().del();
                                        Log.e("Meka vada", "oooooooooooooooo");
//                                        boolean a ;
//                                        a  = new MainActivity().getDriverOnlineFlag();
//                                        a = false;
                                        new MainActivity().setDriverOnlineFlag(false);
                                        SharedPreferences mPrefs = getSharedPreferences("IDvalue", 0);
                                        strr = mPrefs.getInt("DriverIDValue", 0);
                                        firebaseHelper = new FirebaseHelper(strr.toString());
                                        firebaseHelper.deleteDriver();
                                        Log.e("Meka hriiii", "yyyyyyyyyyyyyyy");
                                        return;
                                    }
                                })
                                .setNegativeButton(android.R.string.no, null).show();
                    }else{
                        final AlertDialog.Builder alert = new AlertDialog.Builder(CustomerRideRequest.this, R.style.MaterialThemeDialog);
                        final TextView confirm_title = new TextView(CustomerRideRequest.this);

                        confirm_title.setText("     Respect cab service");
                        confirm_title.setTextSize(20);
                        confirm_title.setTextColor(Color.BLUE);
                        alert
                                .setCustomTitle(confirm_title)
                                .setMessage("No Customer rides Available")
                                .setPositiveButton(android.R.string.no, null).show();

                    }

                }
            });


    }

    private void firebaseUserSearch(String s) {
        Toast.makeText(CustomerRideRequest.this, "Started Search", Toast.LENGTH_LONG).show();


        final Query firebaseSearchQuery4 = FirebaseDatabase.getInstance().getReference("CustomerRideAssign").orderByChild("driverid").equalTo(s);


        FirebaseRecyclerAdapter<PackageModel, PackageViewHolder> firebaseRecyclerAdapter_package = new FirebaseRecyclerAdapter<PackageModel, PackageViewHolder>(
                PackageModel.class,
                R.layout.assign_package_one_list,
                PackageViewHolder.class,
                firebaseSearchQuery4

        ) {
            @Override
            protected void populateViewHolder(PackageViewHolder viewHolder, PackageModel model, int position) {
                viewHolder.setDetails(model.getPkgid(), model.getAssignid(), model.getCustomerphone());
                pkg_name = pckage_name.getText().toString();
//                d_id = driver_id_f.getText().toString();

                SharedPreferences mPrefs_9 = getSharedPreferences("IDvalue",0);
                Integer str1 = mPrefs_9.getInt("DriverIDValue", 0);

                firebaseUserSearch4(String.valueOf(pkg_name),String.valueOf(str1));

                firebaseUserSearchPackage();

                packageidsession = getSharedPreferences("driverPackageid", 0);
                SharedPreferences.Editor editortypePackageid = packageidsession.edit();
                editortypePackageid.putString("pkgid", pckage_name.getText().toString());
                editortypePackageid.apply();
            }
        };


        mRecyclerView.setAdapter(firebaseRecyclerAdapter_package);
    }




    public static class PackageViewHolder extends RecyclerView.ViewHolder implements com.nexeyotest.respectcab.PackageViewHolder {

        View mView;

        public PackageViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

        }

        private void setDetails(String packagename, String initialdistance_package, String customerphone) {

            pckage_name = (TextView) mView.findViewById(R.id.package_name);
            pckage_name.setText(packagename);

            customer_phone_number_f = (TextView) mView.findViewById(R.id.customer_phone_number_f);
            customer_phone_number_f.setText(customerphone);

        }




    }

    private void firebaseUserSearch4(String pkg_name,String dr_id) {

        final Query firebaseSearchQuery_package = FirebaseDatabase.getInstance().getReference("packagedetails").orderByChild("packagen").equalTo(pkg_name);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("vehicle");

        reference.orderByChild("vdidname").equalTo(dr_id).addListenerForSingleValueEvent(new ValueEventListener() {
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

        FirebaseRecyclerAdapter<PackageModel, CustomerRideRequest.PackageAssignViewHolder> firebaseRecyclerAdapter_package_assign = new FirebaseRecyclerAdapter<PackageModel, CustomerRideRequest.PackageAssignViewHolder>(
                PackageModel.class,
                R.layout.customer_ride_request_assign,
                CustomerRideRequest.PackageAssignViewHolder.class,
                firebaseSearchQuery_package
        ) {
            @Override
            protected void populateViewHolder(CustomerRideRequest.PackageAssignViewHolder viewHolder, PackageModel model, int position) {
                //viewHolder.setDetails(String.valueOf(model.getDriverid()));
                viewHolder.setDetails4(model.getPackagen(), model.getDriverid(), model.getInitialdistance(), model.getInitialcost(), model.getInitialhours(), model.getNexthourcost(), model.getNexthours(), model.getVehicletype(), model.getWaitingcost(), model.getTripstatus(), model.getCustomerphone(), model.getNextcost());



                packagesession = getSharedPreferences("driverPackageDetails", 0);
                SharedPreferences.Editor editortypeP = packagesession.edit();
                editortypeP.putString("initialdistance", initialdistance_f.getText().toString());
                editortypeP.putString("initialcost", initialcharge_f.getText().toString());
                editortypeP.putString("initialhours", initialhours_f.getText().toString());
                editortypeP.putString("nexthourcost", nexthours_cost_f.getText().toString());
                editortypeP.putString("nexthours", nexthours_f.getText().toString());
                editortypeP.putString("vehicletype", vehicle_type_f.getText().toString());
                editortypeP.putString("waitingcost", waiting_cost_f.getText().toString());
                editortypeP.putString("customerphone", customer_phone_f.getText().toString());
                editortypeP.putString("nextcostfordistance", nextcostdistance_f.getText().toString());
//                editortype.putString("carcharge", vehiclecharge_f.getText().toString());
                editortypeP.apply();


            }
        };

        mRecyclerViewB.setAdapter(firebaseRecyclerAdapter_package_assign);

        Toast.makeText(CustomerRideRequest.this, "Started setAdapter", Toast.LENGTH_LONG).show();
    }








    public static class PackageAssignViewHolder extends RecyclerView.ViewHolder {

        View mViewP;
      //  Button mStsrtPackage3;
        public PackageAssignViewHolder(@NonNull View itemViewT) {
            super(itemViewT);
            mViewP = itemViewT;

        }

        @SuppressLint("SetTextI18n")
        public void setDetails4(String packagename, String driverid, String initialdistance, String initialcost, String initialhours, String nexthourcost, String nexthours, String vehicletype, String waitingcost, String tripstatus, String customerphone, String nextcost) {

            initialdistance_f = (TextView) mViewP.findViewById(R.id.initialdistance_f2);
            initialdistance_f.setText(String.valueOf(initialdistance));

            initialcharge_f = (TextView) mViewP.findViewById(R.id.initialcost_f);
            initialcharge_f.setText(String.valueOf(initialcost));

            initialhours_f = (TextView) mViewP.findViewById(R.id.initial_hours_f);
            initialhours_f.setText(initialhours);

            nexthours_cost_f = (TextView) mViewP. findViewById(R.id.next_hours_cost_f);
            nexthours_cost_f.setText(nexthourcost);

            nexthours_f = (TextView) mViewP. findViewById(R.id.next_hours_f);
            nexthours_f.setText(nexthours);

            vehicle_type_f = (TextView) mViewP. findViewById(R.id.vehicle_type_f);
            vehicle_type_f.setText(vehicletype);

            waiting_cost_f = (TextView) mViewP. findViewById(R.id.waiting_cost_f);
            waiting_cost_f.setText(waitingcost);

            customer_phone_f = (TextView) mViewP. findViewById(R.id.customer_phone_f);
            customer_phone_f.setText(customerphone);

            nextcostdistance_f = (TextView) mViewP. findViewById(R.id.nextcost);
            nextcostdistance_f.setText(nextcost);

          //  mStsrtPackage3 = (Button) mViewP.findViewById(R.id.start_package3);


        }
    }



    private void firebaseUserSearchPackage() {
        Toast.makeText(CustomerRideRequest.this, "Started Search", Toast.LENGTH_LONG).show();

        final Query firebaseSearchQueryPackage = FirebaseDatabase.getInstance().getReference("customers").orderByChild("mobile").equalTo(customer_phone_number_f.getText().toString());

        FirebaseRecyclerAdapter<CustomerEmail_Model, UsersViewHolderPackage> firebaseRecyclerAdapterPackage = new FirebaseRecyclerAdapter<CustomerEmail_Model, CustomerRideRequest.UsersViewHolderPackage>(
                CustomerEmail_Model.class,
                R.layout.assign_package_email,
                CustomerRideRequest.UsersViewHolderPackage.class,
                firebaseSearchQueryPackage
        ) {
            @Override
            protected void populateViewHolder(CustomerRideRequest.UsersViewHolderPackage viewHolder, CustomerEmail_Model model, int position) {
                viewHolder.setDetailsEmail(model.getEmail());

                email_for_package = getSharedPreferences("customer_email_Package", 0);
                SharedPreferences.Editor editortypePEmail = email_for_package.edit();
                editortypePEmail.putString("customer_email_Package", customer_email_f.getText().toString());
                editortypePEmail.apply();
            }
        };

        mRecyclerViewEmail.setAdapter(firebaseRecyclerAdapterPackage);
    }



    public static class UsersViewHolderPackage extends RecyclerView.ViewHolder {

        View mViewPackage;

        public UsersViewHolderPackage(@NonNull View itemView) {
            super(itemView);
            mViewPackage = itemView;
        }

        @SuppressLint("SetTextI18n")
        private void setDetailsEmail(String email) {

            customer_email_f = (TextView) mViewPackage. findViewById(R.id.customer_email_f);
            customer_email_f.setText(email);

        }

    }


}