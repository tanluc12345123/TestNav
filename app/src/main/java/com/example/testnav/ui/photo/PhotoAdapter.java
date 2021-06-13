package com.example.testnav.ui.photo;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.testnav.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;


import java.util.List;
import java.util.UUID;

import dmax.dialog.SpotsDialog;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private List<Photo>list_photo;
    private Context context;

    public PhotoAdapter(List<Photo> list_photo, Context context) {
        this.list_photo = list_photo;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Photo listData=list_photo.get(position);

        Picasso.with(context)
                .load(listData
                        .getLink_Meme())
                .into(holder.img);
        holder.url = listData.getLink_Meme();



    }


    @Override
    public int getItemCount() {
        return list_photo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private static final int PERMISSION_REQUEST_CODE = 1000;
        private ImageView img;
        private FloatingActionButton floatingActionButton;
        private String url;

        public ViewHolder(View itemView) {
            super(itemView);


            img=(ImageView)itemView.findViewById(R.id.iv_photos);
            floatingActionButton = (FloatingActionButton) itemView.findViewById(R.id.Button_Copy);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions((Activity)context, new String[]{
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                                },
                                PERMISSION_REQUEST_CODE);
                    }
                    if(ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(floatingActionButton.getContext(),"You Should grant permission",Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions((Activity)context, new String[]{}, PERMISSION_REQUEST_CODE);
                    }else {
                        AlertDialog dialog = new SpotsDialog(context);
                        dialog.show();
                        dialog.setMessage("Downloading...");

                        String fileName = UUID.randomUUID().toString();
                        try{
                            Picasso.with(context).load(url)
                                    .into(new SavePhoto(context,
                                            dialog,
                                            context.getApplicationContext().getContentResolver(),fileName,"Image description"));
                            Toast.makeText(floatingActionButton.getContext(),"Download Success",Toast.LENGTH_SHORT).show();
                        }catch(Exception e){

                        }

                    }
                }
            });
        }
    }
    public void getAllPhotos(List<Photo> photoList){
        this.list_photo = photoList;
    }

}