package com.example.project_artificial_life_androidclient.RecyclerViewAdapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_GeneratedImage;
import com.example.project_artificial_life_androidclient.APIes.Models.Kandinsky_SendRequestToGenerate_Params;
import com.example.project_artificial_life_androidclient.databinding.KandinskyAssistantMessageLayoutBinding;

import java.util.Base64;

public class KandinskyMessageHistoryAdapterViewHolder_Assistant extends KandinskyMessageHistoryAdapterUniversalViewHolder{

    public KandinskyMessageHistoryAdapterViewHolder_Assistant(KandinskyAssistantMessageLayoutBinding binding){
        super(binding);
        this.binding = binding;
    }

    @Override
    public void Bind(Object object) {
        if(object.getClass() != Kandinsky_GeneratedImage.class){
            throw new IllegalArgumentException("KandinskyMessageHistoryAdapterViewHolder_Assistant пришел не Kandinsky_GeneratedImage");
        }
        Kandinsky_GeneratedImage AssistantMessage = (Kandinsky_GeneratedImage)object;
        if(AssistantMessage.getImages() == null){
            throw new IllegalArgumentException("В список сообщений от Kandinsky попал ответ без картинки");
        }
        byte[] decodedImageString = Base64.getDecoder().decode(AssistantMessage.getImages().get(0));
        Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedImageString, 0, decodedImageString.length);
        binding.kandiskyAssistantMessageLayoutGeneratedImage.setImageBitmap(decodedImage);
    }


    private KandinskyAssistantMessageLayoutBinding binding;
}
