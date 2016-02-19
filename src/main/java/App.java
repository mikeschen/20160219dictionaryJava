import java.util.HashMap;
import java.util.ArrayList;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String [] args){
    staticFileLocation("/public");
    ProcessBuilder process = new ProcessBuilder();
    Integer port;
    if (process.environment().get("PORT") != null) {
    port = Integer.parseInt(process.environment().get("PORT"));
    } else {
      port = 4567;
    }
      
    setPort(port);
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String,Object>model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      model.put("words", Word.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/example", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/example.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/definitions", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("definitions", Definition.all());
      model.put("template", "templates/definitions.vtl");
      return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

    get("definitions/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/definition-form.vtl");
      return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

    post("/definitions", (request,response) -> {
      HashMap<String, Object>model = new HashMap<String, Object>();
      Word word = Word.find(Integer.parseInt(request.queryParams("wordId")));
      String description = request.queryParams("description");
      Definition newDefinition = new Definition(description);
      word.addDefinition(newDefinition);
      model.put("word", word);
      model.put("template", "templates/word.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

   get("/definitions/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Definition definition = Definition.find(Integer.parseInt(request.params(":id")));
      model.put("definition", definition);
      model.put("template", "templates/definition.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/word-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request,response) -> {
      HashMap<String, Object>model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Word newWord = new Word(name);
      model.put("word", newWord);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("word", Word.find(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/word.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/:id/definitions/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("word", Word.find(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/word-definition-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
