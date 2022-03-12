package com.overone.KursTest.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Saler {

    @GetMapping("/addItem")
    public String addItem(Model model,
                          @RequestParam(name="nameEnter")String name,
                          @RequestParam(name="priceEnter")String price,
                          @RequestParam(name="urlEnter")String url,
                          @RequestParam(name="numberEnter")String number){
        Item item = new Item(name, Integer.parseInt(price), Integer.parseInt(number), url);
        Shop.items.add(item);
        String answer = "Вы успешно добавили предмет!";
        int idNext = 0;
        for (Item i :
                Shop.items) {
            i.id = idNext++;
        }
        model.addAttribute("answerAdding", answer);
        return "saler";
    }

    @GetMapping("/deleteItem")
    public String addItem(Model model,
                          @RequestParam(name="nameEnter")String name) {
        String answer = "";
        if (Shop.items.removeIf(item -> item.name.equals(name))) {
            answer = "Вы успешно удалили предмет!";
            int idNext = 0;
            for (Item i :
                    Shop.items) {
                i.id = idNext++;
            }
        }
        else
            answer = "Такого предмета нет!";
        model.addAttribute("answerDeleting", answer);
        return "saler";
    }
}
