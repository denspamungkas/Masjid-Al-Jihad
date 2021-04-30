package com.example.deni.masjidal_jihad.Adapter.Agenda;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.EditAgendaMaulidActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.LihatAgendaMaulidActivity;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaMaulid;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EditAgendaMaulidAdapter extends RecyclerView.Adapter<EditAgendaMaulidAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<AgendaMaulid> mEditAgendaMaulid;

    public EditAgendaMaulidAdapter(Context mContext, ArrayList<AgendaMaulid> mEditAgendaMaulid) {
        this.mContext = mContext;
        this.mEditAgendaMaulid = mEditAgendaMaulid;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_edit_agenda_maulid, parent, false);


        return new EditAgendaMaulidAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.txtJudul.setText(mEditAgendaMaulid.get(position).getJudul());
        holder.txtTanggal.setText(mEditAgendaMaulid.get(position).getTanggal());
        holder.txtIsi.setText(mEditAgendaMaulid.get(position).getIsi());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = mEditAgendaMaulid.get(position).getJudul();
                String tanggal = mEditAgendaMaulid.get(position).getTanggal();
                String isi = mEditAgendaMaulid.get(position).getIsi();
                String key = mEditAgendaMaulid.get(position).getKey();

                Intent intent = new Intent(mContext, EditAgendaMaulidActivity.class);
                intent.putExtra(EditAgendaMaulidActivity.EXTRA_JUDUL_MAULID, judul);
                intent.putExtra(EditAgendaMaulidActivity.EXTRA_TANGGAL_MAULID, tanggal);
                intent.putExtra(EditAgendaMaulidActivity.EXTRA_ISI_MAULID, isi);
                intent.putExtra(EditAgendaMaulidActivity.EXTRA_KEY_MAULID, key);
                mContext.startActivity(intent);
                ((LihatAgendaMaulidActivity)mContext).finish();
            }
        });

    }



    @Override
    public int getItemCount() {
         return mEditAgendaMaulid.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtJudul, txtTanggal, txtIsi;
        Button btnEdit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txtjuduleditmaulid);
            txtTanggal = itemView.findViewById(R.id.txttanggaleditmaulid);
            txtIsi = itemView.findViewById(R.id.txtisieditmaulid);
            btnEdit = itemView.findViewById(R.id.btn_edit_maulid1);
        }
    }
}
