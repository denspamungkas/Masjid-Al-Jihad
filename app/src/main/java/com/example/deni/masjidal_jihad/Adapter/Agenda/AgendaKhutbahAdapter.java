package com.example.deni.masjidal_jihad.Adapter.Agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaKhutbah;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AgendaKhutbahAdapter extends RecyclerView.Adapter<AgendaKhutbahAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<AgendaKhutbah> mAgendaKhutbah;


    public AgendaKhutbahAdapter(Context mContext, ArrayList<AgendaKhutbah> mAgendaKhutbah) {
        this.mContext = mContext;
        this.mAgendaKhutbah = mAgendaKhutbah;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagendakhutbahuser, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtTanggal.setText(mAgendaKhutbah.get(position).getTanggal());
        holder.txtKhatib.setText(mAgendaKhutbah.get(position).getKhatib());
        holder.txtPekan.setText(mAgendaKhutbah.get(position).getPekan());

    }

    @Override
    public int getItemCount() {
        return mAgendaKhutbah.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtTanggal, txtKhatib, txtPekan;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTanggal = itemView.findViewById(R.id.txttanggalagendakhatib);
            txtKhatib = itemView.findViewById(R.id.txtagendakhatib);
            txtPekan = itemView.findViewById(R.id.txtagendakhatibpekan);
        }
    }
}
