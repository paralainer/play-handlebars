package tags;

import groovy.lang.Closure;
import models.handlebars.TemplateManager;
import play.templates.FastTags;
import play.templates.GroovyTemplate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Sergey Talov on 05.05.2014.
 * email: serg.talov@gmail.com
 */
@FastTags.Namespace("handlebars")
public class HandlebarTags extends FastTags {

    public static void _renderJs(Map<?, ?> args, Closure body, PrintWriter out, GroovyTemplate.ExecutableTemplate template, int fromLine) throws IOException {
        Object context = args.get("context");
        String templateName = (String) args.get("template");

        out.println(TemplateManager.INSTANCE.renderTemplate(templateName, (com.google.gson.JsonObject) context));
    }
}
