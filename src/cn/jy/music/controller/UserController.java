package cn.jy.music.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.jy.music.pojo.User;
import cn.jy.music.service.UserService;

@Controller("UserController")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "/login",produces = "text/plain;charset=utf-8",method = RequestMethod.POST)
	@ResponseBody
	private String login(@RequestBody String params,HttpServletResponse response) {
		System.out.println("�û���¼����--"+params);
		User user = JSON.parseObject(params, User.class);
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			User u = userService.login(user);
			System.out.println("�û���Ϣ"+u);
			if (u == null) {
				result.put("success", false);
				result.put("message", "�û������������");
			} else {
				String cookInfo = URLEncoder.encode(u.getId()+"2048,"+u.getUsername()+","+u.getNickname(), "UTF-8");
				Cookie cookie = new Cookie("user", cookInfo);
				cookie.setMaxAge(60 * 60 * 24 * 7);
				cookie.setPath("/");//("/")��ʾ���Ƿ��ʵ�ǰ�����µ�����webApp�������cookie
	            cookie.setHttpOnly(false);
				 Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
			        boolean firstHeader = true;
			        for (String header : headers) { // �����ж��Set Cookie����
			            if (firstHeader) {
			                response.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; %s", header, "SameSite=Strict"));
			                firstHeader = false;
			                continue;
			            }
			            response.addHeader(HttpHeaders.SET_COOKIE, String.format("%s; %s", header, "SameSite=Strict"));
			        }
			    response.addCookie(cookie);
				result.put("success", true);
				result.put("message", "");
				result.put("userInfo", u);
			}
		} catch (Exception e) {
			result.put("success", false);
			result.put("message", "��¼�쳣�����Ժ�����"+e);
		}
		return JSON.toJSONString(result);
	}
	
	@RequestMapping(value = "/loginOut",method = RequestMethod.POST)
	@ResponseBody
	private String loginOut(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Cookie cookie = new Cookie("user", null);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
			result.put("success", true);
		}catch (Exception e) {
			result.put("success", false);
		}
		return JSON.toJSONString(result);
	}
	
	@RequestMapping(value = "/getDataByUrl",produces = "text/plain;charset=utf-8",method = RequestMethod.GET)
	@ResponseBody
	private Object getDataByUrl(String getUrl) {
		Object result = new Object();
		try {
            //����һ��url������ָ��Ҫ�ɼ���Ϣ����ַ
            URL url = new URL(getUrl);
            //����ȡ�����ֽ�ת��Ϊ�ַ�
            InputStreamReader inStrRead = new InputStreamReader(url.openStream(),"utf-8");
            //��ȡInputStreamReaderת���ɵ��ַ�
            BufferedReader bufRead = new BufferedReader(inStrRead); 
            result =  bufRead.readLine();
            bufRead.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getHomeMusicContent",produces = "text/plain;charset=utf-8",method = RequestMethod.GET)
	private Object getHomeMusicContent() {
		Object result = new Object();
		try {
			URL url = new URL("https://c.y.qq.com/mv/fcgi-bin/getmv_by_tag?g_tk_new_20200303=5381&g_tk=5381&loginUin=2500667233&hostUin=0&format=json&inCharset=utf8&outCharset=GB2312&notice=0&platform=yqq.json&needNewCode=0&cmd=shoubo&lan=all");
			 //����ȡ�����ֽ�ת��Ϊ�ַ�
			try {
				InputStreamReader inStrRead = new InputStreamReader(url.openStream(),"utf-8");
				//��ȡInputStreamReaderת���ɵ��ַ�
	            BufferedReader bufRead = new BufferedReader(inStrRead);
	            result =  bufRead.readLine();
	            bufRead.close();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
