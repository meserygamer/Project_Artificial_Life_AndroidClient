package com.example.project_artificial_life_androidclient.RecyclerViewAdapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_GeneratedImage;
import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_SendRequestToGenerate_Params;
import com.example.project_artificial_life_androidclient.databinding.ChatgptUserMessageLayoutBinding;
import com.example.project_artificial_life_androidclient.databinding.KandinskyAssistantMessageLayoutBinding;

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
        switch (viewType){
            case USER_MESSAGE :
                return new KandinskyMessageHistoryAdapterViewHolder_User(
                        ChatgptUserMessageLayoutBinding.inflate(LayoutInflater.from(parent.getContext()))
                );
            case ASSISTANT_MESSAGE:
                return new KandinskyMessageHistoryAdapterViewHolder_Assistant(
                        KandinskyAssistantMessageLayoutBinding.inflate(LayoutInflater.from(parent.getContext()))
                );
            default: throw new IllegalArgumentException("Неизвестный тип view, при формировании истории сообщений с Kandinsky");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull KandinskyMessageHistoryAdapterUniversalViewHolder holder, int position) {
        holder.Bind(kandisnkyChatHistory.get(position));
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
