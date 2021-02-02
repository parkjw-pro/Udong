package com.ssafy.udong.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.udong.dao.UserDao;
import com.ssafy.udong.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	private static String code;

	@Autowired
	UserDao userDao;

	@Override
	public UserDto login(UserDto userDto) throws Exception {
		try {
<<<<<<< HEAD
			//return sqlSession.getMapper(UserDao.class).login(userDto);
=======
			// return sqlSession.getMapper(UserDao.class).login(userDto);
>>>>>>> 957186f5317204bf0dcb340e09212b0079d0118f
			UserDto dto = new UserDto();
			SecurityUtil securityUtil = new SecurityUtil();
			userDto.setPassword(securityUtil.encryptSHA256(userDto.getPassword()));
			System.out.println(userDto.getPassword());
<<<<<<< HEAD
			dto = dao.login(userDto);
			if( dto.getUserState() == null) { // 정지상태
				return dto;
			}else {
				return null;
			}
			
=======
			dto = userDao.login(userDto);
			if (dto.getUserState() == null) { // 정지상태
				return dto;
			} else {
				return null;
			}

>>>>>>> 957186f5317204bf0dcb340e09212b0079d0118f
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserDto> selectUserAll() throws Exception {
		try {
			return userDao.selectUserAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserDto selectUser(UserDto userDto) throws Exception {
		try {
<<<<<<< HEAD
			
			return dao.selectUser(userDto);
=======

			return userDao.selectUser(userDto);
>>>>>>> 957186f5317204bf0dcb340e09212b0079d0118f
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteUser(UserDto userDto) throws Exception {
		try {
			userDto.setUserState("1");
			return userDao.deleteUser(userDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insertUser(UserDto userDto) throws Exception {
		try {
			SecurityUtil securityUtil = new SecurityUtil();
			userDto.setPassword(securityUtil.encryptSHA256(userDto.getPassword()));

<<<<<<< HEAD
			return dao.insertUser(userDto);
=======
			return userDao.insertUser(userDto);
>>>>>>> 957186f5317204bf0dcb340e09212b0079d0118f
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateUser(UserDto userDto) throws Exception {
<<<<<<< HEAD
		
=======

>>>>>>> 957186f5317204bf0dcb340e09212b0079d0118f
		try {
			SecurityUtil securityUtil = new SecurityUtil();
			userDto.setPassword(securityUtil.encryptSHA256(userDto.getPassword()));

<<<<<<< HEAD
			return dao.updateUser(userDto);
=======
			return userDao.updateUser(userDto);
>>>>>>> 957186f5317204bf0dcb340e09212b0079d0118f
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String selectDuplicateID(String userId) throws Exception {
		try {
			return userDao.selectDuplicateID(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public String selectDuplicateNickname(String nickname) throws SQLException {
		try {
			return userDao.selectDuplicateNickname(nickname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public String selectDuplicateEmail(String email) throws Exception {
		try {
			return userDao.selectDuplicateEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void gmailSend(String email) throws Exception {
		String user = "youngug159@gmail.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
<<<<<<< HEAD
		String password = "d1n7qkqh12";   // 패스워드
=======
		String password = "d1n7qkqh12"; // 패스워드
>>>>>>> 957186f5317204bf0dcb340e09212b0079d0118f

		// SMTP 서버 정보를 설정한다.
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));

			// 수신자메일주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

			// Subject
			message.setSubject("우동의 메일인증입니다."); // 메일 제목을 입력

			// Text
			RandomCode rc = new RandomCode();
			String code = rc.randomcodes();
			this.code = code;
			message.setText("인증 코드 : " + code); // 메일 내용을 입력

			// send the message
			Transport.send(message); //// 전송
			System.out.println("message sent successfully...");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int gmailCheck(String checkCode) throws Exception {
		// this.code = "a";
		try {
<<<<<<< HEAD
			if(checkCode.equals(this	.code)) {
=======
			if (checkCode.equals(this.code)) {
>>>>>>> 957186f5317204bf0dcb340e09212b0079d0118f
				return 1;
			} else {
				return 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updatePassword(UserDto userDto) throws Exception {
		try {
			SecurityUtil securityUtil = new SecurityUtil();
			userDto.setPassword(securityUtil.encryptSHA256(userDto.getPassword()));

			return userDao.updatePassword(userDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
