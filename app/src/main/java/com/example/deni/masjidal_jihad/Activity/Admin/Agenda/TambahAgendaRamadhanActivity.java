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

import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaMaulid;
import com.example.deni.masjidal_jihad.model.AgendaRamadhan;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TambahAgendaRamadhanActivity extends AppCompatActivity {

    @BindView(R.id.btn_tambah_ramadhan)
    Button btnTambahRamadhan;
    @BindView(R.id.tanggaltambahramadhan)
    EditText tanggaltambahramadhan;
    @BindView(R.id.namatambahramadhan)
    EditText namatambahmaulid;
    @BindView(R.id.alamattambahramadhan)
    EditText alamattambahmaulid;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaeReference;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_agenda_ramadhan);
        ButterKnife.bind(this);

        dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        tanggaltambahramadhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        btnTambahRamadhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TambahAgendaRamadhan();

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

                tanggaltambahramadhan.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();


    }

    private void TambahAgendaRamadhan() {
        databaeReference = database.getReference();
        String tanggal = tanggaltambahramadhan.getText().toString();
        String nama = namatambahmaulid.getText().toString();
        String alamat = alamattambahmaulid.getText().toString();

        if (tanggal.isEmpty()) {
            tanggaltambahramadhan.setError("Tanggal Tidak Boleh Kosong");
            tanggaltambahramadhan.requestFocus();
            return;
        } else if (nama.isEmpty()) {
            namatambahmaulid.setError("Nama Tidak Boleh Kosong");
            namatambahmaulid.requestFocus();
            return;
        } else if (alamat.isEmpty()) {
            alamattambahmaulid.setError("Alamat Tidak Boleh Kosong");
            alamattambahmaulid.requestFocus();
            return;
        } else {

            databaeReference.child("AgendaRamadhan").push().setValue(new AgendaRamadhan(tanggal, nama, alamat)).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(TambahAgendaRamadhanActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                    tanggaltambahramadhan.setText("");
                    namatambahmaulid.setText("");
                    alamattambahmaulid.setText("");
                    tanggaltambahramadhan.requestFocus();
                    startActivity(new Intent(TambahAgendaRamadhanActivity.this, KelolaAgendaRamadhanActivity.class));
                    finish();
                }
            });
        }

    }
}