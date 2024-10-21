package com.techelevator.dao;

import com.techelevator.model.Artist;

import java.util.List;

public interface ArtistDao {

    List<Artist> getAllArtists();

    Artist createArtist(Artist artist);

    Artist updateArtist(Artist artist);


    boolean deleteArtist(int id);

    Artist getArtistById(int id);
}

