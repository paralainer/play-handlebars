package models.handlebars;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Sergey Talov on 07.05.2014.
 * email: serg.talov@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Template {
    TemplateType type() default TemplateType.PREDIFINED;
    String value();
}
