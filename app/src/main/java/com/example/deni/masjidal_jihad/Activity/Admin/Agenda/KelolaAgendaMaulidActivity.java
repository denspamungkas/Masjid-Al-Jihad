package com.example.deni.masjidal_jihad.Activity.Admin.Agenda;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.deni.masjidal_jihad.Adapter.Agenda.HapusAgendaMaulidAdapter;
import com.example.deni.masjidal_jihad.R;

public class KelolaAgendaMaulidActivity extends AppCompatActivity {
    @BindView(R.id.btnkelolatambahmaulid)
    Button btnkelolatambahmaulid;
    @BindView(R.id.btnkelolaeditmaulid)
    Button btnkelolaeditmaulid;
    @BindView(R.id.btnkelolahapusmaulid)
    Button btnkelolahapusmaulid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_agenda_maulid);
        ButterKnife.bind(this);

        btnkelolatambahmaulid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaMaulidActivity.this, TambahAgendaMaulidActivity.class));

            }
        });

        btnkelolaeditmaulid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaMaulidActivity.this, LihatAgendaMaulidActivity.class));

            }
        });

        btnkelolahapusmaulid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaAgendaMaulidActivity.this, HapusAgendaMaulidActivity.class));

            }
        });
    }
}