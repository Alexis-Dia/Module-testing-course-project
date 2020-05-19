package com.moduleTesting.portal.service.email;

import com.moduleTesting.portal.service.email.template.BaseEmailTemplate;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Alexey Druzik on 5/19/2020
 */
@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private static final String DELIMITER = ", ";
    private static final String EMAIL_TEMPLATE_FTL = "email-template.ftl";
    private static final String LOGO_PNG = "logo.png";
    private static final String MEMORYNOTFOUND_LOGO_PNG = "memorynotfound-logo.png";

    @Autowired
    private JavaMailSender emailSender;

    @Qualifier("freeMarkerConfiguration")
    @Autowired
    private Configuration freemarkerConfig;

    public void sendSimpleMessage(BaseEmailTemplate mail) throws MessagingException, IOException, TemplateException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
            MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
            StandardCharsets.UTF_8.name());

        helper.addAttachment(LOGO_PNG, new ClassPathResource(MEMORYNOTFOUND_LOGO_PNG));

        Template t = freemarkerConfig.getTemplate(EMAIL_TEMPLATE_FTL);

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getChildModel());

        helper.setTo(String.join(DELIMITER, mail.getChildModel().values()));
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        emailSender.send(message);
    }


}
