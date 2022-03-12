package com.overone.KursTest;

import com.overone.KursTest.user.Roles;
import com.overone.KursTest.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerMain {

    @Autowired
    public static User currentUser;

//    @GetMapping("/")
//    public String home(Model model){
//        Iterable<Product> products = productRepo.findByName("Божена");
//        model.addAttribute("List", products);
//        return "index";
//    }
//
//    @PostMapping("/hello")
//    public String hello(@RequestParam String name, @RequestParam String cost){
//        System.out.println(name + " " + cost);
//        productRepo.save(new Product(name, Double.parseDouble(cost)));
//        return "redirect:/";
//    }

    @GetMapping("/hello")
    public String greeting(@RequestParam(name="id", required = false)Integer id,
                           Model model){
        return "hello";
    }

    @GetMapping("/userInfo")
    public String user(Model model){
        return "user";
    }

    @GetMapping("/admin")
    public String administrator(Model model){
        //String answer = "";
        if(currentUser != null) {
            if(currentUser.getRole() == Roles.ADMIN) {
                //answer = "Вы вошли в систему как администатор";
                //model.addAttribute("ans", answer);
                return "admin";
            }
            else
                return "userbooking";
        }
        //else
            //answer = "Вы не вошли в систему";
        //model.addAttribute("ans", answer);
        return "hello";
    }
}
