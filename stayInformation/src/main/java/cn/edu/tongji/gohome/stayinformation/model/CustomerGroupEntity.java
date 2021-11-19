package cn.edu.tongji.gohome.stayinformation.model;

import javax.persistence.*;

/**
 * TODO:此处写CustomerGroupEntity类的描述
 *
 * @author 汪明杰
 * @date 2021/11/19 17:17
 */
@Entity
@Table(name = "customer_group", schema = "GoHome", catalog = "")
public class CustomerGroupEntity {
    private int customerLevel;
    private String customerLevelName;
    private long customerLevelDegree;

    @Id
    @Column(name = "customer_level")
    public int getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(int customerLevel) {
        this.customerLevel = customerLevel;
    }

    @Basic
    @Column(name = "customer_level_name")
    public String getCustomerLevelName() {
        return customerLevelName;
    }

    public void setCustomerLevelName(String customerLevelName) {
        this.customerLevelName = customerLevelName;
    }

    @Basic
    @Column(name = "customer_level_degree")
    public long getCustomerLevelDegree() {
        return customerLevelDegree;
    }

    public void setCustomerLevelDegree(long customerLevelDegree) {
        this.customerLevelDegree = customerLevelDegree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerGroupEntity that = (CustomerGroupEntity) o;

        if (customerLevel != that.customerLevel) return false;
        if (customerLevelDegree != that.customerLevelDegree) return false;
        if (customerLevelName != null ? !customerLevelName.equals(that.customerLevelName) : that.customerLevelName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerLevel;
        result = 31 * result + (customerLevelName != null ? customerLevelName.hashCode() : 0);
        result = 31 * result + (int) (customerLevelDegree ^ (customerLevelDegree >>> 32));
        return result;
    }
}
