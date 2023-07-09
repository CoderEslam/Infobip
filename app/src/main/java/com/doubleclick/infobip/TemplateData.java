package com.doubleclick.infobip;

import com.doubleclick.infobip.Header;

import java.util.List;

public class TemplateData {

    private Body body;
    private List<Button> buttons;
    private Header header;

    public TemplateData(Body body, List<Button> buttons, Header header) {
        this.body = body;
        this.buttons = buttons;
        this.header = header;
    }
}
