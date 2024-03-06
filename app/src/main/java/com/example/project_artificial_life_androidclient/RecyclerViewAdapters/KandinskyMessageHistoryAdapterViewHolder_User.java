package com.example.project_artificial_life_androidclient.RecyclerViewAdapters;

import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_SendRequestToGenerate_Params;
import com.example.project_artificial_life_androidclient.databinding.ChatgptUserMessageLayoutBinding;
import com.example.project_artificial_life_androidclient.databinding.KandinskyAssistantMessageLayoutBinding;

public class KandinskyMessageHistoryAdapterViewHolder_User extends KandinskyMessageHistoryAdapterUniversalViewHolder {

    public KandinskyMessageHistoryAdapterViewHolder_User(ChatgptUserMessageLayoutBinding binding){
        super(binding);
        this.binding = binding;
    }


    @Override
    public void Bind(Object object) {
        if(object.getClass() != Kandinsky_SendRequestToGenerate_Params.class){
            throw new IllegalArgumentException("KandinskyMessageHistoryAdapterViewHolder_User пришел не Kandinsky_SendRequestToGenerate_Params");
        }
        Kandinsky_SendRequestToGenerate_Params userMessage = (Kandinsky_SendRequestToGenerate_Params)object;
        binding.chatGPTUserMessageLayoutMessageText.setText(userMessage.getGenerateParams().getQuery());
    }


    private ChatgptUserMessageLayoutBinding binding;
}
