package com.github.attemper.java.sdk.rest.entity;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

public class MyHttpDelete extends HttpEntityEnclosingRequestBase {

    public String getMethod() {
        return HttpDelete.METHOD_NAME;
    }

    public MyHttpDelete(final String uri) {
        super();
        setURI(URI.create(uri));
    }
    public MyHttpDelete(final URI uri) {
        super();
        setURI(uri);
    }
    public MyHttpDelete() {
        super();
    }
}
