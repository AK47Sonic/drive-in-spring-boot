package com.sonic.repository;

import com.sonic.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * PersonRepository
 * Person 关联的类
 * Integer 主键类型
 * 标记Repository
 * 1. 使用接口extends JpaRepository<Person, Integer>
 * 2. 使用注解{@code @RepositoryDefinition}
 *
 * @author Sonic
 * @since 2020/3/1
 */
//@RepositoryDefinition(domainClass = Person.class, idClass = Integer.class)
//public interface PersonRepository {
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person getByLastName(String lastName);

    List<Person> getByLastNameStartingWithAndIdLessThan(String lastName, Integer id);

    List<Person> getByEmailInOrBirthLessThan(List<String> emails, Date birth);
    /**
     * 使用级联属性查询
     * 若Person类中有addressId属性，则优先使用，而不使用级联属性
     * 强制使用级联getByAddress_IdGreaterThan
     */
    List<Person> getByAddressIdGreaterThan(Integer id);

    /**
     * 使用级联属性查询
     */
    List<Person> getByAddress_ProvinceEquals(String province);
}
