package vn.techmaster.person_managerment.repository;

import java.util.ArrayList;
import java.util.List;

import vn.techmaster.person_managerment.models.Job;

public class JobRepo {
    private List<Job> jobs = new ArrayList<>();
//Tạo sẵn ds job
    public JobRepo() {
        List<Job> list = List.of(new Job(1, "Developer"), new Job(2, "Tester"), new Job(3, "Doctor"));
        for (Job job : list) {
            jobs.add(job);
        }
    }

    public List<Job> getAll() {
        return this.jobs;
    }

 //Thêm Job
    public Job create(Job job) {
        int id;
        if (jobs.isEmpty()) {
            id = 1;
        } else {
            Job lastJob = jobs.get(jobs.size() - 1);
            id = lastJob.getId() + 1;
        }
        job.setId(id);
        for (Job iJob : jobs) {
            if (job.getName().equalsIgnoreCase(iJob.getName()))
                return iJob;
        }
        jobs.add(job);
        return job;
    }
}
