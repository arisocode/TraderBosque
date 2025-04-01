package co.edu.unbosque.traderbosque.controller.TraderModule;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trader")
public class TraderController {

    @GetMapping("/market")
    public String showMarketView() {
        return "market";
    }

    @GetMapping("/menu")
    public String showMenu() {
        return "menu";
    }
}
