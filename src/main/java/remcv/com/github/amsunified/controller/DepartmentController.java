package remcv.com.github.amsunified.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import remcv.com.github.amsunified.model.Department;
import remcv.com.github.amsunified.repository.DepartmentRepository;

import java.util.List;

@Controller
public class DepartmentController {

    // fields
    private final DepartmentRepository departmentRepository;

    // constructor
    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // methods
    @GetMapping("/departments")
    public String showDepartments(Model model) {
        List<Department> departments = departmentRepository.findAll(Sort.by("id"));
        model.addAttribute("departments", departments);

        return "department";
    }

}
