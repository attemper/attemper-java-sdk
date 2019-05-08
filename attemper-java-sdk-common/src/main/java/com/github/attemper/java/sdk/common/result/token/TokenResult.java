package com.github.attemper.java.sdk.common.result.token;

/**
 * @author ldang
 */
public class TokenResult {

    /**
     * 刷新后的token
     */
    private String token;

    public TokenResult() {
    }

    private TokenResult(Builder builder) {
        setToken(builder.token);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getToken() {
        return token;
    }

    public TokenResult setToken(String token) {
        this.token = token;
        return this;
    }


    public static final class Builder {
        private String token;

        private Builder() {
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public TokenResult build() {
            return new TokenResult(this);
        }
    }
}
