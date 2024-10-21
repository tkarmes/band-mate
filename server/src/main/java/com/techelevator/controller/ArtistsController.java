package com.techelevator.controller;


import com.techelevator.dao.ArtistDao;
import com.techelevator.dao.GenreDao;
import com.techelevator.dao.TalentDao;
import com.techelevator.model.Artist;
import com.techelevator.model.Genre;
import com.techelevator.model.Talent;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/artists")
public class ArtistsController {


    private final ArtistDao artistDao;
    private final TalentDao talentDao;

    private final GenreDao genreDao;

    public ArtistsController(ArtistDao artistDao, TalentDao talentDao, GenreDao genreDao) {
        this.artistDao = artistDao;
        this.talentDao = talentDao;
        this.genreDao = genreDao;
    }



    @GetMapping
    public List<Artist> getAllArtists(){
        return artistDao.getAllArtists();

    }

    @GetMapping(path = "/{artistId}/talents")
    public List<Talent> getAllTalentsForArtist(@PathVariable long artistId){

        return talentDao.getAllTalentsForArtist(artistId);

    }

    @GetMapping(path = "/{artistId}/genres")
    public List<Genre> getAllGenresForArtist(@PathVariable long artistId){

        return genreDao.getAllGenresForArtist(artistId);

    }

    @PostMapping(path = "/{artistId}/talent/{talentId}")
    public int addTalentToArtist(Artist artist, Talent talent, @PathVariable long artistId, @PathVariable long talentId){
        return talentDao.addTalentToArtist(artist, talent);
    }

}
