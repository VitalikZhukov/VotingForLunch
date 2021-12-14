package ru.vote.web.menu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;


@ContextConfiguration({
        "classpath:spring/spring.xml",
        "classpath:spring/springDB.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/initDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MenuRestControllerTest {

    @Autowired
    private MenuRestController controller;

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getListByRestaurantId() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void delete() {
    }
}