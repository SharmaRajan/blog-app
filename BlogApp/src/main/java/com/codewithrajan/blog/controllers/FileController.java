package com.codewithrajan.blog.controllers;


import com.codewithrajan.blog.payloads.FileResponse;
import com.codewithrajan.blog.service.IFileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private IFileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping(value = "/upload")
    public ResponseEntity<FileResponse> fileUpload(
            @RequestParam("image")MultipartFile image
            ){
        String fileName = null;

        try{
            fileName = this.fileService.uploadImage(path,image);

            // repository call
            // file name save

        }catch (IOException exc){
            exc.printStackTrace();
            return new ResponseEntity<FileResponse>(new FileResponse(null,"Image is not uploaded"), HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<FileResponse>(new FileResponse(fileName,"Image uploaded"),HttpStatus.ACCEPTED);
    }

    // method to serve files
    @GetMapping(value = "/profiles/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable String imageName, HttpServletResponse response
    ) throws IOException{
        InputStream resource = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

}
