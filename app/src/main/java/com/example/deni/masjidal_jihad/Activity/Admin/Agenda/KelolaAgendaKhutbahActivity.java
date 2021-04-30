package com.example.deni.masjidal_jihad.Activity.Admin.Agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.deni.masjidal_jihad.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class KelolaAgendaKhutbahActivity extends AppCompatActivity {

    @BindView(R.id.btnkelolatambahkhutbah)
    Button btnkelolatambahkhutbah;
    @BindView(R.id.btnkelolaeditkhutbah)
    Button btnkelolaeditkhutbah;
    @BindView(R.id.btnkelolahapuskhutbah)
    Button btnkelolahapuskhutbah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_agenda_khutbah);
        ButterKnife.bind(this);
        btnkelolatambahkhutbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaKhutbahActivity.this, TambahAgendaKhutbahActivity.class));

            }
        });

        btnkelolaeditkhutbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaKhutbahActivity.this, LihatAgendaKhutbahActivity.class));

            }
        });

        btnkelolahapuskhutbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaKhutbahActivity.this, HapusAgendaKhutbahActivity.class));

            }
        });
    }
}