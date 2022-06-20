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

/**
 * Klasa służąca do inicjowania obiektów RecycleView w celu ich wyświetlenia.
 */
public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder> {
    private ArrayList<Friend> friends;
    private int user_id;
    private DeleteBtnClicked deleteBtnClicked;

    public FriendsAdapter(ArrayList<Friend> friends, int user, DeleteBtnClicked deleteBtnClicked) {
        this.friends = friends;
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
    public FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.fragment_item_friend, parent, false);
        FriendsViewHolder flightViewHolder = new FriendsViewHolder(listItem);
        return flightViewHolder;
    }

    /**
     * Metoda inicująca wartości obiektów klasy FlightViewHolder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull FriendsViewHolder holder, int position) {

        //holder.imPhoto.setImageResource(flights.get(position).getImgID());

        holder.contentFriend.setText(String.valueOf(friends.get(position).getEmail()));
        holder.contentFriendID.setText(String.valueOf(friends.get(position).getId_user()));
    }

    /**
     * Metoda zwracająca długość tablicy lotów do wyświetlenia
     * @return
     */
    @Override
    public int getItemCount() {
        return friends.size();
    }

    /**
     * Obiekt RecycleView - flight_row.xml.
     * Do obiektu jezt przypisana referencja przypisanego lotu - OpenSky flight
     */
    public class FriendsViewHolder extends RecyclerView.ViewHolder{
        //ImageView imPhoto;
        TextView contentFriend;
        TextView contentFriendID;

        public FriendsViewHolder(@NonNull View itemView){
            super(itemView);
            contentFriend = itemView.findViewById(R.id.contentFriend);
            contentFriendID = itemView.findViewById(R.id.contentFriendID);

            /**
             * Metoda opisująca działanie przycisku usuwania dodanego lotu
             */
            itemView.findViewById(R.id.deleteBtnFriend).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    profile.removeItem(flight);

                    String url = "http://192.168.43.43:8081/api/usersusers/delete?friend_id=" + contentFriendID.getText().toString()
                            + "&user_id=" + user_id;
                    Log.i("mesDeleteFriend", url);
                    Log.i("usuwamy usera", url);
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


