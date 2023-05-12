package remcv.com.github.amsunified.model.entity;

import org.springframework.data.annotation.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hospital_total_yearly")
@Immutable
public class HospitalTotalYearlyView {
    
    // fields
    @Id
    @Column(name = "order_year")
    private Integer orderYear;

    @Column(name = "ddd_per100obd")
    private Double dddPer100Obd;

    // methods
    public Integer getOrderYear() {
        return orderYear;
    }

    public Double getDddPer100Obd() {
        return dddPer100Obd;
    }

}
