package com.budan.springappblog.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@RedisHash(value = "Activate", timeToLive = 86400)
public class ActivateCode implements Serializable {
    private UUID id;
    private String activateCode;
    private boolean activate;
    private String username;
}
