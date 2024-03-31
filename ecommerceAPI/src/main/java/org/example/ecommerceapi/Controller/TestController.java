package org.example.ecommerceapi.Controller;

import org.example.ecommerceapi.Entity.ImageFile;
import org.example.ecommerceapi.Entity.User;
import org.example.ecommerceapi.Service.ImageService;
import org.example.ecommerceapi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final ImageService imageService;

    private final UserService userService;

    @Autowired
    public TestController(ImageService imageService,UserService userService){
        this.imageService = imageService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String sampleTest(){
        return "API endpoint working";
    }

    @GetMapping("/image/details/{id}")
    public ImageFile getImageDetailsById(@PathVariable int id){
        return imageService.getImageFileById(id);
    }

    @GetMapping("/image/download/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable int id) throws IOException {
        byte[] imageData = imageService.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @PostMapping("/image/upload")
    public ImageFile uploadImage(@RequestParam("image")MultipartFile img) throws IOException{
        return imageService.uploadFile(img,"User_Picture");
    }

    @GetMapping("/users/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping("/users/new")
    public int addUser(@RequestParam("image")MultipartFile img,
                       @RequestParam("firstName") String firstName,
                       @RequestParam("lastName") String lastName,
                       @RequestParam("email") String email) throws IOException{
        ImageFile imageFile = imageService.uploadFile(img,"user_picture");

        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .image(imageFile)
                .build();

        return userService.saveUser(user);
    }

    //redo
    @PutMapping("/users/update/Image")
    public User updateImage(@RequestParam("image")MultipartFile img,@RequestBody User user) throws IOException{
        int del_id = user.getImage().getId();

        ImageFile imageFile = imageService.uploadFile(img,"user_picture");

        user.setImage(imageFile);

        userService.updateUser(user);

        imageService.deleteImage(del_id);

        return user;
    }

    @PutMapping("/users/update")
    public User updateUser(@RequestBody User user){
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return "Deletion successful";
    }


}
