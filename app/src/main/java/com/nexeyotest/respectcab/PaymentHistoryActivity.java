package com.nexeyotest.respectcab;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.nexeyo.respectcab.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PaymentHistoryActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;

    RecyclerView mRecyclerViewB;
    LinearLayoutManager mLayoutManagerB;

    public String formattedDate;

    public Button b;
    SharedPreferences sp;

    Integer str;

    static double sum_f = 0.00;
    static double sumCommission_f = 0.00;

    static Double pTotal_earn_f = 0.00;
    static Double pTotal_commission_f = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_historyold);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SharedPreferences mPrefs = getSharedPreferences("IDvalue",0);
        str = mPrefs.getInt("DriverIDValue", 0);

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c.getTime());

        mRecyclerView = (RecyclerView) findViewById(R.id.commission_list);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerViewB = (RecyclerView) findViewById(R.id.balance_list);
        mLayoutManagerB = new LinearLayoutManager(this);
        mLayoutManagerB.setReverseLayout(true);
        mRecyclerViewB.setHasFixedSize(true);
        mRecyclerViewB.setLayoutManager(mLayoutManagerB);

        firebaseUserSearch4(String.valueOf(str));

        firebaseUserSearch(String.valueOf(str));

//      View tab1 = this.getLayoutInflater().inflate(R.layout.activity_finance_list, null);
//        View tab1 = this.getLayoutInflater().inflate(R.layout.activity_finance_list, null);
//        b = (Button) tab1.findViewById(R.id.button_trip_cost_f);
//
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //System.out.println("123");
//                Intent intent = new Intent(Finance_Activity.this, DriverHistory.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }

    public void front(View v) {

        TableRow t = (TableRow) v;
        TextView firstTextView = (TextView) t.getChildAt(0);
        //TextView secondTextView = (TextView) t.getChildAt(1);
        String firstText = firstTextView.getText().toString();
        //Toast.makeText(Finance_Activity.this, firstTextView.getText().toString(), Toast.LENGTH_LONG).show();

        sp = getSharedPreferences("dateofselect", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("dateofselect", firstTextView.getText().toString());
        editor.commit();

//        Intent intent = new Intent(PaymentHistoryActivity.this, DriverCommissionDetails.class);
//        startActivity(intent);
//        finish();
        //String secondText = secondTextView.getText().toString();
    }

    private void firebaseUserSearch(String s) {

//        final Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference("DriverHistory_Model").child("1");
        final Query firebaseSearchQuery3 = FirebaseDatabase.getInstance().getReference("Driver_Earning_And_Commission_RecordWise").child(String.valueOf(str)).orderByChild(s);

        FirebaseRecyclerAdapter<Payment_model, PaymentHistoryActivity.paymentViewHolder> firebaseRecyclerAdapter3 = new FirebaseRecyclerAdapter<Payment_model, PaymentHistoryActivity.paymentViewHolder>(
                Payment_model.class,
                R.layout.activity_payment_history,
                PaymentHistoryActivity.paymentViewHolder.class,
                firebaseSearchQuery3
        ) {
            @Override
            protected void populateViewHolder(PaymentHistoryActivity.paymentViewHolder viewHolder, Payment_model model, int position) {
                //viewHolder.setDetails(String.valueOf(model.getDriverid()));
                viewHolder.setDetails(model.getpaid_date(),  model.getpaid_amount());
            }
        };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter3);


//        Toast.makeText(PaymentHistoryActivity.this, "Started setAdapter", Toast.LENGTH_LONG).show();
    }

    public static class paymentViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public paymentViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        @SuppressLint("SetTextI18n")
        public void setDetails(String paid_date,  double paid_amount) {
            TextView user_name5 = (TextView) mView.findViewById(R.id.trip_date_f);
            user_name5.setText(paid_date);

//            TextView user_name6 = (TextView) mView.findViewById(R.id.trip_id_f);
//            user_name6.setText(String.valueOf(totalearning));

            TextView user_name7 = (TextView) mView.findViewById(R.id.trip_cost_f);
            user_name7.setText(String.format("%.2f",paid_amount));

        }
    }

    private void firebaseUserSearch4(String s) {

//        final Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference("DriverHistory_Model").child("1");
        final Query firebaseSearchQuery4 = FirebaseDatabase.getInstance().getReference("Driver_Earning_And_Commission2").orderByChild("driverid").equalTo(s);


        FirebaseRecyclerAdapter<PaymentBalance_Model, PaymentHistoryActivity.paymentViewHolder4> firebaseRecyclerAdapter4 = new FirebaseRecyclerAdapter<PaymentBalance_Model, paymentViewHolder4>(
                PaymentBalance_Model.class,
                R.layout.activity_payment_balance,
                PaymentHistoryActivity.paymentViewHolder4.class,
                firebaseSearchQuery4
        ) {


            @Override
            protected void populateViewHolder(PaymentHistoryActivity.paymentViewHolder4 viewHolder, PaymentBalance_Model model, int position) {
                //viewHolder.setDetails(String.valueOf(model.getDriverid()));
                viewHolder.setDetails4(model.getBalance(), model.getTotalcommission(), model.getPaidcommission());
            }
        };

        mRecyclerViewB.setAdapter(firebaseRecyclerAdapter4);

//        Toast.makeText(PaymentHistoryActivity.this, "Started setAdapter", Toast.LENGTH_LONG).show();
    }

    public static class paymentViewHolder4 extends RecyclerView.ViewHolder {

        View mView;

        public paymentViewHolder4(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        @SuppressLint("SetTextI18n")
        public void setDetails4(double balance, double total_commission, double paid_commission) {
            TextView balance_f = (TextView) mView.findViewById(R.id.balance_f);
            balance_f.setText(String.format("%.2f",balance));

            TextView total_commission_f = (TextView) mView.findViewById(R.id.total_commission_f);
            total_commission_f.setText(String.format("%.2f",total_commission));

            TextView paid_commission_f = (TextView) mView.findViewById(R.id.paid_commission_f);
            paid_commission_f.setText(String.format("%.2f",paid_commission));
        }
    }



}
