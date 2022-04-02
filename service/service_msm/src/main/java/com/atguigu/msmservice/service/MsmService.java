package com.atguigu.msmservice.service;

import java.util.Map;

public interface MsmService {
    boolean send(String email, Map<String, Object> param);
}
