package io.reinert.gwtsamples.twitter.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

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
        final String path = "/tmp/files";
        final String fileName = req.getHeader("File-Name");

        resp.setContentType("text/html;charset=UTF-8");

        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = resp.getWriter();

        try {
            out = new FileOutputStream(new File(path + File.separator + fileName));

            int read;
            final byte[] bytes = new byte[4096];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            writer.println("New file " + fileName + " created at " + path);
            //LOGGER.log(Level.INFO, "File {0} being uploaded to {1}", new Object[]{fileName, path});
        } catch (FileNotFoundException fne) {
            writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + fne.getMessage());

            fne.printStackTrace();

            //LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", new Object[]{fne.getMessage()});
        } finally {
            if (out != null)
                out.close();
            if (filecontent != null)
                filecontent.close();
            writer.close();
        }
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
