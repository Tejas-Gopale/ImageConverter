package com.gtservice.converter.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
@Service
public class FileConversionService {

	public byte[] convertImage(MultipartFile file, String targetFormat) {
        try {
            BufferedImage originalImage = ImageIO.read(file.getInputStream());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, targetFormat, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error converting image: " + e.getMessage());
        }
    }

}
