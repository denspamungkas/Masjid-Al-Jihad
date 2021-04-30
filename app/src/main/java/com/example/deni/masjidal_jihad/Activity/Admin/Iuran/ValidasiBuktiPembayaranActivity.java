package com.example.deni.masjidal_jihad.Activity.Admin.Iuran;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.deni.masjidal_jihad.Activity.User.UploadBuktiPembayaranActivity;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.Iuran;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ValidasiBuktiPembayaranActivity extends AppCompatActivity {

    public static final String EXTRA_NAMA_IURAN = "extraNamaIuran";
    public static final String EXTRA_BULAN_IURAN = "extraBulanIuran";
    public static final String EXTRA_JUMLAH_IURAN = "extraJumlahIuran";
    public static final String EXTRA_IMAGE_IURAN = "extraImageIuran";
    public static final String EXTRA_USERNAME_IURAN = "extraUsernameIuran";
    public static final String EXTRA_KEY_IURAN = "extraKeyIuran";
    public static final String EXTRA_VALIDASI_IURAN = "extraValidasiIuran";
    @BindView(R.id.img_bukti_pembayaran_validasi)
    ImageView imgBuktiPembayaranValidasi;
    @BindView(R.id.btn_terima_bukti_pembayaran)
    Button btnTerimaBuktiPembayaran;
    @BindView(R.id.btn_tolak_bukti_pembayaran)
    Button btnTolakBuktiPembayaran;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validasi_bukti_pembayaran);
        ButterKnife.bind(this);

        final String nama = getIntent().getStringExtra(EXTRA_NAMA_IURAN);
        final String bulan = getIntent().getStringExtra(EXTRA_BULAN_IURAN);
        final String jumlah = getIntent().getStringExtra(EXTRA_JUMLAH_IURAN);
        final String image = getIntent().getStringExtra(EXTRA_IMAGE_IURAN);
        final String username = getIntent().getStringExtra(EXTRA_USERNAME_IURAN);

        Glide.with(ValidasiBuktiPembayaranActivity.this)
                .load(image)
                .error(R.mipmap.ic_launcher)
                .into(imgBuktiPembayaranValidasi);

        btnTerimaBuktiPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TerimaBukti();
            }
        });

        btnTolakBuktiPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TolakBukti();
            }
        });
    }

    private void TolakBukti() {

        final String nama = getIntent().getStringExtra(EXTRA_NAMA_IURAN);
        final String bulan = getIntent().getStringExtra(EXTRA_BULAN_IURAN);
        final String jumlah = getIntent().getStringExtra(EXTRA_JUMLAH_IURAN);
        final String image = getIntent().getStringExtra(EXTRA_IMAGE_IURAN);
        final String username = getIntent().getStringExtra(EXTRA_USERNAME_IURAN);

        final String validasi = "Ditolak";

        Iuran setIuran = new Iuran();
        setIuran.setNama(nama);
        setIuran.setBulan(bulan);
        setIuran.setIuran(jumlah);
        setIuran.setUsername(username);
        setIuran.setImage(image);
        setIuran.setValidasi(validasi);
        updateIuran(setIuran);

    }

    private void TerimaBukti() {

        final String nama = getIntent().getStringExtra(EXTRA_NAMA_IURAN);
        final String bulan = getIntent().getStringExtra(EXTRA_BULAN_IURAN);
        final String jumlah = getIntent().getStringExtra(EXTRA_JUMLAH_IURAN);
        final String image = getIntent().getStringExtra(EXTRA_IMAGE_IURAN);
        final String username = getIntent().getStringExtra(EXTRA_USERNAME_IURAN);

        final String validasi = "Diterima";

        Iuran setIuran = new Iuran();
        setIuran.setNama(nama);
        setIuran.setBulan(bulan);
        setIuran.setIuran(jumlah);
        setIuran.setUsername(username);
        setIuran.setImage(image);
        setIuran.setValidasi(validasi);
        updateIuran(setIuran);

    }

    private void updateIuran(Iuran setIuran) {

        final String key = getIntent().getStringExtra(EXTRA_KEY_IURAN);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();


        databaseReference.child("Iuran")
                .child(key)
                .setValue(setIuran)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ValidasiBuktiPembayaranActivity.this, "Validasi Berhasil", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}