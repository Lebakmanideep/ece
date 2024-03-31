package org.example.ecommerceapi.Service;

import org.example.ecommerceapi.Entity.ImageFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    ImageFile getImageFileById(int id);

    ImageFile uploadFile(MultipartFile img, String type) throws IOException;

    byte[] downloadImage(int id)  throws IOException;

    String deleteImage(int id);


}
