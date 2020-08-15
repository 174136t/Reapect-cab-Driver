package com.nexeyotest.respectcab;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nexeyo.respectcab.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DriverVehicle extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerViewB;
    LinearLayoutManager mLayoutManagerB;
    private static TextView driver_id1;
    public static TextView vehicle_id1;
    private static TextView mShowName;
    private static TextView mVehicleName;

    private static TextView driver_id_text;
    private static TextView plateno_text;
    private static TextView vehicle_type_T;
    private static TextView vehicle_charge;

    private static TextView vehiclecharge_f;
    private static TextView vehicletype_f;
    Object key;
    Object keytype;
    RadioGroup radioGroup;
    Object str;
    RadioButton radioSelect;
    List<TextView> allEds = new ArrayList<TextView>();
    SharedPreferences sptype;
    SharedPreferences sptcharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.vehicle_show_tabold);

        SharedPreferences mPrefs = getSharedPreferences("IDvalue", 0);
        str = mPrefs.getInt("DriverIDValue", 0);

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
                                    firebaseUserSearch();
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

        //mDatabase = FirebaseDatabase.getInstance().getReference("DriverHistory_Model");

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.vehicle_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mLayoutManagerB = new LinearLayoutManager(this);
        mLayoutManagerB.setReverseLayout(true);
        mLayoutManagerB.setStackFromEnd(true);
        mRecyclerViewB = (RecyclerView) findViewById(R.id.vehicle_type_list);
        mRecyclerViewB.setHasFixedSize(true);
        mRecyclerViewB.setLayoutManager(mLayoutManagerB);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogrp);
        TextView show_selected = (TextView) findViewById(R.id.show_selected);
        radioSelect = (RadioButton) findViewById(R.id.radioButton);
        driver_id1 = (TextView) findViewById(R.id.driverid);



    }

    private void firebaseUserSearch() {
        Toast.makeText(DriverVehicle.this, "Started Search", Toast.LENGTH_LONG).show();

        //final Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference("drivers").orderByChild("mobile").equalTo(searchText);

        final Query firebaseSearchQuery4 = FirebaseDatabase.getInstance().getReference("vehicle").orderByChild("vdidname").equalTo(String.valueOf(key));




        FirebaseRecyclerAdapter<Vehicle_Model, UsersViewHolder> firebaseRecyclerAdapter4 = new FirebaseRecyclerAdapter<Vehicle_Model, UsersViewHolder>(
                Vehicle_Model.class,
                R.layout.vehicle_select_tabold,
                UsersViewHolder.class,
                firebaseSearchQuery4

        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, Vehicle_Model model, int position) {
                viewHolder.setDetails(model.getName2(), model.getPlateno(), model.getDriverid(), model.getVehicletype(), model.getCharges());

                sptype = getSharedPreferences("vehicledetails", 0);
                SharedPreferences.Editor editortype = sptype.edit();
                editortype.putString("cartype", vehicle_type_T.getText().toString());
                editortype.putString("carplate", plateno_text.getText().toString());
//                editortype.putString("carcharge", vehiclecharge_f.getText().toString());
                editortype.apply();
            }
        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter4);
    }


    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        private void setDetails(String userName, String plateno, String driverid, String vehicle_type, String charges) {

            vehicle_type_T = (TextView) mView.findViewById(R.id.vehicleid);
            vehicle_type_T.setText(vehicle_type);

            plateno_text = (TextView) mView.findViewById(R.id.driveridselect);
            plateno_text.setText(plateno);

            driver_id_text = (TextView) mView.findViewById(R.id.driverid);
            driver_id_text.setText(driverid);

            vehicle_charge = (TextView) mView.findViewById(R.id.textView7);
            vehicle_charge.setText(charges);

        }
    }






    private void firebaseUserSearch4() {

//        final Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference("DriverHistory_Model").child("1");
        final Query firebaseSearchQuery4 = FirebaseDatabase.getInstance().getReference("vehicletype").orderByChild("vehicletype").equalTo(String.valueOf(keytype));






        FirebaseRecyclerAdapter<VehicleType_Model, DriverVehicle.VehicleTypeViewHolder> firebaseRecyclerAdapter4 = new FirebaseRecyclerAdapter<VehicleType_Model, DriverVehicle.VehicleTypeViewHolder>(
                VehicleType_Model.class,
                R.layout.vehicle_type_select,
                DriverVehicle.VehicleTypeViewHolder.class,
                firebaseSearchQuery4
        ) {
            @Override
            protected void populateViewHolder(DriverVehicle.VehicleTypeViewHolder viewHolder, VehicleType_Model model, int position) {
                //viewHolder.setDetails(String.valueOf(model.getDriverid()));
                viewHolder.setDetails4(model.getVehicletype(), model.getCharges());

                sptcharge = getSharedPreferences("vehiclecharges", 0);
                SharedPreferences.Editor editorcharge = sptcharge.edit();

                editorcharge.putString("carcharge", vehiclecharge_f.getText().toString());
                editorcharge.apply();
            }
        };

        mRecyclerViewB.setAdapter(firebaseRecyclerAdapter4);

        Toast.makeText(DriverVehicle.this, "Started setAdapter", Toast.LENGTH_LONG).show();
    }

    public static class VehicleTypeViewHolder extends RecyclerView.ViewHolder {

        View mViewT;

        public VehicleTypeViewHolder(@NonNull View itemViewT) {
            super(itemViewT);
            mViewT = itemViewT;
        }

        @SuppressLint("SetTextI18n")
        public void setDetails4(String vehicletype, String charge) {
            vehicletype_f = (TextView) mViewT.findViewById(R.id.vehicle_type);
            vehicletype_f.setText(String.valueOf(charge));

            vehiclecharge_f = (TextView) mViewT.findViewById(R.id.vehicle_charge_f);
            vehiclecharge_f.setText(String.valueOf(charge));

        }
    }
}