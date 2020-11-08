package dao;

import model.User;
import utilite.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends AbstractDao<User> {
    @Override
    public String insert(User user) {
        String name = user.getName();
        String email = user.getEmail();
        String surname = user.getSurname();
        String password = user.getPassword();

        Connection con = null;
        PreparedStatement preparedStatement = null;


        try {
            con = DBConnection.createConnection();
            String query = "insert into \"user\"(name,email,surname,password,image) values (?,?,?,?,?)";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, surname);
            preparedStatement.setString(4, password);

            int i = preparedStatement.executeUpdate();

            if (i != 0)
                return "SUCCESS";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return "Вероятно такой пользователь уже существует";

    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
