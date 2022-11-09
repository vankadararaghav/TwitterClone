package tech.codingclub.helix.entity;

import java.util.Date;

public class Tweet {
    public String message;
    public Long id;
    public Long created_at;
    public Long author_id;

    public String getMessage() {
        return message;
    }

    public Long getId() {
        return id;
    }

    public Long getDate() {
        return created_at;
    }

    public Long getAuthorid() {
        return author_id;
    }

    public Tweet(String message, Long id, Long date, Long authorid) {
        this.message = message;
        this.id = id;
        this.created_at = date;
        this.author_id = authorid;
    }
}
