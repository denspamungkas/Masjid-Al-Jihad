package com.example.deni.masjidal_jihad.Adapter.Agenda;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.EditAgendaKhutbahActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Agenda.LihatAgendaKhutbahActivity;
import com.example.deni.masjidal_jihad.Activity.Admin.Informasi.LihatInformasiAdmin;
import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaKhutbah;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EditAgendaKhutbahAdapter extends RecyclerView.Adapter<EditAgendaKhutbahAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<AgendaKhutbah> mEditAgendaKhutbah;


    public EditAgendaKhutbahAdapter(Context mContext, ArrayList<AgendaKhutbah> mEditAgendaKhutbah) {

        this.mContext = mContext;
        this.mEditAgendaKhutbah = mEditAgendaKhutbah;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_edit_agenda_khutbah, parent, false);

        return new EditAgendaKhutbahAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.txtTanggal.setText(mEditAgendaKhutbah.get(position).getTanggal());
        holder.txtKhatib.setText(mEditAgendaKhutbah.get(position).getKhatib());
        holder.txtPekan.setText(mEditAgendaKhutbah.get(position).getPekan());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggal = mEditAgendaKhutbah.get(position).getTanggal();
                String khatib = mEditAgendaKhutbah.get(position).getKhatib();
                String pekan = mEditAgendaKhutbah.get(position).getPekan();
                String key = mEditAgendaKhutbah.get(position).getKey();

                Intent intent = new Intent(mContext, EditAgendaKhutbahActivity.class);
                intent.putExtra(EditAgendaKhutbahActivity.EXTRA_TANGGAL_AGENDA_KHUTBAH, tanggal);
                intent.putExtra(EditAgendaKhutbahActivity.EXTRA_KHATIB_AGENDA_KHUTBAH, khatib);
                intent.putExtra(EditAgendaKhutbahActivity.EXTRA_PEKAN_AGENDA_KHUTBAH, pekan);
                intent.putExtra(EditAgendaKhutbahActivity.EXTRA_KEY_AGENDA_KHUTBAH, key);
                mContext.startActivity(intent);
                ((LihatAgendaKhutbahActivity) mContext).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mEditAgendaKhutbah.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtTanggal, txtKhatib, txtPekan;
        Button btnEdit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTanggal = itemView.findViewById(R.id.txttanggaleditagendakhutbah);
            txtKhatib = itemView.findViewById(R.id.txtkhatibeditagendakhutbah);
            txtPekan = itemView.findViewById(R.id.txtpekaneditagendakhutbah);
            btnEdit = itemView.findViewById(R.id.btn_edit_khutbah);
        }
    }

}
