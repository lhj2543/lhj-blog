package com.lhj.blog.define;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UeditorConfig {

    @Value("${ueditor.attach.url:localhost}")
    public  String ueditor_attach_url;

    @Value("${ueditor.attach.path:C:/lhj-blog}")
    public  String ueditor_attach_path;

    @Value("${ueditor.congif.path}")
    public  String ueditor_config_path;

}
