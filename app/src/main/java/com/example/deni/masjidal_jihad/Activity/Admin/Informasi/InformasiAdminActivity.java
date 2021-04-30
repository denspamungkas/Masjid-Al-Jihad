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

public class InformasiAdminActivity extends AppCompatActivity {

    @BindView(R.id.judulinformasi)
    EditText judulinformasi;
    @BindView(R.id.tanggalinformasi)
    EditText tanggalinformasi;
    @BindView(R.id.isiinformasi)
    EditText isiinformasi;
    @BindView(R.id.btn_tambah_informasi)
    Button btnTambahInformasi;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaeReference;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_admin);
        ButterKnife.bind(this);

        dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        tanggalinformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        btnTambahInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TambahInformasi();

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

    private void TambahInformasi() {
        databaeReference = database.getReference();
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
        }else {

            databaeReference.child("Informasi").push().setValue(new Informasi(judul, tanggal, isi)).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(InformasiAdminActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                    judulinformasi.setText("");
                    tanggalinformasi.setText("");
                    isiinformasi.setText("");
                    judulinformasi.requestFocus();
                    startActivity(new Intent(InformasiAdminActivity.this, KelolaInformasiDkm.class));
                    finish();
                }
            });
        }
    }

}