package com.techelevator.dao;

import com.techelevator.model.Artist;
import com.techelevator.model.Talent;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.techelevator.model.Genre;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGenreDao implements GenreDao{


    private final JdbcTemplate template;

    private RowMapper<Genre> mapper = new RowMapper<Genre>() {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            String name = rs.getString("genre_name");
            int id = rs.getInt("id");
            Genre genre = new Genre(name);
            genre.setId(id);
            return genre;
        }
    };

    public JdbcGenreDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);

    }

    @Override
    public List<Genre> getAllGenres() {

        return template.query("SELECT * FROM genre", mapper);

    }

    public List<Genre> getAllGenresForArtist(long artistId){
        List<Genre> allGenres = new ArrayList<>();
        SqlRowSet rows = template.queryForRowSet("SELECT genre_name, id FROM genre WHERE id IN (SELECT genre_id FROM artist_genre WHERE artist_id = ?)", artistId);
        while(rows.next()){
            Genre genre = new Genre(rows.getString("genre_name"));
            genre.setId(rows.getInt("id"));
            allGenres.add(genre);

        }
        return allGenres;


    }

    @Override
    public Genre createGenre(String genreName) {
        Genre newGenre = null;
        String sql = "INSERT INTO genre (genre_name) VALUES (?) RETURNING id";
        try {
            int id = template.queryForObject(sql, int.class, genreName);
            newGenre = getGenreById(id);
        } catch (DataIntegrityViolationException | CannotGetJdbcConnectionException e) {
            e.printStackTrace();
            return null;
        }
        return newGenre;
    }

    @Override
    public Genre updateGenre(Genre newGenre) {
        String sql = "UPDATE genre SET genre_name = ? WHERE id = ?";
        try {
            int rowsAffected = template.update(sql, newGenre.getName(), newGenre.getId() );
            if (rowsAffected == 0) {
                return null; // No genre found with the given old name
            }
        } catch (DataIntegrityViolationException | CannotGetJdbcConnectionException e) {
            e.printStackTrace();
            return null;
        }
        return newGenre;
    }

    @Override
    public boolean deleteGenre(String name) {
        String sql = "DELETE FROM genre WHERE genre_name = ?";
        try {
            int rowsAffected = template.update(sql, name);
            return rowsAffected > 0;
        } catch (DataIntegrityViolationException | CannotGetJdbcConnectionException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int deleteGenreById(int id) {
        String sql = "DELETE FROM genre WHERE id = ?";
        try {
            return template.update(sql, id);
        } catch (DataIntegrityViolationException | CannotGetJdbcConnectionException e) {
            e.printStackTrace();
            return id;

        }
    }

    @Override
    public Genre getGenreById(int id) {
        String sql = "SELECT * FROM genre WHERE id = ?";

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
    public int addGenreToArtist(Artist artist, Genre genre){

        String sql = "INSERT INTO artist_genre (artist_id, genre_id) VALUES ((SELECT artist_id FROM artist WHERE name = ?), (SELECT genre_id FROM genre WHERE genre_name = ?))";
        int rows = template.update(sql, artist.getName(),genre.getName());

        return rows;


    }


//    @Override
//    public List<Genre> getAllGenres() {
//        List<Genre> genres = new ArrayList<>();
//
//        String sql = "SELECT * FROM genre";
//
//        SqlRowSet rs = template.queryForRowSet(sql);
//
//        while(rs.next()){
//            Genre genre = mapRowToGenre(rs);
//            genres.add(genre);
//        }
//
//
//        return genres;
//    }
//
//    private static Genre mapRowToGenre(SqlRowSet rs) {
//        String name = rs.getString("genre_name");
//        Genre genre = new Genre(name);
//        return genre;
//    }
}
