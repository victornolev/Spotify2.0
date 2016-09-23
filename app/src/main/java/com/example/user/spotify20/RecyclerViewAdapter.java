package com.example.user.spotify20;

import android.content.Context;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    private List<Song> songList;
    public static IRecycleViewSelectedElement mListener;

    public static class  ViewHolder extends RecyclerView.ViewHolder{

        TextView mTVSongNumber, mTVTitle, mTVArtist;
        ImageView mIVSettings, mIVDownload, mIVExplicit;
        int position;

        public void setItemPosition(int position)
        {
            this.position = position;
        }

        public ViewHolder(View itemView){
            super(itemView);

            mTVSongNumber = (TextView) itemView.findViewById(R.id.TV_songNumber);
            mTVTitle = (TextView) itemView.findViewById(R.id.TV_title);
            mTVArtist = (TextView) itemView.findViewById(R.id.TV_artist);
            mIVSettings  = (ImageView) itemView.findViewById(R.id.IV_settings);
            mIVDownload  = (ImageView) itemView.findViewById(R.id.IV_download);
            mIVExplicit  = (ImageView) itemView.findViewById(R.id.IV_explicit);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    mListener.onItemSelected(position);
                }
            });
        }

    }

    public RecyclerViewAdapter(List<Song> songsList, IRecycleViewSelectedElement listener){
        this.songList = songsList;
        this.mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_row,parent,false);
        ViewHolder vh = new ViewHolder(mView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder != null) {

            Song song = songList.get(position);

            holder.mTVSongNumber.setText(String.valueOf(song.getNumber()));
            holder.mTVTitle.setText(song.getTitle());
            holder.mTVArtist.setText(song.getArtist());
            holder.setItemPosition(position);

            if (position % 2 == 0){
                holder.mIVExplicit.setVisibility(View.VISIBLE);
            }
            else{
                holder.mIVDownload.setVisibility(View.VISIBLE);

            }
        }
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }


}
