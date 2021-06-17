package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.Util.JsonUtil;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class TwitterService implements Service{

    private CrdDao dao;
    private static final int TWEET_CHAR_LIMIT = 140;

    @Autowired
    public TwitterService(CrdDao dao){
        this.dao = dao;
    }

    @Override
    public Tweet postTweet(Tweet tweet) {

        // Business logic:
        // text length
        // lat/lon range
        // id format
        validatePostTweet(tweet);
        return (Tweet) dao.create(tweet);
    }

    private void validatePostTweet(Tweet tweet) {
        if (tweet.getText().length() > TWEET_CHAR_LIMIT){
            throw new IllegalArgumentException("Tweet exceeds character limit of 140");
        }

        if (tweet.getCoordinates() != null){
            float lon = tweet.getCoordinates().getCoordinates().get(0);
            float lat = tweet.getCoordinates().getCoordinates().get(1);

            if (lon > 180 || lon < -180 || lat < -90 || lat > 90) {
                throw new IllegalArgumentException("Invalid Coordinates");
            }
        }
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {
        validateShowTweet(id, fields);
        Tweet tweet = (Tweet) dao.findById(id);
        if (fields != null) {
            try {
                return JsonUtil.retainFields(tweet, fields);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error while processing Tweet returned by Get By Id");
            }
        }
        return tweet;
    }

    private void validateShowTweet(String id, String[] fields) {
        String[] validFields = {
                "created_at",
                "id",
                "id_str",
                "text",
                "entities",
                "coordinates",
                "retweet_count",
                "favorite_count",
                "favorited",
                "retweeted"
        };
        if (fields != null){
            for (String field:fields){
                if (!Arrays.asList(validFields).contains(field)) {
                    throw new IllegalArgumentException("Invalid field name: " + field);
                }
            }
        }
        validateId(id);
    }

    private void validateId(String id) {
        if (!id.matches("[0-9]+")) {
            throw new IllegalArgumentException("Invalid tweet Id");
        }
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        for (String id:ids){
            validateId(id);
        }
        List<Tweet> tweets = Arrays.stream(ids).map(id -> (Tweet) dao.deleteById(id)).collect(
                Collectors.toList());
        return tweets;
    }
}
