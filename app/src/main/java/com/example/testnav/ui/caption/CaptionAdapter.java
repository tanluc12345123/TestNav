package com.example.testnav.ui.caption;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.testnav.R;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;


import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;

public class CaptionAdapter extends RecyclerView.Adapter<CaptionAdapter.ViewHolder> {
    private List<Caption> list_caption;
    private Context context;

    public CaptionAdapter(List<Caption> list_caption, Context context) {
        this.list_caption = list_caption;
        this.context = context;
    }

    @Override
    public CaptionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_caption,parent,false);
        return new CaptionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CaptionAdapter.ViewHolder holder, int position) {
        Caption listDataCaption=list_caption.get(position);

        Picasso.with(context)
                .load(listDataCaption
                        .getLink_Icon())
                .into(holder.img);
        holder.tvCaption.setText(listDataCaption.getCaption());


    }

    @Override
    public int getItemCount() {
        return list_caption.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tvCaption;
        private FloatingActionButton btn_copy;
        ClipData clipData;
        ClipboardManager clipboardManager;

        public ViewHolder(View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.iv_icon);
            tvCaption = (TextView) itemView.findViewById(R.id.tv_caption);
            btn_copy = (FloatingActionButton)itemView.findViewById(R.id.floatingActionButton2);

            clipboardManager =(ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);

            btn_copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = tvCaption.getText().toString();
                    clipData = ClipData.newPlainText("text",text);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(context.getApplicationContext(), "Text copied", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void getAllCaptions(List<Caption> captionList){
        this.list_caption = captionList;
    }

}
