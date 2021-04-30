package com.example.deni.masjidal_jihad.Activity.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.KelolaAgendaDkmActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Informasi.KelolaInformasiDkm;
import com.example.deni.masjidal_jihad.Activity.Admin.Iuran.IuranAdminActivity;
import com.example.deni.masjidal_jihad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DkmActivity extends AppCompatActivity {


    @BindView(R.id.linearinformasi)
    LinearLayout linearinformasi;
    @BindView(R.id.linearagenda)
    LinearLayout linearagenda;
    @BindView(R.id.lineariuran)
    LinearLayout lineariuran;
    @BindView(R.id.bottomNavigationMain)
    BottomNavigationView bottomNavigationMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homedkm_activity);
        ButterKnife.bind(this);

        BottomNavigationClicked();

        linearinformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DkmActivity.this, KelolaInformasiDkm.class));
            }
        });

        linearagenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DkmActivity.this, KelolaAgendaDkmActivity.class));
            }
        });

        lineariuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DkmActivity.this, IuranAdminActivity.class));
            }
        });

    }

    private void BottomNavigationClicked() {

        bottomNavigationMain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.action_profile:
                        startActivity(new Intent(DkmActivity.this, ProfileAdminActivity.class));
                        finish();
                        break;
                }

                return false;
            }
        });
    }


}



