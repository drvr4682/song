package com.example.song.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.song.model.Song;
import com.example.song.repository.SongRepository;

// Don't modify the below code
public class SongService implements SongRepository {
    private static HashMap<Integer, Song> playlist = new HashMap<>();
    int uniqueSongId = 6;

    public SongService() {
        playlist.put(1, new Song(1, "Shape of You", "Ed Sheeran", "Ed Sheeran", "Steve Mac"));
playlist.put(2, new Song(2, "Blinding Lights", "Abel Tesfaye", "The Weeknd", "Max Martin"));
playlist.put(3, new Song(3, "Levitating", "Dua Lipa", "Dua Lipa", "Stuart Price"));
playlist.put(4, new Song(4, "Peaches", "Justin Bieber", "Justin Bieber, Daniel Caesar, Giveon", "Harv"));
playlist.put(5, new Song(5, "Save Your Tears", "Abel Tesfaye", "The Weeknd", "Max Martin"));
playlist.put(6, new Song(6, "Bad Habits", "Ed Sheeran", "Ed Sheeran", "Fred Gibson"));
playlist.put(7, new Song(7, "Stay", "Justin Bieber, The Kid LAROI", "The Kid LAROI, Justin Bieber", "Cashmere Cat"));
playlist.put(8, new Song(8, "Drivers License", "Olivia Rodrigo", "Olivia Rodrigo", "Dan Nigro"));
playlist.put(9, new Song(9, "Peanut Butter Jelly", "Galantis", "Galantis", "Galantis"));
playlist.put(10, new Song(10, "Easy On Me", "Adele", "Adele", "Adele, Greg Kurstin"));
playlist.put(11, new Song(11, "Shivers", "Ed Sheeran", "Ed Sheeran", "Steve Mac"));
playlist.put(12, new Song(12, "Industry Baby", "Lil Nas X, Jack Harlow", "Lil Nas X, Jack Harlow", "Take a Daytrip"));
playlist.put(13, new Song(13, "Happier Than Ever", "Billie Eilish", "Billie Eilish", "Finneas O'Connell"));
playlist.put(14, new Song(14, "Cold Heart", "Elton John, Dua Lipa", "Elton John, Dua Lipa", "PNAU"));
playlist.put(15, new Song(15, "Montero", "Lil Nas X", "Lil Nas X", "Take a Daytrip"));
playlist.put(16, new Song(16, "Good 4 U", "Olivia Rodrigo", "Olivia Rodrigo", "Dan Nigro"));
playlist.put(17, new Song(17, "Watermelon Sugar", "Harry Styles", "Harry Styles", "Tyler Johnson, Kid Harpoon"));
playlist.put(18, new Song(18, "As It Was", "Harry Styles", "Harry Styles", "Kid Harpoon, Tyler Johnson"));
playlist.put(19, new Song(19, "Circles", "Post Malone", "Post Malone", "Post Malone, Louis Bell"));
playlist.put(20, new Song(20, "Senorita", "Shawn Mendes, Camila Cabello", "Shawn Mendes, Camila Cabello", "Andrew Watt"));
    
    }

    @Override
    public ArrayList<Song> getSongs() {
        Collection<Song> songCollection = playlist.values();
        ArrayList<Song> songs = new ArrayList<>(songCollection);
        return songs;
    } 

    @Override
    public Song getSongById(int songId) {
        Song song = playlist.get(songId);
        if(song == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return song;
    }

    @Override
    public Song addSong(Song song) {
        song.setSongId(uniqueSongId);
        playlist.put(uniqueSongId, song);
        uniqueSongId += 1;
        return song;
    }

    @Override
    public Song updateSong(int songId, Song song) {
        Song existingSong = playlist.get(songId);
        if(existingSong == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (song.getSongName() != null) {
            existingSong.setSongName(song.getSongName());
        }

        if (song.getLyricist() != null) {
            existingSong.setLyricist(song.getLyricist());
        }

        if (song.getSinger() != null) {
            existingSong.setSinger(song.getSinger());
        }

        if (song.getMusicDirector() != null) {
            existingSong.setMusicDirector(song.getMusicDirector());
        }
        return existingSong;
    }

    @Override
    public void deleteSong(int songId) {
        Song song = playlist.get(songId);
        if (song == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            playlist.remove(songId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}