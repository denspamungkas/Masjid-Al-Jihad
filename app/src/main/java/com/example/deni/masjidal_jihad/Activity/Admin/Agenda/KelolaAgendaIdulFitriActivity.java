package com.example.deni.masjidal_jihad.Activity.Admin.Agenda;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.deni.masjidal_jihad.R;

public class KelolaAgendaIdulFitriActivity extends AppCompatActivity {

    @BindView(R.id.btnkelolatambahfitri)
    Button btnkelolatambahfitri;
    @BindView(R.id.btnkelolaeditfitri)
    Button btnkelolaeditfitri;
    @BindView(R.id.btnkelolahapusfitri)
    Button btnkelolahapusfitri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_agenda_idul_fitri);
        ButterKnife.bind(this);
        btnkelolatambahfitri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaIdulFitriActivity.this, TambahAgendaIdulFitriActivity.class));

            }
        });

        btnkelolaeditfitri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaIdulFitriActivity.this, LihatAgendaIdulFitriActivity.class));

            }
        });

        btnkelolahapusfitri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaIdulFitriActivity.this, HapusAgendaIdulFitriActivity.class));

            }
        });
    }
}