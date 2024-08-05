package org.example.dao;

import org.example.model.Book;
import org.example.model.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    //private static Connection connection;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }


    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new PersonMapper())
            .stream()
            .findAny()
            .orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person(first_name, last_name, birth_date) VALUES(?, ?, ?)", person.getFirstName(),
                person.getLastName(), person.getBirthDate());
    }

    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE person SET first_name=?, last_name=?, birth_date=? WHERE id=?", updatedPerson.getFirstName(),
                updatedPerson.getLastName(), updatedPerson.getBirthDate(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);

    }

    public List<Book> getBooksByPersonId(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}