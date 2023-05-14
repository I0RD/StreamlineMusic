package com.app.StreamlineMusic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.BlobType;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="SongName")
    private String name;
    @Column(name ="Author")
    private String author;
    @Column(name ="SongGenre")
    private String genre;
    @Column(name = "AudioFile")
    @Lob
    private byte[] audioFile;
}