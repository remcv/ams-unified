package remcv.com.github.amsunified.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "departments_alias")
@IdClass(DepartmentAliasId.class)
public class DepartmentAlias {

    // fields
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Id
    private String alias;

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
        DepartmentAlias that = (DepartmentAlias) o;
        return Objects.equals(department, that.department) && Objects.equals(alias, that.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, alias);
    }

}
