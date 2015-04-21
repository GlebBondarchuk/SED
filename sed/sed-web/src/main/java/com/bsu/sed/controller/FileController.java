package com.bsu.sed.controller;


import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.service.SystemAttributeService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author gbondarchuk
 */
@Controller
@RequestMapping("/get")
public class FileController {

    private static final int MAX_IMAGE_WIDTH = 750;
    private static final int MAX_IMAGE_HEIGHT = 400;

    @Value("#{serviceProperties.documentFolder}")
    private String documentFolder;

    @Autowired
    private SystemAttributeService systemAttributeService;

    @PreAuthorize("hasPermission(#fileName, '')")
    @RequestMapping("/{year}/{month}/{day}/{fileName:.+}")
    @ResponseBody
    public byte[] getFile(@PathVariable("year") String year,
                          @PathVariable("month") String month,
                          @PathVariable("day") String day,
                          @PathVariable("fileName") String fileName) throws IOException {
        String realPath = documentFolder;
        realPath = realPath + "/" + buildFilePath(year, month, day, fileName);

        Path path = Paths.get(realPath);
        return Files.readAllBytes(path);
    }

    private String buildFilePath(String year, String month, String day, String fileName) {
        return year + "/" + month + "/" + day + "/" + fileName;
    }

    @Cacheable(value = "imageCache")
    @RequestMapping("/crop/{year}/{month}/{day}/{fileName:.+}")
    @ResponseBody
    public byte[] getCropImage(@PathVariable("year") String year,
                               @PathVariable("month") String month,
                               @PathVariable("day") String day,
                               @PathVariable("fileName") String fileName) {
        try {
            String realPath = documentFolder;
            realPath = realPath + "/" + buildFilePath(year, month, day, fileName);
            Path path = Paths.get(realPath);
            File file = path.toFile();
            BufferedImage image = ImageIO.read(file);
            BufferedImage croppedImage;
            if (image.getWidth() >= MAX_IMAGE_HEIGHT && image.getHeight() >= MAX_IMAGE_HEIGHT) {
                croppedImage = image.getSubimage(0, 0, MAX_IMAGE_WIDTH, MAX_IMAGE_HEIGHT);
            } else {
                croppedImage = image;
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(croppedImage, FilenameUtils.getExtension(file.getName()), byteArrayOutputStream);
            byteArrayOutputStream.flush();

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
