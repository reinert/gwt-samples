package io.reinert.gwtsamples.twitter.model;

import java.io.Serializable;

public class Tweet implements Serializable {

    private String userName;
    private String message;

    public Tweet() {
    }

    public Tweet(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tweet)) {
            return false;
        }

        final Tweet tweet = (Tweet) o;

        if (message != null ? !message.equals(tweet.message) : tweet.message != null) {
            return false;
        }
        if (userName != null ? !userName.equals(tweet.userName) : tweet.userName != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
