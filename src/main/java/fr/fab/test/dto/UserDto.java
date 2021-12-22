package fr.fab.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {

    private Long id;
    @NonNull
    private String nom;
    @NonNull
    private String prenom;
    @NonNull
    private Integer age;
    @NonNull
    private String ville;
    @NonNull
    private Character genre;
    
}
