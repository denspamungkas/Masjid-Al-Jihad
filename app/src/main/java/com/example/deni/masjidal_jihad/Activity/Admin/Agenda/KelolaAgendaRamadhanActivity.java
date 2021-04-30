package com.example.deni.masjidal_jihad.Activity.Admin.Agenda;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.deni.masjidal_jihad.Adapter.Agenda.HapusAgendaIdulAdhaAdapter;
import com.example.deni.masjidal_jihad.R;

public class KelolaAgendaRamadhanActivity extends AppCompatActivity {

    @BindView(R.id.btnkelolatambahramadhan)
    Button btnkelolatambahramadhan;
    @BindView(R.id.btnkelolaeditramadhan)
    Button btnkelolaeditramadhan;
    @BindView(R.id.btnkelolahapusramadhan)
    Button btnkelolahapusramadhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_agenda_ramadhan);
        ButterKnife.bind(this);

        btnkelolatambahramadhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaRamadhanActivity.this, TambahAgendaRamadhanActivity.class));

            }
        });

        btnkelolaeditramadhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaRamadhanActivity.this, LihatAgendaRamadhanActivity.class));

            }
        });

        btnkelolahapusramadhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaRamadhanActivity.this, HapusAgendaRamadhanActivity.class));

            }
        });
    }
}