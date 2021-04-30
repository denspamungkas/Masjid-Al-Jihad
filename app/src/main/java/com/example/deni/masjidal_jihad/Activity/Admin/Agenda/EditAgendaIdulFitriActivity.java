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

import com.example.deni.masjidal_jihad.Activity.Admin.Informasi.EditInformasiAdminActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Informasi.LihatInformasiAdmin;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaIdulFitri;
import com.example.deni.masjidal_jihad.model.Informasi;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditAgendaIdulFitriActivity extends AppCompatActivity {
    public static final String EXTRA_JUDUL_AGENDA_FITRI = "extraJudul";
    public static final String EXTRA_TANGGAL_AGENDA_FITRI = "extraTanggal";
    public static final String EXTRA_ISI_AGENDA_FITRI = "extraIsi";
    public static final String EXTRA_KEY_AGENDA_FITRI= "extraKey";
    @BindView(R.id.juduleditagendafitri)
    EditText judulagendafitri;
    @BindView(R.id.tanggaleditagendafitri)
    EditText tanggalagendafitri;
    @BindView(R.id.isieditagendafitri)
    EditText isiagendafitri;
    @BindView(R.id.btn_lihat_edit_agenda_fitri)
    Button btnEditAgendaFitri;

    DatabaseReference databaseReference;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_agenda_idul_fitri);
        ButterKnife.bind(this);

        dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        tanggalagendafitri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();

            }
        });


        judulagendafitri.setText(getIntent().getStringExtra(EXTRA_JUDUL_AGENDA_FITRI));
        tanggalagendafitri.setText(getIntent().getStringExtra(EXTRA_TANGGAL_AGENDA_FITRI));
        isiagendafitri.setText(getIntent().getStringExtra(EXTRA_ISI_AGENDA_FITRI));


        btnEditAgendaFitri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = judulagendafitri.getText().toString();
                String tanggal = tanggalagendafitri.getText().toString();
                String isi = isiagendafitri.getText().toString();

                if (judul.isEmpty()) {
                    judulagendafitri.setError("Judul Tidak Boleh Kosong");
                    judulagendafitri.requestFocus();
                    return;
                } else if (tanggal.isEmpty()) {
                    tanggalagendafitri.setError("Tanggal Tidak Boleh Kosong");
                    tanggalagendafitri.requestFocus();
                    return;
                }else if (isi.isEmpty()) {
                    isiagendafitri.setError("Isi Tidak Boleh Kosong");
                    isiagendafitri.requestFocus();
                    return;
                }else{
                    AgendaIdulFitri setAgendaFitri = new AgendaIdulFitri();
                    setAgendaFitri.setJudul(judulagendafitri.getText().toString());
                    setAgendaFitri.setTanggal(tanggalagendafitri.getText().toString());
                    setAgendaFitri.setIsi(isiagendafitri.getText().toString());
                    updateAgendaFitri(setAgendaFitri);
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

                tanggalagendafitri.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void updateAgendaFitri(AgendaIdulFitri setAgendaFitri) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference();
        String key = getIntent().getStringExtra(EXTRA_KEY_AGENDA_FITRI);

        databaseReference.child("AgendaFitri")
                .child(key)
                .setValue(setAgendaFitri)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditAgendaIdulFitriActivity.this, "Update Berhasil", Toast.LENGTH_SHORT).show();
                        judulagendafitri.setText("");
                        tanggalagendafitri.setText("");
                        isiagendafitri.setText("");
                        tanggalagendafitri.requestFocus();
                        startActivity(new Intent(EditAgendaIdulFitriActivity.this, LihatAgendaIdulFitriActivity.class));
                        finish();
                    }
                });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(EditAgendaIdulFitriActivity.this, LihatAgendaIdulFitriActivity.class));
        finish();
    }
}