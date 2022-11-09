package tech.codingclub.helix.entity;

public class TweetResponse {
     public  String followerName;
     public  String followerEmail;
     public  Long    tweet_id;

    public Long getTweet_id() {
        return tweet_id;
    }

    public  String tweet;

    public long getCount() {
        return count;
    }

    public  long  count;
    public String getFollowerName() {
        return followerName;
    }

    public String getFollowerEmail() {
        return followerEmail;
    }

    public String getTweet() {
        return tweet;
    }
}
