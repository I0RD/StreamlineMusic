package com.app.StreamlineMusic.repository;

import com.app.StreamlineMusic.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
