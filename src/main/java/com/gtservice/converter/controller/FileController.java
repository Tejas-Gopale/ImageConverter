package com.gtservice.converter.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.gtservice.converter.service.FileConversionService;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},maxAge = 3600)
public class FileController {

	 private final FileConversionService fileConversionService;

	    public FileController(FileConversionService fileConversionService) {
	        this.fileConversionService = fileConversionService;
	    }

	    @PostMapping("/convert/image")
	    public ResponseEntity<byte[]> convertImage(@RequestParam("file") MultipartFile file,
	                                                @RequestParam("targetFormat") String targetFormat) {
	        byte[] convertedImage = fileConversionService.convertImage(file, targetFormat);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "attachment; filename=converted." + targetFormat);
	        headers.add("Content-Type", "image/" + targetFormat);
	        return new ResponseEntity<>(convertedImage, headers, HttpStatus.OK);
	    }

	
}
