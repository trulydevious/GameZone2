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
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private ArrayList<Event> events;
    private int user_id;
    private DeleteBtnClicked deleteBtnClicked;

    public EventAdapter(ArrayList<Event> events, int user, DeleteBtnClicked deleteBtnClicked) {
        this.events = events;
        this.user_id = user;
        this.deleteBtnClicked = deleteBtnClicked;
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
        View listItem = layoutInflater.inflate(R.layout.fragment_item_event, parent, false);
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

        holder.contentEventName.setText(String.valueOf(events.get(position).getName()));
        holder.contentEventID.setText(String.valueOf(events.get(position).getId_event()));
        holder.contentEventDate.setText(String.valueOf(events.get(position).getDate()));
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
        TextView contentEventName;
        TextView contentEventID;
        TextView contentEventDate;

        public EventViewHolder(@NonNull View itemView){
            super(itemView);
            contentEventName = itemView.findViewById(R.id.contentEventName);
            contentEventID = itemView.findViewById(R.id.contentEventID);
            contentEventDate = itemView.findViewById(R.id.contentEventDate);

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

