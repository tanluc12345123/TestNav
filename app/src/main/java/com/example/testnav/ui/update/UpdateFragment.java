package com.example.testnav.ui.update;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.testnav.MainActivity;
import com.example.testnav.databinding.FragmentUpdateBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateFragment extends Fragment {


    private FragmentUpdateBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                new ViewModelProvider(this).get(UpdateViewModel.class);

        binding = FragmentUpdateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        binding.floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(binding.floatingActionButton3.getContext(), "Logout Success",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));

            }
        });
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performUpdate();
            }
        });

        return root;
    }

    private void performUpdate(){
        String caption = binding.etCaption.getText().toString();
        String linkIcon = binding.etIcon.getText().toString();
        Call<ApiResponse> call = ApiClient.getApiClient().create(ApiInterface.class).performUpdateCaption(caption,linkIcon);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.code() == 200){
                    if(response.body().getStatus().equals("ok")){
                        if(response.body().getResultCode() == 1){

                            Toast.makeText(binding.btnUpdate.getContext(), "Update Success",Toast.LENGTH_SHORT).show();
                            binding.etCaption.setText("");
                            binding.etIcon.setText("");
                        }else {
                            Toast.makeText(binding.btnUpdate.getContext(), "Caption already exists...",Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(binding.btnUpdate.getContext(), "abc",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(binding.btnUpdate.getContext(), "Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
