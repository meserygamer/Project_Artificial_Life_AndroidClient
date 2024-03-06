package com.example.project_artificial_life_androidclient.RecyclerViewAdapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public abstract class KandinskyMessageHistoryAdapterUniversalViewHolder extends RecyclerView.ViewHolder {

    public KandinskyMessageHistoryAdapterUniversalViewHolder(@NonNull ViewBinding binding){
        super(binding.getRoot());
    }

    public abstract void Bind(Object object);
}
