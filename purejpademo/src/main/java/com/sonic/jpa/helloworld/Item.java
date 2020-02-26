package com.sonic.jpa.helloworld;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Item
 *
 * @author Sonic
 * @since 2020/2/26
 */

@Table(name = "jpa_items")
@Entity
public class Item {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(name = "ITEM_NAME")
    private String itemName;

    @JoinTable(name = "item_category", // 中间表名
            joinColumns = {@JoinColumn(name = "ITEM_ID"
                    , referencedColumnName = "id"
            )},  //和Item主键字段关联， 可以不写
            inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID"
                    , referencedColumnName = "id"
            )}) //和Category主键字段关联， 可以不写
    @ManyToMany
    private Set<Category> categories = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
