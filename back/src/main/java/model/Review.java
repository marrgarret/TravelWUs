package model;

public class Review {
    private User user_id;
    private String text;
    private Tour tour_id;
    private String date;
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }
    
    public Tour getTour_id() {
        return tour_id;
    }

    public void setTour_id(Tour tour_id) {
        this.tour_id = tour_id;
    }

    
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    

    public Review(){
    }
}
