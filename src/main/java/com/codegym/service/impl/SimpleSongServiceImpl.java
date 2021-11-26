package com.codegym.service.impl;

import com.codegym.model.Song;
import com.codegym.service.ISongService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class SimpleSongServiceImpl implements ISongService {
    private static List<Song> songs;
    private static long autoIncreaseId = 0;

    static
    { songs = asList(
                new Song(autoIncreaseId++, "buoc qua nhau", "vu", "ballad","aadasd"),
                new Song(autoIncreaseId++, "chia tay roi", "mhn", "ballad","sdasdsa"),
                new Song(autoIncreaseId++, "phia sau em", "kayTran", "ballad","Adasd"),
                new Song(autoIncreaseId++, "em cua ngay hom qua", "Mtp", "ballad","adsad"),
                new Song(autoIncreaseId++, "Hon ca yeu", "ducphuc", "ballad","asdasd")
        );
    }

    @Override
    public List<Song> findAll() {
        return new ArrayList<>(songs);
    }

    @Override
    public Song findOne(Long id) {
        return songs.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Song save(Song song) {
        return song.getId() == null ? persist(song) : merge(song);
    }

    @Override
    public Song update(Song song) {
        return null;
    }

    @Override
    public List<Song> save(List<Song> songs) {
        return songs.stream()
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public boolean exists(Long id) {
        return songs.stream().anyMatch(s -> s.getId().equals(id));
    }

    @Override
    public List<Song> findAll(List<Long> ids) {
        return ids.stream()
                .map(this::findOne)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return songs.size();
    }

    @Override
    public void delete(Long id) {
        songs.removeIf(s -> s.getId().equals(id));
    }

    @Override
    public void delete(Song customer) {
        delete(customer.getId());
    }

    @Override
    public void delete(List<Song> songs) {
        songs.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        songs = new ArrayList<>();
    }

    private Song persist(Song song) {
        Song clone = song.clone();
        clone.setId(autoIncreaseId++);
        songs.add(clone);
        return clone;
    }

    private Song merge(Song song) {
        Song origin = findOne(song.getId());
        origin.setName(song.getName());
        origin.setAuthor(song.getAuthor());
        origin.setType(song.getType());
        origin.setPath(song.getPath());
        return origin;
    }
}
