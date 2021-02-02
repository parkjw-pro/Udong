package com.ssafy.udong.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.udong.dto.UserDto;

public interface UserService {


	public UserDto login(UserDto userDto) throws Exception;


	// 회원 탈퇴 가입 수정 
	public int deleteUser(String userId) throws Exception;
	public int insertUser(UserDto userDto) throws Exception;
	public int updateUser(UserDto userDto) throws Exception;

	// 비밀번호 찾기 (부분 유저 조회)
	public UserDto selectUser(UserDto userDto) throws Exception;
	public int updatePassword(UserDto userDto)throws Exception;
	//전체 유저 조회
	public List<UserDto> selectUserAll() throws Exception;
	// 아이디 중복체크

	public String selectDuplicateID(String userId) throws Exception;

	//닉네임 중복체크
	public String selectDuplicateNickname(String nickname) throws Exception;
	

	//이메일 인증
	public String selectDuplicateEmail(String email) throws Exception;
	public void gmailSend(String email) throws Exception;
	//이메일 인증 확인
	public int gmailCheck(String checkCode) throws Exception;
}
