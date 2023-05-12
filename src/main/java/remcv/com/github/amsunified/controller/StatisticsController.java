package remcv.com.github.amsunified.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import remcv.com.github.amsunified.model.entity.HospitalTotalYearlyView;
import remcv.com.github.amsunified.repository.HospitalTotalYearlyViewRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StatisticsController {
    
    // fields
    private final HospitalTotalYearlyViewRepository hospitalTotalYearlyViewRepository;

    // constructor
    @Autowired
    public StatisticsController(HospitalTotalYearlyViewRepository hospitalTotalYearlyViewRepository) {
        this.hospitalTotalYearlyViewRepository = hospitalTotalYearlyViewRepository;
    }

    // methods
    @GetMapping("/statistics")
    public String getStatistics(Model model) {
        // get data
        List<HospitalTotalYearlyView> hospitalTotalYearly = hospitalTotalYearlyViewRepository.findAll();

        // add data to model
        model.addAttribute("hospitalTotalYearly", transformHty(hospitalTotalYearly));

        return "statistics";
    }

    private List<List<Object>> transformHty(List<HospitalTotalYearlyView> hospitalTotalYearlyViewList) {
        List<List<Object>> tempList = new ArrayList<>();

        for (HospitalTotalYearlyView item : hospitalTotalYearlyViewList) {
            List<Object> innnerList = List.of(item.getOrderYear(), item.getDddPer100Obd());
            tempList.add(innnerList);
        }

        return tempList;
    }

}
