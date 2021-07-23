package vn.techmaster.person_managerment.controllers;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import vn.techmaster.person_managerment.models.Person;
import vn.techmaster.person_managerment.repository.PersonRepo;
import vn.techmaster.person_managerment.service.StorageService;

@Controller
public class PersonController {
	@Autowired
	private PersonRepo personRepo;
	@Autowired
	private StorageService storageService;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("person", new Person());
		return "home";
	}

	@GetMapping("/addPerson")
	public String showForm(Model model) {
		model.addAttribute("person", new Person());
		return "form";
	}
//produces định nghĩa định dạng dữ liệu sẽ trả về cho user
//consumes là định dạng dữ liệu khi user request tới

	@PostMapping(value = "/post", consumes = { "multipart/form-data" })
	public String post(@Valid @ModelAttribute("person") Person person, BindingResult result, Model model)
			throws Exception {

		if (person.getPhoto().getOriginalFilename().isEmpty()) {
			result.addError(new FieldError("person", "photo", "Photo is required"));

		}
		if (result.hasErrors()) {
			return "form";
		}

		if (person.getId() > 0) {
			personRepo.edit(person);
		} else {
			personRepo.create(person);
		}
		storageService.uploadFile(person.getPhoto(), person.getId());
		model.addAttribute("people", personRepo.getAll());
		return "listPerson";

	}

	@GetMapping("/listPerson")
	public String listPerson(Model model) {
		List<Person> people = personRepo.getAll();

		model.addAttribute("people", people);

		return "listPerson";
	}

	@GetMapping("/person/{id}")
	public String personInfo(@PathVariable("id") int id, Model model) {
		Optional<Person> person = personRepo.get(id);
		if (person.isPresent()) {
			model.addAttribute("person", person.get());
			return "personList";
		}
		return "fail";
	}

	@GetMapping("/person/edit/{id}")
	public String editPerson(@PathVariable("id") int id, Model model) {
		Optional<Person> person = personRepo.get(id);
		if (person.isPresent()) {
			model.addAttribute("person", person.get());
		}
		return "form";
	}

	@GetMapping("/person/delete/{id}")
	public String deletePerson(@PathVariable("id") int id, Model model) {
		personRepo.deleteById(id);
		model.addAttribute("people", personRepo.getAll());
		return "redirect:/listPerson";
	}
}