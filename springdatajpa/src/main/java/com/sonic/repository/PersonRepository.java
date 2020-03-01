package com.sonic.repository;

import com.sonic.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
