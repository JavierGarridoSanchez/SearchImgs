package com.example.searchimgs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

    Context context;
    List<ModeloImg> modeloImgs;

    public ImageAdapter(Context context, List<ModeloImg> modeloImgs) {
        this.context = context;
        this.modeloImgs = modeloImgs;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list,parent,false);
        return new ImageHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ImageAdapter.ImageHolder holder, int position) {

        ModeloImg modeloImg = modeloImgs.get(position);

        Glide.with(context)
                .load(modeloImg.getCdn())
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return modeloImgs.size();
    }

    public class  ImageHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImageHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_list);
        }
    }
}

