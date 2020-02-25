package com.integral.boot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_integral_type")
@Entity
@Getter
@Setter
@ToString
public class IntegralType implements Serializable {

    private static final long serialVersionUID = 4297984373060624523L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    /**
     * 积分类型名
     */
    @Column(length = 128)
    private String typeName;

    /**
     * 积分描述
     */
    @Column
    private String typeExplain;

    /**
     *每次获取的积分数
     */
    @Column
    private Integer gradeType;

    /**
     *每天限制次数
     */
    @Column
    private Integer countType;
    /**
     * 创建时间
     */
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
//    @Transient 忽略数据库对象实体映射

}
