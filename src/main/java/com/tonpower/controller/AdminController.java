package com.tonpower.controller;

import com.tonpower.domain.Admin;
import com.tonpower.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: li377650260
 * @date: 2021/8/18 17:39
 */

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login.do")
    @ResponseBody
    public boolean login(String name, String pwd, HttpSession session){
        Admin admin = adminService.login(name,pwd);
        if (admin != null){
            session.setAttribute("admin",admin);
            return true;
        }else {
            return false;
        }


    }
}
