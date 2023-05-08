package remcv.com.github.amsunified.model;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;

public class AtcId implements Serializable {

    // fields
    @Column(name = "atc")
    private String atc;

    @Column(name = "adm_route")
    private String admRoute;

    // constructors
    public AtcId(String atc, String admRoute) {
        this.atc = atc;
        this.admRoute = admRoute;
    }

    public AtcId() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtcId atcId = (AtcId) o;
        return Objects.equals(atc, atcId.atc) && Objects.equals(admRoute, atcId.admRoute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atc, admRoute);
    }

}
