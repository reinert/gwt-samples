package io.reinert.gwtsamples.twitter.service;

import com.google.gson.Gson;

public class Factory {

    private static final Gson gson = new Gson();

    public static Gson getGson() {
        return gson;
    }
}
