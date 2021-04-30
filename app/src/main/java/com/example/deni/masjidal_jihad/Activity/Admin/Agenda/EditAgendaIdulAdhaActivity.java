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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditAgendaIdulAdhaActivity extends AppCompatActivity {
    public static final String EXTRA_JUDUL_AGENDA_ADHA = "extraJudul";
    public static final String EXTRA_TANGGAL_AGENDA_ADHA = "extraTanggal";
    public static final String EXTRA_ISI_AGENDA_ADHA = "extraIsi";
    public static final String EXTRA_KEY_AGENDA_ADHA= "extraKey";
    @BindView(R.id.juduleditagendaadha)
    EditText judulagendaadha;
    @BindView(R.id.tanggaleditagendaadha)
    EditText tanggalagendaadha;
    @BindView(R.id.isieditagendaadha)
    EditText isiagendaadha;
    @BindView(R.id.btn_lihat_edit_agenda_adha)
    Button btnEditAgendaAdha;

    DatabaseReference databaseReference;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_agenda_idul_adha);
        ButterKnife.bind(this);

        dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        tanggalagendaadha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();

            }
        });


        judulagendaadha.setText(getIntent().getStringExtra(EXTRA_JUDUL_AGENDA_ADHA));
        tanggalagendaadha.setText(getIntent().getStringExtra(EXTRA_TANGGAL_AGENDA_ADHA));
        isiagendaadha.setText(getIntent().getStringExtra(EXTRA_ISI_AGENDA_ADHA));

        btnEditAgendaAdha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = judulagendaadha.getText().toString();
                String tanggal = tanggalagendaadha.getText().toString();
                String isi = isiagendaadha.getText().toString();

                if (judul.isEmpty()) {
                    judulagendaadha.setError("Judul Tidak Boleh Kosong");
                    judulagendaadha.requestFocus();
                    return;
                } else if (tanggal.isEmpty()) {
                    tanggalagendaadha.setError("Tanggal Tidak Boleh Kosong");
                    tanggalagendaadha.requestFocus();
                    return;
                }else if (isi.isEmpty()) {
                    isiagendaadha.setError("Isi Tidak Boleh Kosong");
                    isiagendaadha.requestFocus();
                    return;
                }else{
                    AgendaIdulAdha setAgendaAdha = new AgendaIdulAdha();
                    setAgendaAdha.setJudul(judulagendaadha.getText().toString());
                    setAgendaAdha.setTanggal(tanggalagendaadha.getText().toString());
                    setAgendaAdha.setIsi(isiagendaadha.getText().toString());
                    updateAgendaAdha(setAgendaAdha);
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

                tanggalagendaadha.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void updateAgendaAdha(AgendaIdulAdha setAgendaAdha) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference();
        String key = getIntent().getStringExtra(EXTRA_KEY_AGENDA_ADHA);

        databaseReference.child("AgendaAdha")
                .child(key)
                .setValue(setAgendaAdha)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditAgendaIdulAdhaActivity.this, "Update Berhasil", Toast.LENGTH_SHORT).show();
                        judulagendaadha.setText("");
                        tanggalagendaadha.setText("");
                        isiagendaadha.setText("");
                        tanggalagendaadha.requestFocus();
                        startActivity(new Intent(EditAgendaIdulAdhaActivity.this, LihatAgendaIdulAdhaActivity.class));
                        finish();
                    }
                });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(EditAgendaIdulAdhaActivity.this, LihatAgendaIdulAdhaActivity.class));
        finish();
    }

}