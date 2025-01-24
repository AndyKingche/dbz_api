package com.api.dragonball.app.firebase.controller;

import com.api.dragonball.app.firebase.response.UploadResponseDTO;
import com.api.dragonball.app.firebase.service.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/dbzapp/character/image")
@CrossOrigin("*")
public class FirebaseController {
    private final FirebaseStorageService firebaseStorageService;

    @Autowired
    public FirebaseController(FirebaseStorageService firebaseStorageService) {
        this.firebaseStorageService = firebaseStorageService;
    }
    /**
     * Uploads an image to Firebase Storage.
     *
     * This method handles `POST` requests that send a file (image) to the server.
     * The file is uploaded to Firebase Storage, and the download URL of the uploaded image is returned.
     *
     * @param multipartFile The file (image) to be uploaded.
     * @return A response containing the URL of the uploaded image and the ope
    */
    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile multipartFile) {
        try {
            System.out.println("multipartFile: " + multipartFile);

            String uploadImage = firebaseStorageService.upload(multipartFile);

            UploadResponseDTO message = new UploadResponseDTO();
            message.setLink(uploadImage);
            message.setStatus(true);

            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();

            UploadResponseDTO badMessage = new UploadResponseDTO();
            badMessage.setLink(null);
            badMessage.setStatus(false);

            return new ResponseEntity<>(badMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes an image from Firebase Storage.
     *
     * This method handles `DELETE` requests to remove a file (image) from Firebase Storage.
     * The file is identified by its name, which is passed as a parameter in the URL.
     *
     * @param name The name of the file to be deleted from Firebase Storage.
     * @return A message with the result of the delete operation.
     */
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteImage(@PathVariable("name") String name) {
        try {
            String result = firebaseStorageService.deleteFile(name + ".png");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while deleting the file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
