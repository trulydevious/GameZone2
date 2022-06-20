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
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {
    private ArrayList<Game> games;
    private int user_id;
    private DeleteBtnClicked deleteBtnClicked;

    public GameAdapter(ArrayList<Game> games, int user, DeleteBtnClicked deleteBtnClicked) {
        this.games = games;
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
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.fragment_item_games, parent, false);
        GameViewHolder flightViewHolder = new GameViewHolder(listItem);
        return flightViewHolder;
    }

    /**
     * Metoda inicująca wartości obiektów klasy FlightViewHolder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {

        //holder.imPhoto.setImageResource(flights.get(position).getImgID());

        holder.contentGames.setText(String.valueOf(games.get(position).getName()));
        holder.contentGamesID.setText(String.valueOf(games.get(position).getId_game()));
    }

    /**
     * Metoda zwracająca długość tablicy lotów do wyświetlenia
     * @return
     */
    @Override
    public int getItemCount() {
        return games.size();
    }

    /**
     * Obiekt RecycleView - flight_row.xml.
     * Do obiektu jezt przypisana referencja przypisanego lotu - OpenSky flight
     */
    public class GameViewHolder extends RecyclerView.ViewHolder{
        //ImageView imPhoto;
        TextView contentGames;
        TextView contentGamesID;

        public GameViewHolder(@NonNull View itemView){
            super(itemView);
            contentGames = itemView.findViewById(R.id.contentGames);
            contentGamesID = itemView.findViewById(R.id.contentGamesID);

            /**
             * Metoda opisująca działanie przycisku usuwania dodanego lotu
             */
            itemView.findViewById(R.id.deleteBtnGame).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    profile.removeItem(flight);

                    String url = "http://192.168.43.43:8081/api/usersgames/delete?game_id=" + contentGamesID.getText().toString()
                        + "&user_id=" + user_id;
                    Log.i("mesDeleteGame", url);

                     RequestQueue queue = Volley.newRequestQueue(v.getContext().getApplicationContext());
                     StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, this::onResponse, this::onErrorResponse);
                     queue.add(stringRequest);


                     }

                     private void onResponse(String response) {
                         Log.i("removed", "removed");
                     }

                     private void onErrorResponse(VolleyError error) {
                         Log.i("errorRemoved", String.valueOf(error));
                     }

            });
        }
    }

}

