package com.overone.KursTest.shop;

import com.overone.KursTest.user.Roles;
import com.overone.KursTest.user.User;
import com.overone.KursTest.ControllerMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static com.overone.KursTest.ControllerMain.currentUser;

@Controller
public class Shop {

    static List<Item> items = new ArrayList<>();
    {
        items.add(new Item("Пылесос", 10200, 3,  "https://img.5element.by/import/images/ut/goods/good_3cef0f0f-81e7-11e9-80c7-005056840c70/good_img_6f854c03-8372-11e9-80c7-005056840c70_600.jpg"));
        items.add(new Item("Ноутбук", 25300, 2,  "data:image/jpeg;base64,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"));
        items.add(new Item("Чайник", 4700, 5, "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAoHCBYVFRgUFhUYGBUaGRgaGhwYGRgcGBoaHBkdGRgYGhocIS4lHB4rIRgYJzgnLDAxNTU1GiQ7QDszPy40NTEBDAwMEA8QGBISHjQhGCE0MTQxNDQ0NDQ0NDQ0NDQ0ND80MTE0NDQ0NDQ0NDQ0PzQxNDQ0NDQ/PzQ0NDQ0MTE/P//AABEIAQ4AuwMBIgACEQEDEQH/xAAcAAABBAMBAAAAAAAAAAAAAAAABAUGBwECAwj/xABLEAACAQIDBAYFBgsGBQUAAAABAgADEQQhMQUGEkEiUWFxkbEycoGhwQcTQlKy0RQjYmNzgpKiwuHwFRYkMzSTQ1NUZLM1o8PS4v/EABcBAQEBAQAAAAAAAAAAAAAAAAABAgP/xAAeEQEBAQACAgMBAAAAAAAAAAAAARECMSFBElGRFP/aAAwDAQACEQMRAD8AuaEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCEIQCYhIpvFvnTwzmkF4nFr3PCouL2vY3OYgk1K4Ssk39qsx4iqplb5tQ3fxMxNuXLnrO/97mbR2buOfgsLix4StW3qf8v28fxid96jzJ9sItKEqj+9Y+uPEQ/vSP8AmD9oQLXhKnO9I/5i/tCYO835a+IgWzCVE29oH0x7L/Ccn34K5h6p7mcD99gsC4oSlqPyj1lJJrgi+SMqO1u1kA85M9yN91xztRZOGoq8YIvwsoIBNiSVIJGVzrJq4m0JiMu1t6MJhnCV6602IDAMG0NwDcC3Iyoe4SMDf/Zv/WU/3vukkRgQCDcHMEcxA3hCEAhCEAlR71b245Kz0CxocJ0pqlyp9E8bq9wRncBfZmJbkim/G7IxdLjQAYhASh+sNTTJ7eR5HsJkqxSm0sbWq3NTEYl+x67EfsgADwirZ1kw3GVuisVC8yMrm/eeqIqynMEEMCQQRYgg2II5EGOLJbC26iPEtxfEeEk8rfCZ4Xcn56ilZaVMh0R16ZDAMoYXyAvn1yH7a2elCr8wyur8QUAOxHETYC4Nucu/dlbYTDD8xR/8ayot989or+nT7ay4aaTutiW9Gm/7Sn+Kc6u6eOX/AIVTwv8AGWtgo6Y7ID1RGCg8XsbFICzo6qBcllsAOsxs4m+t5S2N7RehV9RpU4GcYV3pYeo2l/cItw2xazkABrn8tR5tFOz1kk2UOkO+MHKj8mmKYdJQPWqqfskxm2zuwcNWFBwnGUD3UkrYkjUgZ9E8pf8AR9Ed0rH5SE/xlJuulbwd/vjDUE/ssC+Yy6h8bxqOIq0n4kd0axF0ZlNuYupB5SVHLikY2p6Q/rqgLqW92PVeFcXWsNLuxPiTeNm0No1sQ3HWqNUawHE7FjYXsM9Bmcu2cmQi3jNLQMKxBuDJPsrf7aFDhC1y6KAAjqjrYZAXtxAdzSM2MzAvLdD5TqWJZaWIUUKrEBSCTScnQXOaE8gbjtubSxJ5NQjnpLt+S3elq6HCVm4qlNQUY6vT0sTzK3GfMHsJlSxY0IQhBCEIFYfKZurrjaK5/wDHUDUafOgdY+l2Z8jeH4qnw4Ves8J/aN/IiX4ygixFwdQZSe9CgK6qLAPYAaAA5AeyF1bm74thsOPzNL7Cynt7c9pL+nT7YlybFFsPRH5qn9hZTe8hvtNf049z3gT7Axyx4yHcI24COeP0HcIVCt6V/EVfUbylUKM5bO84/EVfUbylVAZwlPWzhJHs0ZiR3Z8keA1EC28Megp7B5SvflKX/EYc9aOPAj75YGAzpp6q+Ugvymp08Kf0g+xAgG06nCjHtHxkexjXCnsj3vCbUz6y+RjFifRp+qPKAYlugnq/xGJPnR1jxks3IwlOri8MlVVemzMGVhdTYMVBHMX4cucv9Nj4ddMPRHdTQfCQryqKg5EeM6Az1JiNh4ZxwvhqLKeTU0I94lS/KZuHTwyDF4VStPiC1KdyVQn0WS+YBORHaLWlNVvwySblY80cVRcG3DUQH1Hujj3nxjAq9G8UbPexJGoAPg4MjT1PCaI1wD1i83lYEIQgaPoe4yo9sbuYlgVydb34h6R7xe9/Ye8y2q/ot3HyjFbMSmkeD3nWlTRKlCsvAiqSApHRUC/SIPLqlYbUxavjhWzCfOcVyMwLk5hbmXfwL9UeAjdWpI/pIh71U+YkxdiIYDeLDDWsB3q6+ax0xm8uEIFsTS0+uB5xzOxMM2uGonvpp900bdnBnXC0f9tPujDUG3h2zh3pVFWvTYlGAAdSSbZDWVvxi+ol+Nurgv8ApKP7CzT+6mB/6Sh/tr90ZTVN4PGIurAR5wu2aKkXfwVj5CWgm7GCGmDw/wDtJ90U0tj4ZfRw1Be6lTH8MYaY8F8oGBREU1HZgoBC031t+UAIxb3bZ/DTS/B8NiW4C5JNPIhgNOEk8uYll4egijooo7gB5TOIXKMNUji92cdiBw/gxRSQbu6La3YxB9xmMbuHVp4d6tWql6dN2VEBN+BS1i5tyB5HvlxVYz7bp8WHrL106g8UIlxNVFuXV4MVhG/7hF9jOg/iM9JTy/u/U4alF/q16bfvKf4Z6gkWsyOb/wBPi2fiR+QD4Mp+EkcZ97afFgsSPzNQ+CE/CEeak9AzpgDme5vdY/CaoMmHfDBHpex/eh+6Rt6g2a/FRpN9ZEPioMVxq3YqcWDwzddGn9gR1lYEIQgcsR6DeqfKMY1EfMT6DeqfKMi6iWB0aNw1jg2kQQOizoZzWdGlGhmJriX4UZrgWUm5vYWF7m3KJcNjONyoIIzIsGBACoelftY+7rgLRMiYEyIHenpMV9PbM09JitpIEVQRBjEujjrUjxBji4iOoMpUeetnPZA3UyH3GeqkNwDPKlFbK46iPdcT1LgXvTRutFPiomWqURFtinxUKy/Wp1B4oRFs51VurDrBHiIR5ZX6Q7TNMJ6Y77eIYTo62Zh1EzXDemvrL5yNvRe4lTiwGGP5sD9klfhJBIp8mb32dR7DUH/uufjJXKwIQhA44n0W7j5RlTUR6xXoN3GR18WiMATncZDWWB6fSIZvVxWRsORjT+FknWXEO6zdjGgVz1nxmtZz1nxjDTniWHA3ELjhNxlmLaZ9cRYE0g5VEVCL2KgAEdEnT2d/D2ZNOJqkAxCcWeuMVNAZsDIcmK7T753TGH6x8TGCYUzlCrpGnZuILIDxG+fPtnbF4plUnI6RhrtUiSppOH9pi3SXwmKeMSoDwMD2cx7IRQjrZ6q9TsPByJ6X3ffiwuHbro0j4opnmzEi1bED85U91Yz0Zuk18DhT/wBtQ/8AGszGqeIQhCPMO16XBiaqdTuPBiI3IbNfqI+0JIN8KXDj8QPzlQ/vsR5yPNqR/XX8Jmtr9+S174K31atRfeG/ikykE+SWpfC1R1Yh/eiGTuaZrMIQhCPalTho1D1I59xlapjxxoDcXIz1Gssfbf8Ap6vqN5SraSDjT1l+0JqJU4xNSyE/knyjEmJzEcsXRHAxztwto1r5HK4zkXw9FwRnfsvflnnYHWXtOkhSrM1qsQ00a/8AI/0ZnEo/0c+vXLs0hGmLrnhNtfZ8Y1rXe54rgcsx4ZRU9CqyswAsNc/ja1u2cqezarEjocQtccYJzv8AVvaBgV+0zcYn8s+0fcZ1TYtYm1kH6+o5kAKTlN02LU+kUUd7H+EZ+2MXTns7EEoCCDrobeYinaGJYUybHl2/f5RNs/ZzBLFx1+j19t4oxtCyEcZ07Neq45QhoOJBGZAy1Onttp4RmqVXUsVNj1jQHrEkSfMgDipANb0s3N7Wvme85aWMacXiSOIKiZXAPCLkX1hpWNc3q1idS7k95e5norcs/wCAwv6CkPBQJ53xn+dV73PvvPQe4hvs/C/ol92U5xqpDCEbtubTXDUKldyLIpsDzbRV9ptKiit+SG2hXt9Z/c1j7wZFq4sx7j5Rx/CTWqvVY3LE5+25Mb6h4nPabeOUz7bXb8kP+mrH89/8aSwJEfkywXzeARiLGoz1PYTwr4qqn2yXTTNZhCEIQbb/ANPV9RvKVglvnE9ZOXaBLO27/p6vqGVkq/jE19JfC4liVK8V6Deq3lGGgcx/XKP+MFkb1W8pHqBzE3xZ5HaiYrAt43iOievtion293P+XfKjd7Ho+XblOD4LMMjMDcXN8rG1yOvrz6vHs7ZXAvnbS9uXn5xHiqD2BRLlh0gTb0RkDnkO62vOA4BMrM3Fl9IC9+R0yM513Ghy4j7DYjSx10iNaTkogUgKSbnLI9Lkc8wOrIntBW8C3sekRmL2y5WGlsxA70SMzyv7RoD75pjHYAkAAgGxOXjredMMCQADYkgDn7cj7dZtWNw4JDZHXnlkL5d0ze2p0juLqM2ZsTz4Qo06uz2xrxF7nu59vu8451cK6KWvZQTdG1HapB0PtHjG/EJxAMoJFhna+WoMUittqC2Irfr/AGby+vk6rq+zsMVINkKm3IhiCD1GUTtZD+E1F5m49pSNdPG1qXEtOtUQH0gjuoblmFIvOcdK9L7e3mw2DW9eqqta6oCDUb1UGft07ZR2+m+1XaDcIHBQU9BL59XE55t7hoOZMQIJJJJJOpJuT3k6zvTFs5dTCz5zgThGpHgJ33e2U+JxCUE9J2te3ojVnPYq3PgI3IrOwUAszEAAAkkk2AAGpPVL5+Tjc/8AAqZq1QPwmoBxDX5tNRTB69CxHMAZ2vItqZYXDrTRaaCyoqqo6lUWA8BO8ISsiEIQG7bxth6p/IMrCm/FUQ/lLz7RaWdvB/p6vqGVfh/8xPWXzEsSplj8kb1W8pG6ORHUNPL2yRY70H9VvIyM0geqaicj3Q8P60ipv619p7YjwzG17d/9eMVE5X1Hj2cppl1JAFzpreZLWN73FslyuT1g3nLEr+LeygDhJuRodQ2QvcW5c+qNmzdos7kelk2ZUjThXIlRlcXt+XGrIeKdUPdcw1tDa/8ARnHhfiyTvu17+J0++bYcuzkDhdQQCTk2l8rDMZc7WmtfEPxgKjm4BRlHEjA/WtkOefK0mmHCm5UNwgcXlcG/fEmIawJJsLHrNsuydgjhsx0TYEZZa3PI29vVNHOTHTLPUm9r/GZvazoxO71GVWs1EEXUXBcdVuU4Y8EX6IAsDbMcsjn48tItQgEMxW19CbXNjYdesRYusWPDnkLa5i+nsirFW7euuIexzyz71H3xtAjvt6nxYlgefD9kSyfk43MwVfCrXrUfnahdx02YrYNYdAHh0HMTDanmcDWw8JIt39zMZjCClFkpnWpUBRLdYuOJv1QfZPQOB2FhqOdLDUaZHNKaKfEC8c4xNRDc/cShgQH/AM3EWsajC3DfUU1+iPEnmZL4QlQQhCAQhCA2bxA/g1a2vAxy7Bf4SnUx/wCNpjojpqOV7lhl3y69o0g9Koh0ZHU9xUiUStHhdWZeIqQVPtBAI01vnlrz1gxYO1a4FJyTojaeqT8JCUxgNyHJ5XvkMu/X+ekdMVtlKtN0dCrFGGoIuRb6QF9eo6c5G/mOG54W7CCBc5WOY00Fh1dksSn3CYtuu49cjmBpkAL5ewR2w2I4yLPlmNTbO3URnr1aiMez8NxgkOVUXW9m6721BC2Gelw3UBOipYdBmIy6yOs5mxFvMjvlwSSviAD6ZyAJCk2zF7aZnyIiT+0i1tOHonMdLQk56EWOvUSYiUtwlzYDogZ8RYnsHZ5+CSgnUuWZOTcJsdbgEWHBmc/SB1FxQ+YixBKvwEjRvSBzyyOdsuzSNdIZAO6jg9LhPTJJyBByXLTKdTTVkuXN/RYWHVYpa172a33ZzomFQENk1yGNhmSOVybgXtrfP2SB9w9Y8HQXhU6k62Od+ln459kWPwovRGoz6zYRopY/p8DmwIvw3ucgb6aTpidr0wtjYC1s+EC2lu6RTWGLHMrY5kfS1B1t0c8ph2AXS9tbAdXXG8bdw9MkFw4sbJTuTxd65DQa9UZsfvDUZGWmnAhueJrFz+roJNMMG2nviSRb6Ohvyl2fJV/6en6Sr9szz7Scl7kkkm5J11noL5Kf/Tqfr1ftmRb0mcIQlQQhCAQhCAQhCByr+i3qnylKItwDLtfQ9xlMU06K90LFk19kYeqgL0kYkDO1m0+sLGVRtHZqpVKo7oOK2TH4y4MMegvqjylXbZH4/wDX+MKzQ2A5FkxLr3qGHwndt1sSihlxa9gNK1vaGjvg+UW4mk/CzBtbBRxOLCwDN6RUH0rdAm9je2UGIhW2fjFFhiKVgQf8sjMaRq/tHGcJT55OHPLg+OskuONXPLpcBGVrFgjC4uebcJGV9L2zkVYG+Wl/j29kJkdRUxTZGuoFycqY1Nr8+weAnanhq59LEtb8lFHaec50eKy5HTpaAk5ae+ONDlr7dYMOWwt2Eq8RqVq7WtkHCg3HOw7I9tujg0RmFEM3Cc3Z2zt1E290zuq3p/q/GPmLPQb1T5Qqm6agLkAO6N+PaysewxyX0Y341Lq3qmER6iekPZ5Sebp/KNUwdNcMMOjoONwxZlJuxZuRFgSRfslfJnykrwuydqVMNTVKNV8KCz0woRlBLNxEBTxelxZRBbe7Xyh0MUy0mVqNVjZQxDIx+qr5Z9hA6hcybSiN0908Y2Jos+HqU0SojuzqUACsGIHFmSbWy65e8tz0ghCEgIQhAIQhA5V2srEagE9eg6ucpyhXV1BXqzHMfy7ZcVdwqsx0CknuAvKOx+yXDcdFrE8uV/EeII7bwsW5gm6CeqPKVrttfx59b4zalvRj8MAtagHUZZ9E5aWJ4b+wN3xgx+8yO/G6MmdyGB6+tgJNXE4wfKO7+gJCcFvbhsr1Ld6k+V5vtre1CESjVUKQWdwM8iAEHEpsTcm9uU1xnyuQpVicMVCji4rKos1rHhUjkNM7530HfIoUzOfO9r5ZG9vZN6u1Sbf4knrzX3XUEfy7cktFqZPSq2HXxW+E7fz8vufrHyhwwgsOXLTn7hHCmYgc4RQtsTYn0ukxtkOXDbK/tsewzhQ23RCgs4B55N46Tly43j3jUurA3VOb9y/GP2JPQbuPlK32VvzhaBYsztcCwRc/3iJ0xvynKykUsK7XBzqMF9ygj96ZUxjSIcW4Cm5AyOsRHFYh8lUKPH36e+Zo7ON+KoxY9R0kDtsH5OsbiKdPEIKS03HEpdyG4bkcRUKe/utL13b2V+C4alh+LiKLYtawJJLNYchcmIdw6wbAYcgWsrL+w7Jfu6MkcrIhCEAhCEAhCEAhCEDRhfKV7tPdupTbiojjpg3CXs6DqUnJ16gbHlnLEiRlhdRmltWieg7cDnIpVBRu6z5H2XkF2lgqZqW4FsW+j0b/ALNpbeIwiOvC6K69TKGHgYw4nczCubhGQ9dN3S3cL8I8JDUAO6eGfVCO0EE/vgzXE/J9hgAVeoLjmU+AEmrblsv+XjKy+utN/NQffNKu7eMtYYqkwGnHQ4fsvC6rbEblIt7VX8P/ANRmO7o+ufA//aWjX3Ux7f8AEwp9lUffEI3Cxn/Mww7hVMqIFT3bXm58P5xVS3Zpcy5/Z+KmThNwMTzxNEd1Nz5uIopbgVfpYwfqUB/E5kDXu1uxhX4i1Li4bWuzjW+oUgHSSDHbJw9Ki7JRpofm3s3AvF6J+kRf3zvhNyeDI4vE2OoRkQHv4Vv74vo7oYUHian843XVZ6h8HJHuhdVRRptUPBTR6j9SKXPttkB2mSfY+4lVyGxJ+bTmiENUbsZh0UHdc90sijhVQcKKqqNAoAHgMp2FOVCjB4daaKiKFRVCqo0AAyAiiI0BHOKg0I2hCEAhCEAhCEAhCEAnBlneaEQOVpqVnfhmOGBx4YcM7cMxwQOJWY4J34JjhgceGHDO3DDhgcgs24Z0CzbhgcgkyEnThmbQNAs3CzNpmBgCZhCAQhCAQhCAQhCAQhCATEzCBi0LTMIGLQtMwgYtC0zCBi0zCEAhCEAhCEAhCEAhCEAhCED/2Q=="));

    }
    List<Item> basket = new ArrayList<>();
//    public static List<User> users = new ArrayList<>();
//    {
//        users.add(new User("Слава", "Астапович", "12345", "1", 10000));
//        users.add(new User("Анастасия", "Абаканович", "54321", "3", 30000));
//        users.add(new User("Божена", "Марчик", "12321", "4", 25000));
//    }

