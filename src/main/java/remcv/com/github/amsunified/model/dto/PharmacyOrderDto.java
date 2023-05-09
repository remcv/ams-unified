package remcv.com.github.amsunified.model.dto;

import java.time.LocalDate;
import java.util.Objects;

public class PharmacyOrderDto {

    // fields
    private String hospitalizationType;
    private String orderNumber;
    private String fo;
    private String cnp;
    private LocalDate orderDate;
    private String departmentAlias;
    private String cim;
    private String physicianId;
    private Double quantity;

    // methods
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

    public String getDepartmentAlias() {
        return departmentAlias;
    }

    public void setDepartmentAlias(String departmentAlias) {
        this.departmentAlias = departmentAlias;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
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
        PharmacyOrderDto that = (PharmacyOrderDto) o;
        return Objects.equals(hospitalizationType, that.hospitalizationType) && Objects.equals(orderNumber, that.orderNumber) && Objects.equals(fo, that.fo) && Objects.equals(cnp, that.cnp) && Objects.equals(orderDate, that.orderDate) && Objects.equals(departmentAlias, that.departmentAlias) && Objects.equals(cim, that.cim) && Objects.equals(physicianId, that.physicianId) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hospitalizationType, orderNumber, fo, cnp, orderDate, departmentAlias, cim, physicianId, quantity);
    }

}
