package remcv.com.github.amsunified.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cim")
public class Cim {

    // fields
    @Id
    private String cim;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "atc", referencedColumnName = "atc"),
            @JoinColumn(name = "adm_route", referencedColumnName = "adm_route")
    })
    private Atc atc;

    @Column(name = "pharmaceutical_form")
    private String pharmaceuticalForm;

    private String container;

    @Column(name = "unit_dose")
    private Double unitDose;

    // methods
    public Atc getAtc() {
        return atc;
    }

    public void setAtc(Atc atc) {
        this.atc = atc;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getPharmaceuticalForm() {
        return pharmaceuticalForm;
    }

    public void setPharmaceuticalForm(String pharmaceuticalForm) {
        this.pharmaceuticalForm = pharmaceuticalForm;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public Double getUnitDose() {
        return unitDose;
    }

    public void setUnitDose(Double unitDose) {
        this.unitDose = unitDose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cim cim1 = (Cim) o;
        return Objects.equals(cim, cim1.cim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cim);
    }

}
