package com.sonic.service;

import com.sonic.domain.Person;
import com.sonic.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * PersonService
 *
 * @author Sonic
 * @since 2020/3/2
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public void updatePersonEmail(String email, Integer id) {
        personRepository.updatePersonEmail(id, email);

    }

    @Transactional
    public void savePersons(List<Person> persons) {
        personRepository.saveAll(persons);

    }

}
