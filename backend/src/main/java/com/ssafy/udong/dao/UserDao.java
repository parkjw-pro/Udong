package com.ssafy.udong.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.udong.dto.UserDto;

@Mapper
public interface UserDao {
	
	public UserDto login(UserDto userDto) throws SQLException;
	
	// 회원탈퇴
	public int deleteUser(UserDto userDto) throws SQLException;
	//회원가입 수정
	public int insertUser(UserDto userDto) throws SQLException;
	public int updateUser(UserDto userDto) throws SQLException;
	// 비밀번호 찾기 (해당아뒤 비번있나조회)
	public UserDto selectUser(UserDto userDto) throws SQLException;
	//비밀번호 찾기(비밀번호 재설정)
	public int updatePassword(UserDto userDto) throws SQLException;
	// 전체 유저 조회
	public List<UserDto> selectUserAll() throws SQLException;
	// 아이디 중복체크
	
	public String selectDuplicateID(String userId) throws SQLException;
	
	//닉네임 중복체크
	public String selectDuplicateNickname(String nickname) throws SQLException;
	
	//이메일 인증
	public String selectDuplicateEmail(String email) throws SQLException;

	public void createUserAddress(String dongcode);
}

