package controllers;

import models.handlebars.TemplateManager;
import models.json.UserJSON;
import play.*;
import play.mvc.*;

import java.io.IOException;
import java.util.*;

import models.*;

public class Application extends BaseController {

    public static void index() {
        UserJSON user = new UserJSON();
        user.setName("Bob");
        user.setAge("1131");
        renderJs("app/index", user);
    }

    public static void second(){
        UserJSON user = new UserJSON();
        user.setName("John");
        user.setAge("");
        renderJs("app/second", user);
    }

    public static void list(){
        List<UserJSON> result = Arrays.asList(new UserJSON(), new UserJSON());
        renderJs("app/list", result);
    }


}