package com.example.testnav.ui.photo;


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

import com.example.testnav.databinding.FragmentPhotoBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoFragment extends Fragment {
    private PhotoViewModel photoViewModel;
    private FragmentPhotoBinding binding;
    private PhotoAdapter adapter;
    private PhotoRepository photoRepository;
    private List<Photo> photoList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        photoViewModel =
                new ViewModelProvider(this).get(PhotoViewModel.class);
        binding = FragmentPhotoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        photoList = new ArrayList<>();
        photoRepository = new PhotoRepository(getActivity().getApplication());
        adapter = new PhotoAdapter(photoList,getActivity());

        final RecyclerView rvPhoto = binding.fragmentPhoto;
        rvPhoto.setLayoutManager(new LinearLayoutManager(getContext()));
        photoViewModel.getAllPhotos().observe(getViewLifecycleOwner(), new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photoList) {
                adapter.getAllPhotos(photoList);
                rvPhoto.setAdapter(adapter);
            }
        });
        networkRequest();
        return root;
    }
    private void networkRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PhotoApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PhotoApi api = retrofit.create(PhotoApi.class);
        Call<List<Photo>> call = api.getAllPhotos();

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if(response.isSuccessful()){
                    photoRepository.delete();
                    photoRepository.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
