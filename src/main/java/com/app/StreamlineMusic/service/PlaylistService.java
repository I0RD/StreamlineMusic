package com.app.StreamlineMusic.service;

import com.app.StreamlineMusic.payload.PlaylistDto;
import com.app.StreamlineMusic.payload.SongDto;

import java.util.List;


public interface PlaylistService {
    PlaylistDto createPlaylist(PlaylistDto playlistDto);
    PlaylistDto updatePlaylist(long playlistId, PlaylistDto playlistDto);
    void deletePlaylist(long playlistId);
    void addSongToPlaylist(long playlistId, long songId);
    List<SongDto> getAllSongsInPlaylist (long playlist_id);
}
