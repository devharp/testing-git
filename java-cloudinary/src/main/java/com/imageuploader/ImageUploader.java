package com.imageuploader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class ImageUploader {
  
  private Cloudinary cloudinary;

  /**
   * Constructs an ImageUploader object with the specified Cloudinary credentials.
   * 
   * @param cloudName   The Cloudinary cloud name (type: String).
   * @param apiKey      The Cloudinary API key (type: String).
   * @param apiSecret   The Cloudinary API secret (type: String).
   */
  public ImageUploader(String cloudName, String apiKey, String apiSecret) {
    this.cloudinary = new Cloudinary(ObjectUtils.asMap(
        "cloud_name", cloudName,
        "api_key", apiKey,
        "api_secret", apiSecret));
  }

  /**
   * Uploads an image file to Cloudinary and returns the secure URL of the uploaded image.
   * 
   * @param imageFile  The image file to upload.
   * @return The secure URL of the uploaded image.
   * @throws IOException If an error occurs during the upload process.
   */
  public String uploadImage(File imageFile) throws IOException {
    Map<?, ?> uploadResult = cloudinary.uploader().upload(imageFile, ObjectUtils.emptyMap());
    return (String) uploadResult.get("secure_url");
  }

  /**
   * Uploads multiple image files to Cloudinary and returns the secure URLs of the uploaded images.
   *
   * @param imageFiles The list of image files to upload.
   * @return The list of secure URLs of the uploaded images.
   * @throws IOException If an error occurs during the upload process.
   */
  public List<String> uploadImages(List<File> imageFiles) throws IOException {
    List<String> paths = new ArrayList<String>();
    for (File imageFile: imageFiles) {
      paths.add(uploadImage(imageFile));
    }
    return paths;
  }
}
