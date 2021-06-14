package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import com.google.gdata.util.common.base.PercentEscaper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import ca.jrvs.apps.twitter.Util.JsonUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;


public class TwitterDao implements CrdDao<Tweet, String> {

    // URI constants
    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH = "/1.1/statuses/update.json";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/1.1/statuses/destroy";

    // URI symbols
    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";

    // Response code
    private static final int HTTP_OK = 200;

    private HttpHelper httpHelper;

    @Autowired
    public TwitterDao(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    private URI getPostUri(Tweet tweet) throws URISyntaxException, UnsupportedEncodingException {
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        String status = percentEscaper.escape(tweet.getText());
        float longitude = tweet.getCoordinates().getCoordinates().get(0);
        float latitude = tweet.getCoordinates().getCoordinates().get(1);
        String postUri = API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL + status + AMPERSAND + "long"
                + EQUAL + longitude + AMPERSAND + "lat" + EQUAL + latitude;
        return new URI(postUri);
    }
    
    public Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
        Tweet tweet = null;

        //check response status
        int status = response.getStatusLine().getStatusCode();
        if (status != expectedStatusCode) {
            try {
                System.out.println(EntityUtils.toString(response.getEntity()));
            } catch (IOException e) {
                System.out.println("Response has no entity");
            }
            throw new RuntimeException("Unexpected HTTP status:" + status);
        }

        if (response.getEntity() == null) {
            throw new RuntimeException("Empty response body");
        }

        //Convert Response Entity to Str
        String jsonStr;
        try {
            jsonStr = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert entity to String", e);
        }

        //Convert JSON string to Tweet object
        try {
            tweet = JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
        } catch (IOException e) {
            throw new RuntimeException("Unable to convert JSON str to Object", e);
        }

        return tweet;
    }

    @Override
    public Tweet create(Tweet tweet) {
        // Construct URI
        URI uri;
        try {
            uri = getPostUri(tweet);
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Invalid tweet input", e);
        }

        // Execute HTTP POST Request
        HttpResponse response = httpHelper.httpPost(uri);

        // Validate response and deserialize response to Tweet object
        return parseResponseBody(response, HTTP_OK);
    }

    @Override
    public Tweet findById(String id) {
        // Construct URI
        URI showURI;
        try {
            showURI = new URI(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + id);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid Input Id String", e);
        }

        // Execute HTTP GET Request
        HttpResponse response = httpHelper.httpGet(showURI);

        // Validate response and deserialize response to Tweet object
        return parseResponseBody(response, HTTP_OK);
    }

    @Override
    public Tweet deleteById(String id) {
        // Construct URI
        URI deleteURI;
        try {
            deleteURI = new URI(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + id);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid Input Id String", e);
        }

        // Execute HTTP GET Request
        HttpResponse response = httpHelper.httpPost(deleteURI);

        // Validate response and deserialize response to Tweet object
        return parseResponseBody(response, HTTP_OK);
    }
}