package dao;

import model.User;
import utilite.DBConnection;
import utilite.Hashing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrDao {
    public String registerUser(User user){
        String username  = user.getName();
        String surname = user.getSurname();
        String password = Hashing.md5(user.getPassword());
        String email = user.getEmail();
        String phone = user.getPhone();

        Connection con = DBConnection.createConnection();
        try{
            String query = "insert into user(username,password,surname,email,phone" +
                    ") values (?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,surname);
            preparedStatement.setString(4,email);
            preparedStatement.setString(5,phone);

            int i= preparedStatement.executeUpdate();
            if (i != 0){
                return "SUCCESS";
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
