package com.app.StreamlineMusic.controller;

import com.app.StreamlineMusic.payload.PlaylistDto;
import com.app.StreamlineMusic.payload.SongDto;
import com.app.StreamlineMusic.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    private PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {this.playlistService = playlistService;}

    @PostMapping
    public ResponseEntity<PlaylistDto> createPlaylist (@RequestBody PlaylistDto playlistDto){
        return new ResponseEntity<PlaylistDto>(playlistService.createPlaylist(playlistDto), HttpStatus.CREATED);
    }
    @PutMapping("/{playlist_id}")
    public ResponseEntity<PlaylistDto> updatePlaylist (@PathVariable(name = "playlist_id") long playlist_id,@RequestBody PlaylistDto playlistDto){
        return ResponseEntity.ok(playlistService.updatePlaylist(playlist_id,playlistDto));
    }
    @DeleteMapping("/{playlist_id}")
    public ResponseEntity<String> deletePlaylist(@PathVariable(name = "playlist_id") long playlist_id){
        playlistService.deletePlaylist(playlist_id);
        return new ResponseEntity<String>("Playlist successful deleted",HttpStatus.OK);
    }
    @PostMapping("/{playlist_id}/song/{song_id}")
    public ResponseEntity<String> addSongToPlaylist(@PathVariable(name = "playlist_id") long playlist_id,@PathVariable(name = "song_id") long song_id){
        playlistService.addSongToPlaylist(playlist_id,song_id);
        return ResponseEntity.ok("Song added to playlist");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{playlist_id}")
    public ResponseEntity<List<SongDto>>getAllSongsInPlaylist(@PathVariable(name = "playlist_id")long playlistId){
        return ResponseEntity.ok(playlistService.getAllSongsInPlaylist(playlistId));
    }
}
