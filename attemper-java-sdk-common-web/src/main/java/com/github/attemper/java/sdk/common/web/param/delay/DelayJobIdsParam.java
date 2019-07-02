package com.github.attemper.java.sdk.common.web.param.delay;

import com.github.attemper.java.sdk.common.param.BaseParam;

import java.util.List;

public class DelayJobIdsParam implements BaseParam {

    protected List<String> ids;

    public String validate() {
        if(ids == null || ids.isEmpty()){
            return "6300";
        }
        return null;
    }

    public List<String> getIds() {
        return ids;
    }

    public DelayJobIdsParam setIds(List<String> ids) {
        this.ids = ids;
        return this;
    }
}
