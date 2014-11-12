package io.reinert.gwtsamples.twitter.database;

import io.reinert.gwtsamples.twitter.model.Tweet;

import java.util.ArrayList;
import java.util.List;

public final class TweetTable {

    private static List<Tweet> tweets = new ArrayList<>();

    public static int size() {
        return tweets.size();
    }

    public static boolean add(Tweet tweet) {
        return tweets.add(tweet);
    }

    public static void clear() {
        tweets.clear();
    }

    public static Tweet get(int i) {
        return tweets.get(i);
    }

    public static List<Tweet> get() {
        return tweets;
    }

    public static Tweet remove(int i) {
        return tweets.remove(i);
    }

    public static int indexOf(Object o) {
        return tweets.indexOf(o);
    }
}
