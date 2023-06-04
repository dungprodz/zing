package com.kma.zing.controller;

import com.kma.zing.model.responsebody.UploadResponseBody;
import com.kma.zing.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@CrossOrigin("*")
@RestController
@RequestMapping("/zing/v1/upload")
public class UploadController {
    private final UploadService uploadService;
    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }
    @PostMapping()
    public ResponseEntity<UploadResponseBody> uploadImg(@RequestParam MultipartFile file) throws IOException {
        UploadResponseBody responseBody = uploadService.uploadUserImg(file);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
