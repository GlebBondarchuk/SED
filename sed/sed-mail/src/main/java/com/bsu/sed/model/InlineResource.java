package com.bsu.sed.model;

/**
 * Resource to inline to message.
 *
 * @author gbondarchuk
 */
public class InlineResource {
    private String identifier;
    private String url;

    public InlineResource(String identifier, String url) {
        this.identifier = identifier;
        this.url = url;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getUrl() {
        return url;
    }
}
