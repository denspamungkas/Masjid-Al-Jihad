package com.example.deni.masjidal_jihad.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.KelolaAgendaIdulAdhaActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.TambahAgendaIdulAdhaActivity;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.SharedPreferences.SharedPreferencedConfig;
import com.example.deni.masjidal_jihad.model.AgendaIdulAdha;
import com.example.deni.masjidal_jihad.model.Iuran;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class IuranJamaahActivity extends AppCompatActivity {

    @BindView(R.id.txt_nama_iuran)
    EditText txtNamaIuran;
    @BindView(R.id.txt_bulan_iuran)
    EditText txtBulanIuran;
    @BindView(R.id.txt_jumlah_tagihan_iuran)
    EditText txtJumlahTagihanIuran;
    @BindView(R.id.button_iuran)
    Button buttonIuran;
    @BindView(R.id.txt_riwayat_iuran)
    TextView txtRiwayatIuran;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    private SharedPreferencedConfig preferencedConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iuran_jamaah);
        ButterKnife.bind(this);

        preferencedConfig = new SharedPreferencedConfig(this);

        txtJumlahTagihanIuran.setText("Rp.25.000");

        buttonIuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TambahIuran();
            }
        });

        txtRiwayatIuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IuranJamaahActivity.this, RiwayatTransaksiJaActivity.class));
                finish();
            }
        });

    }

    private void TambahIuran(){
        databaseReference = database.getReference();
        String nama = txtNamaIuran.getText().toString();
        String bulan = txtBulanIuran.getText().toString();
        String jumlahtagihan = txtJumlahTagihanIuran.getText().toString();
        String imageBuktiPembayaran = "Belum Ada";
        String validasi = "Belum Valid";
        String username = preferencedConfig.getPreferenceUsername();

        if (nama.isEmpty()) {
            txtNamaIuran.setError("Nama Tidak Boleh Kosong");
            txtNamaIuran.requestFocus();
            return;
        } else if (bulan.isEmpty()) {
            txtBulanIuran.setError("Bulan Tidak Boleh Kosong");
            txtBulanIuran.requestFocus();
            return;
        } else if (jumlahtagihan.isEmpty()) {
            txtJumlahTagihanIuran.setError("Jumlah Tagihan Tidak Boleh Kosong");
            txtJumlahTagihanIuran.requestFocus();
            return;
        } else {

            databaseReference.child("Iuran").push().setValue(new Iuran(nama, bulan, jumlahtagihan, imageBuktiPembayaran, validasi, username)).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(IuranJamaahActivity.this, "Pemesanan Berhasil", Toast.LENGTH_SHORT).show();
                    txtNamaIuran.setText("");
                    txtBulanIuran.setText("");
                    txtJumlahTagihanIuran.setText("");
                    startActivity(new Intent(IuranJamaahActivity.this, PembayaranActivity.class));
                    finish();
                }
            });
        }

    }
}