package com.example.testnav.ui.music;



import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testnav.R;
import com.example.testnav.ui.caption.Caption;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.UserItemViewHolder> {
    private List<Music> musics;
    private Context context;

    public MusicAdapter(List<Music> musics, Context c) {
        this.musics = musics;
        this.context = c;
    }

    @Override
    public int getItemCount() {
        return musics.size();
    }

    @Override
    public UserItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_music, parent, false);

        return new UserItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserItemViewHolder holder, int position) {
        Music u = musics.get(position);

        holder.linkSong = u.getLink_Music();
        holder.tvSong.setText(u.getSong());
    }

    public static class UserItemViewHolder extends RecyclerView.ViewHolder {

        MediaPlayer mediaPlayer;
        public FloatingActionButton btn;
        public TextView tvSong;
        public String linkSong;
        Boolean flag = true;
        public UserItemViewHolder(View itemView) {
            super(itemView);
            btn = (FloatingActionButton) itemView.findViewById(R.id.floatingActionButton);
            tvSong = itemView.findViewById(R.id.song);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    playAudio(linkSong);
                }
            });

        }

        public void playAudio(String audioUrl) {


            if(flag){
                mediaPlayer = new MediaPlayer();

                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                try {
                    mediaPlayer.setDataSource(audioUrl);

                    mediaPlayer.prepare();
                    Toast.makeText(btn.getContext(), "Connect Internet Success",Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(btn.getContext(), "No Internet",Toast.LENGTH_SHORT).show();
                }
                flag = false;
            }
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }else{
                mediaPlayer.start();
            }
        }
    }
    public void getAllMusics(List<Music> musicList){
        this.musics = musicList;
    }
}
