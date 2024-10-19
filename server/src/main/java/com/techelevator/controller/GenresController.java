package com.techelevator.controller;


import com.techelevator.dao.ArtistDao;
import com.techelevator.dao.GenreDao;
import com.techelevator.model.Genre;
import com.techelevator.model.Talent;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/genres")
public class GenresController {

    private final GenreDao genreDao;

    private final ArtistDao artistDao;


    public GenresController(GenreDao genreDao, ArtistDao artistDao) {
        this.genreDao = genreDao;
        this.artistDao = artistDao;
    }

    @GetMapping
    public List<Genre> getAllGenres(){
        return genreDao.getAllGenres();

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/create")
    public Genre createGenre(@RequestBody String genreName){

        return genreDao.createGenre(genreName);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{id}/delete", method = RequestMethod.DELETE)
    public int deleteGenreById(@PathVariable int id){

        return genreDao.deleteGenreById(id);
    }

}
