package com.example.testnav.ui.home;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.testnav.R;
import com.example.testnav.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    MediaPlayer mediaPlayer;
    Boolean flag = true;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        String link_audio1 = "https://thesaltyfish.000webhostapp.com/Musics/BachNguyetQuangVaNotChuSa-DaiTuDaZi-6911991_hq.mp3";
        String link_audio2 = "https://thesaltyfish.000webhostapp.com/Musics/Ma%20Phap%20Tinh%20Yeu%20-%20Cover.mp3";
        String link_audio3 = "https://thesaltyfish.000webhostapp.com/Musics/ILikeYouSoMuchYoullKnowItEnglishCover-YsabelleCuevas-5825523.mp3";
        binding.floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(link_audio1,binding.floatingActionButton3);
            }
        });
        binding.floatingActionButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(link_audio2,binding.floatingActionButton6);
            }
        });
        binding.floatingActionButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(link_audio3,binding.floatingActionButton9);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void playAudio(String audioUrl, FloatingActionButton btn) {


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
