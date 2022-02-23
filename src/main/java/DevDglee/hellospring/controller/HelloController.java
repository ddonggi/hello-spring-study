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

    /* 스프링 웹개발에서 이야기하는 api
    * 정적 컨텐츠 방식(html을 웹브라우저에 넘겨주는 방식)을 제외하면
    * api 방식으로 데이터를 바로 주는방식이 많다.
    * */
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
