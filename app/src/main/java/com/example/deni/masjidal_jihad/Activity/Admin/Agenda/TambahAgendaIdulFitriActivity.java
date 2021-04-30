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
import com.example.deni.masjidal_jihad.model.AgendaIdulAdha;
import com.example.deni.masjidal_jihad.model.AgendaIdulFitri;
import com.example.deni.masjidal_jihad.model.AgendaKhutbah;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TambahAgendaIdulFitriActivity extends AppCompatActivity {

    @BindView(R.id.btn_tambah_fitri)
    Button btnTambahFitri;
    @BindView(R.id.tanggaltambahfitri)
    EditText tanggaltambahfitri;
    @BindView(R.id.judultambahfitri)
    EditText judultambahfitri;
    @BindView(R.id.isitambahfitri)
    EditText isitambahfitri;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_agenda_idul_fitri);
        ButterKnife.bind(this);

        dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        tanggaltambahfitri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();

            }
        });

        btnTambahFitri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TambahAgendaFitri();

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

                tanggaltambahfitri.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();


    }

    private void TambahAgendaFitri() {
        databaseReference = database.getReference();
        String judul = judultambahfitri.getText().toString();
        String tanggal = tanggaltambahfitri.getText().toString();
        String isi = isitambahfitri.getText().toString();

        if (judul.isEmpty()) {
            judultambahfitri.setError("Judul Tidak Boleh Kosong");
            judultambahfitri.requestFocus();
            return;
        } else if (tanggal.isEmpty()) {
            tanggaltambahfitri.setError("Tanggal Tidak Boleh Kosong");
            tanggaltambahfitri.requestFocus();
            return;
        } else if (isi.isEmpty()) {
            isitambahfitri.setError("Isi Tidak Boleh Kosong");
            isitambahfitri.requestFocus();
            return;
        } else {

            databaseReference.child("AgendaFitri").push().setValue(new AgendaIdulFitri(judul, tanggal, isi)).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(TambahAgendaIdulFitriActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                    judultambahfitri.setText("");
                    tanggaltambahfitri.setText("");
                    isitambahfitri.setText("");
                    tanggaltambahfitri.requestFocus();
                    startActivity(new Intent(TambahAgendaIdulFitriActivity.this, KelolaAgendaIdulFitriActivity.class));
                    finish();
                }
            });
        }

    }


}