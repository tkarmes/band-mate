-- database m2_final_project
BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS artist, genre, talent, artist_genre, artist_talent, instrument, artist_instrument  CASCADE;


-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************

--users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE artist (
	id serial PRIMARY KEY,
	user_id int FOREIGN KEY,
	name VARCHAR(50),
	bio VARCHAR(250)
	

);



CREATE TABLE genre (
	id serial PRIMARY KEY,
	genre_name VARCHAR (50) UNIQUE


);

CREATE TABLE talent (
	id serial PRIMARY KEY,
	talent_name VARCHAR (50) UNIQUE


);

CREATE TABLE artist_genre (
	artist_id int REFERENCES artist(id),
	genre_id int REFERENCES genre(id)



);

CREATE TABLE artist_talent (


	artist_id int REFERENCES artist(id),
	talent_id int REFERENCES talent(id)


);

-- *************************************************************************************************
-- Insert some sample starting data
-- *************************************************************************************************

-- Users
-- Password for all users is password
INSERT INTO
    users (username, password_hash, role)
VALUES
    ('user', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
    ('user','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
	 ('user','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
	  ('user','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
	   ('admin','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN');


    insert into artist (name, user_id, bio) values ('Virgie', 1, 'Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.');
    insert into artist (name, user_id, bio) values ('Nedi', 2, 'Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.');
    insert into artist (name, user_id, bio) values ('Maribeth', 3, 'Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.');
    insert into artist (name, user_id, bio) values ('Dru', 4, 'Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.');
    insert into artist (name, user_id, bio) values ('Trina', 5, 'Tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.');


    insert into genre (genre_name) values ('blues');
    insert into genre (genre_name) values ('jazz');
    insert into genre (genre_name) values ('soul');
    insert into genre (genre_name) values ('rap');
    insert into genre (genre_name) values ('heavy metal');
    insert into genre (genre_name) values ('ultricies eu nibh');
    insert into genre (genre_name) values ('at velit eu');
    insert into genre (genre_name) values ('abset');
    insert into genre (genre_name) values ('aenean');
    insert into genre (genre_name) values ( 'ipsum primis');
    insert into genre (genre_name) values ( 'suspendisse potenti');
    insert into genre (genre_name) values ( 'mus etiam vel');
    insert into genre (genre_name) values ( 'in quam');
    insert into genre (genre_name) values ( 'elementum nullam varius');
    insert into genre (genre_name) values ( 'sollicitudin');
    insert into genre (genre_name) values ( 'porttitor');
    insert into genre (genre_name) values ( 'dui luctus rutrum');
    insert into genre (genre_name) values ( 'nulla suspendisse potenti');
    insert into genre (genre_name) values ( 'commodo vulputate justo');
    insert into genre (genre_name) values ( 'varius nulla');


    insert into talent (talent_name) values ('bass');
    insert into talent (talent_name) values ('drums');
    insert into talent (talent_name) values ('keyboard');
    insert into talent (talent_name) values ('painter');
    insert into talent (talent_name) values ('fiddle');
    insert into talent (talent_name) values ('vocals');
    insert into talent (talent_name) values ('suscipit');
    insert into talent (talent_name) values ('est');
    insert into talent (talent_name) values ('guitar');
    insert into talent (talent_name) values ( 'risus');
    insert into talent (talent_name) values ( 'ultrices');
    insert into talent (talent_name) values ( 'in');
    insert into talent (talent_name) values ( 'piano');
    insert into talent (talent_name) values ( 'morbi');
    insert into talent (talent_name) values ( 'tempus');
    insert into talent (talent_name) values ( 'sed');
    insert into talent (talent_name) values ( 'fusce');
    insert into talent (talent_name) values ( 'maecenas');
    insert into talent (talent_name) values ( 'ante');
    insert into talent (talent_name) values ( 'potenti');
	
	insert into artist_talent (artist_id, talent_id) values (1, 5), (2, 1), (3, 2), (3, 1), (4, 3), (5, 6);
	
	insert into artist_genre (artist_id, genre_id) values (1, 2), (2, 3), (3, 5), (4, 1), (5, 4);



COMMIT TRANSACTION;
