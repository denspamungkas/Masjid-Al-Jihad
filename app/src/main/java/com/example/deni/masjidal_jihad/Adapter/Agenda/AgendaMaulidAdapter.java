package com.example.deni.masjidal_jihad.Adapter.Agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaKhutbah;
import com.example.deni.masjidal_jihad.model.AgendaMaulid;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AgendaMaulidAdapter extends RecyclerView.Adapter<AgendaMaulidAdapter.MyViewHolder>{
    private Context mContext;
    private ArrayList<AgendaMaulid> mAgendaMaulid;

    public AgendaMaulidAdapter(Context mContext, ArrayList<AgendaMaulid> mAgendaMaulid) {
        this.mContext = mContext;
        this.mAgendaMaulid = mAgendaMaulid;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagendamauliduser, parent, false);

        return new AgendaMaulidAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtJudul.setText(mAgendaMaulid.get(position).getJudul());
        holder.txtTanggal.setText(mAgendaMaulid.get(position).getTanggal());
        holder.txtIsi.setText(mAgendaMaulid.get(position).getIsi());
    }

    @Override
    public int getItemCount() {
        return mAgendaMaulid.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtJudul, txtTanggal, txtIsi;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txtjudulagendamaulid);
            txtTanggal = itemView.findViewById(R.id.txttanggalagendamaulid);
            txtIsi = itemView.findViewById(R.id.txtisiagendamaulid);
        }
    }
}
