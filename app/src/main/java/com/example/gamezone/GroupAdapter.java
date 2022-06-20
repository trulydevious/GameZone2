package com.example.gamezone;

import android.content.Context;
import android.content.Intent;
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
public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {
    private ArrayList<Group> groups;
    private int user_id;
    private DeleteBtnClicked deleteBtnClicked;

    public GroupAdapter(ArrayList<Group> groups, int user, DeleteBtnClicked deleteBtnClicked) {
        this.groups = groups;
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
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.fragment_item_groups, parent, false);
        GroupViewHolder flightViewHolder = new GroupViewHolder(listItem);
        return flightViewHolder;
    }

    /**
     * Metoda inicująca wartości obiektów klasy FlightViewHolder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {

        //holder.imPhoto.setImageResource(flights.get(position).getImgID());

        holder.contentGroup.setText(String.valueOf(groups.get(position).getName()));
        holder.contentGroupID.setText(String.valueOf(groups.get(position).getId_group()));
    }

    /**
     * Metoda zwracająca długość tablicy lotów do wyświetlenia
     * @return
     */
    @Override
    public int getItemCount() {
        return groups.size();
    }

    /**
     * Obiekt RecycleView - flight_row.xml.
     * Do obiektu jezt przypisana referencja przypisanego lotu - OpenSky flight
     */
    public class GroupViewHolder extends RecyclerView.ViewHolder{
        //ImageView imPhoto;
        TextView contentGroup;
        TextView contentGroupID;

        public GroupViewHolder(@NonNull View itemView){
            super(itemView);
            contentGroup = itemView.findViewById(R.id.contentGroup);
            contentGroupID = itemView.findViewById(R.id.contentGroupID);

            /**
             * Metoda opisująca działanie przycisku usuwania dodanego lotu
             */
            itemView.findViewById(R.id.deleteBtnGroup).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    profile.removeItem(flight);

                    String url = "http://192.168.43.43:8081/api/group/delete/user?user_id=" + user_id
                            + "&group_id=" + contentGroupID.getText().toString();
                    Log.i("mesLeaveGroup", url);

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

            itemView.findViewById(R.id.item_img_group).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), GroupActivity.class);
                    intent.putExtra("groupName", contentGroup.getText().toString());
                    intent.putExtra("groupID", String.valueOf(contentGroupID.getText()));
                    v.getContext().startActivity(intent);



                }

            });
        }
    }

}

