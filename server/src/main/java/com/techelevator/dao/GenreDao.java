package com.techelevator.dao;

import com.techelevator.model.Artist;
import com.techelevator.model.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> getAllGenres();

    Genre getGenreById(int id);


    Genre createGenre(String genreName);

    Genre updateGenre(Genre newGenre);

    boolean deleteGenre(String name);

    List<Genre> getAllGenresForArtist(long artistId);

    int deleteGenreById(int id);

    int addGenreToArtist(Artist artist, Genre genre);

}
