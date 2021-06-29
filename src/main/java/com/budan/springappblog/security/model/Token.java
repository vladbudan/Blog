package com.budan.springappblog.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Token {

    private Integer userId;
    private long exp;

    public Token(final Integer userId, final long exp) {
        this.userId = userId;
        this.exp = exp;
    }

}