    @GetMapping("/shopSaler")
    public String shops(@RequestParam(name="id", required = false)Integer id,
                        Model model){
        if (id != null) {
            basket.add(items.stream().filter(item -> item.id == id).findFirst().get());
        }
        model.addAttribute("count_basket", basket.size());
        if (currentUser != null)
            model.addAttribute("userMoney", currentUser.getMoney());
        else
            model.addAttribute("userMoney", "нет денег, войдите в систему!");
        model.addAttribute("items", items);
        return "shop";
    }

    @GetMapping("/shop")
    public String bye(@RequestParam(name="id", required = false)Integer id,
                      Model model){
        if(currentUser != null) {
            if (!currentUser.getRole().equals(Roles.SALER)) {
                if (id != null) {
                    basket.add(items.stream().filter(item -> item.id == id).findFirst().get());
                }
                model.addAttribute("count_basket", basket.size());
                if (currentUser != null)
                    model.addAttribute("userMoney", currentUser.getMoney());
                else
                    model.addAttribute("userMoney", "нет денег, войдите в систему!");
                model.addAttribute("items", items);
                return "shop";
            } else {
                if (id != null) {
                    basket.add(items.stream().filter(item -> item.id == id).findFirst().get());
                }
                model.addAttribute("count_basket", basket.size());
                if (currentUser != null)
                    model.addAttribute("userMoney", currentUser.getMoney());
                else
                    model.addAttribute("userMoney", "нет денег, войдите в систему!");
                model.addAttribute("items", items);
                return "saler";
            }
        }
        return "hello";
    }

