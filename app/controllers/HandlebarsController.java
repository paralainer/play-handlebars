package controllers;

import models.handlebars.TemplateManager;
import play.mvc.Controller;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Sergey Talov on 05.05.2014.
 * email: serg.talov@gmail.com
 */
public class HandlebarsController extends Controller {

    public static void templates() throws IOException {
        response.setContentTypeIfNotSet("text/javascript");

        renderText(TemplateManager.INSTANCE.getJsTemplates());
    }
}
