package com.example.testnav.ui.update;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;


import com.example.testnav.MainActivity2;
import com.example.testnav.databinding.FragmentLoginBinding;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {


    private FragmentLoginBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                new ViewModelProvider(this).get(UpdateViewModel.class);

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });

        return root;
    }
    private void performLogin(){
        String username = binding.etUsername.getText().toString();
        String password = binding.etPassword.getText().toString();
        Call<ApiResponse> call = ApiClient.getApiClient().create(ApiInterface.class).performUserLogIn(username,password);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.code()==200){

                    if(response.body().getStatus().equals("ok")){
                        if(response.body().getResultCode() == 1){

                            Toast.makeText(binding.btnLogin.getContext(), "Login Success",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity2.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(binding.btnLogin.getContext(), "Wrong Username or Password",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(binding.btnLogin.getContext(), "Wrong Username or Password",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(binding.btnLogin.getContext(), "Wrong Username or Password",Toast.LENGTH_SHORT).show();
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