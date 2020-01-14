package com.github.attemper.java.sdk.common.web.result.delay;

public class DelayJobResult {

    protected String id;

    public String getId() {
        return id;
    }

    public DelayJobResult setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "[" + "id=" + id + "]";
    }
}
