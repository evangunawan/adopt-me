package com.evangunawan.adoptme.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

//A class to handle bitmaps. Download bitmaps and return Bitmap object.
public class BitmapHandler extends Thread {

    private BitmapLoaderListener listener;
    private ArrayList<String> urlList;
    private ArrayList<Bitmap> loadedBitmap;

    public BitmapHandler(ArrayList<String> urlList, BitmapLoaderListener listener) {

        //BitmapLoaderListener is an interface to implement Callback when the Bitmap is successfully downloaded.
        this.urlList = urlList;
        this.listener = listener;
        this.loadedBitmap = new ArrayList<>();
    }

    public void loadBitmap() {
        this.start(); //Start this class run(), which is get Bitmap object from the URL list passed.

        //Creating a new thread to check whether the images is successfully downloaded and execute Callback method to the caller.
        new Thread(() -> {
            while (true) {
                Log.wtf("Carousel", "Loading Bitmaps... Size: " + loadedBitmap.size() + "/" + urlList.size()); //Debugging.
                if (loadedBitmap.size() >= urlList.size()) {
                    Log.i("Carousel", "Image Loaded successfully");
                    listener.onBitmapLoaded(loadedBitmap);
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void run() {
        for (String url : urlList) {
            Log.i("BitmapFactory", "Loading from url: " + url);
            try {
                URL targetUrl = new URL(url);
                Bitmap image = BitmapFactory.decodeStream(targetUrl.openConnection().getInputStream());
                loadedBitmap.add(image);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

}
