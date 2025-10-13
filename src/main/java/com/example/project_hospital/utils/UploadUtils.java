package com.example.project_hospital.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class UploadUtils {

    private static final String UPLOAD_DIR = "uploads/phacdo/";

    // Lưu MultipartFile, trả về fileUrl
    public static String saveFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Path.of(UPLOAD_DIR + fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());
        return "/" + UPLOAD_DIR + fileName;
    }

    // Lưu file từ base64, trả về fileUrl
    public static String saveBase64(String base64) throws IOException {
        if (base64 == null || base64.isEmpty()) return null;
        byte[] decoded = Base64.getDecoder().decode(base64);
        String fileName = System.currentTimeMillis() + ".png";
        Path path = Path.of(UPLOAD_DIR + fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, decoded);
        return "/" + UPLOAD_DIR + fileName;
    }
}
