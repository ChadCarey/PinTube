/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gdata.client.Service;
import com.google.gdata.client.youtube.YouTubeQuery;
import com.google.gdata.data.*;
import com.google.gdata.data.extensions.Rating;
import com.google.gdata.data.geo.impl.GeoRssWhere;
import com.google.gdata.data.media.mediarss.MediaKeywords;
import com.google.gdata.data.media.mediarss.MediaPlayer;
import com.google.gdata.data.media.mediarss.MediaThumbnail;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.data.youtube.YouTubeMediaContent;
import com.google.gdata.data.youtube.YouTubeMediaGroup;
import com.google.gdata.data.youtube.YouTubeMediaRating;
import com.google.gdata.data.youtube.YtPublicationState;
import com.google.gdata.data.youtube.YtStatistics;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Developer key = AI39si51dV26gLpWp-I582THR2vsvGyVcpkqXcUfu3HA6xUvpWOulk22KaWPyfUEVYLQesjhDg3U6lwEJzv4b10YthdxB68tlw
 * gData Set Up: http://mrhaki.blogspot.com/2009/02/add-google-data-java-client-library-to.html 
 * @author chad
 */
@WebServlet(urlPatterns = {"/SearchYouTube"})
public class SearchYouTube extends HttpServlet {

public static void printVideoEntry(VideoEntry videoEntry, boolean detailed) {
  System.out.println("Title: " + videoEntry.getTitle().getPlainText());

  if(videoEntry.isDraft()) {
    System.out.println("Video is not live");
    YtPublicationState pubState = videoEntry.getPublicationState();
    if(pubState.getState() == YtPublicationState.State.PROCESSING) {
      System.out.println("Video is still being processed.");
    }
    else if(pubState.getState() == YtPublicationState.State.REJECTED) {
      System.out.print("Video has been rejected because: ");
      System.out.println(pubState.getDescription());
      System.out.print("For help visit: ");
      System.out.println(pubState.getHelpUrl());
    }
    else if(pubState.getState() == YtPublicationState.State.FAILED) {
      System.out.print("Video failed uploading because: ");
      System.out.println(pubState.getDescription());
      System.out.print("For help visit: ");
      System.out.println(pubState.getHelpUrl());
    }
  }

  if(videoEntry.getEditLink() != null) {
    System.out.println("Video is editable by current user.");
  }

  if(detailed) {

    YouTubeMediaGroup mediaGroup = videoEntry.getMediaGroup();

    System.out.println("Uploaded by: " + mediaGroup.getUploader());

    System.out.println("Video ID: " + mediaGroup.getVideoId());
    System.out.println("Description: " + 
      mediaGroup.getDescription().getPlainTextContent());

    MediaPlayer mediaPlayer = mediaGroup.getPlayer();
    System.out.println("Web Player URL: " + mediaPlayer.getUrl());
    MediaKeywords keywords = mediaGroup.getKeywords();
    System.out.print("Keywords: ");
    for(String keyword : keywords.getKeywords()) {
      System.out.print(keyword + ",");
    }

    GeoRssWhere location = videoEntry.getGeoCoordinates();
    if(location != null) {
      System.out.println("Latitude: " + location.getLatitude());
      System.out.println("Longitude: " + location.getLongitude());
    }

    Rating rating = videoEntry.getRating();
    if(rating != null) {
      System.out.println("Average rating: " + rating.getAverage());
    }

    YtStatistics stats = videoEntry.getStatistics();
    if(stats != null ) {
      System.out.println("View count: " + stats.getViewCount());
    }
    System.out.println();

    System.out.println("\tThumbnails:");
    for(MediaThumbnail mediaThumbnail : mediaGroup.getThumbnails()) {
      System.out.println("\t\tThumbnail URL: " + mediaThumbnail.getUrl());
      System.out.println("\t\tThumbnail Time Index: " +
      mediaThumbnail.getTime());
      System.out.println();
    }

    System.out.println("\tMedia:");
    for(YouTubeMediaContent mediaContent : mediaGroup.getYouTubeContents()) {
      System.out.println("\t\tMedia Location: "+ mediaContent.getUrl());
      System.out.println("\t\tMedia Type: "+ mediaContent.getType());
      System.out.println("\t\tDuration: " + mediaContent.getDuration());
      System.out.println();
    }

    for(YouTubeMediaRating mediaRating : mediaGroup.getYouTubeRatings()) {
      System.out.println("Video restricted in the following countries: " +
        mediaRating.getCountries().toString());
    }
  }
}    

        public static void printVideoFeed(VideoFeed videoFeed, boolean detailed) {
            for(VideoEntry videoEntry : videoFeed.getEntries() ) {
                printVideoEntry(videoEntry, detailed);
        }
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchYouTube</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchYouTube at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        YouTubeQuery query = new YouTubeQuery(new URL("http://gdata.youtube.com/feeds/api/videos"));
        String search = request.getParameter("search");
        // order results by the number of views (most viewed first)
        query.setOrderBy(YouTubeQuery.OrderBy.VIEW_COUNT);

        // search for puppies and include restricted content in the search results
        query.setFullTextQuery(search);
        query.setSafeSearch(YouTubeQuery.SafeSearch.NONE);
        Service service = new Service();
        
        try {
            VideoFeed videoFeed = service.query(query, VideoFeed.class);
            printVideoFeed(videoFeed, true);
        } catch (ServiceException ex) {
            System.out.println("Do Get function Error");
            Logger.getLogger(SearchYouTube.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
