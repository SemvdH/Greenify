package greenify.client;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Friend {

    private SimpleIntegerProperty place;
    private SimpleStringProperty friend;
    private SimpleDoubleProperty score;

    /**
     * Constructor for a friend.
     * @param place place in the leaderboard
     * @param friend name of the user
     * @param friendScore score of the user
     */
    public Friend(Integer place, String friend, Double friendScore) {
        this.place = new SimpleIntegerProperty(place);
        this.friend =  new SimpleStringProperty(friend);
        this.score =  new SimpleDoubleProperty(friendScore);
    }

    public Integer getPlace() {
        return place.get();
    }

    public void setPlace(Integer place) {
        this.place = new SimpleIntegerProperty(place);
    }

    public String getFriend() {
        return friend.get();
    }

    public void setFriend(String name) {
        this.friend = new SimpleStringProperty(name);
    }

    public Double getScore() {
        return score.get();
    }

    public void setScore(Double score) {
        this.score = new SimpleDoubleProperty(score);
    }
}
