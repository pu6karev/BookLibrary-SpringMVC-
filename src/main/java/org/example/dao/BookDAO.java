package org.example.dao;

import org.example.model.Book;
import org.example.model.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    private static Connection connection;


    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM book", new BookMapper());
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id}, new BookMapper())
                .stream()
                .findAny()
                .orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO book(name, author, year) VALUES (?,?,?)", book.getName(),
                                book.getAuthor(), book.getYear());
    }

    public void update(int id, Book bookUpdated){
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? WHERE id=?", bookUpdated.getName(),
                            bookUpdated.getAuthor(), bookUpdated.getYear(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    // --- Create JOIN for "book" and "person" tables for getting the reader, who owns the book with the specified ID
    public Optional<Person> getBookOwner(int id){
        return  jdbcTemplate.query("SELECT person.* FROM book JOIN person ON book.person_id=person.id WHERE book.id=?",
                    new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                    .stream()
                    .findAny();
    }

    public void release(int id){
        jdbcTemplate.update("UPDATE book SET person_id=NULL WHERE id=?", id);
    }

    public void assign(int id, Person selectedPerson){
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE id=?", selectedPerson.getId(), id);
    }
}
