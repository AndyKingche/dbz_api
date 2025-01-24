package com.api.dragonball.app.firebase.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class FirebaseStorageService {
    /**
     *
     * @param file The file to upload to firebase
     * @param fileName The name of file
     * @return the URL to download of URl of file in Firebase
     * @throws Exception
     */
    private String uploadFile(File file, String fileName) throws Exception {
        BlobId blobId = BlobId.of("importadorakaleth.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();

        InputStream inputStream = FirebaseStorageService.class.getClassLoader().getResourceAsStream("izenshy-projects-firebase.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(credentials)
                .build()
                .getService();

        storage.create(blobInfo, (Storage.BlobTargetOption) file.toPath());

        String downloadUrl = "https://firebasestorage.googleapis.com/v0/b/importadorakaleth.appspot.com/o/%s?alt=media";
        return String.format(downloadUrl, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    /**
     * Converts a MultipartFile to a physical file in the system.
     *
     * @param multipartFile The file received as MultipartFile.
     * @param fileName The name to assign to the converted file.
     * @return The converted file.
     * @throws Exception If an error occurs during file conversion.
     */
    private File convertToFile(MultipartFile multipartFile, String fileName) throws Exception {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }

    /**
     * Gets the extension of a file based on its name.
     *
     * @param fileName The name of the file.
     * @return The file extension (e.g., .jpg, .png).
     */
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * Uploads a file to Firebase Storage and returns the download URL.
     *
     * @param multipartFile The file to upload.
     * @return The public download URL of the uploaded file.
     */
    public String upload(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            if (fileName == null) {
                return "Image couldn't upload, Something went wrong";
            }

            fileName = UUID.randomUUID() + getExtension(fileName);
            File file = convertToFile(multipartFile, fileName);
            String url = uploadFile(file, fileName);
            file.delete();
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return "Image couldn't upload, Something went wrong";
        }
    }

    /**
     * Deletes a file from Firebase Storage.
     *
     * @param fileName The name of the file to delete.
     * @return A message indicating the result of the deletion operation.
     */
    public String deleteFile(String fileName) {
        try {
            BlobId blobId = BlobId.of("importadorakaleth.appspot.com", "api-dragonball/" + fileName);
            InputStream inputStream = FirebaseStorageService.class.getClassLoader().getResourceAsStream("izenshy-projects-firebase.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);
            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(credentials)
                    .build()
                    .getService();

            storage.delete(blobId);
            return "File deleted successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while deleting the file.";
        }
    }
}
