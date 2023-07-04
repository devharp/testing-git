package com.harp;

import java.io.File;
import java.io.IOException;

import com.imageuploader.ImageUploader;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

/**
 * Hello world!
 *
 */
public class App {
    private ImageUploader imageUploader;

    /**
     * @throws IOException
     * 
     */
    private App() throws IOException {
        imageUploader = new ImageUploader("duiloptvq", "586615437941542", "5HSuzKJGHh_v4SIOQZy_c9HU8-0");
        String savedPath = imageUploader.uploadImage(new File("assets\\images\\Olympic_flag.jpg"));
        System.out.println(savedPath);

    }

    public static void main(String[] args) {
        try {
            new App();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
