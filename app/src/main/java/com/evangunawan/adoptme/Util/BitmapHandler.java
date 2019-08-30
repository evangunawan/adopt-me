package com.evangunawan.adoptme.Util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;


//A class to handle bitmaps. Download bitmaps and return Bitmap object.
public class BitmapHandler extends Thread {

    ArrayList<String> urlList;
    private ArrayList<Bitmap> loadedBitmap;

    public BitmapHandler(ArrayList<String> list){
        this.urlList = list;
        this.loadedBitmap = new ArrayList<>();
    }

    public ArrayList<Bitmap> getProcessedBitmap(){
        this.run();
        return loadedBitmap;
    }

    @Override
    public void run() {
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                loadedBitmap.add(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };

        for (String url : urlList) {
            Picasso.get().load(url).into(target);
        }
    }
}
