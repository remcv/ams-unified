package remcv.com.github.amsunified.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import remcv.com.github.amsunified.model.Cim;
import remcv.com.github.amsunified.repository.CimRepository;

import java.util.List;

@Service
public class CimService {

    // fields
    private final CimRepository cimRepository;

    // constructor
    @Autowired
    public CimService(CimRepository cimRepository) {
        this.cimRepository = cimRepository;
    }

    // methods
    public List<Cim> getAllCim() {
        return cimRepository.findAll();
    }

}
