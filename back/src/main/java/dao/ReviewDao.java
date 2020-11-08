//package dao;
//
//import model.Review;
//import model.Tour;
//import model.User;
//import utilite.DBConnection;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ReviewDao extends AbstractDao<ReviewDao> {
//    public List<Review> findAllByEventId(int id) {
//        List<Review> reviews = new ArrayList<>();
//        Tour res = null;
//        Connection con = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            con = DBConnection.createConnection();
//            preparedStatement = con.prepareStatement("SELECT * FROM  review  where event_id=? ");
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                int user_id = resultSet.getInt("user_id");
//                int tour_id = resultSet.getInt("event_id");
//                String text = resultSet.getString("text");
//                String date = resultSet.getString("date");
//
//                UserDao userDao = new UserDao();
//                User user = userDao.findById(user_id);
////                EventDao eventDao = new EventDao();
//                Tour tour = tourDao.findById(tour_id);
//
//                Review review = new Review();
//                review.setDate(date);
//                review.setTour_id(tour);
//                review.setUser_id(user);
//                review.setText(text);
//                reviews.add(review);
//            }
//        } catch (SQLException | SQLException e) {
//            throw new IllegalStateException(e);
//        } finally {
//            if (resultSet != null) {
//                try {
//                    resultSet.close();
//                } catch (SQLException ignore) {
//                }
//            }
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException ignore) {
//                }
//            }
//            if (con != null) {
//                try {
//                    con.close();
//                } catch (SQLException ignore) {
//                }
//            }
//        }
//
//        return reviews;
//    }
//
//    public String insert(Review review) {
//        String text = review.getText();
//
//        Connection con = null;
//
//        PreparedStatement preparedStatement = null;
//
//        try {
//            con = DBConnection.createConnection();
//            String query = "insert into review(user_id,event_id,text,date) values (?,?,?,?)";
//            preparedStatement = con.prepareStatement(query);
//            preparedStatement.setInt(1, review.getUser_id().getId());
//            preparedStatement.setInt(2, review.getEvent_id().getId());
//            preparedStatement.setString(3, text);
//            preparedStatement.setString(4, review.getDate());
//
//
//            int i = preparedStatement.executeUpdate();
//
//            if (i != 0)
//                return "SUCCESS";
//        } catch (SQLException e) {
//            throw new IllegalStateException(e);
//        } finally {
//            if (con != null) {
//                try {
//                    con.close();
//                } catch (SQLException ignore) {
//                }
//            }
//
//        }
//
//        return "Что-то пошло не так";
//    }
//
//    @Override
//    public String insert(ReviewDao adr) {
//        return null;
//    }
//
//    @Override
//    public ReviewDao findById(int id) {
//        return null;
//    }
//
//    @Override
//    public void update(ReviewDao adr) {
//
//    }
//
//
//    @Override
//    public List<ReviewDao> findAll() {
//        return null;
//    }
//}
