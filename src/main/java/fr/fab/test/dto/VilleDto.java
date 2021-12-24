package fr.fab.test.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class VilleDto {
    
    private Long id;
    @NonNull
    private String nom;
    @NonNull
    @JsonBackReference
    private PaysDto pays;
    
}