    @GetMapping("/basket")
    public String bucket(Model model){
        model.addAttribute("items", basket);
        return "basket";
    }

    @GetMapping("/buy")
    public String buy(@RequestParam(name="id", required = false)Integer id,
                      Model model) {
        String answer = "";
        if(id != null && currentUser!=null) {
            items.get(id).number--;
            if(currentUser.getMoney() >= items.get(id).price) {
                currentUser.setMoney((int) (currentUser.getMoney() - items.get(id).price));
                answer = "У Вас осталось " + currentUser.getMoney() + "$";
                basket.remove(basket.stream().filter(item -> item.id==id).findFirst().get());
                if(items.get(id).number == 0) {
                    items.remove(items.get(id));
                    int idNext = 0;
                    for (Item i:
                            items) {
                        i.id = idNext++;
                    }
                }
            } else
            {
                answer = "У Вас недостаточно денег";
            }
        }
        else
            answer = "Вы не вошли как пользователь!";
        model.addAttribute("items", basket);
        model.addAttribute("infoAnswer", answer);
        return "basket";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam(name="id", required = false)Integer id,
                         Model model) {
        if(id != null && currentUser!=null)
            basket.remove(basket.stream().filter(item -> item.id==id).findFirst().get());
        model.addAttribute("items", basket);
        return "basket";
    }
}
