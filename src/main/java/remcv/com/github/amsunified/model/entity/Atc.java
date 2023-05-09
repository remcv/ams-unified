package remcv.com.github.amsunified.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "atc")
@IdClass(AtcId.class)
public class Atc {

    // fields
    @Id
    private String atc;

    @Id
    private String admRoute;

    private String inn;
    private Double ddd;

    @Column(name = "ddd_update_date")
    private LocalDate dddUpdateDate;

    @ManyToOne
    @JoinColumn(name = "measurement_unit_id")
    private MeasurementUnit measurementUnit;

    @Column(name = "antibiotic_class")
    private String antibioticClass;

    @Column(name = "has_resistance_risk")
    private Boolean hasResistanceRisk;

    @Column(name = "has_cdi_risk")
    private Boolean hasCdiRisk;

    private String aware;

    // methods
    public String getAtc() {
        return atc;
    }

    public void setAtc(String atc) {
        this.atc = atc;
    }

    public String getAdmRoute() {
        return admRoute;
    }

    public void setAdmRoute(String admRoute) {
        this.admRoute = admRoute;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Double getDdd() {
        return ddd;
    }

    public void setDdd(Double ddd) {
        this.ddd = ddd;
    }

    public LocalDate getDddUpdateDate() {
        return dddUpdateDate;
    }

    public void setDddUpdateDate(LocalDate dddUpdateDate) {
        this.dddUpdateDate = dddUpdateDate;
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public String getAntibioticClass() {
        return antibioticClass;
    }

    public void setAntibioticClass(String antibioticClass) {
        this.antibioticClass = antibioticClass;
    }

    public Boolean getHasResistanceRisk() {
        return hasResistanceRisk;
    }

    public void setHasResistanceRisk(Boolean hasResistanceRisk) {
        this.hasResistanceRisk = hasResistanceRisk;
    }

    public Boolean getHasCdiRisk() {
        return hasCdiRisk;
    }

    public void setHasCdiRisk(Boolean hasCdiRisk) {
        this.hasCdiRisk = hasCdiRisk;
    }

    public String getAware() {
        return aware;
    }

    public void setAware(String aware) {
        this.aware = aware;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atc atc1 = (Atc) o;
        return Objects.equals(atc, atc1.atc) && Objects.equals(admRoute, atc1.admRoute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atc, admRoute);
    }

}
