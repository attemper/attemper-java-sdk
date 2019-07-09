package com.github.attemper.java.sdk.common.result.execution;

import java.util.Map;

public class TaskResult extends LogResult {

    /**
     * it will be transmitted all over the job instance
     */
    protected Map<String, Object> paramMap;

    /**
     * in the task scope
     */
    protected Map<String, String> dataMap;

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public TaskResult setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
        return this;
    }

    public Map<String, String> getDataMap() {
        return dataMap;
    }

    public TaskResult setDataMap(Map<String, String> dataMap) {
        this.dataMap = dataMap;
        return this;
    }
}
