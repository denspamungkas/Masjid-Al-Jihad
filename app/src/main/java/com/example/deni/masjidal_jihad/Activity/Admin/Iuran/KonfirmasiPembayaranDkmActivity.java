package com.example.deni.masjidal_jihad.Activity.Admin.Iuran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.deni.masjidal_jihad.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class KonfirmasiPembayaranDkmActivity extends AppCompatActivity {

    public static final String EXTRA_NAMA_IURAN = "extraNamaIuran";
    public static final String EXTRA_BULAN_IURAN = "extraBulanIuran";
    public static final String EXTRA_JUMLAH_IURAN = "extraJumlahIuran";
    public static final String EXTRA_IMAGE_IURAN = "extraImageIuran";
    public static final String EXTRA_USERNAME_IURAN = "extraUsernameIuran";
    public static final String EXTRA_KEY_IURAN = "extraKeyIuran";
    public static final String EXTRA_VALIDASI_IURAN = "extraValidasiIuran";

    @BindView(R.id.txt_nama_konfirmasi_admin)
    TextView txtNamaKonfirmasiAdmin;
    @BindView(R.id.txt_bulan_konfirmasi_admin)
    TextView txtBulanKonfirmasiAdmin;
    @BindView(R.id.txt_konfirmasi_jumlah_tagihan_admin)
    TextView txtKonfirmasiJumlahTagihanAdmin;
    @BindView(R.id.btn_konfirmasi_pembayaran)
    Button btnKonfirmasiPembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pembayaran_dkm);
        ButterKnife.bind(this);

        final String nama = getIntent().getStringExtra(EXTRA_NAMA_IURAN);
        final String bulan = getIntent().getStringExtra(EXTRA_BULAN_IURAN);
        final String jumlah = getIntent().getStringExtra(EXTRA_JUMLAH_IURAN);
        final String image = getIntent().getStringExtra(EXTRA_IMAGE_IURAN);
        final String username = getIntent().getStringExtra(EXTRA_USERNAME_IURAN);
        final String key = getIntent().getStringExtra(EXTRA_KEY_IURAN);
        final String validasi = getIntent().getStringExtra(EXTRA_VALIDASI_IURAN);

        txtNamaKonfirmasiAdmin.setText(nama);
        txtBulanKonfirmasiAdmin.setText(bulan);
        txtKonfirmasiJumlahTagihanAdmin.setText(jumlah);

        btnKonfirmasiPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentValidasi = new Intent(KonfirmasiPembayaranDkmActivity.this, ValidasiBuktiPembayaranActivity.class);
                intentValidasi.putExtra(ValidasiBuktiPembayaranActivity.EXTRA_NAMA_IURAN, nama);
                intentValidasi.putExtra(ValidasiBuktiPembayaranActivity.EXTRA_BULAN_IURAN, bulan);
                intentValidasi.putExtra(ValidasiBuktiPembayaranActivity.EXTRA_JUMLAH_IURAN, jumlah);
                intentValidasi.putExtra(ValidasiBuktiPembayaranActivity.EXTRA_IMAGE_IURAN, image);
                intentValidasi.putExtra(ValidasiBuktiPembayaranActivity.EXTRA_USERNAME_IURAN, username);
                intentValidasi.putExtra(ValidasiBuktiPembayaranActivity.EXTRA_KEY_IURAN, key);
                intentValidasi.putExtra(ValidasiBuktiPembayaranActivity.EXTRA_VALIDASI_IURAN, validasi);
                startActivity(intentValidasi);
                finish();
            }
        });
    }
}