package com.example.deni.masjidal_jihad.Activity.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Activity.Admin.DkmActivity;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.SharedPreferences.SharedPreferencedConfig;
import com.example.deni.masjidal_jihad.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsernameLogin, edtPasswordLogin;
    Button btnLogin;
    Button btnRegister;
    TextView txtLupaPassword;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

    private SharedPreferencedConfig preferencedConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        preferencedConfig = new SharedPreferencedConfig(this);
        edtUsernameLogin = findViewById(R.id.edt_username_login);
        edtPasswordLogin = findViewById(R.id.edt_password_login);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_registerlogin);
        txtLupaPassword = findViewById(R.id.txt_lupapassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        txtLupaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, LupaPasswordActivity.class));
            }
        });

        if (preferencedConfig.getPreferenceIsLogin()){
            if (preferencedConfig.getPreferenceLevelUser().equals("admin")){
                startActivity(new Intent(LoginActivity.this, DkmActivity.class));
                finish();
            }else if (preferencedConfig.getPreferenceLevelUser().equals("user")){
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

        }

    }


    private void Login() {

        final String username = edtUsernameLogin.getText().toString();
        final String password = edtPasswordLogin.getText().toString();
        final String levelAdmin ="admin";
        final String levelUser ="user";


        databaseReference = database.getReference();

        databaseReference.child("user").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user = dataSnapshot.getValue(User.class);

                if (username.equals(user.getUsername()) && password.equals(user.getPassword()) && levelAdmin.equals(user.getLevel())){
                    Toast.makeText(LoginActivity.this, "LOGIN SUKSES", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, DkmActivity.class));
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_USERNAME, user.getUsername());
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_LEVEL_USER, user.getLevel());
                    preferencedConfig.savePrefBoolean(SharedPreferencedConfig.PREFERENCE_IS_LOGIN, true);
                    finish();
                }else if(username.equals(user.getUsername()) && password.equals(user.getPassword()) && levelUser.equals(user.getLevel())){
                    Toast.makeText(LoginActivity.this, "LOGIN SUKSES", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_USERNAME, user.getUsername());
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_LEVEL_USER, user.getLevel());
                    preferencedConfig.savePrefBoolean(SharedPreferencedConfig.PREFERENCE_IS_LOGIN, true);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "LOGIN GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
