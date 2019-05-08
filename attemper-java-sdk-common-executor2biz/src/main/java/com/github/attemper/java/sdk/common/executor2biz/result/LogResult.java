package com.github.attemper.java.sdk.common.executor2biz.result;

public class LogResult {

    protected String logKey;

    protected String logText;

    public String getLogKey() {
        return logKey;
    }

    public LogResult setLogKey(String logKey) {
        this.logKey = logKey;
        return this;
    }

    public String getLogText() {
        return logText;
    }

    public LogResult setLogText(String logText) {
        this.logText = logText;
        return this;
    }
}
