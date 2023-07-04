package com.harp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.imageuploader.ImageUploader;

/**
 * Hello world!
 *
 */
public class App {
    private ImageUploader imageUploader;

    private final String imagePath = "assets\\images\\Olympic_flag.jpg";

    private final String[] imagePaths = {
        "assets\\images\\Olympic_flag.jpg",
        "assets\\images\\Olympic_flag.jpg",
        "assets\\images\\Olympic_flag.jpg",
        "assets\\images\\Olympic_flag.jpg",
        "assets\\images\\Olympic_flag.jpg",
        // Add more image paths as needed
    };

    /**
     * @throws IOException
     * 
     */
    private App() throws IOException {
        imageUploader = new ImageUploader("duiloptvq", "586615437941542", "5HSuzKJGHh_v4SIOQZy_c9HU8-0");
        
        // Example for a single image upload 
        // String savedPath = imageUploader.uploadImage(new File(imagePath));
        // System.out.println(savedPath);

        List<String> savedPaths = imageUploader.uploadImages(pathToFileArray(this.imagePaths));

        for (String path: savedPaths) {
            System.out.println(path);
        }

    }

     /**
     * Converts an array of image paths to a list of File objects.
     *
     * @param paths The array of image paths.
     * @return The list of File objects representing the image files.
     */
    private List<File> pathToFileArray(String[] paths) {
        List<File> imageFiles = new ArrayList<File>();

        for (String path: paths){
            File imageFile = new File(path);
            imageFiles.add(imageFile);
        }

        return imageFiles;
    }

    public static void main(String[] args) {
        try {
            new App();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
