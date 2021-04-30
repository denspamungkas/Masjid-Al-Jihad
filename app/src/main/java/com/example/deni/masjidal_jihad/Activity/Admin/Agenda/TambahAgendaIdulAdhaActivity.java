package com.example.deni.masjidal_jihad.Activity.Admin.Agenda;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deni.masjidal_jihad.Activity.Admin.Informasi.InformasiAdminActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Informasi.KelolaInformasiDkm;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaIdulAdha;
import com.example.deni.masjidal_jihad.model.AgendaKhutbah;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TambahAgendaIdulAdhaActivity extends AppCompatActivity {
    @BindView(R.id.btn_tambah_adha)
    Button btnTambahAdha;
    @BindView(R.id.judultambahadha)
    EditText judultambahadha;
    @BindView(R.id.tanggaltambahadha)
    EditText tanggaltambahadha;
    @BindView(R.id.isitambahadha)
    EditText isitambahadha;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_agenda_idul_adha);
        ButterKnife.bind(this);

        dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        tanggaltambahadha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();

            }
        });

        btnTambahAdha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TambahAgendaAdha();

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

                tanggaltambahadha.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();


    }

    private void TambahAgendaAdha() {
        databaseReference = database.getReference();
        String judul = judultambahadha.getText().toString();
        String tanggal = tanggaltambahadha.getText().toString();
        String isi = isitambahadha.getText().toString();

        if (judul.isEmpty()) {
            judultambahadha.setError("Judul Tidak Boleh Kosong");
            judultambahadha.requestFocus();
            return;
        } else if (tanggal.isEmpty()) {
            tanggaltambahadha.setError("Tanggal Tidak Boleh Kosong");
            tanggaltambahadha.requestFocus();
            return;
        } else if (isi.isEmpty()) {
            isitambahadha.setError("Isi Tidak Boleh Kosong");
            isitambahadha.requestFocus();
            return;
        } else {

            databaseReference.child("AgendaAdha").push().setValue(new AgendaIdulAdha(judul, tanggal, isi)).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(TambahAgendaIdulAdhaActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                    judultambahadha.setText("");
                    tanggaltambahadha.setText("");
                    isitambahadha.setText("");
                    tanggaltambahadha.requestFocus();
                    startActivity(new Intent(TambahAgendaIdulAdhaActivity.this, KelolaAgendaIdulAdhaActivity.class));
                    finish();
                }
            });
        }

    }

}