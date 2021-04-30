package com.example.deni.masjidal_jihad.Activity.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Activity.User.LoginActivity;
import com.example.deni.masjidal_jihad.Activity.User.MainActivity;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.SharedPreferences.SharedPreferencedConfig;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileAdminActivity extends AppCompatActivity {

    @BindView(R.id.img_profile)
    CircleImageView imgProfile;
    @BindView(R.id.text_nama_profile)
    TextView textNamaProfile;
    @BindView(R.id.btn_logout)
    Button btnLogout;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_admin);
        ButterKnife.bind(this);

        preferencedConfig = new SharedPreferencedConfig(this);

        textNamaProfile.setText(preferencedConfig.getPreferenceUsername());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileAdminActivity.this, "Logout...", Toast.LENGTH_SHORT).show();
                preferencedConfig.savePrefBoolean(SharedPreferencedConfig.PREFERENCE_IS_LOGIN, false);
                startActivity(new Intent(ProfileAdminActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String level = preferencedConfig.getPreferenceLevelUser();

        if (level.equals("admin") || level.equals("Admin")){
            startActivity(new Intent(ProfileAdminActivity.this, DkmActivity.class));
            finish();
        }else if (level.equals("user") || level.equals("User")){
            startActivity(new Intent(ProfileAdminActivity.this, MainActivity.class));
            finish();
        }
    }
}