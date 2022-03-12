package com.overone.KursTest.booking;

import com.overone.KursTest.ControllerMain;
import com.overone.KursTest.admin.Administrator;
import com.overone.KursTest.admin.Comfort;
import com.overone.KursTest.admin.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookingOnline {

    @GetMapping("/bookRoom")
    public String buyRoom(Model model,
                          @RequestParam(name="nameEnter")String name,
                          @RequestParam(name="roomDays")int days,
                          @RequestParam(name="role")String numberRole){
//        for (Room rm:
//                Administrator.rooms) {
//            System.out.println(rm.toString());
//        }
        String answer = "";
        Comfort comfort = Comfort.STANDART;
        switch(numberRole){
            case "1":
                comfort = Comfort.STANDART;
                break;
            case "2":
                comfort = Comfort.LUKS;
                break;
            case "3":
                comfort = Comfort.PENTHOUSE;
        }
        for (Room rm:
                Administrator.rooms) {
            if(rm.getComfort() == comfort && !rm.isBooked()){
                int price = 0;
                switch(numberRole){
                    case "1":
                        price = Administrator.standartPrice;
                        break;
                    case "2":
                        price = Administrator.luksPrice;
                        break;
                    case "3":
                        price = Administrator.penthousePrice;
                }
                if(ControllerMain.currentUser.getMoney() > price) {
                    ControllerMain.currentUser.setMoney(ControllerMain.currentUser.getMoney()-price*days);
                    rm.setCurrentUser(ControllerMain.currentUser);
                    rm.setBooked(true);
                    answer = "Комната " + rm.getNumber() + " забронирована";
                }
                else
                    answer = "У пользователя недостаточно денег";
                break;
            }
        }

//        for (Room rm:
//                Administrator.rooms) {
//            System.out.println(rm.toString());
//        }
        model.addAttribute("answerBookOnline", answer);
        return "userbooking";
    }
}