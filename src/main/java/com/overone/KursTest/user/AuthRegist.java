package com.overone.KursTest.user;

import com.overone.KursTest.ControllerMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthRegist {

    @Autowired
    UserRepository UserRepo;

    @GetMapping("/userRegister")
    public String userRegister(Model model,
                               @RequestParam(name="name")String name,
                               @RequestParam(name="surname")String surname,
                               @RequestParam(name="password")String password,
                               @RequestParam(name="role")String numberRole){
        System.out.println(name + " " + surname + " " + password + " " + numberRole);
        String answer = "";
        List<User> usr = UserRepo.findByNameAndSurname(name, surname);
        if(usr.isEmpty()) {
            UserRepo.save(new User(name, surname, password, numberRole));
            answer = "Вы успешно зарегистрировались";
        }
        else
            answer = "Такой пользователь уже существует";
        model.addAttribute("greeting", answer);
        return "user";
    }

    @GetMapping("/userEnter")
    public String userEnter(Model model,
                            @RequestParam(name="nameEnter")String name,
                            @RequestParam(name="passwordEnter")String password){
        System.out.println(name + " " + password);
        String answer = "";
        User user;
        user = UserRepo.findFirstByNameAndPassword(name, password);
        if(user != null){
            answer = "Вы успешно вошли!";
            ControllerMain.currentUser = user;
        } else
            answer = "Такого пользователя нет";
        model.addAttribute("greeting", answer);
        return "user";
    }
}
