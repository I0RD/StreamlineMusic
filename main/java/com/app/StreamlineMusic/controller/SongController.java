package com.app.StreamlineMusic.controller;

import com.app.StreamlineMusic.entity.Song;
import com.app.StreamlineMusic.payload.SongDto;
import com.app.StreamlineMusic.payload.SongResponse;
import com.app.StreamlineMusic.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private static SongService songService;

    public SongController(SongService songService){
        this.songService=songService;
    }

    @PostMapping
    public ResponseEntity<SongDto> createSong(@RequestBody SongDto postDto){
        return new ResponseEntity<SongDto>(songService.createSong(postDto),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable(name = "id")long idSong){
        songService.deleteSong(idSong);
        return ResponseEntity.ok("Successfully deleted song id: "+idSong);
    }
    @PostMapping("/{id}")
    public ResponseEntity<SongDto> updateSong(@ModelAttribute SongDto songDto,@PathVariable(name = "id")long idSong){
            return ResponseEntity.ok(songService.updateSong(songDto,idSong));
    }
    @GetMapping("/\"{title}\"")
    public ResponseEntity<SongResponse>showSongsByTitle(
            @PathVariable(name = "title") String title,
            @RequestParam(value = "pageNo", defaultValue = "0",required = false)int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "4",required = false)int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "author",required = false)String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc",required = false)String sortDir
    ){
        return ResponseEntity.ok(songService.showSongsByTitle(title,pageNo,pageSize,sortBy,sortDir));
    }
    public static List<Song> getAllSongs() {
        return songService.getAllSongs();
    }
}
