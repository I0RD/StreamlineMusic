package com.app.StreamlineMusic.service;

import com.app.StreamlineMusic.entity.Song;
import com.app.StreamlineMusic.payload.SongDto;
import com.app.StreamlineMusic.payload.SongResponse;

import java.util.List;


public interface SongService {
        SongDto createSong(SongDto songDto);
        void deleteSong(long id);
        SongDto updateSong(SongDto songDto, long id);
        SongResponse showSongsByTitle(String title, int pageNo, int pageSize, String sortBy, String sortDir);
        List<Song> getAllSongs();
}
