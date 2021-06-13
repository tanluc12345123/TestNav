package com.example.testnav.ui.music;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testnav.databinding.FragmentMusicBinding;
import com.example.testnav.ui.caption.Caption;
import com.example.testnav.ui.caption.CaptionAdapter;
import com.example.testnav.ui.caption.CaptionApi;
import com.example.testnav.ui.caption.CaptionRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MusicFragment extends Fragment {
    private MusicViewModel musicViewModel;
    private FragmentMusicBinding binding;
    private MusicAdapter adapter;

    private MusicRepository musicRepository;
    private List<Music> musicList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        musicViewModel =
                new ViewModelProvider(this).get(MusicViewModel.class);

        binding = FragmentMusicBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        musicList = new ArrayList<>();
        musicRepository = new MusicRepository(getActivity().getApplication());
        adapter = new MusicAdapter(musicList,getActivity());

        final RecyclerView rvMusic = binding.fragmentMusic;
        rvMusic.setHasFixedSize(true);
        rvMusic.setLayoutManager(new LinearLayoutManager(getContext()));
        musicViewModel.getAllMusics().observe(getViewLifecycleOwner(), new Observer<List<Music>>() {
            @Override
            public void onChanged(List<Music> musicList) {
                adapter.getAllMusics(musicList);
                rvMusic.setAdapter(adapter);
            }
        });
        networkRequest();
        return root;
    }

    private void networkRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MusicApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MusicApi api = retrofit.create(MusicApi.class);
        Call<List<Music>> call = api.getAllMusics();

        call.enqueue(new Callback<List<Music>>() {
            @Override
            public void onResponse(Call<List<Music>> call, Response<List<Music>> response) {
                if(response.isSuccessful()){
                    musicRepository.delete();
                    musicRepository.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Music>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

