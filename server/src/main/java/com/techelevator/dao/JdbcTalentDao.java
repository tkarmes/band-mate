package com.techelevator.dao;

import com.techelevator.model.Artist;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.techelevator.model.Talent;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTalentDao implements TalentDao {

    private final JdbcTemplate template;

    private RowMapper<Talent> mapper = new RowMapper<Talent>() {
        @Override
        public Talent mapRow(ResultSet rs, int rowNum) throws SQLException {
            String name = rs.getString("talent_name");
            int id = rs.getInt("id");
            Talent talent = new Talent(name);
            talent.setId(id);
            return talent;
        }
    };


    public JdbcTalentDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);

    }

    @Override
    public List<Talent> getAllTalents() {

        return template.query("SELECT * FROM talent", mapper);
    }

    @Override
    public List<Talent> getAllTalentsForArtist(long artistId) {
        List<Talent> allTalents = new ArrayList<>();
        SqlRowSet rows =  template.queryForRowSet("SELECT talent_name, id FROM talent WHERE id IN (SELECT talent_id FROM artist_talent WHERE artist_id = ?)", artistId);
        while (rows.next()){
            Talent talent = new Talent (rows.getString("talent_name"));
            talent.setId(rows.getInt("id"));
            allTalents.add(talent);


        }
        return allTalents;
    }

    @Override
    public Talent createTalent(String talentName) {
        Talent newTalent = null;
        String sql = "INSERT INTO talent (talent_name) VALUES (?) RETURNING id";
        try {
            int id = template.queryForObject(sql, int.class, talentName);
            newTalent = getTalentById(id);
        } catch (DataIntegrityViolationException | CannotGetJdbcConnectionException e) {
            e.printStackTrace();
            return null;
        }
        return newTalent;
    }

    @Override
    public Talent updateTalent(Talent newTalent) {
        String sql = "UPDATE talent SET talent_name = ? WHERE id = ?";
        try {
            int rowsAffected = template.update(sql, newTalent.getName(), newTalent.getId());
            if (rowsAffected == 0) {
                return null; // No talent found with the given old name
            }
        } catch (DataIntegrityViolationException | CannotGetJdbcConnectionException e) {
            e.printStackTrace();
            return null;
        }
        return newTalent;
    }

    @Override
    public boolean deleteTalent(String name) {
        String sql = "DELETE FROM talent WHERE talent_name = ?";
        try {
            int rowsAffected = template.update(sql, name);
            return rowsAffected > 0;
        } catch (DataIntegrityViolationException | CannotGetJdbcConnectionException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int deleteTalentById(int id) {
        String sql = "DELETE FROM talent WHERE id = ?";
        try {
            return template.update(sql, id);
        } catch (DataIntegrityViolationException | CannotGetJdbcConnectionException e) {
            e.printStackTrace();
            return id;

        }
    }

    @Override
    public Talent getTalentById(int id) {
        String sql = "SELECT * FROM talent WHERE id = ?";

        try {
            return template.queryForObject(sql, new Object[]{id}, mapper);
        } catch (DataIntegrityViolationException | CannotGetJdbcConnectionException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            // If no artist is found or any other exception occurs
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int addTalentToArtist(Artist artist, Talent talent){

        String sql = "INSERT INTO artist_talent (artist_id, talent_id) VALUES ((SELECT artist_id FROM artist WHERE name = ?), (SELECT talent_id FROM talent WHERE talent_name = ?))";
        int rows = template.update(sql, artist.getName(), talent.getName());

        return rows;


    }

   // INSERT INTO movie_genre (movie_id, genre_id)
   // VALUES ((SELECT movie_id FROM movie WHERE title = 'Coach Carter'), (SELECT genre_id FROM genre WHERE genre_name = 'Sports'));

}

