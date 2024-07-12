package com.example.entity;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Compressimg {

    public static File compressImage(MultipartFile file, int maxWidth, int maxHeight, float quality, String path) throws IOException {
        File compressedImage = new File(path);

        try {
            // 确保文件的父目录存在
            if (!compressedImage.getParentFile().exists()) {
                compressedImage.getParentFile().mkdirs();
            }

            // 将 MultipartFile 写入到指定文件
            file.transferTo(compressedImage);

            // 使用 Thumbnails 对象进行压缩
            Thumbnails.of(compressedImage)
                    .size(maxWidth, maxHeight)
                    .outputQuality(quality)
                    .toFile(compressedImage);

            return compressedImage;
        } catch (IOException e) {
            // 处理文件操作和压缩过程中可能出现的异常
            throw new IOException("Failed to compress image: " + e.getMessage());
        }
    }
}
