package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

    private static long id;
    private static String content;

    public Message() {
    }

    public static long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", content=" + content +
                '}';
    }
}