package org.example.dao;

import org.example.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();

        person.setId(resultSet.getInt("id"));
        person.setFirstName( resultSet.getString("first_name") );
        person.setLastName(resultSet.getString("last_name"));

        java.sql.Date sqlDate = resultSet.getDate("birth_date");
        if (sqlDate != null) {
            LocalDate birthDate = sqlDate.toLocalDate();
            person.setBirthDate(birthDate);
        }

        return person;
    }
}