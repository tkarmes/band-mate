package com.techelevator.dao;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.techelevator.model.Artist;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcArtistDao implements ArtistDao{


    private RowMapper<Artist> mapper = new RowMapper<Artist>() {
        @Override
        public Artist mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String bio = rs.getString("bio");
            Artist artist = new Artist(id, name, bio);
            artist.setId(id);
            return artist;
        }
    };



    private final JdbcTemplate template;

    public JdbcArtistDao(DataSource dataSource){

        this.template = new JdbcTemplate(dataSource);

    };


    @Override
    public List<Artist> getAllArtists() {


        String sql = "SELECT * FROM artist";

       return template.query(sql, mapper);


    }

    @Override
    public Artist createArtist(Artist artist) {
        String sql = "INSERT INTO artist (name, user_id, bio) VALUES (?, ?, ?) RETURNING id";

        try {
            int id = template.queryForObject(sql, int.class, artist.getName(), artist.getUserId(), artist.getBio());

                artist.setId(id);

        } catch (DataIntegrityViolationException | CannotGetJdbcConnectionException e) {
            e.printStackTrace();
            return null;
        }

        return artist;
    }

    @Override
    public Artist updateArtist(Artist artist){
        Artist updateArtist = null;
        String sql = "UPDATE artist SET name = ?, bio = ? WHERE id = ?";

        try {
            int rowsAffected = template.update(sql, artist.getName(), artist.getBio(), artist.getId());
            if (rowsAffected == 0) {
                return null; // No artist found with the given name
            }
        } catch (DataIntegrityViolationException | CannotGetJdbcConnectionException e) {
            e.printStackTrace();
            return null;
        }
        return artist;

    }

    @Override
    public boolean deleteArtist(int id) {
        String sql = "DELETE FROM artist WHERE id = ?";
        try {
            int rowsAffected = template.update(sql, id);
            return rowsAffected > 0;
        } catch (DataIntegrityViolationException | CannotGetJdbcConnectionException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Artist getArtistById(int id) {
        String sql = "SELECT * FROM artist WHERE id = ?";

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


}






