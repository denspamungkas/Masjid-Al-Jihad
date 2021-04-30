package com.example.deni.masjidal_jihad.Adapter.Agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaIdulFitri;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AgendaIdulFitriAdapter extends RecyclerView.Adapter<AgendaIdulFitriAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<AgendaIdulFitri> mAgendaIdulFitri;

    public AgendaIdulFitriAdapter(Context mContext, ArrayList<AgendaIdulFitri> mAgendaIdulFitri) {
        this.mContext = mContext;
        this.mAgendaIdulFitri = mAgendaIdulFitri;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagendaidulfitriuser, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txtJudul.setText(mAgendaIdulFitri.get(position).getJudul());
        holder.txtTanggal.setText(mAgendaIdulFitri.get(position).getTanggal());
        holder.txtIsi.setText(mAgendaIdulFitri.get(position).getIsi());

    }

    @Override
    public int getItemCount() {
        return mAgendaIdulFitri.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtJudul, txtTanggal, txtIsi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.txtjudulagendaidulfitri);
            txtTanggal = itemView.findViewById(R.id.txttanggalagendaidulfitri);
            txtIsi = itemView.findViewById(R.id.txtisiagendaidulfitri);
        }
    }
}
