package com.doubleclick.infobip;

public class Content {

    private String language;
    private TemplateData templateData;
    private String templateName;

    public Content(String language, TemplateData templateData, String templateName) {
        this.language = language;
        this.templateData = templateData;
        this.templateName = templateName;
    }
}
