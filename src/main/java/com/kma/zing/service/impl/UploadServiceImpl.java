package com.kma.zing.service.impl;

import com.kma.zing.entity.TblUserInforEntity;
import com.kma.zing.model.responsebody.UploadResponseBody;
import com.kma.zing.repository.UserInfoRepository;
import com.kma.zing.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
public class UploadServiceImpl implements UploadService {
    private final UserInfoRepository userInfoRepository;
    @Autowired
    public UploadServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UploadResponseBody uploadUserImg(MultipartFile file) throws IOException {
        UploadResponseBody responseBody = new UploadResponseBody();
        String fileName = getUniqueUploadFileName(Objects.requireNonNull(file.getOriginalFilename()));
        // tạo đường dẫn tới folder chứa avatar
        String pathToAvatar = "D:/f8/ZingMP3/beginer/src/assets/" + fileName;
        File fileDirectory = new File(pathToAvatar);
        if (!fileDirectory.exists()) {
            fileDirectory.mkdirs();
        }
        // lưu avatar vào đường dẫn trên
        file.transferTo(new File(pathToAvatar));
        responseBody.setUserImg("../../assets/"+fileName);

        return responseBody;
    }

    private String getUniqueUploadFileName(String fileName) {
        String[] splitFileName = fileName.split("\\.");
        return splitFileName[0] + System.currentTimeMillis() + "." + splitFileName[1];
    }
}
