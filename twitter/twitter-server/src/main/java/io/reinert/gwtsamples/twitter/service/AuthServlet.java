package io.reinert.gwtsamples.twitter.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthServlet extends HttpServlet {

    public static final String APPLICATION_JSON = "application/json";
    public static final String ACCEPT = "Accept";
    public static final String CONTENT_TYPE = "Content-Type";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String accept = req.getHeader(ACCEPT);
//        if (accept.equalsIgnoreCase(APPLICATION_JSON)) {
//            try (BufferedReader r = req.getReader()) {
//                Tweet tweet = Factory.getGson().fromJson(r, Tweet.class);
//                TweetTable.add(tweet);
//            }
//        } else {
//            resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
//        }
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
//        String accept = req.getHeader(ACCEPT);
//        if (accept.equalsIgnoreCase(APPLICATION_JSON)) {
//            resp.setHeader(CONTENT_TYPE, APPLICATION_JSON);
//            ResourceMatcher resource = new ResourceMatcher(req, resp);
//            if (resource.matchesOne()) {
//                try (PrintWriter w = resp.getWriter()) {
//                    String tweetsJson = Factory.getGson().toJson(TweetTable.get(resource.getId()));
//                    w.println(tweetsJson);
//                }
//            } else if (resource.matchesAll()) {
//                try (PrintWriter w = resp.getWriter()) {
//                    String tweetsJson = Factory.getGson().toJson(TweetTable.get());
//                    w.println(tweetsJson);
//                }
//            }
//        } else {
//            resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
//        }
    }
}
