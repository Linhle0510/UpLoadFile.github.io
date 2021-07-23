package vn.techmaster.person_managerment.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private MultipartFile photo;

    private int id;
    @NotBlank(message = "your name is required")
    @Size(min = 5, max = 20, message = "Name must between 5 and 20")

    private String name;
    @NotBlank(message = "your job is required")

    private String job;

    private boolean gender;

    @NotBlank(message = "your birthday is required")
    private String birthDay;

    // @NotBlank, @Size -> validation
}