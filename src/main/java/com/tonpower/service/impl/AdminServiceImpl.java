package com.tonpower.service.impl;

import com.tonpower.dao.AdminMapper;
import com.tonpower.domain.Admin;
import com.tonpower.domain.AdminExample;
import com.tonpower.service.AdminService;
import com.tonpower.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: li377650260
 * @date: 2021/8/18 17:22
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String name, String password) {

        Admin admin = null;
        String Md5pwd = MD5Util.getMD5(password);
        AdminExample example = new AdminExample();
        example.createCriteria().andANameEqualTo(name);
//        example.createCriteria().andAPassEqualTo(Md5pwd);
        List<Admin> list = adminMapper.selectByExample(example);
        if (list.size() > 0){
            admin = list.get(0);
            if (Md5pwd.equals(admin.getaPass())){
                return admin;
            }
        }
        return null;
    }
}
