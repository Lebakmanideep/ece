package org.example.ecommerceapi.Service;

import org.example.ecommerceapi.DAO.ImageRepository;
import org.example.ecommerceapi.Entity.ImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    //path separator is independent of the operating system
    private final String BASE_PATH = "C:" + File.separator+"Users" + File.separator + "karth" + File.separator + "Desktop" + File.separator + "FileSystemStorage" + File.separator;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }
    @Override
    public ImageFile getImageFileById(int id) {
        Optional<ImageFile> temp = imageRepository.findById(id);

        ImageFile img = null;

        if(temp.isPresent()){
            img = temp.get();
        }

        return img;
    }
    @Override
    public ImageFile uploadFile(MultipartFile img,String type) throws IOException {
//        need to handle case where the file names are similar
        String originalImageName = img.getOriginalFilename();
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String filePath = BASE_PATH+timeStamp+"_"+originalImageName;

        ImageFile image = imageRepository.save(ImageFile.builder()
                .path(filePath)
                .type(type)
                .build());

        System.out.println(image);

        img.transferTo(new File(filePath));
        return image;
    }
    @Override
    public byte[] downloadImage(int id)  throws IOException{
        Optional<ImageFile> image = imageRepository.findById(id);

        String imagePath = image.get().getPath();
        return Files.readAllBytes(new File(imagePath).toPath());
    }
    @Override
    public String deleteImage(int id) {
        imageRepository.deleteById(id);
        return "Deletion successful";
    }
}
