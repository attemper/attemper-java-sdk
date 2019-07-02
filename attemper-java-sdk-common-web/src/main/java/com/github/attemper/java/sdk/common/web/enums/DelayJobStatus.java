package com.github.attemper.java.sdk.common.web.enums;

public enum DelayJobStatus {

    VALID(0),

    INVALID(1),

    ;

    private int status;

    DelayJobStatus(int status) {
        this.status = status;
    }

    public int getStatus(){
        return status;
    }

    public static DelayJobStatus get(int status) {
        for (DelayJobStatus item : DelayJobStatus.values()) {
            if(item.getStatus() == status){
                return item;
            }
        }
        return null;
    }
}
