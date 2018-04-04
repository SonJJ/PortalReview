package kr.ac.jejunu;

import java.sql.*;


public class UserDao {
    public final ConnectionMaker connectionMaker = new JejuConnectionMaker();

    public User get(int id) throws SQLException, ClassNotFoundException{
        //사용자는 어디에 저장되어있나?
        //Database를 사용해보자, Mysql을 사용해보자
        Connection connection = connectionMaker.getConnection();

        //쿼리를 만들어서
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM userinfo WHERE id=?");
        preparedStatement.setInt(1,id);

        //실행시키고
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        //결과를 User에 잘 매핑하
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        //자원을 해지한다.
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }

    public Integer insert(User user) throws SQLException, ClassNotFoundException {
        //connection을 맺고
        Connection connection = connectionMaker.getConnection();
        //쿼리문 만들고
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO userinfo(name, password) VALUES (?,?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        //업데이트하고
        preparedStatement.executeUpdate();

        //마지막 id값
        preparedStatement = connection.prepareStatement(
                "SELECT last_insert_id()");
        //결과맵핑
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        //인덱스값 하나???
        Integer id = resultSet.getInt(1);
        //자원해제
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return id;
    }

    //내용물을 추상화시킴
}
