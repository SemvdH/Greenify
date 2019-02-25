package hello;

public class Greeting {

    private static long id;
    private static String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public static long getId() {
        return id;
    }

    public static String getContent() {
        return content;
    }
}