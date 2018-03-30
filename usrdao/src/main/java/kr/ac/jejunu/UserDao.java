package kr.ac.jejunu;

import java.sql.*;


public class UserDao {
    public User get(int id) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/jeju",
                "root",
                "root1234");
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM userinfo WHER id=?");
        //결과 맵핑
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }
}
