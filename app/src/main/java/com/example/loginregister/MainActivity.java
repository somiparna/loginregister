package com.example.loginregister;

import android.arch.persistence.room.Query;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/*
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;*/
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView Login;
    EditText email_id;
    EditText password;
    Button loginSubmit;
    Button Register;
    String Password;
    String EmailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email_id = findViewById(R.id.et_email);
        password = findViewById(R.id.password);
        loginSubmit = findViewById(R.id.bt_loginsubmit);
        Register = findViewById(R.id.bt_Register);

        SharedPreferences sp = getSharedPreferences("SavedEmail",MODE_PRIVATE);
        String mail = sp.getString("Email","0");

        if(mail.equals("0")){
            email_id.setText("");
        }
        else{
            email_id.setText(mail);
        }

                /* Activity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstname.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please Enter the required Field",Toast.LENGTH_SHORT ).show();
                }
                else {
                    String fname= firstname.getText().toString().trim();
                    Intent Intent=new Intent(MainActivity.this, com.example.explicitintents.Activity2.class);
                    Intent.putExtra("fname",fname);
                    startActivity(Intent);
                }
            }
        });*/
        loginSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                        if(email_id.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this, "Please fill the above details!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            EmailID=email_id.getText().toString().trim();
                            Password=password.getText().toString().trim();
                            //Context context = getActivity();

                            FetchPersonalDetails fetch = new FetchPersonalDetails(RegisterDatabase.getInstance(MainActivity.this));
                            fetch.execute();

                            SharedPreferences sharedPreferences = getSharedPreferences("SavedEmail", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("Email",EmailID);
                            editor.apply();
                        }
            }
            });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new  Intent(MainActivity.this,com.example.loginregister.Register.class);
                startActivity(intent);
            }
        });
    }


    class FetchPersonalDetails extends AsyncTask<PersonalDetails,Void,Void> {
        private final RegisterDao registerDao;

        FetchPersonalDetails(RegisterDatabase instance){registerDao = instance.getRegisterDao();}

        List<PersonalDetails> detailsList;
        int count;

        @Override
        protected Void doInBackground(PersonalDetails... personalDetails) {
            detailsList = registerDao.getEntry(Password,EmailID);
            count = detailsList.size();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(count==1){
                Intent intent=new  Intent(MainActivity.this,com.example.loginregister.welcomePage.class);
                startActivity(intent);}

            else {
                Log.e("C0", "Entry not matched!");
                Toast.makeText(MainActivity.this, "Invalid Entry!", Toast.LENGTH_SHORT).show();
                super.onPostExecute(aVoid);
            }
        }

    }
}

//com.example.loginregister.PersonalDetails has some fields [fname, lname, mob_no, emailID] which are not returned by the query. If they are not supposed to be read from the result, you can mark them with @Ignore annotation. You can suppress this warning by annotating the method with @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH). Columns returned by the query: password. Fields in com.example.loginregister.PersonalDetails: fname, lname, mob_no, emailID, password.
//error: The columns returned by the query does not have the fields [emailID] in com.example.loginregister.PersonalDetails even though they are annotated as non-null or primitive. Columns returned by the query: [password]