package greenify.client;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Friend {

    private SimpleStringProperty friend;
    private SimpleDoubleProperty score;

    public Friend(String friend, Double friendScore) {
        this.friend =  new SimpleStringProperty(friend);
        this.score =  new SimpleDoubleProperty(friendScore);
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
