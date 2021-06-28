package com.budan.springappblog.service;

import com.budan.springappblog.model.User;

public interface MailService {
    Boolean isNull(User user);

    void send(String emailTo, String subject, String message);
}
