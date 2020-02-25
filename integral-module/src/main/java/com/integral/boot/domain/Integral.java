package com.integral.boot.domain;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 积分表
 */
@Table(name = "t_integral")
@Entity
public class Integral implements Serializable {
    private static final long serialVersionUID = -2367531355825273070L;
    /**
     * 积分主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IntegralID;

    /**
     * 积分名
     */
    @Column
    private String IntegralName;

    /**
     * 积分 类型  多对多
     */

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "integral_type",
            joinColumns = {@JoinColumn(name = "typeId")},
            inverseJoinColumns={@JoinColumn(name = "IntegralID")})
    private List<IntegralType> integralTypes;

    /**
     * 积分值
     */
    @Column
    private Integer gradeCount;

    /**
     * 客户积分 多对一
     */
    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name="customer_id")
    private Customer customer;

    /**
     * 积分描述
     */
    private String description;

    /**
     * 积分创建时间
     */
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Long getIntegralID() {
        return IntegralID;
    }

    public void setIntegralID(Long integralID) {
        IntegralID = integralID;
    }

    public String getIntegralName() {
        return IntegralName;
    }

    public void setIntegralName(String integralName) {
        IntegralName = integralName;
    }

    public List<IntegralType> getIntegralTypes() {
        return integralTypes;
    }

    public void setIntegralTypes(List<IntegralType> integralTypes) {
        this.integralTypes = integralTypes;
    }

    public Integer getGradeCount() {
        return gradeCount;
    }

    public void setGradeCount(Integer gradeCount) {
        this.gradeCount = gradeCount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
