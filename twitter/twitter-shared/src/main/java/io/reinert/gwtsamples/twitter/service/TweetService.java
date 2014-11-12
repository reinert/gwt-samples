package io.reinert.gwtsamples.twitter.service;

import io.reinert.gwtsamples.twitter.model.Tweet;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("tweet")
public interface TweetService extends RemoteService {

    public String tweet(Tweet tweet);

    public String delete(Tweet tweet);

    public List<Tweet> getAll();
}
