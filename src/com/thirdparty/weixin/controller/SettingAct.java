package com.thirdparty.weixin.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//import com.legal.common.office.FileUtils;
//import com.legal.common.util.GridJsonResult;
//import com.legal.common.util.MobileJsonResult;
import com.thirdparty.weixin.base.BaseSvc;
import com.thirdparty.weixin.bean.WeChatKF;
import com.thirdparty.weixin.service.WeChatKFSvc;

import net.sf.json.JSONObject;

@Controller
public class SettingAct {
	@Autowired
	private WeChatKFSvc weChatKFSvc;

	/**
	 * 主动刷新access_token
	 */
	@RequestMapping("update_access_token.jspx")
	@ResponseBody
	public void update_access_token() {
		BaseSvc.update_access_token();
	}
	@RequestMapping("test4.jspx")
	@ResponseBody
	public void test4() {
		weChatKFSvc.test4();
	}
	@RequestMapping("test5.jspx")
	@ResponseBody
	public void test5() {
		weChatKFSvc.test5();
	}

	/**
	 * 添加微信客服账号
     * @param invite_wx 微信号
     * @param nickname  微信昵称
     * @param head_img  客服头像
	 */
	@RequestMapping("add_invite_kf.jspx")
	@ResponseBody
	public String add_invite_kf(HttpServletRequest request, String kf_account, String kf_nick,String account,
			@RequestParam(required=false,value="kf_headimgurl")MultipartFile head_img) throws IllegalStateException, IOException {
		if(StringUtils.isBlank(kf_account)){
			return JSONObject.fromObject(new JsonResult(1, "微信账号不能为空")).toString();
		}
		if(StringUtils.isBlank(kf_nick)){
			return JSONObject.fromObject(new JsonResult(1, "微信昵称不能为空")).toString();
		}
		File targetFile= null;
		String path = new File(request.getSession().getServletContext().getRealPath(File.separator)).getParent();
		if(head_img == null || head_img.getSize() == 0){
			targetFile = new File(path.endsWith(File.separator) ? path +"res/12348_weixin/images/logo.jpg" : path + "/res/12348_weixin/images/logo.jpg");
			if(StringUtils.isNotBlank(account)){
				return weChatKFSvc.update_kfnc(account, kf_nick).toString();
			}
		}else{
		String fileName = head_img.getOriginalFilename();
//		fileName = "12348_wx_" + DateUtils.toString(new Date(), "yyyyMMddHHmmss") + FileUtils.getFileExt(fileName);
		if(kf_account.contains("@")){
			kf_account = kf_account.substring(0, kf_account.indexOf("@"));
		}
		fileName = "12348_wx_" + kf_account + FileUtils.getFileExt(fileName);
		targetFile = new File(path.endsWith(File.separator) ? path + "upload/wx/" : path + "/upload/wx/",
				fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		head_img.transferTo(targetFile);
		}
		if(StringUtils.isNotBlank(account)){
			return weChatKFSvc.upload_headimg(account, targetFile).toString();
		}
		Boolean bo = weChatKFSvc.add_invite_kf(kf_account, kf_nick, targetFile);
		if (bo) {
			JsonResult mobileJsonResult = new JsonResult(0, "已向该微信号发送成为邀请,接受邀请后将成为客服");
			return  JSONObject.fromObject(mobileJsonResult).toString();
		} else {
			return JSONObject.fromObject(new JsonResult(1, "客服创建失败,原因:1、字段错误 2、该微信号对应的客服已存在")).toString();
		}
	}

	/**
	 * 获取客服列表
	 */
//	@RequestMapping("getlist.jspx")
//	@ResponseBody
//	public GridJsonResult get_kflist() {
//		// TODO
//		List<WeChatKF> list = weChatKFSvc.get_kflist();
//		GridJsonResult g = new GridJsonResult(list);
//		return g;
//	}
	/**
	 * 删除客服
	 * @param kf_account
	 * @return
	 */
	@RequestMapping("del_kf.jspx")
	@ResponseBody
	public JSONObject del_kf(String kf_account){
		JSONObject json = weChatKFSvc.del_kfaccount(kf_account);
		return json;
	}
}
