package jobs;

import jregex.Pattern;
import models.handlebars.Template;
import models.handlebars.TemplateManager;
import models.handlebars.TemplateType;
import play.jobs.Job;
import play.mvc.ActionInvoker;
import play.mvc.Router;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Sergey Talov on 07.05.2014.
 * email: serg.talov@gmail.com
 */

@play.jobs.OnApplicationStart
public class OnApplicationStart extends Job {

    @Override
    public void doJob() throws Exception {
        for (Router.Route route : Router.routes) {
            Object[] m = null;
            try {
                m = ActionInvoker.getActionMethod(route.action);
            } catch (Exception e){
                //do nothing
            }

            if (m == null){
                continue;
            }
            Method method = (Method) m[1];
            Template template = method.getAnnotation(Template.class);
            if (template != null && template.type() != TemplateType.USER_DEFINED ){
                Field patternField = route.getClass().getDeclaredField("pattern");
                patternField.setAccessible(true);
                Pattern patternString = (Pattern) patternField.get(route);
                TemplateManager.INSTANCE.addActionTemplate(patternString.toString().replaceAll("\\{[^}]+\\}", ""), template.value());
            }
        }
    }
}
