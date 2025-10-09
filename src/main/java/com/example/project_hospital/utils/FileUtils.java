package com.example.project_hospital.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class FileUtils {

    private static final String UPLOAD_DIR = "uploads/";

    public String saveFile(MultipartFile file, String prefix) {
        if (file == null || file.isEmpty()) return null;
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));

            String originalName = file.getOriginalFilename();
            String ext = "";
            if (originalName != null && originalName.contains(".")) {
                ext = originalName.substring(originalName.lastIndexOf("."));
            }

            String fileName = prefix + "_" +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ext;

            File dest = new File(UPLOAD_DIR + fileName);
            file.transferTo(dest);
            return "/uploads/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi lưu ảnh: " + e.getMessage());
        }
    }

    public void deleteFile(String path) {
        if (path == null) return;
        try {
            String realPath = path.replace("/uploads/", UPLOAD_DIR);
            Files.deleteIfExists(Paths.get(realPath));
        } catch (IOException e) {
            System.err.println("Không thể xóa ảnh: " + e.getMessage());
        }
    }
}
