package greenify.client;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class Friend {

    private SimpleStringProperty friend;
    private SimpleFloatProperty score;

    public Friend(String friend, Float friendScore) {
        this.friend =  new SimpleStringProperty(friend);
        this.score =  new SimpleFloatProperty(friendScore);
    }


    public String getFriend() {
        return friend.get();
    }

    public void setFriend(String name) {
        this.friend = new SimpleStringProperty(name);
    }

    public Float getScore() {
        return score.get();
    }

    public void setScore(Float score) {
        this.score = new SimpleFloatProperty(score);
    }
}