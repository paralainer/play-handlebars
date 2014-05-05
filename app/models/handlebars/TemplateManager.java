package models.handlebars;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import play.Play;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * Created by Sergey Talov on 05.05.2014.
 * email: serg.talov@gmail.com
 */

public class TemplateManager {

    public static final TemplateManager INSTANCE = new TemplateManager();
    public static final String PREFIX = Play.applicationPath + File.separator + "app" + File.separator + "views" + File.separator + "js" + File.separator;
    public static final String POSTFIX = ".html";

    private final Map<String, Template> templateCache = new HashMap<String, Template>();

    private final boolean useCache = Play.mode == Play.Mode.PROD;

    public String renderTemplate(String templateName, Object context) throws IOException {
        Template compiledTemplate = getTemplate(templateName);

        return compiledTemplate.apply(
                Context.newBuilder(context).resolver(new JavaBeanValueResolver()).build()
        );
    }

    public TemplateLoader getTemplateLoader() {
        return new FileTemplateLoader(
                PREFIX, POSTFIX
        );
    }

    public Map<String, String> getTemplates() throws IOException {
        final Map<String, String> result = new HashMap<String, String>();

        Files.walkFileTree(Paths.get(PREFIX), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String templateName = file.toFile().getAbsolutePath()
                        .replace(PREFIX, "")
                        .replace(POSTFIX, "")
                        .replace(File.separator, "/");
                result.put(templateName, getTemplate(templateName).toJavaScript());
                return FileVisitResult.CONTINUE;
            }
        });

        return result;
    }

    private Template getTemplate(String templateName) throws IOException {
        Template compiledTemplate;

        if (useCache) {
            compiledTemplate = templateCache.get(templateName);
            if (compiledTemplate == null) {
                compiledTemplate = compile(templateName);
                templateCache.put(templateName, compiledTemplate);
            }
        } else {
            compiledTemplate = compile(templateName);
        }
        return compiledTemplate;
    }

    private Template compile(String templateName) throws IOException {
        TemplateLoader loader = getTemplateLoader();

        Handlebars handlebars = new Handlebars(loader);

        return handlebars.compile(templateName);
    }


}
