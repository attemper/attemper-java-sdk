package com.github.attemper.java.sdk.common.result.execution;

public class LogResult {

    protected boolean success = true;

    protected String logKey;

    protected String logText;

    public boolean getSuccess() {
        return success;
    }

    /**
     * The default vaule is <code>true</code>
     *
     * @param success true or false
     * @return this
     */
    public LogResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

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
