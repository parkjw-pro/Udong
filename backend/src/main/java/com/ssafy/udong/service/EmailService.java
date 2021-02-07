package com.ssafy.udong.service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ssafy.udong.dto.UserDto;

@Service
public class EmailService {

	private JavaMailSender javaMailSender;
	private static String code;

	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}


	public void sendMail(UserDto userDto) throws MessagingException {
		StringBuffer emailcontent = new StringBuffer();
		// Text 랜덤 코듭라생
		RandomCode rc = new RandomCode();
		String c = rc.randomcodes();
		this.code = c;
		System.out.println("들어오는 이메일 : "+userDto.getEmail());
		emailcontent.append("<!DOCTYPE html>");
		emailcontent.append("<html>");
		emailcontent.append("<head>");
		emailcontent.append("</head>");
		emailcontent.append("<body>");
		//	     if(isMailExisted==0) {
		emailcontent.append(
				" <div"                                                                                                                                                                                                     + 
						"    style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid #9932CC; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"        + 
						"    <h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">"                                                                                                                            + 
						"        <span style=\"font-size: 15px; margin: 0 0 10px 3px;\">우동</span><br />"                                                                                                                    + 
						"        <span style=\"color: #9932CC\">메일인증</span> 안내입니다."                                                                                                                                                + 
						"    </h1>\n"                                                                                                                                                                                                + 
						"    <p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">"                                                                                                                    + 
						"        반려동물과의 일상 이야기 [우동]에 가입해 주셔서 진심으로 감사드립니다.<br />"                                                                                                                                                        + 
						"        아래 <b style=\"color: #9932CC\">"+c +"</b> 를 입력하여 회원가입을 완료해 주세요.<br />"                                                                                                                    + 
						"        감사합니다."                                                                                                                                                                                            + 
						"    </p>"                                                                                                                                                                                                    + 
						"    <a style=\"color: #FFF; text-decoration: none; text-align: center;\""                                                                                                                                    +
						"        <div"                                                                                                                                                                                                    +
						"            style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: #9932CC; line-height: 45px; vertical-align: middle; color: white; font-size: 16px;\">"                            + 
						""+"</div>"                                                                                                                                                                                        + 
						"    </a>"                                                                                                                                                                                                    +
						"    <div style=\"border-top: 4px solid #9932CC; padding: 5px;\"></div>"                                                                                                                                        +
						" </div>"
				);
		//	    }
		//	    else if(isMailExisted==1) { //비밀번호 찾기 위한 인증이면!
		//	        emailcontent.append(
		//	                " <div"                                                                                                                                                                                                     + 
		//	                "    style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid #9932CC; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"        + 
		//	                "    <h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">"                                                                                                                            + 
		//	                "        <span style=\"font-size: 15px; margin: 0 0 10px 3px;\">PETMILY</span><br />"                                                                                                                    + 
		//	                "        <span style=\"color: #9932CC\">메일인증</span> 안내입니다."                                                                                                                                                + 
		//	                "    </h1>\n"                                                                                                                                                                                                + 
		//	                "    <p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">"                                                                                                                    + 
		//	                "        반려동물과의 일상 이야기 [PETMILY]입니다.<br />"                                                                                                                                                        + 
		//	                "        아래 <b style=\"color: #9932CC\">인증코드</b> 를 입력하여 비밀번호 갱신을 완료해 주세요.<br />"                                                                                                                    + 
		//	                "        감사합니다."                                                                                                                                                                                            + 
		//	                "    </p>"                                                                                                                                                                                                    + 
		//	                "    <a style=\"color: #FFF; text-decoration: none; text-align: center;\""                                                                                                                                    +
		//	                "        <div"                                                                                                                                                                                                    +
		//	                "            style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: #9932CC; line-height: 45px; vertical-align: middle; color: white; font-size: 16px;\">"                            + 
		//	                ""+certified+"</div>"                                                                                                                                                                                        + 
		//	                "    </a>"                                                                                                                                                                                                    +
		//	                "    <div style=\"border-top: 4px solid #9932CC; padding: 5px;\"></div>"                                                                                                                                        +
		//	                " </div>"
		//	        );
		//	    }
		emailcontent.append("</body>");
		emailcontent.append("</html>");

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		System.out.println("받는사람 : "+ userDto.getEmail());
		helper.setFrom("Udong"); //보내는사람
		helper.setTo(userDto.getEmail() ); //받는사람
		helper.setSubject("[udong 이메일 인증]"); //메일제목
		helper.setText(emailcontent.toString(), true); //ture넣을경우 html형식으로 보낼 수 있음(controller에 작성함)


		javaMailSender.send(mimeMessage);
	}
	public int gmailCheck(String checkCode) throws Exception {
		// this.code = "a";
		try {

			if (checkCode.equals(this.code)) {

				return 1;
			} else {
				return 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}