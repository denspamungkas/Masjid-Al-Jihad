package com.example.deni.masjidal_jihad.Adapter.Agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deni.masjidal_jihad.R;
import com.example.deni.masjidal_jihad.model.AgendaIdulAdha;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AgendaIdulAdhaAdapter extends RecyclerView.Adapter<AgendaIdulAdhaAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<AgendaIdulAdha> mAgendaIdulAdha;

    public AgendaIdulAdhaAdapter(Context mContext, ArrayList<AgendaIdulAdha> mAgendaIdulAdha) {
        this.mContext = mContext;
        this.mAgendaIdulAdha = mAgendaIdulAdha;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listagendaiduladhauser, parent, false);

        return new MyViewHolder(view);
   }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtJudul.setText(mAgendaIdulAdha.get(position).getJudul());
        holder.txtTanggal.setText(mAgendaIdulAdha.get(position).getTanggal());
        holder.txtIsi.setText(mAgendaIdulAdha.get(position).getIsi());

    }

    @Override
    public int getItemCount() {
        return mAgendaIdulAdha.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


            TextView txtJudul, txtTanggal, txtIsi;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                txtJudul = itemView.findViewById(R.id.txtjudulagendaiduladha);
                txtTanggal = itemView.findViewById(R.id.txttanggalagendaiduladha);
                txtIsi = itemView.findViewById(R.id.txtisiagendaiduladha);
            }


    }
}
