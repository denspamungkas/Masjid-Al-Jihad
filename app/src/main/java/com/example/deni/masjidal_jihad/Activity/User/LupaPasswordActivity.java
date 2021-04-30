package com.example.deni.masjidal_jihad.Activity.User;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LupaPasswordActivity extends AppCompatActivity {


    @BindView(R.id.edt_password_lupapassword)
    EditText edtPasswordLupapassword;
    @BindView(R.id.edt_repassword_lupa)
    EditText edtRepasswordRegister;
    @BindView(R.id.btn_lupapassword)
    Button btnLupapassword;

    FirebaseDatabase database;
    DatabaseReference user;
    ArrayList<User> userList;

    String key, username, alamat, email, level, notelpon, password;
    @BindView(R.id.edt_notelp_lupapassword)
    EditText edtNotelpLupapassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);
        ButterKnife.bind(this);

        userList = new ArrayList<User>();

        database = FirebaseDatabase.getInstance();
        user = database.getReference("User");

        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    User usr = dataSnapshot1.getValue(User.class);
                    usr.setKey(dataSnapshot1.getKey());
                    key = dataSnapshot1.getKey();
                    userList.add(usr);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });


        btnLupapassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = edtPasswordLupapassword.getText().toString();
                String repassword = edtRepasswordRegister.getText().toString();

                User usr = new User();

                alamat = usr.getAlamat();
                level = usr.getLevel();
                email = usr.getEmail();
                notelpon = edtNotelpLupapassword.getText().toString();



                if (password.isEmpty()) {
                    edtPasswordLupapassword.setError("Password Tidak Boleh Kosong");
                    edtPasswordLupapassword.requestFocus();
                    return;
                }

                if (repassword.isEmpty()) {
                    edtRepasswordRegister.setError("Repassword Tidak Boleh Kosong");
                    edtRepasswordRegister.requestFocus();
                    return;
                }

                if (!repassword.equals(password)) {
                    edtRepasswordRegister.setError("Pasword Tidak Sama");
                    edtRepasswordRegister.requestFocus();
                    return;
                }
                EditPasswor();
            }
        });

    }

    private void EditPasswor() {

        String key = edtNotelpLupapassword.getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        user = database.getReference();
        user.child("user")
                .child(key)
                .child("password")
                .setValue(password)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(LupaPasswordActivity.this, "Edit Password Berhasil", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LupaPasswordActivity.this, LoginActivity.class));
                        finish();
                    }
                });


    }
}