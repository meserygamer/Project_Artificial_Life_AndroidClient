package com.example.project_artificial_life_androidclient.RecyclerViewAdapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_GeneratedImage;
import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_SendRequestToGenerate_Params;

import java.util.List;

public class KandinskyMessageHistoryAdapter extends RecyclerView.Adapter<KandinskyMessageHistoryAdapterUniversalViewHolder> {

    //public Constructors
    public KandinskyMessageHistoryAdapter(List<Object> kandisnkyChatHistory){
        this.kandisnkyChatHistory = kandisnkyChatHistory;
    }

    //public Const
    public static final int USER_MESSAGE = 0;
    public static final int ASSISTANT_MESSAGE = 1;

    //public Methods
    @NonNull
    @Override
    public KandinskyMessageHistoryAdapterUniversalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull KandinskyMessageHistoryAdapterUniversalViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        if(kandisnkyChatHistory.get(position).getClass() == Kandinsky_SendRequestToGenerate_Params.class){
            return USER_MESSAGE;
        }
        if(kandisnkyChatHistory.get(position).getClass() == Kandinsky_GeneratedImage.class){
            return ASSISTANT_MESSAGE;
        }
        throw new IllegalArgumentException("Постороннее сообщение в истории сообщений с Kandinsky!");
    }

    @Override
    public int getItemCount() {
        return kandisnkyChatHistory.size();
    }

    //private Fields
    private List<Object> kandisnkyChatHistory;
}
