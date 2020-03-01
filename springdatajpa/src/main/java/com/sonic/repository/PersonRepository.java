package com.sonic.repository;

import com.sonic.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("select p from Person p where p.id = (select max(p2.id) from Person p2)")
    Person getMaxIdPerson();

    /**
     * 使用占位符
     */
    @Query("select p from Person p where p.lastName = ?1 and p.email = ?2")
    List<Person> testQueryAnnotationParams(String lastName, String email);

    /**
     * 命名参数的方式
     */
    @Query("select p from Person p where p.lastName = :lastName and p.email = :email")
    List<Person> testQueryAnnotationParams2(@Param("email") String email, @Param("lastName") String lastName);

    @Query("select p from Person p where p.lastName like ?1% or p.email like ?2%")
//    @Query("select p from Person p where p.lastName like ?1 or p.email like ?2")
    List<Person> testQueryAnnotationLikeParam(String lastName, String email);

    @Query(value = "select count(id) from data_persons", nativeQuery = true)
    long getTotalCount();
}
