package com.androidtutorialshub.loginregister.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.Song;

import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class SongsRecyclerAdapter extends RecyclerView.Adapter<SongsRecyclerAdapter.SongViewHolder> {

    private List<Song> listSongs;

    public SongsRecyclerAdapter(List<Song> listSongs) {
        this.listSongs = listSongs;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_song_recycler, parent, false);

        return new SongViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        holder.textViewName.setText(listSongs.get(position).getName());
        holder.textViewArtist.setText(listSongs.get(position).getArtist());
        holder.textViewAlbum.setText(listSongs.get(position).getAlbum());
    }

    @Override
    public int getItemCount() {
        Log.v(SongsRecyclerAdapter.class.getSimpleName(),""+listSongs.size());
        return listSongs.size();
    }


    /**
     * ViewHolder class
     */
    public class SongViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName, textViewArtist, textViewAlbum;

        public SongViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewArtist = (AppCompatTextView) view.findViewById(R.id.textViewArtist);
            textViewAlbum = (AppCompatTextView) view.findViewById(R.id.textViewAlbum);
        }
    }


}
