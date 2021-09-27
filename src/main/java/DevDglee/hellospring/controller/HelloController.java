package DevDglee.hellospring.controller;
/*
 * Created by 이동기 on 2021-08-31
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello~!!동기");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam(value = "name",required = false,defaultValue = "기동이")String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam(value = "name")String name){
        return "hello"+name; //"hello name"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloAPI(@RequestParam("name")String name,@RequestParam("id")int id){
        Hello hello = new Hello();
        hello.setName(name);
        hello.setId(id);
        return hello;
    }

    static class Hello{
        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
