package controllers;

import models.handlebars.TemplateManager;
import play.mvc.Controller;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Sergey Talov on 05.05.2014.
 * email: serg.talov@gmail.com
 */
public class Handlebars extends Controller {

    public static void templates() throws IOException {
        response.setContentTypeIfNotSet("text/javascript");
        Map<String, String> templates = TemplateManager.INSTANCE.getTemplates();
        StringBuilder builder = new StringBuilder("Handlebars.templates = Handlebars.templates || {};\n");
        for (Map.Entry<String, String> entry : templates.entrySet()) {
            builder.append("Handlebars.templates['").append(entry.getKey()).append("'] = ").append("Handlebars.template(").append(entry.getValue()).append(");\n");
        }
        renderText(builder.toString());
    }
}
