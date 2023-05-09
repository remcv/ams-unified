package remcv.com.github.amsunified.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "pharmacy_orders")
public class PharmacyOrder {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hospitalization_type")
    private String hospitalizationType;

    @Column(name = "order_number")
    private String orderNumber;

    private String fo;
    private String cnp;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "department_alias", referencedColumnName = "alias")
    private DepartmentAlias departmentAlias;

    @ManyToOne
    @JoinColumn(name = "cim")
    private Cim cim;

    @Column(name = "physician_id")
    private String physicianId;

    private Double quantity;

    // methods
    public DepartmentAlias getDepartmentAlias() {
        return departmentAlias;
    }

    public Cim getCim() {
        return cim;
    }

    public void setCim(Cim cim) {
        this.cim = cim;
    }

    public void setDepartmentAlias(DepartmentAlias departmentAlias) {
        this.departmentAlias = departmentAlias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHospitalizationType() {
        return hospitalizationType;
    }

    public void setHospitalizationType(String hospitalizationType) {
        this.hospitalizationType = hospitalizationType;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getFo() {
        return fo;
    }

    public void setFo(String fo) {
        this.fo = fo;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(String physicianId) {
        this.physicianId = physicianId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmacyOrder that = (PharmacyOrder) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
