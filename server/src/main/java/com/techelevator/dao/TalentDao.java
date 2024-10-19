package com.techelevator.dao;

import com.techelevator.model.Artist;
import com.techelevator.model.Talent;

import java.util.List;

public interface TalentDao {

    List<Talent> getAllTalents();

    List<Talent> getAllTalentsForArtist(long artistId);


    Talent createTalent(String talentName);

    Talent updateTalent(Talent newTalent);

    Talent getTalentById(int id);

    boolean deleteTalent(String name);

    int deleteTalentById(int id);

    int addTalentToArtist(Artist artist, Talent talent);
}
