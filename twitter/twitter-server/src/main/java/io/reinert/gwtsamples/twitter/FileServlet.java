package io.reinert.gwtsamples.twitter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/server/files/*")
public class FileServlet extends HttpServlet {

    public static final String IMAGE_JPG = "image/jpg";
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
//        if (accept.equalsIgnoreCase(IMAGE_JPG)) {
            resp.setHeader(CONTENT_TYPE, IMAGE_JPG);
            resp.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
            OutputStream out = resp.getOutputStream();
            InputStream in = getServletContext().getResourceAsStream("/images/tiger.jpg");
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }
            in.close();
            out.flush();
//        } else {
//            resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
//        }
    }
}
