package fr.fab.test.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class PaysDto {

    private Long id;
    @NonNull
    private String nomPays;
    @NonNull
    private String langue;
    @NonNull
    @JsonManagedReference
    private List<VilleDto> villes;

}
