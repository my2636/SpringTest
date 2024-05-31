package com.my.springtest.dao;

import com.my.springtest.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    --arraylist--
//    private static int PEOPLE_COUNT;
//    private List<Person> people;
//    {
//        people = new ArrayList<>();
//        people.add(new Person(++PEOPLE_COUNT, "Tom", 32, "anymail@mail.com" ));
//        people.add(new Person(++PEOPLE_COUNT, "Bob", 18, "bobmail@mail.com"));
//        people.add(new Person(++PEOPLE_COUNT, "Mike", 25, "mikemail@mail.com"));
//    }

//    Подключение
//    private static final String URL = "jdbc:postgresql://localhost:5432/springtest_db";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "1";
//
//    private static Connection connection;
//
//    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//        --JDBC API--
//        List<Person> people = new ArrayList<>();
//
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "select * from Person";
//            ResultSet resultSet = statement.executeQuery(SQL);
//
//            while ((resultSet.next())) {
//                Person person = new Person();
//
//                person.setId(resultSet.getInt("id"));
//                person.setName(resultSet.getString("name"));
//                person.setAge(resultSet.getInt("age"));
//                person.setEmail(resultSet.getString("email"));
//
//                people.add(person);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return people;
    }

    public Person show(int id) {
        Person person = null;
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
//        --JDBC API--
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("select * from Person where id=?");
//
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//
//            person = new Person();
//            person.setId(resultSet.getInt("id"));
//            person.setName(resultSet.getString("name"));
//            person.setAge(resultSet.getInt("age"));
//            person.setEmail(resultSet.getString("email"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return person;
//        --arraylist--
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person VALUES(1, ?, ?, ?)", person.getName(), person.getAge(), person.getEmail());
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("insert into Person values(1, ?, ?, ?)");
//
//            preparedStatement.setString(1, person.getName());
//            preparedStatement.setInt(2, person.getAge());
//            preparedStatement.setString(3, person.getEmail());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        --allow SQL injection--
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "insert into Person values(" + 1 + ",'" + person.getName() + "'," + person.getAge() + ",'" +
//                    person.getEmail() + "')";
//            statement.executeUpdate(SQL);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void update( int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?",updatePerson.getName(),
                updatePerson.getAge(), updatePerson.getEmail(), id);
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("update Person set name=?, age=?, email=? where id=?");
//
//            preparedStatement.setString(1, updatePerson.getName());
//            preparedStatement.setInt(2, updatePerson.getAge());
//            preparedStatement.setString(3, updatePerson.getEmail());
//            preparedStatement.setInt(4, id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        --arraylist--
//        Person personToBeUpdated = show(id);
//        personToBeUpdated.setName(updatePerson.getName());
//        personToBeUpdated.setAge(updatePerson.getAge());
//        personToBeUpdated.setEmail(updatePerson.getEmail());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("delete from Person where id=?");
//            preparedStatement.setInt(1, id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        people.removeIf(p -> p.getId() == id);
    }
}
