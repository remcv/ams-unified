package remcv.com.github.amsunified.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import remcv.com.github.amsunified.model.entity.DepartmentAlias;
import remcv.com.github.amsunified.repository.DepartmentAliasRepository;

import java.util.List;

@Controller
public class DepartmentAliasController {

    // fields
    private final DepartmentAliasRepository departmentAliasRepository;

    // constructor
    @Autowired
    public DepartmentAliasController(DepartmentAliasRepository departmentAliasRepository) {
        this.departmentAliasRepository = departmentAliasRepository;
    }

    // methods
    @GetMapping("/departmentAlias")
    public String showDepartmentAliases(Model model) {
        List<DepartmentAlias> departmentAliases = departmentAliasRepository.findAll(Sort.by("department.fullName", "alias"));
        model.addAttribute("departmentAliases", departmentAliases);

        return "departmentAlias";
    }

}
