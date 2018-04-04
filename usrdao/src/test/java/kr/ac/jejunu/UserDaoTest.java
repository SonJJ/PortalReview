package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class UserDaoTest {
    private UserDao userDao;

    //factory method pattern
    //새로운 유저에 등장
    //New User는 다르게 디비를 쓰고싶어
    //하지만 중복이 너무 많아
    //userDao만 재활용하고 사용자에따른 클래스를 각자 생성

    //strategy pattern
    //유저마다 클래스를 생성해서 사용하는 불편함???
    //스트래티지 패턴에 대한 개념에 이해가 필요함!!!!!!
    //추상화말고 인터페이스를 사용해보자



    @Before
    public void setup(){
        userDao = new UserDao();
//        hallaUserDao = new HallaUserDao();
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        int id = 1;
        User user = userDao.get(id);
        assertThat(user.getId(), is(1));
        assertThat(user.getName(), is("허윤호"));
        assertThat(user.getPassword(), is("1234"));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException{
        User user = new User();
        user.setName("길동");
        user.setPassword("1111");
        Integer id = userDao.insert(user);

        User insertedUser = userDao.get(id);
        assertThat(insertedUser.getId(), is(id));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));

    }


//    @Test
//    public void hallaGet() throws SQLException, ClassNotFoundException {
//        int id = 1;
//        User user = hallaUserDao.get(id);
//        assertThat(user.getId(), is(1));
//        assertThat(user.getName(), is("허윤호"));
//        assertThat(user.getPassword(), is("1234"));
//    }
//
//    @Test
//    public void hallaAdd() throws SQLException, ClassNotFoundException{
//        User user = new User();
//        user.setName("길동");
//        user.setPassword("1111");
//        Integer id = hallaUserDao.insert(user);
//
//        User insertedUser = hallaUserDao.get(id);
//        assertThat(insertedUser.getId(), is(id));
//        assertThat(insertedUser.getName(), is(user.getName()));
//        assertThat(insertedUser.getPassword(), is(user.getPassword()));
//
//    }
}