package kr.ac.jejunu;

import java.sql.*;


public class UserDao {
    public User get(int id) throws SQLException, ClassNotFoundException{
        //사용자는 어디`에 저장되어있나?
        //Database를 사용해보자, Mysql을 사용해보자
        Class.forName("com.mysql.jdbc.Driver");

        //Connection을 맺고
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/jeju?characterEncoding=utf-8",
                "root",
                "root12345");

        //쿼리를 만들어서
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM userinfo WHERE i~d=?");
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
}
