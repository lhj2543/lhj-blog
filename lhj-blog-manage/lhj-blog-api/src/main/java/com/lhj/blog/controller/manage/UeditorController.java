package com.lhj.blog.controller.manage;

import com.baidu.ueditor.ActionEnter;
import com.lhj.common.support.JsonSupport;
import com.lhj.model.system.SysAttachs;
import com.lhj.mybatis.service.DataBaseService;
import com.lhj.system.support.SessionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * 用于处理关于ueditor插件相关的请求
 */
@CrossOrigin
@RestController
@RequestMapping("/site/ueditor")
public class UeditorController {


	/*日志*/
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	DataBaseService dataBaseService;

	@Autowired
	JsonSupport jsonSupport;

	@Autowired
	SessionSupport sessionSupport;

	@RequestMapping(value = "/exec")
	public String exec2(HttpServletRequest request, HttpServletResponse response) {

		SysAttachs result = new SysAttachs();

		logger.info("ueditor插件相关的请求开始");
		try {
			request.setCharacterEncoding("utf-8");

			//String rootPath = request.getRealPath("/");
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String exec = new ActionEnter(request, rootPath).exec();
			System.err.println(exec);
			return  exec;

		} catch(Exception e) {
			result.setSuccess(false);
			result.setMessage("ueditor插件相关的请求开始异常");
			logger.error("ueditor插件相关的请求开始异常",e);
			e.printStackTrace();
		}
		logger.info("ueditor插件相关的请求开始结束");

		return jsonSupport.toJson(result);

	}

}
