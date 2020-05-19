package com.moduleTesting.portal.service.email.template;

import exceptions.EmailException;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Alexey Druzik on 5/19/2020
 */
public abstract class BaseEmailTemplate {

    private static final String TEMPLATES_PATH = "/email-templates";
    private static final String BASE_TEMPLATE_NAME = "base-template.ftl";
    private static final String CONTENT_KEY = "content";

    private final Configuration config;
    private final String from;
    private final Set<String> to;
    private final Set<String> cc;
    private final String subject;
    private final Map<String, String> mainModel;
    private final Map<String, String> childModel;

    BaseEmailTemplate(Configuration config, String from, Set<String> to, Set<String> cc, String subject) {

        this.config = config;
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.subject = subject;
        this.childModel = new HashMap<>();
        this.config.setClassForTemplateLoading(this.getClass(), TEMPLATES_PATH);
        this.mainModel = new HashMap<>();
    }

    public String getFrom() {
        return from;
    }

    public Set<String> getTo() {
        return to;
    }

    public Set<String> getCc() {
        return cc;
    }

    public String getSubject() {
        return subject;
    }

    public Map<String, String> getMainModel() {
        return mainModel;
    }

    public abstract String getTemplateName();

    public Map<String, String> getChildModel() {
        return childModel;
    }

    protected abstract void fillModel();

    protected void putToModel(String key, String value) {

        childModel.put(key, value);
    }

    private Template getTemplate(String templateName) throws EmailException {

        try {
            return config.getTemplate(templateName);
        } catch (IOException e) {
            throw new EmailException("Email template: " + templateName + " not found.");
        }
    }
}
