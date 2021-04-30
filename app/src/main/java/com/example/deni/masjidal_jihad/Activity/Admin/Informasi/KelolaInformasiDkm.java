package com.example.deni.masjidal_jihad.Activity.Admin.Informasi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.deni.masjidal_jihad.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class KelolaInformasiDkm extends AppCompatActivity {

    @BindView(R.id.btnkelolatambahinformasi)
    Button btnkelolatambahinformasi;
    @BindView(R.id.btnkelolaeditinformasi)
    Button btnkelolaeditinformasi;
    @BindView(R.id.btnkelolahapusinformasi)
    Button btnkelolahapusinformasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_informasi_dkm);
        ButterKnife.bind(this);
        btnkelolatambahinformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaInformasiDkm.this, InformasiAdminActivity.class));

            }
        });

        btnkelolaeditinformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaInformasiDkm.this, LihatInformasiAdmin.class));
            }
        });

        btnkelolahapusinformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KelolaInformasiDkm.this, HapusInformasiAdminActivity.class));
            }
        });
    }
}