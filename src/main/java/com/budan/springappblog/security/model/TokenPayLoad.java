package com.budan.springappblog.security.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TokenPayLoad {

    private long userId;
    private long exp;

    public TokenPayLoad(final long userId, final long exp) {
        this.userId = userId;
        this.exp = exp;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }
}
