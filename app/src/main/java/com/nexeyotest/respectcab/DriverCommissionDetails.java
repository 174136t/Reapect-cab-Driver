package com.nexeyotest.respectcab;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nexeyo.respectcab.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DriverCommissionDetails extends AppCompatActivity {

    String str2;
    Integer str;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    Button mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_commission_detailsold);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SharedPreferences mPrefs2 = getSharedPreferences("dateofselect",0);
        str2 = mPrefs2.getString("dateofselect", "Hi");

        SharedPreferences mPrefs = getSharedPreferences("IDvalue",0);
        str = mPrefs.getInt("DriverIDValue", 0);

        mRecyclerView = (RecyclerView) findViewById(R.id.driver_commission_details_list);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mBack = (Button) findViewById(R.id.button_back);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DriverCommissionDetails.this, Finance_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        firebaseUserSearch(str2);


    }


    private void firebaseUserSearch(String s) {

//        final Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference("DriverHistory_Model").child("1");
        final Query firebaseSearchQuery3 = FirebaseDatabase.getInstance().getReference("DriverHistory_Model Date Wise").child(String.valueOf(str2)).child(String.valueOf(str)).orderByChild(s);

        FirebaseRecyclerAdapter<DriverCommissionDetails_Model, DriverCommissionDetails.DriverCommissionDetailsViewHolder> firebaseRecyclerAdapter3 = new FirebaseRecyclerAdapter<DriverCommissionDetails_Model, DriverCommissionDetails.DriverCommissionDetailsViewHolder>(
                DriverCommissionDetails_Model.class,
                R.layout.activity_driver_commission_details_oneold,
                DriverCommissionDetails.DriverCommissionDetailsViewHolder.class,
                firebaseSearchQuery3
        ) {
            @Override
            protected void populateViewHolder(DriverCommissionDetails.DriverCommissionDetailsViewHolder viewHolder, DriverCommissionDetails_Model model, int position) {
                //viewHolder.setDetails(String.valueOf(model.getDriverid()));
                viewHolder.setDetails(model.getTripstartdate(), model.getTriptotalcost(), model.getTripcommission(), model.getTripid());
            }
        };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter3);


        Toast.makeText(DriverCommissionDetails.this, "Started setAdapter", Toast.LENGTH_LONG).show();
    }

    public static class DriverCommissionDetailsViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public DriverCommissionDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        @SuppressLint("SetTextI18n")
        public void setDetails(String tripdate, double totalearning, double totalcommission, String tripid) {
            TextView user_name5 = (TextView) mView.findViewById(R.id.trip_date_f_details);
            user_name5.setText(tripid);

            TextView user_name6 = (TextView) mView.findViewById(R.id.trip_id_f_details);
            user_name6.setText(String.valueOf(totalearning));

            TextView user_name7 = (TextView) mView.findViewById(R.id.trip_cost_f_details);
            user_name7.setText(String.valueOf(totalcommission));
        }
    }
}
