package kr.ac.jejunu;

import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDaoTest {
    @Test

    public void get() throws SQLException, ClassNotFoundException {
        int id = 1;
        UserDao userDao = new UserDao();
        User user = userDao.get(id);
        assertThat(user.getId(), is(1));
        assertThat(user.getName(), is("손경호"));
        assertThat(user.getPassword(), is("1234"));
    }
}