package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import models.handlebars.Template;
import models.handlebars.TemplateType;
import play.mvc.Controller;

/**
 * Created by Sergey Talov on 05.05.2014.
 * email: serg.talov@gmail.com
 */
public class BaseController extends Controller {

    protected static void renderJs(Object context){
        Template template = getActionAnnotation(Template.class);
        if (template == null || template.type() == TemplateType.USER_DEFINED){
            throw new IllegalStateException("No template name specified");
        }

        renderJs(template.value(), context);
    }

    protected static void renderJs(String template, Object context) {
        if (isApiCall()) {
            renderJSON(context);
        } else {
            JsonElement contextJson = new Gson().toJsonTree(context);
            renderTemplate("mainjs.html", template, contextJson);
        }
    }

    protected static boolean isApiCall() {
        return "true".equals(request.params.get("json"));
    }
}
