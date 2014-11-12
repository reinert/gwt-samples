package io.reinert.gwtsamples.twitter.service;

import io.reinert.gwtsamples.twitter.database.TweetTable;
import io.reinert.gwtsamples.twitter.model.Tweet;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.List;

public class TweetServiceImpl extends RemoteServiceServlet implements TweetService {

    @Override
    public String tweet(Tweet tweet) {
        TweetTable.add(tweet);
        return "Parabéns! Você publicou um tweet! :D";
    }

    @Override
    public String delete(Tweet tweet) {
        TweetTable.remove(TweetTable.indexOf(tweet));
        return "Oh! Você excluiu seu tweet! :(";
    }

    @Override
    public List<Tweet> getAll() {
        return TweetTable.get();
    }
}
