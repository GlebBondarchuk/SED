package com.bsu.sed.service.builder;

import com.bsu.sed.model.InlineResource;
import com.bsu.sed.model.MailMessage;
import com.bsu.sed.model.MessagePriority;
import com.bsu.sed.model.persistent.User;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.EscapeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Mail Builder.
 *
 * @author gbondarchuk
 */
@Component
public class MailBuilderImpl implements MailBuilder {

    @Value("#{mailProperties.serverUrl}")
    private String serverUrl;

    @Autowired
    private VelocityEngine velocityEngine;

    @Override
    public MailMessage buildRegistrationMessage(User user) {
        MailMessage message = MailMessage.newMessage();
        Map<String, Object> model = getModel();

        message.setSubject("User Registration");
        model.put("user", user);

        InlineResource resource = new InlineResource("bsu", "/image/bsu.png");
        message.setInlineResource(resource);
        model.put("image", resource.getIdentifier());

        String emailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                "/template/registration_notification.vm", ENCODING_DEFAULT, model);


        message.setEmailBody(emailBody);
        message.setRecipients(user.getEmail());
        message.setPriority(MessagePriority.NORMAL);



        return message;
    }

    /**
     * Create default model for all messages.
     *
     * @return Model map.
     */
    private Map<String, Object> getModel() {
        Map<String, Object> model = new HashMap<>();
        model.put(EscapeTool.DEFAULT_KEY, new EscapeTool());
        model.put("serverUrl", serverUrl);
        return model;
    }
}
