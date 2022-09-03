package ru.kamarov.Project311.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kamarov.Project311.models.Person;
import ru.kamarov.Project311.repositories.PeopleRepository;


import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

    private final PeopleRepository peopleRepository;


    public PersonServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }



    public List<Person> index() {
        return peopleRepository.findAll();
    }


    public Person show(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }


    @Transactional  // требуется транзакция на запись, на классе висит транзакция realOnly
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
