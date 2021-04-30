package com.example.deni.masjidal_jihad.Activity.Admin.Agenda;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.deni.masjidal_jihad.R;

public class KelolaAgendaIdulAdhaActivity extends AppCompatActivity {

    @BindView(R.id.btnkelolatambahadha)
    Button btnkelolatambahadha;
    @BindView(R.id.btnkelolaeditadha)
    Button btnkelolaeditadha;
    @BindView(R.id.btnkelolahapusadha)
    Button btnkelolahapusadha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_agenda_idul_adha);
        ButterKnife.bind(this);
        btnkelolatambahadha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaIdulAdhaActivity.this, TambahAgendaIdulAdhaActivity.class));

            }
        });

        btnkelolaeditadha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaIdulAdhaActivity.this, LihatAgendaIdulAdhaActivity.class));

            }
        });

        btnkelolahapusadha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaIdulAdhaActivity.this, HapusAgendaIdulAdhaActivity.class));

            }
        });
    }
}