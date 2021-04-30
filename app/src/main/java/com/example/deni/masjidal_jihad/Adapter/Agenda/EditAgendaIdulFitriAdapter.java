package com.example.deni.masjidal_jihad.Adapter.Agenda;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.EditAgendaIdulFitriActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.LihatAgendaIdulFitriActivity;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaIdulFitri;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EditAgendaIdulFitriAdapter extends RecyclerView.Adapter<EditAgendaIdulFitriAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<AgendaIdulFitri> mEditAgendaIdulFitri;


    public EditAgendaIdulFitriAdapter(Context mContext, ArrayList<AgendaIdulFitri> mEditAgendaIdulFitri) {
        this.mContext = mContext;
        this.mEditAgendaIdulFitri = mEditAgendaIdulFitri;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_edit_agenda_fitri, parent, false);


        return new EditAgendaIdulFitriAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.txtJudul.setText(mEditAgendaIdulFitri.get(position).getJudul());
        holder.txtTanggal.setText(mEditAgendaIdulFitri.get(position).getTanggal());
        holder.txtIsi.setText(mEditAgendaIdulFitri.get(position).getIsi());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = mEditAgendaIdulFitri.get(position).getJudul();
                String tanggal = mEditAgendaIdulFitri.get(position).getTanggal();
                String isi = mEditAgendaIdulFitri.get(position).getIsi();
                String key = mEditAgendaIdulFitri.get(position).getKey();

                Intent intent = new Intent(mContext, EditAgendaIdulFitriActivity.class);
                intent.putExtra(EditAgendaIdulFitriActivity.EXTRA_JUDUL_AGENDA_FITRI, judul);
                intent.putExtra(EditAgendaIdulFitriActivity.EXTRA_TANGGAL_AGENDA_FITRI, tanggal);
                intent.putExtra(EditAgendaIdulFitriActivity.EXTRA_ISI_AGENDA_FITRI, isi);
                intent.putExtra(EditAgendaIdulFitriActivity.EXTRA_KEY_AGENDA_FITRI, key);
                mContext.startActivity(intent);
                ((LihatAgendaIdulFitriActivity)mContext).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mEditAgendaIdulFitri.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtJudul, txtTanggal, txtIsi;
        Button btnEdit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txtjuduleditagendafitri);
            txtTanggal = itemView.findViewById(R.id.txttanggaleditagendafitri);
            txtIsi = itemView.findViewById(R.id.txtisieditagendafitri);
            btnEdit = itemView.findViewById(R.id.btn_edit_agenda_fitri);
        }

    }
}
