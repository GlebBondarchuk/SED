package com.bsu.sed.parser;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndImage;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.URL;

/**
 * @author gbondarchuk
 */
public class RssParser {
    /**
     * Parse rss URL into DTO.
     *
     * @param url Rss URL.
     * @return SyndFeed object.
     * @throws IOException
     * @throws FeedException
     */
    public static SyndFeed parse(String url) throws IOException, FeedException {
        SyndFeedInput input = new SyndFeedInput();
        URL rssUrl = new URL(url);
        XmlReader reader = new XmlReader(rssUrl);
        return input.build(reader);
    }

    public static boolean isValid(String url) {
        try {
            parse(url);
            return true;
        } catch (IOException | FeedException e) {
            return false;
        }
    }

    public static String getImageUrl(String url) throws IOException, FeedException {
        SyndFeed feed = parse(url);
        SyndImage image = feed.getImage();
        if(image != null) {
            return image.getUrl();
        }
        return null;
    }
}
