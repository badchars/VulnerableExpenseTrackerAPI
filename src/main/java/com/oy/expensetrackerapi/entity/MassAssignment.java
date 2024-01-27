package com.oy.expensetrackerapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MassAssignment {
    private String name;

    private String surname;
    private String email;

    private String role;


}
