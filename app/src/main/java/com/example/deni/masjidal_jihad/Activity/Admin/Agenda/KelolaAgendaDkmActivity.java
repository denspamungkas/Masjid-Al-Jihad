package com.example.deni.masjidal_jihad.Activity.Admin.Agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.deni.masjidal_jihad.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class KelolaAgendaDkmActivity extends AppCompatActivity {

    @BindView(R.id.linear_khutbah)
    LinearLayout linearKhutbah;
    @BindView(R.id.linear_idul_fitri)
    LinearLayout linearIdulFitri;
    @BindView(R.id.linear_maulid)
    LinearLayout linearMaulid;
    @BindView(R.id.linear_idul_adha)
    LinearLayout linearIdulAdha;
    @BindView(R.id.linear_ramadhan)
    LinearLayout linearRamadhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_agenda_dkm);
        ButterKnife.bind(this);
        linearKhutbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaDkmActivity.this, KelolaAgendaKhutbahActivity.class));
            }
        });

        linearIdulFitri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaDkmActivity.this, KelolaAgendaIdulFitriActivity.class));
            }
        });


        linearMaulid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaDkmActivity.this, KelolaAgendaMaulidActivity.class));
            }
        });

        linearIdulAdha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaDkmActivity.this, KelolaAgendaIdulAdhaActivity.class));
            }
        });


        linearRamadhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaDkmActivity.this, KelolaAgendaRamadhanActivity.class));
            }
        });
    }
}