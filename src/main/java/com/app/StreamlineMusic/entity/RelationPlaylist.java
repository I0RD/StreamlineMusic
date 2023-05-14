package com.app.StreamlineMusic.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "relation")
public class RelationPlaylist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long playlist_Id;
    private Long song_Id;

}
