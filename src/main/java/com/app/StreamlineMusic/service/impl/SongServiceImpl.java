package com.app.StreamlineMusic.service.impl;

import com.app.StreamlineMusic.entity.Song;
import com.app.StreamlineMusic.exception.ResourceNotFoundException;
import com.app.StreamlineMusic.payload.SongDto;
import com.app.StreamlineMusic.payload.SongResponse;
import com.app.StreamlineMusic.repository.SongRepository;
import com.app.StreamlineMusic.service.SongService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class SongServiceImpl implements SongService {

    private SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public SongDto createSong(SongDto songDto) {
        Song song =mapToSong(songDto);
        songRepository.save(song);
        SongDto songResponded =mapToDto(song);
        return songResponded;
    }

    @Override
    public void deleteSong(long id) {
        Song song = songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song","id",id));
        songRepository.delete(song);
    }

    @Override
    public SongDto updateSong(SongDto songDto, long id){
        Song song = songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song","id",id));
        song.setName(songDto.getName());
        song.setAuthor(songDto.getAuthor());
        song.setGenre(songDto.getGenre());
        try {
            song.setAudioFile(songDto.getAudioFile().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        songRepository.save(song);
        SongDto songResponded =mapToDto(song);
        Sound sound = new Sound(songDto.getAudioFile().getOriginalFilename());
        sound.play();
        return songResponded;
    }

    @Override
    public SongResponse showSongsByTitle(String title,int pageNo, int pageSize, String sortBy, String sortDir) {
        int count = 0;
        Sort sort =sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Song> songs = songRepository.findAll(pageable);
        List<Song> listOfSongs = new ArrayList<>(songs.getContent());

        while(count<listOfSongs.size()){
            if(!listOfSongs.get(count).getName().equalsIgnoreCase(title)){
                listOfSongs.remove(count);
            }
            else{
                count++;
            }
        }
        System.out.println(listOfSongs);
        List<SongDto> a=new ArrayList<>();
        for (Song listOfSong : listOfSongs) {
            a.add(mapToDto(listOfSong));
        }
        System.out.println(a);
        List<SongDto> content = listOfSongs.stream().map(song -> mapToDto(song)).collect(Collectors.toList());
        SongResponse songResponse = new SongResponse();
        songResponse.setContent(content);
        songResponse.setPageNo(songs.getNumber());
        songResponse.setPageSize(songs.getSize());
        songResponse.setTotalElements(songs.getTotalElements());
        songResponse.setTotalPages(songs.getTotalPages());
        songResponse.setLast(songs.isLast());
        /*
        List<Song> test = songRepository.findAll(sort);
        while(count<test.size()){
            if(!test.get(count).getName().equalsIgnoreCase(title)){
                test.remove(count);
            }
            else{
                count++;
            }
        }
        Page<Song> pTest=new PageImpl<>(test,pageable, test.size());



        System.out.println(pTest.getNumber());
        SongResponse songResponse=new SongResponse();
        songResponse.setContent(test);
        songResponse.setPageNo(pTest.getNumber());
        songResponse.setPageSize(pTest.getSize());
        songResponse.setTotalElements(pTest.getTotalElements());
        songResponse.setTotalPages(pTest.getTotalPages());
        songResponse.setLast(pTest.isLast());
         */
        return songResponse;
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    private Song mapToSong(SongDto songDto){

        Song song = new Song();
        song.setAuthor(songDto.getAuthor());
        song.setGenre(songDto.getGenre());
        song.setName(songDto.getName());
        try {
            song.setAudioFile(songDto.getAudioFile().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return song;
    }
    private SongDto mapToDto(Song song){

        SongDto songDto = new SongDto();
        songDto.setId(song.getId());
        songDto.setAuthor(song.getAuthor());
        songDto.setGenre(song.getGenre());
        songDto.setName(song.getName());
    //    songDto.setAudioFile(song.getAudioFile());
        return songDto;
    }
}
class Sound {
    private Clip clip;
    public Sound(String fileName) {
        // specify the sound to play
        // (assuming the sound can be played by the audio system)
        // from a wave File
        try {
            File file = new File(fileName);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                // load the sound into memory (a Clip)
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
            else {
                throw new RuntimeException("Sound: file not found: " + fileName);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }

        // play, stop, loop the sound clip
    }
    public void play(){
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
