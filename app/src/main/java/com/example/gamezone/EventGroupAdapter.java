package com.example.gamezone;


import android.content.Context;
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

/**
 * Klasa służąca do inicjowania obiektów RecycleView w celu ich wyświetlenia.
 */
public class EventGroupAdapter extends RecyclerView.Adapter<EventGroupAdapter.EventViewHolder> {
    private ArrayList<Event> events;

    public EventGroupAdapter(ArrayList<Event> events) {
        this.events = events;
    }

    /**
     * Metoda inicjująca stworzenie holdera.
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.fragment_item_event_group, parent, false);
        EventViewHolder flightViewHolder = new EventViewHolder(listItem);
        return flightViewHolder;
    }

    /**
     * Metoda inicująca wartości obiektów klasy FlightViewHolder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {

        //holder.imPhoto.setImageResource(flights.get(position).getImgID());

        holder.contentEventGroupName.setText(String.valueOf(events.get(position).getName()));
        holder.contentEventGroupNameID.setText(String.valueOf(events.get(position).getId_event()));
        holder.contentEventGroupNameDate.setText(String.valueOf(events.get(position).getDate()));
    }

    /**
     * Metoda zwracająca długość tablicy lotów do wyświetlenia
     * @return
     */
    @Override
    public int getItemCount() {
        return events.size();
    }

    /**
     * Obiekt RecycleView - flight_row.xml.
     * Do obiektu jezt przypisana referencja przypisanego lotu - OpenSky flight
     */
    public class EventViewHolder extends RecyclerView.ViewHolder{
        //ImageView imPhoto;
        TextView contentEventGroupName;
        TextView contentEventGroupNameID;
        TextView contentEventGroupNameDate;

        public EventViewHolder(@NonNull View itemView){
            super(itemView);
            contentEventGroupName = itemView.findViewById(R.id.contentEventGroupName);
            contentEventGroupNameID = itemView.findViewById(R.id.contentEventGroupNameID);
            contentEventGroupNameDate = itemView.findViewById(R.id.contentEventGroupNameDate);

            /**
             * Metoda opisująca działanie przycisku usuwania dodanego lotu
             */
//            itemView.findViewById(R.id.deleteBtnGame).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    profile.removeItem(flight);

//                    String url = "http://192.168.1.51:8081/api/usersgames/delete?game_id=" + contentEventID.getText().toString()
//                            + "&user_id=" + user_id;
//                    Log.i("mesDeleteGame", url);
//
//                    RequestQueue queue = Volley.newRequestQueue(v.getContext().getApplicationContext());
//                    StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, this::onResponse, this::onErrorResponse);
//                    queue.add(stringRequest);


//                }
//
//                private void onResponse(String response) {
//                    Log.i("removed", "removed");
//                }
//
//                private void onErrorResponse(VolleyError error) {
//                    Log.i("errorRemoved", String.valueOf(error));
//                }
//
//            });
        }
    }

}


