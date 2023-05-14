package com.app.StreamlineMusic.service.impl;

import com.app.StreamlineMusic.entity.Playlist;
import com.app.StreamlineMusic.entity.Song;
import com.app.StreamlineMusic.exception.ResourceNotFoundException;
import com.app.StreamlineMusic.payload.PlaylistDto;
import com.app.StreamlineMusic.payload.SongDto;
import com.app.StreamlineMusic.repository.PlaylistRepository;
import com.app.StreamlineMusic.repository.SongRepository;
import com.app.StreamlineMusic.service.PlaylistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlaylistServiceImpl implements PlaylistService {
    PlaylistRepository playlistRepository;
    SongRepository songRepository;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository, SongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }
    @Override
    public PlaylistDto createPlaylist(PlaylistDto playlistDto) {
        Playlist playlist=mapToEntity(playlistDto);
        playlistRepository.save(playlist);
        PlaylistDto playlistResponded=mapToDto(playlist);
        return playlistResponded;
    }

    @Override
    public PlaylistDto updatePlaylist(long playlistId, PlaylistDto playlistDto) {
        Playlist playlist;
        playlist=playlistRepository.findById(playlistId).orElseThrow(()->new ResourceNotFoundException("Playlist ","id", playlistId));

        playlist.setDescription(playlistDto.getDescription());
        playlist.setName(playlistDto.getName());
        playlistRepository.save(playlist);

        PlaylistDto playlistResponded=mapToDto(playlist);
        return playlistResponded;
    }

    @Override
    public void deletePlaylist(long playlistId) {
        Playlist playlist;
        playlist=playlistRepository.findById(playlistId).orElseThrow(()->new ResourceNotFoundException("Playlist ","id", playlistId));
        playlistRepository.delete(playlist);
    }
    @Override
    public void addSongToPlaylist(long playlistId, long songId) {
        Playlist playlist;
        Song song;
        playlist=playlistRepository.findById(playlistId).orElseThrow(()->new ResourceNotFoundException("Playlist ","id", playlistId));
        song=songRepository.findById(songId).orElseThrow(()->new ResourceNotFoundException("Song ","id", songId));
        playlist.addSong(song);
    }

    @Override
    public List<SongDto> getAllSongsInPlaylist(long playlistId) {
        Playlist playlist=playlistRepository.findById(playlistId).orElseThrow(()->new ResourceNotFoundException("Playlist ","id", playlistId));
        List<SongDto>songsBelongPlaylist=playlist.getSongs().stream().map(song -> mapToDtoSong(song)).collect(Collectors.toList());
        return songsBelongPlaylist;
    }
    private PlaylistDto mapToDto(Playlist playlist){
        PlaylistDto playlistDto = new PlaylistDto();

        playlistDto.setId(playlist.getId());
        playlistDto.setName(playlist.getName());
        playlistDto.setDescription(playlist.getDescription());

        return playlistDto;
   }
    private Playlist mapToEntity(PlaylistDto playlistDto){
        Playlist playlist = new Playlist();

        playlist.setId(playlistDto.getId());
        playlist.setName(playlistDto.getName());
        playlist.setDescription(playlistDto.getDescription());

        return playlist;
    }
    private SongDto mapToDtoSong(Song song){
        SongDto songDto = new SongDto();

        songDto.setId(song.getId());
        songDto.setName(song.getName());
        songDto.setAuthor(song.getAuthor());
        songDto.setGenre(song.getGenre());

        return songDto;
    }
}