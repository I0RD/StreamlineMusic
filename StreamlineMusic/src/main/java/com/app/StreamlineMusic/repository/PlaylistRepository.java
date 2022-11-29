package com.app.StreamlineMusic.repository;

import com.app.StreamlineMusic.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
