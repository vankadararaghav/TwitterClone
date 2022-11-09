package tech.codingclub.helix.entity;

public class Retweet {
    public Long tweet_id;
    public Long user_id;

    public Retweet(Long tweet_id, Long user_id) {
        this.tweet_id = tweet_id;
        this.user_id = user_id;
    }

    public Long getTweetId() {
        return tweet_id;
    }

    public Long getUserId() {
        return user_id;
    }
}
