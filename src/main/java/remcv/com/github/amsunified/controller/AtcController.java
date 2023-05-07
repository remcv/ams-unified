package remcv.com.github.amsunified.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import remcv.com.github.amsunified.model.Atc;
import remcv.com.github.amsunified.repository.AtcRepository;

import java.util.List;

@Controller
public class AtcController {

    // fields
    private final AtcRepository atcRepository;

    // constructor
    @Autowired
    public AtcController(AtcRepository atcRepository) {
        this.atcRepository = atcRepository;
    }

    // methods
    @GetMapping("/atc")
    public String showAtc(Model model) {
        List<Atc> atcList = atcRepository.findAll();
        model.addAttribute("atcList", atcList);

        return "atc";
    }

}
