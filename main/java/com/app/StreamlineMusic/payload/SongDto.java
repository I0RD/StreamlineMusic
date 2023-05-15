package com.app.StreamlineMusic.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

@Data
public class SongDto {
    private long id;
    private String name;
    private String author;
    private String genre;
    private MultipartFile audioFile;
}
