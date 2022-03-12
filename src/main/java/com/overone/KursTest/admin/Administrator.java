package com.overone.KursTest.admin;

import com.overone.KursTest.user.Roles;
import com.overone.KursTest.user.User;
import com.overone.KursTest.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Administrator {
    @Autowired
    UserRepository UserRepo;

    public static final int standartPrice = 100;
    public static final int luksPrice = 500;
    public static final int penthousePrice = 1200;
    public static List<Room> rooms = new ArrayList<>();
    {
        rooms.add(new Room(1, Comfort.STANDART, false, 0,  null));
        rooms.add(new Room(2, Comfort.LUKS, false, 0,  null));
        rooms.add(new Room(3, Comfort.PENTHOUSE, false, 0,  null));
        rooms.add(new Room(4, Comfort.STANDART, false, 0,  null));
        rooms.add(new Room(5, Comfort.LUKS, false, 0,  null));
        rooms.add(new Room(6, Comfort.PENTHOUSE, false, 0, null));
        rooms.add(new Room(7, Comfort.STANDART, false, 0, null));
        rooms.add(new Room(8, Comfort.LUKS, false, 0, null));
        rooms.add(new Room(9, Comfort.PENTHOUSE, false, 0, null));
    }

    @GetMapping("/addmoney")
    public String userRegister(Model model,
                               @RequestParam(name="name")String name,
                               @RequestParam(name="password")String password,
                               @RequestParam(name="money")int money){
        String answer;
        System.out.println(name + " " + password + " " + money);
        User user;
        user = UserRepo.findFirstByNameAndPassword(name, password);
        if(user != null){
            answer = "На счету у данного пользователя стало:";
            user.setMoney(user.getMoney() + money);
            answer += String.valueOf(user.getMoney());
            UserRepo.save(user);
        } else
            answer = "Такого пользователя нет";
        model.addAttribute("answer", answer);
        return "admin";
    }

    @GetMapping("/buyRoom")
    public String buyRoom(Model model,
                          @RequestParam(name="nameEnter")String name,
                          @RequestParam(name="passwordEnter")String password,
                          @RequestParam(name="roomEnter")int room,
                          @RequestParam(name="role")String numberRole){
        for (Room rm:
                rooms) {
            System.out.println(rm.toString());
        }
        User current = null;
        String answer = "Такого пользователя не существует";
        int price = 0;
        switch(numberRole){
            case "1":
                price = standartPrice;
                break;
            case "2":
                price = luksPrice;
                break;
            case "3":
                price = penthousePrice;
        }
        System.out.println(name + " " + password + " " + room);
        current = UserRepo.findFirstByNameAndPassword(name, password);
        if(current != null){
            answer = "Эта комната занята";
            for (Room rm:
                    rooms) {
                if(rm.getNumber() == room && !rm.isBooked()){
                    if(current.getMoney() > price) {
                        current.setMoney(current.getMoney()-price);
                        UserRepo.save(current);
                        rm.setCurrentUser(current);
                        rm.setBooked(true);
                        answer = "Комната " + rm.getNumber() + " оплачена";
                    }
                    else
                        answer = "У пользователя недостаточно денег";
                    break;
                }
            }
        }
        for (Room rm:
                rooms) {
            System.out.println(rm.toString());
        }
        model.addAttribute("answerBook", answer);
        return "admin";
    }

    @GetMapping("/deleteBooking")
    public String deleteBooking(Model model,
                                @RequestParam(name="delBook")int numDeletingRoom){
        String answer = "Эта комната не была забронирована или такой комнаты нет";
        for (Room rm:
                rooms) {
            if(rm.getNumber() == numDeletingRoom){
                answer = "Комната номер " + rm.getNumber() + " свободна";
                rm.setBooked(false);
                rm.setCurrentUser(null);
            }
        }
        for (Room rm:
                rooms) {
            System.out.println(rm.toString());
        }
        model.addAttribute("answerDel", answer);
        return "admin";
    }

    @GetMapping("/staffRegist")
    public String staffRegister(Model model,
                               @RequestParam(name="name")String name,
                               @RequestParam(name="surname")String surname,
                               @RequestParam(name="password")String password,
                               @RequestParam(name="role")String numberRole){
        System.out.println(name + " " + surname + " " + password + " " + numberRole);
        String answer = "";
        List<User> usr = UserRepo.findByNameAndSurname(name, surname);
        if(usr.isEmpty()) {
            UserRepo.save(new User(name, surname, password, numberRole));
            answer = "Персонал зарегистрировалан";
        }
        else
            answer = "Такой пользователь уже существует";
        model.addAttribute("answerStaff", answer);
        return "admin";
    }

    @GetMapping("/userDelete")
    public String userDeleting(Model model,
                                @RequestParam(name="name")String name,
                                @RequestParam(name="surname")String surname,
                                @RequestParam(name="role")String numberRole) {
        System.out.println(name + " " + surname + " " + numberRole);
        String answer = "";
        Roles role;
        switch (numberRole) {
            case "1":
                role = Roles.USER;
                break;
            case "2":
                role = Roles.SALER;
                break;
            case "3":
                role = Roles.ADMIN;
                break;
            default:
                role = Roles.USER;
        }
        User usr = UserRepo.findFirstByNameAndSurnameAndRole(name, surname, role);
        if(usr != null) {
            UserRepo.delete(usr);
            answer = "Пользователь удален";
        }
        else
            answer = "Такого пользователя нет";
        model.addAttribute("answerDelete", answer);
        return "admin";
    }
}
