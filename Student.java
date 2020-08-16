package pl.wojcikiewicz.studentsapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String postalCode;

}
