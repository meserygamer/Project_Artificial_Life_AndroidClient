package com.example.project_artificial_life_androidclient.Services;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Time;

public class InternalStorageImageSaver implements ImageSaver{

    public InternalStorageImageSaver(ContentResolver contentResolver){
        this.contentResolver = contentResolver;
    }


    @Override
    public void SaveBitmapImage(Bitmap image) {

        java.time.LocalDateTime date = java.time.LocalDateTime.now();
        File imageSave = new File(Environment.getExternalStorageDirectory().toString(), Integer.toString(date.getYear()) + Integer.toString(date.getMonthValue()) + Integer.toString(date.getDayOfMonth()) + Integer.toString(date.getHour()) + Integer.toString(date.getMinute()) + Integer.toString(date.getSecond()));
        OutputStream fOut;
        try
        {
            fOut = new FileOutputStream(imageSave);
        }
        catch (Exception ex){
            return;
        }
        image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
            fOut.close();
            MediaStore.Images.Media.insertImage(contentResolver, imageSave.getAbsolutePath(), imageSave.getName(),  imageSave.getName());
        }
        catch (Exception ex){
            return;
        }
    }


    private ContentResolver contentResolver;
}
