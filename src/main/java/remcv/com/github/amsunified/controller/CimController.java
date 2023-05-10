package remcv.com.github.amsunified.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import remcv.com.github.amsunified.model.entity.Cim;
import remcv.com.github.amsunified.service.CimService;

import java.util.List;

@Controller
public class CimController {

    // fields
    private final CimService cimService;

    // constructor
    @Autowired
    public CimController(CimService cimService) {
        this.cimService = cimService;
    }

    // methods
    @GetMapping("/cim")
    public String showCim(Model model) {
        List<Cim> cimList = cimService.getAllCim();
        model.addAttribute("cimList", cimList);

        return "cim";
    }

}
