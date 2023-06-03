package com.kma.zing.service;

import com.kma.zing.model.responsebody.UploadResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    UploadResponseBody uploadUserImg(MultipartFile file) throws IOException;
}
