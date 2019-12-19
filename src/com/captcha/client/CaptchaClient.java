package com.captcha.client;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
public class CaptchaClient {
    @Resource
    private ImageCaptchaService imageCaptchaService;
    
    @RequestMapping("/checkCaptcha")
    @ResponseBody
    public String checkCaptcha(HttpServletRequest request,String captcha){
        if (imageCaptchaService.validateResponseForID(request.getSession().getId(), captcha)) {
            return "Captcha true";
        }
        return "Captcha false";
    }
}
