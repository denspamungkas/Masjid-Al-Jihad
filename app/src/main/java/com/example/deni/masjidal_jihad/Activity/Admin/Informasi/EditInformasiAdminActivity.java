package com.example.deni.masjidal_jihad.Activity.Admin.Informasi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.Informasi;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EditInformasiAdminActivity extends AppCompatActivity {

    public static final String EXTRA_JUDUL_INFORMASI = "extraJudul";
    public static final String EXTRA_TANGGAL_INFORMASI = "extraTanggal";
    public static final String EXTRA_ISI_INFORMASI = "extraIsi";
    public static final String EXTRA_KEY_INFORMASI = "extraKey";
    @BindView(R.id.judulinformasi)
    EditText judulinformasi;
    @BindView(R.id.tanggalinformasi)
    EditText tanggalinformasi;
    @BindView(R.id.isiinformasi)
    EditText isiinformasi;
    @BindView(R.id.btn_lihat_edit_informasi_admin)
    Button btnEditInformasiAdmin;

    DatabaseReference databaeReference;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_informasi_admin);
        ButterKnife.bind(this);


        judulinformasi.setText(getIntent().getStringExtra(EXTRA_JUDUL_INFORMASI));
        tanggalinformasi.setText(getIntent().getStringExtra(EXTRA_TANGGAL_INFORMASI));
        isiinformasi.setText(getIntent().getStringExtra(EXTRA_ISI_INFORMASI));

        dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        tanggalinformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        btnEditInformasiAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = judulinformasi.getText().toString();
                String tanggal = tanggalinformasi.getText().toString();
                String isi = isiinformasi.getText().toString();

                if (judul.isEmpty()) {
                    judulinformasi.setError("Judul Tidak Boleh Kosong");
                    judulinformasi.requestFocus();
                    return;
                } else if (tanggal.isEmpty()) {
                    tanggalinformasi.setError("Tanggal Tidak Boleh Kosong");
                    tanggalinformasi.requestFocus();
                    return;
                }else if (isi.isEmpty()) {
                    isiinformasi.setError("Isi Tidak Boleh Kosong");
                    isiinformasi.requestFocus();
                    return;
                }else{
                    Informasi setInformasi = new Informasi();
                    setInformasi.setJudul(judulinformasi.getText().toString());
                    setInformasi.setTanggal(tanggalinformasi.getText().toString());
                    setInformasi.setIsi(isiinformasi.getText().toString());
                    updateInformasi(setInformasi);
                }

            }
        });
    }

    private void showDateDialog() {


        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                tanggalinformasi.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();


    }

    private void updateInformasi(Informasi setInformasi) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        databaeReference = database.getReference();
        String key = getIntent().getStringExtra(EXTRA_KEY_INFORMASI);

        databaeReference.child("Informasi")
                .child(key)
                .setValue(setInformasi)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditInformasiAdminActivity.this, "Update Berhasil", Toast.LENGTH_SHORT).show();
                        judulinformasi.setText("");
                        tanggalinformasi.setText("");
                        isiinformasi.setText("");
                        judulinformasi.requestFocus();
                        startActivity(new Intent(EditInformasiAdminActivity.this, LihatInformasiAdmin.class));
                        finish();
                    }
                });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(EditInformasiAdminActivity.this, LihatInformasiAdmin.class));
        finish();
    }
}