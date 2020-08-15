package com.nexeyotest.respectcab;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.nexeyo.respectcab.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class DriverLoginActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.uber.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.example.uber.EXTRA_NUMBER";


    private static EditText mEmail, mdriver_id_login, mTotalEarn, mDriver_name, mDriver_mobile, mDriver_full_name;
    private EditText mPassword;
    private EditText mSearchField;
    private RecyclerView mRecyclerView;
    private Button mLogin, mRegistration, mBack;
    private Button mSearchBtn;
    private String st1;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private DatabaseReference mDatabase;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        //sp1=getSharedPreferences("login",MODE_PRIVATE);

        mDatabase = FirebaseDatabase.getInstance().getReference("drivers");

        mAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(DriverLoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
                else {
                    Toast.makeText(DriverLoginActivity.this, "Sign in error", Toast.LENGTH_SHORT).show();
                }
            }
        };

        mSearchField = (EditText) findViewById(R.id.search_field);
        mEmail = (EditText) findViewById(R.id.email);
        mdriver_id_login = (EditText) findViewById(R.id.driver_id_login);
        mPassword = (EditText) findViewById(R.id.password);
        mDriver_name = (EditText) findViewById(R.id.driver_name);
        mDriver_mobile = (EditText) findViewById(R.id.driver_mobile_no);
        mDriver_full_name = (EditText) findViewById(R.id.driver_full_name);


        mRecyclerView = (RecyclerView) findViewById(R.id.result_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mLogin = (Button) findViewById(R.id.login);
        mRegistration = (Button) findViewById(R.id.registration);
        mSearchBtn = (Button) findViewById(R.id.search_btn);
        mBack = (Button) findViewById(R.id.bck_btn);


        mLogin.setVisibility(View.INVISIBLE);
        mPassword.setVisibility(View.INVISIBLE);
        mBack.setVisibility(View.INVISIBLE);
        mdriver_id_login.setVisibility(View.INVISIBLE);
        mEmail.setVisibility(View.INVISIBLE);



        mRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(DriverLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(DriverLoginActivity.this, "Sign in error", Toast.LENGTH_SHORT).show();
                        } else {
                            String user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Riders").child(user_id);
                            current_user_db.setValue(true);
                        }
                    }
                });

            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String password = mPassword.getText().toString();
                final String email = mEmail.getText().toString();
                if (email.isEmpty())
                {
                    Toast.makeText(DriverLoginActivity.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                }
                else {

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(DriverLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(DriverLoginActivity.this, "Sign in error", Toast.LENGTH_SHORT).show();
                        }

                        EditText editText = (EditText) findViewById(R.id.driver_id_login);
                        int driver_ID = Integer.parseInt(editText.getText().toString().trim());

                        EditText editText2 = (EditText) findViewById(R.id.driver_name);
                        String driver_name = editText2.getText().toString();

                        EditText editText3 = (EditText) findViewById(R.id.driver_mobile_no);
                        String driver_mobile = editText3.getText().toString();

                        EditText editText4 = (EditText) findViewById(R.id.driver_full_name);
                        String driver_full_name = editText4.getText().toString();

                        sp = getSharedPreferences("IDvalue", 0);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("DriverIDValue", driver_ID);
                        editor.putString("drivername", driver_name);
                        editor.putString("drivermobile", driver_mobile);
                        editor.putString("driverfullname", driver_full_name);
                        editor.commit();


                    }
                });



            }}
        });

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = mSearchField.getText().toString();

                firebaseUserSearch(searchText);


                mSearchField.setVisibility(View.INVISIBLE);
                mPassword.setVisibility(View.VISIBLE);
                mPassword.requestFocus();
                mLogin.setVisibility(View.VISIBLE);
                mSearchBtn.setVisibility(View.INVISIBLE);
                mBack.setVisibility(View.VISIBLE);


            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DriverLoginActivity.this, DriverLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

    }



    private void firebaseUserSearch(String searchText) {
        Toast.makeText(DriverLoginActivity.this, "Started Search", Toast.LENGTH_LONG).show();

        final Query firebaseSearchQuery = FirebaseDatabase.getInstance().getReference("drivers").orderByChild("mobile").equalTo(searchText);

        FirebaseRecyclerAdapter<User_Model, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User_Model, UsersViewHolder>(
                User_Model.class,
                R.layout.list_layout,
                UsersViewHolder.class,
                firebaseSearchQuery
        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, User_Model model, int position) {
                viewHolder.setDetails(model.getEmail());
                viewHolder.setDetails2(model.getIndex(), model.getDname(), model.getMobile(), model.getDmiddlename());
            }
        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        private void setDetails(String userName) {
            TextView user_name = (TextView) mView.findViewById(R.id.name_text);
            //user_name.setText("Enter Password for " + userName);

            mEmail.setText(userName);
        }

        @SuppressLint("SetTextI18n")
        private void setDetails2(Integer driverId, String dname, String mobile, String dmiddlename) {
            TextView user_name = (TextView) mView.findViewById(R.id.driver_id);
            //user_name.setText("Enter Password for " + userName);

            mdriver_id_login.setText(Integer.toString(driverId));
            mDriver_name.setText(dmiddlename);
            mDriver_mobile.setText(mobile);
            mDriver_full_name.setText(dname);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }
}
