package com.example.loginregister;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
/*import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;*/

public class Register extends AppCompatActivity {

    TextInputEditText fname, lname, mob_no, emailID, password, password2;
    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.et_FName);
        lname = findViewById(R.id.et_LName);
        emailID = findViewById(R.id.et_email1);
        password = findViewById(R.id.et_password);
        mob_no = findViewById(R.id.et_mobNo);
        password2 = findViewById(R.id.et_password2);
        Submit = findViewById(R.id.bt_reg_submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateemail() || !validMobNo() || !validName() || !validpass()) {
                    Toast.makeText(Register.this, "Please fill in all the above mentioned fields properly!", Toast.LENGTH_SHORT).show();
                    return;
                    //     FetchPersonalDetails fetchedDetails = new FetchPersonalDetails(RegisterDatabase.getInstance(Register.this));
                    //   fetchedDetails.execute();
                } else {
                    String Fname, Lname, Email, Pass, Mob;
                    Fname = fname.getText().toString().trim();
                    Lname = lname.getText().toString().trim();
                    Email = emailID.getText().toString().trim();
                    Pass = password.getText().toString().trim();
                    Mob = mob_no.getText().toString().trim();

                    PersonalDetails inpDetails = new PersonalDetails(Fname, Lname, Mob, Email, Pass);

                    Duplication duplication= new Duplication(RegisterDatabase.getInstance(Register.this));
                    duplication.execute(inpDetails);
                    boolean f=duplication.uniqueMail;
                    if(f==true) {
                        Log.e("d0","checking uniqueness of email");
                        SavePersonalDetails personalDetails1 = new SavePersonalDetails(RegisterDatabase.getInstance(Register.this));
                        personalDetails1.execute(inpDetails);
                    }
                    else {
                        Toast.makeText(Register.this, "This Email ID already exists, Entry not supported!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean validName() {
        String Fname = fname.getText().toString().trim();
        if (Fname.isEmpty()) {
            fname.setError("Name field can't be empty!");
            return false;
        } else {
            fname.setError(null);
            //emailID.setEnabled(false);
            return true;
        }
    }

    private boolean validateemail() {
        String Email = emailID.getText().toString().trim();
        if (Email.isEmpty()) {
            emailID.setError("Field can't be empty !");
            return false;
        } else {
            emailID.setError(null);
            //emailID.setEnabled(false);
            return true;
        }
    }

    private final boolean validMobNo() {
        String MobNo = mob_no.getText().toString().trim();
        if (MobNo.isEmpty()) {
            mob_no.setError("Field can't be empty");
            return false;
        } else if (MobNo.length() != 10) {
            mob_no.setError("Number should be of 10 digits");
            return false;
        } else {
            mob_no.setError(null);
            //emailID.setEnabled(false);
            return true;
        }
    }

    private boolean validpass() {
        String Pass = password.getText().toString().trim();
        if (Pass.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else {
            password.setError(null);
            if(confirmpass(Pass))
                return true;
            else
                return false;
        }
    }

    private boolean confirmpass(String password) {
        String Pass2 = password2.getText().toString().trim();
        if (Pass2.isEmpty()) {
            password2.setError("Field can't be empty!");
            return false;
        }
        else if (!Pass2.isEmpty() && Pass2.equals(password)) {
            password2.setError(null);
            return true;
        }
        else {
            password2.setError("Retype the correct password");
            return false;
        }
    }


    class SavePersonalDetails extends AsyncTask<PersonalDetails,Void,Void> {
        private final RegisterDao registerDao;

        SavePersonalDetails(RegisterDatabase instance){registerDao = instance.getRegisterDao();}

            boolean accepted,flag=false;


        @Override
        protected void onPreExecute() {
            if( validateemail() && validMobNo() && validName() && validpass() ){
                accepted = true;
            }
            else {
                accepted = false;
            }
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(PersonalDetails... personalDetails) {

            if(accepted){
            registerDao.insert(personalDetails[0]);
                Log.e("C0", "Entry Inserted!");
                flag = true;
            return null;
            }
            else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.e("C1", "Successfully inserted!");
            if(flag==true){
                Toast.makeText(Register.this, "Successfully registered!", Toast.LENGTH_SHORT).show();
                Intent intent=new  Intent(Register.this,com.example.loginregister.MainActivity.class);
            startActivity(intent);
           }
            else {
                Toast.makeText(Register.this, "Some error occurred, Can't be registered!", Toast.LENGTH_SHORT).show();
                return;
            }
            super.onPostExecute(aVoid);
        }
    }

    class Duplication extends AsyncTask<PersonalDetails, Void, Void> {
        private final RegisterDao registerDao;

        Duplication(RegisterDatabase instance) {
            registerDao = instance.getRegisterDao();
        }

        int count;
        String emailid = emailID.toString().trim();
        boolean uniqueMail;
        PersonalDetails ob;

        @Override
        protected Void doInBackground(PersonalDetails... personalDetails) {
            count = registerDao.DuplicateEmail(emailid);

            ob = new PersonalDetails(personalDetails[0].getFname(), personalDetails[0].getLname(), personalDetails[0].getMob_no(), personalDetails[0].getEmailID(), personalDetails[0].getPassword());
            if (count == 0) {
                uniqueMail = true;
            } else {
                uniqueMail = false;
            }

            Log.e("c1", "count = " + count);
            Log.e("c1", "uniqueMail = " + uniqueMail);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (uniqueMail) {
                Log.e("d0", "No duplicate email, details can be inserted");
                SavePersonalDetails personalDetails1 = new SavePersonalDetails(RegisterDatabase.getInstance(Register.this));
                personalDetails1.execute(ob);
            } else {
                Log.e("d1", "Duplicate mail, can't be inserted!");
            }
        }
    }
}

