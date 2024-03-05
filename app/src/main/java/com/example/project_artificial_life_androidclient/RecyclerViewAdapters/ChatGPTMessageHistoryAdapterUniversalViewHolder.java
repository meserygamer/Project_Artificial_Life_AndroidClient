package com.example.project_artificial_life_androidclient.RecyclerViewAdapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public class ChatGPTMessageHistoryAdapterUniversalViewHolder extends RecyclerView.ViewHolder {
    public ChatGPTMessageHistoryAdapterUniversalViewHolder(@NonNull ViewBinding itemViewBinding) {
        super(itemViewBinding.getRoot());
        binding = itemViewBinding;
    }


    public ViewBinding getBinding()
    {
        return binding;
    }


    @NonNull
    protected ViewBinding binding;
}
