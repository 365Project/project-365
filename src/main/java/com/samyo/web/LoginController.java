package com.samyo.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.samyo.domain.MemberVO;
import com.samyo.kakao.KakaoAccessToken;
import com.samyo.kakao.KakaoUserInfo;
import com.samyo.service.MemberService;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/login")
	public String login() {
		System.out.println("로그인 페이지 이동 !");
		return "login";
	}

	@RequestMapping(value = "/login/getKakaoAuthUrl")
	public @ResponseBody String getKakaoAuthUrl(HttpServletRequest request) throws Exception {
		String reqUrl = "https://kauth.kakao.com/oauth/authorize" + "?client_id=f6901986138e44bdb93305d1621fd37b"
				+ "&redirect_uri=http://localhost:8080/login/oauth_kakao" + "&response_type=code";
				//+ "&redirect_uri=http://61.72.99.219:9130/login/oauth_kakao" + "&response_type=code";

		return reqUrl;
	}

	// 카카오 연동정보 조회
	@RequestMapping(value = "/login/oauth_kakao")
	public String oauthKakao(@RequestParam(value = "code", required = false) String code)
			throws Exception {

		System.out.println("###code###" + code);
		// String access_Token = getAccessToken(code);
		// System.out.println("###access_Token#### : " + access_Token);

		JsonNode accessToken;

		// JsonNode트리형태로 토큰받아온다
		JsonNode jsonToken = KakaoAccessToken.getKakaoAccessToken(code);
		// 여러 json객체 중 access_token을 가져온다
		accessToken = jsonToken.get("access_token");

		System.out.println("access_token : " + accessToken);

		// access_token을 통해 사용자 정보 요청
		JsonNode userInfo = KakaoUserInfo.getKakaoUserInfo(accessToken);

		// Get id
		String id = userInfo.path("id").asText();
		String nickname = null;
		String email = null;
		String gender = null;
		String age = null;
		String token = accessToken.asText();
		// 유저정보 카카오에서 가져오기 Get properties
		JsonNode properties = userInfo.path("properties");
		JsonNode kakao_account = userInfo.path("kakao_account");

		nickname = properties.path("nickname").asText();
		email = kakao_account.path("email").asText();
		gender = kakao_account.path("gender").asText();
		age = kakao_account.path("age_range").asText();

		MemberVO member = new MemberVO();

		member.setId(id);
		member.setNickname(nickname);
		member.setEmail(email);
		member.setGender(gender);
		member.setAge_range(age);
		member.setToken(token);

		System.out.println("## id : " + id);
		System.out.println("## nickname : " + nickname);
		System.out.println("## email : " + email);
		System.out.println("## gender : " + gender);
		System.out.println("## age : " + age);
		System.out.println("## token : " + token);
		// System.out.println(member);
		
		// 기존 회원인지 중복체크
		int cnt = memberService.getMember(member.getEmail());

		if (cnt > 0) {
			System.out.println("이미 가입 된 회원 정보");
			return "afterLogin"; 
		} else {
			memberService.insertMember(member);
			return "home";
		}

	}

}
