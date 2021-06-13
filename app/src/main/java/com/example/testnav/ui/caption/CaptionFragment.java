package com.example.testnav.ui.caption;

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

import com.example.testnav.databinding.FragmentCaptionBinding;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CaptionFragment extends Fragment {
    private CaptionViewModel captionViewModel;
    private FragmentCaptionBinding binding;
    private CaptionAdapter adapter;
    private CaptionRepository captionRepository;
    private List<Caption> captionList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        captionViewModel =
                new ViewModelProvider(this).get(CaptionViewModel.class);

        binding = FragmentCaptionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        captionList = new ArrayList<>();
        captionRepository = new CaptionRepository(getActivity().getApplication());
        adapter = new CaptionAdapter(captionList,getActivity());

        final RecyclerView rvCaption = binding.fragmentCaption;
        rvCaption.setLayoutManager(new LinearLayoutManager(getContext()));
        captionViewModel.getAllCaptions().observe(getViewLifecycleOwner(), new Observer<List<Caption>>() {
            @Override
            public void onChanged(List<Caption> captionList) {
                adapter.getAllCaptions(captionList);
                rvCaption.setAdapter(adapter);
            }
        });

        networkRequest();
        return root;
    }
    private void networkRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CaptionApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CaptionApi api = retrofit.create(CaptionApi.class);
        Call<List<Caption>> call = api.getAllCaptions();

        call.enqueue(new Callback<List<Caption>>() {
            @Override
            public void onResponse(Call<List<Caption>> call, Response<List<Caption>> response) {
                if(response.isSuccessful()){
                    captionRepository.delete();
                    captionRepository.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Caption>> call, Throwable t) {

            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
