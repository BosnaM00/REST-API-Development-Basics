package com.example.microservicescourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //We use @Controller to make a Java class as a Spring MVC Controller

//All rest apis return json to the client
@ResponseBody // - @ResponseBody convert java object into json.
              // - The @ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON
              // and passed back into the HttpResponse object.

//@RestController // @RestController annotation is the combination of @Controller and @ResponseBody
public class HelloWorldController {

    @GetMapping("/hello-world") //We use @GetMapping annotation to map HTTP GET request onto specific handler method
                                   //http://localhost:8080/hello-world
    public String helloWorld(){
        return "Hello world!";
    }

}
