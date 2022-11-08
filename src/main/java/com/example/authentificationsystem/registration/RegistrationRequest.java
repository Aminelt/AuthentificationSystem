package com.example.authentificationsystem.registration;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private String firstname ;
    private String lastName ;
    private String email ;
    private String password ;

}
