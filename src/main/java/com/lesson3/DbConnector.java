package com.lesson3;

import org.springframework.stereotype.Service;

public interface DbConnector {
    void connect();

    void save();

    void disconnect();
}
