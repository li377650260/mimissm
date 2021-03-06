package com.tonpower.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tonpower.domain.ProductInfo;
import com.tonpower.service.ProductInfoService;
import com.tonpower.service.impl.ProductInfoServiceImpl;
import com.tonpower.utils.FileNameUtil;
import com.tonpower.vo.ProductSearchVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: li377650260
 * @date: 2021/8/18 19:55
 */
@Controller
@RequestMapping(value = "/prod")
public class ProductInfoController {

    public static final int PAGE_SIZE = 5;
    String saveFileName = "";

    @Autowired
    private ProductInfoService productInfoService;

    @RequestMapping("/getAll.do")
    public String getAll(HttpServletRequest request){
        List<ProductInfo> list = productInfoService.getAll();
        request.setAttribute("list",list);
        return "product";
    }

    @RequestMapping("/split.do")
    public ModelAndView split(){
        ModelAndView mv = new ModelAndView();
        PageInfo info = productInfoService.pageList(1,PAGE_SIZE);
        mv.addObject("info",info);
        mv.setViewName("product");
        return mv;
    }

    @RequestMapping("/ajaxsplit.do")
    @ResponseBody
    public void ajaxSplit(Integer page,ProductSearchVo vo, HttpSession session){
        PageInfo info = productInfoService.pageList(page,PAGE_SIZE,vo);
        session.setAttribute("info",info);
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxImg.do")
    public Object ajaxImg(MultipartFile pimage,HttpServletRequest request) throws IOException {
        //?????????????????????UUID+?????????????????????.jpg  .png

        saveFileName = FileNameUtil.getUUIDFileName()+FileNameUtil.getFileType(pimage.getOriginalFilename());
        String path = request.getServletContext().getRealPath("/image_big");
        pimage.transferTo(new File(path+File.separator + saveFileName));

        JSONObject object = new JSONObject();
        object.put("imgurl",saveFileName);
        return object.toString();
    }

    @RequestMapping("/save.do")
    public String save(ProductInfo info,HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        info.setpImage(saveFileName);
        info.setpDate(new Date());
        int result = productInfoService.save(info);
        if (result > 0){
             request.setAttribute("msg","??????????????????");
        }else {
             request.setAttribute("msg","??????????????????");
        }
        saveFileName = "";
        return "forward:/prod/split.do";
    }

    @RequestMapping("/one.do")
    public ModelAndView one(Integer pid,Integer page){
        ProductInfo info = productInfoService.getById(pid);
        ModelAndView mv = new ModelAndView();
        mv.addObject("prod",info);
        mv.addObject("page",page);
        mv.setViewName("update");
        return mv;
    }

    @RequestMapping("update.do")
    public String update(ProductInfo info,HttpServletRequest request){
        /*
        ajax????????????????????????????????????????????????saveFileName??????????????????????????????
        ????????????saveFileName???????????????????????????ajax?????????????????????info??????????????????????????????????????????
        ?????????saveFileName????????????info????????????saveFileName???????????????????????????????????????
         */
        if (!"".equals(saveFileName)){
            info.setpImage(saveFileName);
        }
        int num = -1;
        num = productInfoService.update(info);
        if (num > 0){
            request.setAttribute("msg","??????????????????");
        }else {
            request.setAttribute("msg","??????????????????");
        }
        saveFileName = "";
        return "forward:/prod/split.do";
    }

    @RequestMapping(value = "/delete.do",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delete(Integer pid,Integer page,ProductSearchVo vo,HttpServletRequest request){
        String msg = "";
        int num = -1;
        num = productInfoService.delete(pid);
        if (num > 0){
//            request.setAttribute("msg","??????????????????");
             msg = "??????????????????";
        }else {
//            request.setAttribute("msg","??????????????????");
             msg = "??????????????????";
        }
//        return "forward:/prod/split.do";
        PageInfo info = productInfoService.pageList(page,PAGE_SIZE,vo);
        request.getSession().setAttribute("info",info);
        return msg;
    }

    @RequestMapping("deletebatch")
    public String deleteBatch(String str,HttpServletRequest request){
        String[] ids = str.split(",");
        int nums = 0;

        try {
            for (String id : ids){
               nums += productInfoService.delete(Integer.valueOf(id));
            }
            if (nums == ids.length){
                request.setAttribute("msg","??????????????????");
            }else {
                request.setAttribute("msg","??????????????????");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "forward:/prod/split.do";
    }
}
