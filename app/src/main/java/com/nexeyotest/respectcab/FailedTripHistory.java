package com.nexeyotest.respectcab;

        import android.annotation.SuppressLint;
        import android.content.SharedPreferences;
        import android.content.pm.ActivityInfo;
        import android.graphics.Color;
        import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
        import android.view.Gravity;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.firebase.ui.database.FirebaseRecyclerAdapter;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;
        import com.nexeyo.respectcab.R;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

public class FailedTripHistory extends AppCompatActivity {

    private static TextView mDriver_id_history, mtripdateandtime;
    private RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    TextView mPaymentMethod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences mPrefs2 = getSharedPreferences("DriverTotalEarn",0);
        Integer str2 = mPrefs2.getInt("DriverTatalEarn", 0);

        SharedPreferences mPrefs = getSharedPreferences("IDvalue",0);
        Integer str = mPrefs.getInt("DriverIDValue", 0);

        setContentView(R.layout.fail_trip_main);
        //mDatabase = FirebaseDatabase.getInstance().getReference("DriverHistory_Model");
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.history_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mPaymentMethod = (TextView) findViewById(R.id.payment_method);


        firebaseUserSearch(String.valueOf(str));
    }

    private void firebaseUserSearch(String s) {

//        final Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference("DriverHistory_Model").child("1");
        final Query firebaseSearchQuery2 = FirebaseDatabase.getInstance().getReference("DriverTracking").orderByChild("driverid").equalTo(s);



        FirebaseRecyclerAdapter<FailedTripHistory_model, HistoryViewHolder> firebaseRecyclerAdapter2 = new FirebaseRecyclerAdapter<FailedTripHistory_model, HistoryViewHolder>(
                FailedTripHistory_model.class,
                R.layout.fail_trip_list,
                HistoryViewHolder.class,
                firebaseSearchQuery2
        ) {
            @Override
            protected void populateViewHolder(HistoryViewHolder viewHolder, FailedTripHistory_model model, int position) {
                //viewHolder.setDetails(String.valueOf(model.getDriverid()));
                viewHolder.setDetails(model.getDriverid(), model.getTripstartdate(), model.getStartaddress(), model.getEndaddress(), model.getTripid(), model.getTriptotalcost(),
//                        model.getTripcommission(),
                        model.getTripstarttime(),
                        model.getTripendtime()
//                        model.getPaymentmethod()
                );
            }
        };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter2);

//        Toast.makeText(DriverHistory.this, "Started setAdapter", Toast.LENGTH_LONG).show();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        @SuppressLint("SetTextI18n")
        public void setDetails(String driverid, String tripstartdate, String tripstartaddress, String tripendaddress, Long tripid, double triptotalcost, String tripstarttime,
                               String tripendtime
        ) {
            TextView user_name5 = (TextView) mView.findViewById(R.id.trip_date);
            user_name5.setText(tripstartdate);

            TextView user_name = (TextView) mView.findViewById(R.id.history_text);
            user_name.setText("  " + tripstartaddress );

            TextView user_name1 = (TextView) mView.findViewById(R.id.history_text2);
            user_name1.setText("  " + tripendaddress);

//            TextView user_name2 = (TextView) mView.findViewById(R.id.history_time);
//            user_name2.setText("( " + tripstarttime + " To " + tripendtime +" )");

            TextView user_name9 = (TextView) mView.findViewById(R.id.startTime);
            user_name9.setText(tripstarttime);

            TextView user_name10 = (TextView) mView.findViewById(R.id.endTime);
            user_name10.setText(tripendtime);

            TextView user_name3 = (TextView) mView.findViewById(R.id.trip_id);
            user_name3.setText("Trip ID " + tripid);

            TextView user_name6 = (TextView) mView.findViewById(R.id.trip_total_cost);
            user_name6.setText("Rs " + String.format("%.2f",triptotalcost));

//            TextView user_name7 = (TextView) mView.findViewById(R.id.driver_id);
//            user_name7.setText("Driver ID " + driverid);

//            TextView user_name8 = (TextView) mView.findViewById(R.id.payment_method);
//            user_name8.setText(paymentmethod);
//            user_name8.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

//            if (user_name8.getText().toString().equals("Cash")){
//                user_name8.setTextColor(Color.parseColor("#2B9E08"));
////                user_name8.setText(paymentmethod);
//            }else if (user_name8.getText().toString().equals("Credit card")){
//                user_name8.setTextColor(Color.parseColor("#0000FF"));
////                user_name8.setText(paymentmethod);
//            }else {
//                user_name8.setTextColor(Color.parseColor("#FF0000"));
////                user_name8.setText(paymentmethod);
//            }
        }
    }
}
