package com.app.StreamlineMusic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "playlists", uniqueConstraints = {@UniqueConstraint(columnNames={"name"})})
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;


    @ManyToMany
    @JoinTable(
            name = "relation",
            joinColumns = @JoinColumn(name = "playlist_Id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "song_Id", referencedColumnName = "id")
    )
    private List<Song> songs=new ArrayList<>();

    public void addSong(Song song) {
        this.songs.add(song);
    }
}
