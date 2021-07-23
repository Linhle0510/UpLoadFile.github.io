package vn.techmaster.person_managerment.models;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    private int id;
    
    @NotBlank(message = "Job of Name is required")
    private String name;
}
