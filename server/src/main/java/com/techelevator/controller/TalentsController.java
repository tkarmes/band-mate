package com.techelevator.controller;


import com.techelevator.dao.ArtistDao;
import com.techelevator.dao.TalentDao;
import com.techelevator.model.Talent;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/talents")
public class TalentsController {

    private final ArtistDao artistDao;

    private final TalentDao talentDao;


    public TalentsController(ArtistDao artistDao, TalentDao talentDao) {
        this.artistDao = artistDao;
        this.talentDao = talentDao;
    }

    @GetMapping
    public List<Talent> getAllTalents(){
        return talentDao.getAllTalents();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/create")
    public Talent createTalent(@RequestBody String talentName){

        return talentDao.createTalent(talentName);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{id}/delete", method = RequestMethod.DELETE)
    public int deleteTalentById(@PathVariable int id){

        return talentDao.deleteTalentById(id);
    }



}
