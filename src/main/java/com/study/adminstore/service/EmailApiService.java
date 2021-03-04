package com.study.adminstore.service;

import com.study.adminstore.model.domain.Mail;
import com.study.adminstore.model.entity.Member;
import com.study.adminstore.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Service
public class EmailApiService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    JavaMailSender javaMailSender;

//    private static final String FROM_ADDRESS = "ksj@admin.com";
    private static final String FROM_ADDRESS = "nomadjin6706@gmail.com";

    public Mail createMailAndChangePassword(final String email){
        final String str = getTempPassword();
        final Mail mail = new Mail();
        mail.setAddress(email);
        mail.setTitle(email+"님의 임시비밀번호 안내 이메일 입니다.");
        mail.setMessage("안녕하세요. 임시비밀번호 안내 관련 이메일 입니다." + "[" + email + "]" +"님의 임시 비밀번호는 "
                + str + " 입니다.");
//        updatePassword(str,email);
        return mail;
    }

    public void updatePassword(final String str, final String email){
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String pw = encoder.encode(str);
        final Member member = memberRepository.findByEmail(email);
        member.setPassword(pw);
        memberRepository.save(member);
    }

    public String getTempPassword(){
        final char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    public void sendMail(final Mail mail) throws MessagingException {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress());
        message.setFrom(FROM_ADDRESS);
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());

        javaMailSender.send(message);
    }
}
