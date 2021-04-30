package com.example.deni.masjidal_jihad.Adapter.Agenda;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.EditAgendaIdulAdhaActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.EditAgendaIdulFitriActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.LihatAgendaIdulAdhaActivity;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaIdulAdha;



import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EditAgendaIdulAdhaAdapter extends RecyclerView.Adapter<EditAgendaIdulAdhaAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<AgendaIdulAdha> mEditAgendaIdulAdha;


    public EditAgendaIdulAdhaAdapter(Context mContext, ArrayList<AgendaIdulAdha> mEditAgendaIdulAdha) {
        this.mContext = mContext;
        this.mEditAgendaIdulAdha = mEditAgendaIdulAdha;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_edit_agenda_adha, parent, false);


        return new EditAgendaIdulAdhaAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.txtJudul.setText(mEditAgendaIdulAdha.get(position).getJudul());
        holder.txtTanggal.setText(mEditAgendaIdulAdha.get(position).getTanggal());
        holder.txtIsi.setText(mEditAgendaIdulAdha.get(position).getIsi());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = mEditAgendaIdulAdha.get(position).getJudul();
                String tanggal = mEditAgendaIdulAdha.get(position).getTanggal();
                String isi = mEditAgendaIdulAdha.get(position).getIsi();
                String key = mEditAgendaIdulAdha.get(position).getKey();

                Intent intent = new Intent(mContext, EditAgendaIdulAdhaActivity.class);
                intent.putExtra(EditAgendaIdulAdhaActivity.EXTRA_JUDUL_AGENDA_ADHA, judul);
                intent.putExtra(EditAgendaIdulAdhaActivity.EXTRA_TANGGAL_AGENDA_ADHA, tanggal);
                intent.putExtra(EditAgendaIdulAdhaActivity.EXTRA_ISI_AGENDA_ADHA, isi);
                intent.putExtra(EditAgendaIdulAdhaActivity.EXTRA_KEY_AGENDA_ADHA, key);
                mContext.startActivity(intent);
                ((LihatAgendaIdulAdhaActivity)mContext).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mEditAgendaIdulAdha.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtJudul, txtTanggal, txtIsi;
        Button btnEdit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txtjuduleditagendaadha);
            txtTanggal = itemView.findViewById(R.id.txttanggaleditagendaadha);
            txtIsi = itemView.findViewById(R.id.txtisieditagendaadha);
            btnEdit = itemView.findViewById(R.id.btn_edit_agenda_adha);
        }

    }
}
