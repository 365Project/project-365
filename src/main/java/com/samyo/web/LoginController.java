package com.samyo.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.samyo.domain.MemberVO;
import com.samyo.service.KakaoAPI;
import com.samyo.service.MemberService;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {

	@Autowired
	private KakaoAPI kakaoAPI;

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/login/getKakaoAuthUrl")
	public String getKakaoAuthUrl(HttpServletRequest request) throws Exception {
		String reqUrl = "https://kauth.kakao.com/oauth/authorize" + "?client_id=f6901986138e44bdb93305d1621fd37b"
				+ "&redirect_uri=http://localhost:8080/login/oauth_kakao" + "&response_type=code";
		// + "&redirect_uri=http://61.72.99.219:9130/login/oauth_kakao" +
		// "&response_type=code";

		return reqUrl;
	}

	@RequestMapping(value = "/login/oauth_kakao")
	public HashMap<String, String> login(@RequestParam("code") String code, HttpSession session) throws Exception {
		System.out.println("code : " + code);

		String access_Token = kakaoAPI.getAccessToken(code);
		System.out.println("access_Token : " + access_Token);

		HashMap<String, Object> userInfo = kakaoAPI.getUserInfo(access_Token);
		System.out.println("login Controller : " + userInfo);

		// List<MemberVO> list = new ArrayList<>();

		userInfo.put("id", userInfo.get("id"));
		userInfo.put("nickname", userInfo.get("nickname"));
		userInfo.put("email", userInfo.get("email"));
		userInfo.put("gender", userInfo.get("gender"));
		userInfo.put("age", userInfo.get("age"));
		// userInfo.put("token", remappingFunction)
		System.out.println();

		MemberVO member = new MemberVO();

		// 기존 회원인지 중복체크
		int cnt = memberService.getMember(userInfo.get("id"));

		HashMap<String, String> result = new HashMap<String, String>();
		if (cnt > 0) {
			System.out.println("이미 가입 된 회원 정보");
			// 클라이언트의 아이디가 존재할 때 세션에 해당 아이디와 토큰 등록
			if (userInfo.get("id") != null) {
				session.setAttribute("userId", userInfo.get("id"));
				session.setAttribute("access_Token", access_Token);
			}
			result.put("result", "login");
			
			return result;
		} else {
			memberService.insertMember(userInfo);
			if (userInfo.get("id") != null) {
				session.setAttribute("userId", userInfo.get("id"));
				session.setAttribute("access_Token", access_Token);
			}
			result.put("result", "join");
			return result;
		}

	}

	@RequestMapping(value = "/logout")
	public HashMap<String, String> logout(HttpSession session) {
		String access_Token = (String) session.getAttribute("access_Token");
		
		HashMap<String, String> result = new HashMap<String, String>();
		if (access_Token != null && !"".equals(access_Token)) {
			kakaoAPI.kakaoLogout(access_Token);
			session.removeAttribute("access_Token");
			session.removeAttribute("userId");
			result.put("result", "success");
			
			return result;
		} else {
			System.out.println("access_Token is null");
			result.put("result", "access_Token is null");
			return result;
		}
	}

}
