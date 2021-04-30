package com.example.deni.masjidal_jihad.Adapter.Agenda;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.EditAgendaRamadhanActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.LihatAgendaRamadhanActivity;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaRamadhan;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EditAgendaRamadhanAdapter extends RecyclerView.Adapter<EditAgendaRamadhanAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<AgendaRamadhan> mEditAgendaRamadhan;

    public EditAgendaRamadhanAdapter(Context mContext, ArrayList<AgendaRamadhan> mEditAgendaRamadhan) {
        this.mContext = mContext;
        this.mEditAgendaRamadhan = mEditAgendaRamadhan;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_edit_agenda_ramadhan, parent, false);


        return new EditAgendaRamadhanAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.txtTanggal.setText(mEditAgendaRamadhan.get(position).getTanggal());
        holder.txtNama.setText(mEditAgendaRamadhan.get(position).getNama());
        holder.txtAlamat.setText(mEditAgendaRamadhan.get(position).getAlamat());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggal = mEditAgendaRamadhan.get(position).getTanggal();
                String nama = mEditAgendaRamadhan.get(position).getNama();
                String alamat = mEditAgendaRamadhan.get(position).getAlamat();
                String key = mEditAgendaRamadhan.get(position).getKey();

                Intent intent = new Intent(mContext, EditAgendaRamadhanActivity.class);
                intent.putExtra(EditAgendaRamadhanActivity.EXTRA_TANGGAL_RAMADHAN, tanggal);
                intent.putExtra(EditAgendaRamadhanActivity.EXTRA_NAMA_RAMADHAN, nama);
                intent.putExtra(EditAgendaRamadhanActivity.EXTRA_ALAMAT_RAMADHAN, nama);
                mContext.startActivity(intent);
                ((LihatAgendaRamadhanActivity)mContext).finish();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mEditAgendaRamadhan.size();    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTanggal, txtNama, txtAlamat;
        Button btnEdit;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            txtTanggal = itemView.findViewById(R.id.txttanggaleditramadhan);
            txtNama = itemView.findViewById(R.id.txtnamaeditramadhan);
            txtAlamat = itemView.findViewById(R.id.txtalamateditramadhan);
            btnEdit = itemView.findViewById(R.id.btn_edit_ramadhan);
        }
    }
}
