package remcv.com.github.amsunified.model;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

public class DepartmentAliasId implements Serializable {

    // fields
    private Department department;
    private String alias;

    // constructor
    public DepartmentAliasId(Department department, String alias) {
        this.department = department;
        this.alias = alias;
    }

    public DepartmentAliasId() {
    }

    // methods
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentAliasId that = (DepartmentAliasId) o;
        return Objects.equals(department, that.department) && Objects.equals(alias, that.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, alias);
    }

}
