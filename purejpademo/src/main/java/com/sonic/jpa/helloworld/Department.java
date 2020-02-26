package com.sonic.jpa.helloworld;

import javax.persistence.*;

/**
 * Department
 *
 * @author Sonic
 * @since 2020/2/26
 */
@Table(name = "jpa_departments")
@Entity
public class Department {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(name = "DEPT_NAME")
    private String deptName;

//    @PrimaryKeyJoinColumn
    @JoinColumn(name = "MGR_ID", unique = true)
//    @JoinColumn
    @OneToOne(fetch = FetchType.LAZY)
    private Manager mgr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Manager getMgr() {
        return mgr;
    }

    public void setMgr(Manager mgr) {
        this.mgr = mgr;
    }
}
