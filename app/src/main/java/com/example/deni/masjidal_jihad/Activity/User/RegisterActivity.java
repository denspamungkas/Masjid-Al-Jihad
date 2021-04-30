package com.example.deni.masjidal_jihad.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUsername, edtEmail, edtPassword, edtRePassword, edtNoTelp, edtAlamat;
    Button btnRegister;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaeReference;

    String username, password, repassword, email, noTelp, alamat;
    String level = "user";
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        edtUsername = findViewById(R.id.edt_username_register);
        edtEmail = findViewById(R.id.edt_email_register);
        edtPassword = findViewById(R.id.edt_password_register);
        edtRePassword = findViewById(R.id.edt_repassword_register);
        edtNoTelp = findViewById(R.id.edt_notelp_register);
        edtAlamat = findViewById(R.id.edt_alamat_register);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterHandle();
            }
        });

    }

    public void RegisterHandle() {
        username = edtUsername.getText().toString();
        password = edtPassword.getText().toString();
        repassword = edtRePassword.getText().toString();
        email = edtEmail.getText().toString();
        noTelp = edtNoTelp.getText().toString();
        alamat = edtAlamat.getText().toString();


        if(username.isEmpty()){
            edtUsername.setError("Username Tidak boleh Kosong");
            edtUsername.requestFocus();
            return;
        }

        if(password.isEmpty()){
            edtPassword.setError("Password Tidak Boleh Kosong");
            edtPassword.requestFocus();
            return;
        }

        if(repassword.isEmpty()){
            edtRePassword.setError("Password Tidak Boleh Kosong");
            edtRePassword.requestFocus();
            return;
        }

        if(!repassword.equals(password)){
            edtRePassword.setError("Password Harus Sama");
            edtRePassword.requestFocus();
            return;
        }

        if(email.isEmpty()){
            edtEmail.setError("Email Tidak Boleh Kosong");
            edtEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.setError("Masukkan Email Yang Valid");
            edtEmail.requestFocus();
            return;
        }

        if(noTelp.isEmpty()){
            edtNoTelp.setError("No Telp Harus Di isi");
            edtNoTelp.requestFocus();
            return;
        }

        if (alamat.isEmpty()){
            edtAlamat.setError("Masukkan Alamat");
            edtAlamat.requestFocus();
            return;
        }

        SubmitUser();
    }

    private void getValues(){


        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setNoTelp(noTelp);
        user.setAlamat(alamat);
        user.setLevel(level);
    }

    private void SubmitUser(){

        databaeReference = database.getReference("user");
        databaeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getValues();
                String childEmail = edtEmail.getText().toString();
                databaeReference.child(noTelp).setValue(user);
                Toast.makeText(RegisterActivity.this, "Berhasil Register", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
