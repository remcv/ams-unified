package remcv.com.github.amsunified.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import remcv.com.github.amsunified.model.entity.PharmacyOrder;
import remcv.com.github.amsunified.service.PharmacyOrderService;

import java.util.List;
import java.util.Set;

@Controller
public class PharmacyOrderController {

    // fields
    private final PharmacyOrderService pharmacyOrderService;

    // constructor
    @Autowired
    public PharmacyOrderController(PharmacyOrderService pharmacyOrderService) {
        this.pharmacyOrderService = pharmacyOrderService;
    }

    // methods
    @GetMapping("/orders")
    public String showOrders(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "25") int size,
            @RequestParam(name = "sortField", required = false, defaultValue = "orderDate") String sortField,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "desc") String sortOrder,
            Model model
    ) {
        List<PharmacyOrder> orders = pharmacyOrderService.findOrders(page, size, sortField, sortOrder);
        model.addAttribute("orders", orders);

        return "orders";
    }

    @PostMapping("/orders")
    public String addMultipleOrders(
            @RequestParam(name = "filePath") String filePath,
            @RequestParam(name = "delim", required = false, defaultValue = "\t") String delim,
            @RequestParam(name = "dateFormat", required = false, defaultValue = "dd.MM.YYYY") String dateFormat,
            Model model
    ) {
        Set<String> newCims = pharmacyOrderService.addOrdersFromCsv(filePath, delim, dateFormat);
        model.addAttribute("newCims", newCims);

        return "newOrders";
    }

}
