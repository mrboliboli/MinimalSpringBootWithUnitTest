package fr.fab.test.dto;

import java.util.List;

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
    private List<VilleDto> villes;

}
