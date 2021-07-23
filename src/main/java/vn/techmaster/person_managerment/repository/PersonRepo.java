package vn.techmaster.person_managerment.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import vn.techmaster.person_managerment.models.Person;

@Repository
public class PersonRepo {
    private List<Person> people = new ArrayList<>();

    public List<Person> getAll() {
        return this.people;
    }
//Thêm mới
    public Person create(Person person) {
        int id;
        if (people.isEmpty()) {
            id = 1;
        } else {
            Person lastPerson = people.get(people.size() - 1);
            id = lastPerson.getId() + 1;
        }
        person.setId(id);
        people.add(person);
        return person;
    }
//Sửa
    public Person edit(Person person) {
        get(person.getId()).ifPresent(exPerson -> {
            exPerson.setName(person.getName());
            exPerson.setJob(person.getJob());
            exPerson.setGender(person.isGender());
            exPerson.setBirthDay(person.getBirthDay());
            if (person.getPhoto().getOriginalFilename().isEmpty()){
                exPerson.setPhoto(person.getPhoto());
            }
            
        });
        return person;
    }
//Xoá
    public void delete(Person person) {
        deleteById(person.getId());
    }

    public Optional<Person> get(int id) {
        return people.stream().filter(p -> p.getId() == id).findFirst();
    }

    public void deleteById(int id) {
        get(id).ifPresent(existed -> people.remove(existed));
    }

}
