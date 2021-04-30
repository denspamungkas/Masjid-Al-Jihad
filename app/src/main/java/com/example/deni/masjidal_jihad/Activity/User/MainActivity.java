package com.example.deni.masjidal_jihad.Activity.User;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.deni.masjidal_jihad.Activity.Admin.DkmActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.ProfileAdminActivity;
import com.example.deni.masjidal_jihad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.linear_berita_islami_user)
    LinearLayout linear_berita_islami_user;
    @BindView(R.id.linear_informasi_user)
    LinearLayout linear_informasi_user;
    @BindView(R.id.linear_agenda_user)
    LinearLayout linear_agenda_user;
    @BindView(R.id.linear_iuran)
    LinearLayout linearIuran;
    @BindView(R.id.linear_jadwal_sholat)
    LinearLayout linearJadwalSholat;
    @BindView(R.id.linear_kiblat)
    LinearLayout linearKiblat;
    @BindView(R.id.bottomNavigationMainUser)
    BottomNavigationView bottomNavigationMainUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        ButterKnife.bind(this);

        BottomNavigationClicked();
        linear_berita_islami_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BeritaIslamiJaActivity.class));
            }
        });

        linear_informasi_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InformasiJamaahActivity.class));
            }
        });

        linear_agenda_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AgendaJamaahActivity.class));
            }
        });


        linearIuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IuranJamaahActivity.class));
            }
        });

        linearJadwalSholat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, JadwalSholatJaActivity.class));
            }
        });

        linearKiblat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KiblatJaActivity.class));
            }
        });


    }

    private void BottomNavigationClicked() {

        bottomNavigationMainUser.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.action_profile:
                        startActivity(new Intent(MainActivity.this, ProfileAdminActivity.class));
                        finish();
                        break;
                }

                return false;
            }
        });

    }


}





