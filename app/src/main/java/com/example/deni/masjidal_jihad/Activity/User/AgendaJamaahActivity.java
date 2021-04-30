package com.example.deni.masjidal_jihad.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.KelolaAgendaDkmActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.KelolaAgendaIdulAdhaActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.KelolaAgendaIdulFitriActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.KelolaAgendaKhutbahActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.KelolaAgendaMaulidActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.KelolaAgendaRamadhanActivity;
import com.example.deni.masjidal_jihad.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AgendaJamaahActivity extends AppCompatActivity {

    @BindView(R.id.linear_kelola_agenda_khutbah_user)
    LinearLayout linearKelolaAgendaKhutbahUser;
    @BindView(R.id.linear_kelola_agenda_fitri_user)
    LinearLayout linearKelolaAgendaFitriUser;
    @BindView(R.id.linear_kelola_maulid_user)
    LinearLayout linearKelolaMaulidUser;
    @BindView(R.id.linear_kelola_adha_user)
    LinearLayout linearKelolaAdhaUser;
    @BindView(R.id.linear_kelola_ramadhan_user)
    LinearLayout linearKelolaRamadhanUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_jamaah);
        ButterKnife.bind(this);

        linearKelolaAgendaKhutbahUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AgendaJamaahActivity.this, KhutbahJamaahActivity.class));
            }
        });

        linearKelolaAgendaFitriUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AgendaJamaahActivity.this, IdulFitriJamaahActivity.class));
            }
        });


        linearKelolaMaulidUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AgendaJamaahActivity.this, MaulidJamaahActivity.class));
            }
        });

        linearKelolaAdhaUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AgendaJamaahActivity.this, IdulAdhaJamaahActivity.class));
            }
        });


        linearKelolaRamadhanUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AgendaJamaahActivity.this, RamadhanJaActivity.class));
            }
        });
    }
}