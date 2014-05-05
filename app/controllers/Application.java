package controllers;

import models.handlebars.TemplateManager;
import models.json.UserJSON;
import play.*;
import play.mvc.*;

import java.io.IOException;
import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() throws IOException {
        UserJSON user = new UserJSON();
        user.setName("Bob");
        user.setAge("1131");
        renderJs("app/index", user);
    }

    private static void renderJs(String template, Object context){
       renderTemplate("mainjs.html", template, context);
    }

}