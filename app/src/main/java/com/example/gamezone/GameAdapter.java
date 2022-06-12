package com.example.gamezone;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Klasa służąca do inicjowania obiektów RecycleView w celu ich wyświetlenia.
 */
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {
    private ArrayList<Game> games;

    public GameAdapter(ArrayList<Game> games) {
        this.games = games;
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
        holder.contentGames.setText(games.get(position).getName());
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

        public GameViewHolder(@NonNull View itemView){
            super(itemView);
            contentGames = itemView.findViewById(R.id.contentGames);

            /**
             * Metoda opisująca działanie przycisku usuwania dodanego lotu
             */
            itemView.findViewById(R.id.deleteBtnGame).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    profile.removeItem(flight);
                }
            });
        }
    }

}

