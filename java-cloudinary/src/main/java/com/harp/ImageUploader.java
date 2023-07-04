package com.harp;

import java.io.File;
import java.io.IOException;
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
   * @param file  The image file to upload.
   * @return The secure URL of the uploaded image.
   * @throws IOException If an error occurs during the upload process.
   */
  public String uploadImage(File file) throws IOException {
    Map<?, ?> uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
    return (String) uploadResult.get("secure_url");
  }
}