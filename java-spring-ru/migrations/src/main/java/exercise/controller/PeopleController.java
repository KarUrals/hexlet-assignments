package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    JdbcTemplate jdbc;

    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping(path = "")
    public List<Map<String, Object>> getAllPersons() {
        String sql = "SELECT * FROM person";
        List<Map<String, Object>> rows = jdbc.queryForList(sql);
        return rows;
    }

    @GetMapping(path = "/{id}")
    public List<Map<String, Object>> getPerson(@PathVariable("id") String id) {
        String sql = "SELECT * FROM person WHERE id = ?";
//        return jdbc.queryForObject(
//                query, new Object[]{id}, String.class);
//        return (String) jdbc.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Object.class));
//        return id;
        List<Map<String, Object>> row = jdbc.queryForList(sql, id);
        return row;
    }
    // END
}
