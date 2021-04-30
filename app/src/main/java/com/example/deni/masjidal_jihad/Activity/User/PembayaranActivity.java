package com.example.deni.masjidal_jihad.Activity.User;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.Iuran;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PembayaranActivity extends AppCompatActivity {

    @BindView(R.id.txt_nama_pembayaran)
    TextView txtNamaPembayaran;
    @BindView(R.id.txt_bulan_pembayaran)
    TextView txtBulanPembayaran;
    @BindView(R.id.txt_jumlah_tagihan_pembayaran)
    TextView txtJumlahTagihanPembayaran;
    @BindView(R.id.button_bca)
    Button buttonBca;
    @BindView(R.id.button_ovo)
    Button buttonOvo;
    @BindView(R.id.button_bayar)
    Button buttonBayar;

    FirebaseDatabase database;
    DatabaseReference iuran;

    String nama, bulan, iuran2, username, key, validasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        ButterKnife.bind(this);

        database = FirebaseDatabase.getInstance();
        iuran = database.getReference("Iuran");

        buttonBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PembayaranActivity.this, UploadBuktiPembayaranActivity.class);
                intent.putExtra(UploadBuktiPembayaranActivity.EXTRA_NAMA_BAYAR, nama);
                intent.putExtra(UploadBuktiPembayaranActivity.EXTRA_BULAN_BAYAR, bulan);
                intent.putExtra(UploadBuktiPembayaranActivity.EXTRA_IURAN_BAYAR, iuran2);
                intent.putExtra(UploadBuktiPembayaranActivity.EXTRA_USERNAME_BAYAR, username);
                intent.putExtra(UploadBuktiPembayaranActivity.EXTRA_KEY_BAYAR, key);
                intent.putExtra(UploadBuktiPembayaranActivity.EXTRA_VALIDASI_BAYAR, validasi);
                startActivity(intent);
            }
        });

        buttonBca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(PembayaranActivity.this);

                dialog.setContentView(R.layout.dialog_bca);
                dialog.setTitle("INFO");

                final TextView txtTutupBca = dialog.findViewById(R.id.text_tutup_bca);

                dialog.show();

                txtTutupBca.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        buttonOvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogOvo = new Dialog(PembayaranActivity.this);

                dialogOvo.setContentView(R.layout.dialog_ovo);
                dialogOvo.setTitle("INFO");

                final TextView txtOvo = dialogOvo.findViewById(R.id.text_tutup_ovo);

                dialogOvo.show();

                txtOvo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogOvo.dismiss();
                    }
                });
            }
        });

        iuran.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Iuran iuran1 = dataSnapshot1.getValue(Iuran.class);
                    iuran1.setKey(dataSnapshot1.getKey());

                    txtNamaPembayaran.setText(iuran1.getNama());
                    txtBulanPembayaran.setText(iuran1.getBulan());
                    txtJumlahTagihanPembayaran.setText(iuran1.getIuran());

                    nama = iuran1.getNama();
                    bulan = iuran1.getBulan();
                    iuran2 = iuran1.getIuran();
                    username = iuran1.getUsername();
                    key = iuran1.getKey();
                    validasi = iuran1.getValidasi();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}