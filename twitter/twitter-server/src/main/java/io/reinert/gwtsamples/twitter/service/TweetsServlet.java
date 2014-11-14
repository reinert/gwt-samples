package io.reinert.gwtsamples.twitter.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import io.reinert.gwtsamples.twitter.database.TweetTable;
import io.reinert.gwtsamples.twitter.model.Tweet;

public class TweetsServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(TweetsServlet.class.getName());

    public static final Gson gson = new Gson();
    public static final String APPLICATION_JSON = "application/json";
    public static final String ACCEPT = "Accept";
    public static final String CONTENT_TYPE = "Content-Type";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accept = req.getHeader(ACCEPT);
        if (accept.equalsIgnoreCase(APPLICATION_JSON)) {
            try (BufferedReader r = req.getReader()) {
                Tweet tweet = gson.fromJson(r, Tweet.class);
                TweetTable.add(tweet);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accept = req.getHeader(ACCEPT);
        if (accept.equalsIgnoreCase(APPLICATION_JSON)) {
            resp.setHeader(CONTENT_TYPE, APPLICATION_JSON);
            ResourceMatcher resource = new ResourceMatcher(req, resp);
            if (resource.matchesOne()) {
                try (PrintWriter w = resp.getWriter()) {
                    String tweetsJson = gson.toJson(TweetTable.get(resource.getId()));
                    w.println(tweetsJson);
                }
            } else if (resource.matchesAll()) {
                try (PrintWriter w = resp.getWriter()) {
                    String tweetsJson = gson.toJson(TweetTable.get());
                    w.println(tweetsJson);
                }
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    private static class ResourceMatcher {

        private int id = -1;
        private boolean matches;

        public ResourceMatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

            // Check for ID case first, since the All pattern would also match
            if (req.getPathInfo() != null) {
                try {
                    id = Integer.parseInt(req.getPathInfo().substring(1));
                    resp.setStatus(HttpServletResponse.SC_OK);
                    matches = true;
                } catch (NumberFormatException e) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_OK);
                matches = true;
            }
        }

        public int getId() {
            return id;
        }

        public boolean matchesOne() {
            return matches && id > -1;
        }

        public boolean matchesAll() {
            return matches && id == -1;
        }
    }
}
