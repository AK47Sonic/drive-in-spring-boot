package com.sonic.jpa.helloworld;

import javax.persistence.*;

/**
 * Manager
 *
 * @author Sonic
 * @since 2020/2/26
 */
@Table(name = "jpa_managers")
@Entity
public class Manager {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(name="MGR_NAME")
    private String mgrName;

    @OneToOne(mappedBy = "mgr")
//    @JoinColumn(name = "DEPT_ID", unique = true)
//    @JoinColumn
//    @OneToOne
    private Department dept;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMgrName() {
        return mgrName;
    }

    public void setMgrName(String mgrName) {
        this.mgrName = mgrName;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }
}
