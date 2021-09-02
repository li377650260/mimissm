package com.tonpower.service;

import com.tonpower.domain.Admin;

/**
 * @description:
 * @author: li377650260
 * @date: 2021/8/18 17:21
 */
public interface AdminService {
    // 完成登录判断
    Admin login(String name, String password);
}
