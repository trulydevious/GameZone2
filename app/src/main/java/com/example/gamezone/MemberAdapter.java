package com.example.gamezone;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;


public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MembersViewHolder>  {
    private ArrayList<Friend> members;

    public MemberAdapter(ArrayList<Friend> members) {
        this.members = members;
    }

    /**
     * Metoda inicjująca stworzenie holdera.
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MembersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.fragment_item_member, parent, false);
        MembersViewHolder flightViewHolder = new MembersViewHolder(listItem);
        return flightViewHolder;
    }

    /**
     * Metoda inicująca wartości obiektów klasy FlightViewHolder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MembersViewHolder holder, int position) {

        //holder.imPhoto.setImageResource(flights.get(position).getImgID());

        holder.contentMember.setText(String.valueOf(members.get(position).getEmail()));
        holder.contentMemberID.setText(String.valueOf(members.get(position).getId_user()));
    }

    /**
     * Metoda zwracająca długość tablicy lotów do wyświetlenia
     * @return
     */
    @Override
    public int getItemCount() {
        return members.size();
    }

    /**
     * Obiekt RecycleView - flight_row.xml.
     * Do obiektu jezt przypisana referencja przypisanego lotu - OpenSky flight
     */
    public class MembersViewHolder extends RecyclerView.ViewHolder{
        //ImageView imPhoto;
        TextView contentMember;
        TextView contentMemberID;

        public MembersViewHolder(@NonNull View itemView){
            super(itemView);
            contentMember = itemView.findViewById(R.id.contentMember);
            contentMemberID = itemView.findViewById(R.id.contentMemberID);
        }
    }

}
