package vn.techmaster.person_managerment.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vn.techmaster.person_managerment.models.Job;
import vn.techmaster.person_managerment.repository.JobRepo;

@Controller
public class JobController {
    public JobRepo jRepo;

    public JobController() {
        jRepo = new JobRepo();
    }

    @GetMapping("/listJob")
    public String listJob(Model model) {
        List<Job> jobs = jRepo.getAll();

        model.addAttribute("jobs", jobs);
        return "listJob";
    }

    @GetMapping("/addJob")
    public String showForm(Model model) {
        model.addAttribute("job", new Job());
        return "jForm";
    }

    @PostMapping("/postJob")
    public String addJob(@Valid @ModelAttribute("job") Job job, BindingResult result, Model model) {
        Job newJob = jRepo.create(job);
        if (result.hasErrors()) {
            return "jForm";
        } else {
            if (newJob != null) {
                model.addAttribute("jobs", jRepo.getAll());
            }
            return "jForm";

        }

    }
}