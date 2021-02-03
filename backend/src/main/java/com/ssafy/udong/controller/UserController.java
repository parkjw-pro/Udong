package com.ssafy.udong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.udong.dto.UserDto;
import com.ssafy.udong.service.JwtService;
import com.ssafy.udong.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
@Api(value = "/user")
public class UserController {
	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@ApiOperation(value = "회원 로그인", notes = "사용자 정보로 페이지에 로그인 가능한지 확인합니다.\n" +
			"## 필수값\n" + " - userId : 사용자 아이디\n" + " - password : 사용자 비밀번호\n")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody UserDto userDto, HttpServletResponse response, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			System.out.println(userDto.getUserId());
			System.out.println(userDto.getPassword());
			UserDto loginUser = userService.login(userDto);
			if (loginUser != null) {
				// jwt.io에서 확인
				// 로그인 성공했다면 토큰을 생성한다.
				String token = jwtService.create(loginUser);
				logger.trace("로그인 토큰정보 : {}", token);

				// 토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다.
				// response.setHeader("auth-token", token); ->한개보낼떄
				resultMap.put("auth-token", token);
				resultMap.put("user-id", loginUser.getUserId());
				resultMap.put("user-name", loginUser.getNickname());
				resultMap.put("is-manager", loginUser.getIsManager());
				// resultMap.put("status", true);
				// resultMap.put("data", loginUser);
				status = HttpStatus.ACCEPTED;

			} else {
				resultMap.put("message", "로그인 실패");
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원 정보 조회", notes = "사용자 정보를 조회한 후, 토큰에 담아 전달합니다.\n" +
			"## 필수값\n")
	@GetMapping
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
//		System.out.println(">>>>>> " + jwtService.get(req.getHeader("auth-token")));
		try {
			// 사용자에게 전달할 정보이다.
			// String info = memberService.getServerInfo();

			resultMap.putAll(jwtService.get(req.getHeader("auth-token")));
			// resultMap.put("status", true);
			// resultMap.put("info", info);
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원 탈퇴", notes = "사용자 정보를 이용해 해당 사용자를 탈퇴처리합니다.\n" +
			"## 필수값\n" + " - userId : 사용자 아이디\n")
	@DeleteMapping
	public ResponseEntity<String> deleteUser(@RequestBody UserDto userDto) throws Exception {
		int result = userService.deleteUser(userDto);
		if (result == SUCCESS) {
			return new ResponseEntity<String>("회원탈퇴 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("회원탈퇴 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "회원가입", notes = "새로운 사용자를 등록합니다.\n" +
			"## 필수값\n" + " - userId : 사용자 아이디\n"
						+ " - password : 사용자 비밀번호\n"
						+ " - nickname : 사용자 닉네임\n"
						+ " - email : 사용자 이메일 주소\n")
	@PostMapping
	public ResponseEntity<String> insertUser(@RequestBody UserDto userDto) throws Exception {
		int result = userService.insertUser(userDto);
		if (result == SUCCESS) {
			return new ResponseEntity<String>("회원가입 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("회원가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "회원수정", notes = "사용자 정보를 수정합니다.\n" +
			"## 필수값\n" + " - userId : 사용자 아이디\n"
						+ " - password : 변경할 비밀번호\n"
						+ " - nickname : 변경할 닉네임\n"
						+ " - email : 변경할 이메일 주소\n")
	@PutMapping
	public ResponseEntity<String> updateUser(@RequestBody UserDto userDto) throws Exception {
		int result = userService.updateUser(userDto);
		if (result == SUCCESS) {
			return new ResponseEntity<String>("회원수정 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("회원수정 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "비밀번호 찾기", notes = "사용자가 비밀번호를 분실하였을 경우, 개인정보 확인 후 재설정을 위한 인증코드를 발송합니다.\n" +
			"## 필수값\n" + " - userId : 사용자 아이디\n"
						+ " - email : 사용자 이메일 주소\n")

	@GetMapping("/password")
	public ResponseEntity<UserDto> selectUser(@RequestParam(value="userId") String userId,@RequestParam(value="email") String email) throws Exception {
		UserDto userDto = new UserDto();
		userDto.setUserId(userId);
		userDto.setEmail(email);
		UserDto resultDto = userService.selectUser(userDto);

		if (resultDto != null) {// 아이디 비밀번호가있으면 ok
			userService.gmailSend(userDto.getEmail()); // 인증코드전송
			return new ResponseEntity<UserDto>(resultDto, HttpStatus.OK);
		} else { // 아이디비밀번호가없으면 fail
			return new ResponseEntity<UserDto>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "비밀번호 재설정", notes = "사용자의 인증 후 비밀번호를 재설정합니다.\n" +
			"## 필수값\n" + " - userId : 사용자 아이디\n"
						+ " - password : 사용자 비밀번호\n")	
	@PutMapping("/password")
	public ResponseEntity<String> updatePassword(@RequestBody UserDto userDto) throws Exception {
		int result = userService.updatePassword(userDto);
		if (result == SUCCESS) {
			return new ResponseEntity<String>("비밀번호 재설정 성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("비밀번호 재설정 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "전체 회원 조회", notes = "모든 사용자의 정보를 조회합니다.\n" + "## 필수값\n")
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> selectUserAll() throws Exception {
		List<UserDto> resultDto = userService.selectUserAll();
		if (resultDto != null) {
			return new ResponseEntity<List<UserDto>>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<UserDto>>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "아이디 중복 확인", notes = "회원가입 시 입력한 아이디가 기존회원의 아이디와 중복인지 확인합니다.\n" +
			"## 필수값\n" + " - userId : 사용자 아이디\n")
	@GetMapping("/id/{userId}")
	public ResponseEntity<String> selectDuplicateID(@PathVariable String userId) throws Exception {
		String result = userService.selectDuplicateID(userId);
		if (result != null) {
			return new ResponseEntity<String>("현재 사용중인 아이디입니다.\n", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>("사용 가능한 아이디 입니다.\n", HttpStatus.OK);
		}
	}

	@ApiOperation(value = "닉네임 중복 확인", notes = "회원가입 시 입력한 닉네임이 기존회원의 닉네임과 중복인지 확인합니다.\n" +
			"## 필수값\n" + " - nickname : 사용자 닉네임\n")
	@GetMapping("/nickname/{nickname}")
	public ResponseEntity<String> selectDuplicateNickname(@PathVariable String nickname)
			throws Exception {
		String result = userService.selectDuplicateNickname(nickname);
		if (result != null) {
			return new ResponseEntity<String>("현재 사용중인 닉네임입니다..\n", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>("사용 가능한 닉네임입니다.\n", HttpStatus.OK);
		}
	}

	@ApiOperation(value = "이메일 중복 확인과 인증 메일 전송", notes = "회원가입 시 입력한 이메일이 기존회원의 이메일과 중복인지 확인하고, 중복이 아니라면 메일인증키를 전송합니다.\n" +
			"## 필수값\n" + " - email : 사용자 이메일 주소\n")
	@PostMapping("/email")
	public ResponseEntity<String> selectDuplicateEmail(@RequestBody UserDto userDto) throws Exception {
		String result = userService.selectDuplicateEmail(userDto.getEmail());
		if (result != null) { // 이메일이 이미 있으면
			return new ResponseEntity<String>("현재 사용중인 이메일입니다.\n", HttpStatus.INTERNAL_SERVER_ERROR);
		} else { // 이메일이 없으면
			userService.gmailSend(userDto.getEmail());
			return new ResponseEntity<String>("사용 가능한 이메일입니다.\n", HttpStatus.OK);
		}
	}

	@ApiOperation(value = "인증 메일 일치 확인", notes = "인증을 위해 발송한 이메일 코드가 알맞는지 확인합니다.\n" +
			"## 필수값\n" + " - code : 인증 메일로 발송한 코드\n")

	@PostMapping("/email/{code}")
	public ResponseEntity<String> checkDuplicateEmail(@PathVariable String code) throws Exception {
		int result = userService.gmailCheck(code);
		if (result == SUCCESS) { // 코드가 일치하면
			return new ResponseEntity<String>("인증 완료.\n", HttpStatus.OK);
		} else { // 코드가 불일치하면
			return new ResponseEntity<String>("일치하지 않는 인증코드 입니다.\n", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
