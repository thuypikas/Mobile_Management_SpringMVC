package models;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service("mailer2")
public class Mailer {
	@Autowired
	JavaMailSenderImpl mailer;

	public void send(String to) {
		try {
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true,
					"utf-8");
			helper.setFrom(mailer.getUsername(), "BestShop");
			helper.setTo(to);
			helper.setReplyTo(mailer.getUsername(), "BestShop");
			helper.setSubject("Xac nhan don hang");
			helper.setText("Xac nhan don hang", true);
			mailer.send(mail);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	public void send2(String to) {
		try {
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true,
					"utf-8");
			helper.setFrom(mailer.getUsername(), "BestShop");
			helper.setTo(to);
			helper.setReplyTo(mailer.getUsername(), "BestShop");
			helper.setSubject("Đăng ký thành công");
			helper.setText("Chào mừng bạn đến với cửa hàng của chúng tôi", true);
			mailer.send(mail);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
}