package controllers;

import models.handlebars.Template;
import models.handlebars.TemplateManager;
import models.json.UserJSON;
import play.*;
import play.mvc.*;

import java.io.IOException;
import java.util.*;

import models.*;

public class Application extends BaseController {

    @Template("app/index")
    public static void index() {
        UserJSON user = new UserJSON();
        user.setName("Bob");
        user.setAge("1131");
        renderJs(user);
    }

    @Template("app/second")
    public static void second(String age){
        UserJSON user = new UserJSON();
        user.setName("John");
        user.setAge(age);
        renderJs(user);
    }

    @Template("app/list")
    public static void list(){
        List<UserJSON> result = Arrays.asList(new UserJSON(), new UserJSON());
        System.out.println(TemplateManager.INSTANCE.getActionTemplates());
        renderJs(result);
    }


}