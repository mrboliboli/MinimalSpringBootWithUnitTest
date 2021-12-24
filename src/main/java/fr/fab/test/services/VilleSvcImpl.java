package fr.fab.test.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import fr.fab.test.dto.VilleDto;
import fr.fab.test.models.Ville;
import fr.fab.test.repository.VilleRepository;

public class VilleSvcImpl implements VilleSvc {

    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<VilleDto> list() {
        return StreamSupport.stream(villeRepository.findAll().spliterator(), false).map(this::toDto).toList();
    }

    @Override
    public VilleDto byId(Long id) {
        return toDto(villeRepository.getById(id));
    }

    @Override
    public VilleDto save(VilleDto villeDto) {
        return toDto(villeRepository.save(toEntity(villeDto)));
    }

    @Override
    public void delete(VilleDto villeDto) {
        villeRepository.delete(toEntity(villeDto));
    }

    @Override
    public void delete(Long id) {
        villeRepository.deleteById(id);
    }
    

    
    private VilleDto toDto(Ville ville){
        return modelMapper.map(ville, VilleDto.class);
    }
    private Ville toEntity(VilleDto villeDto){
        return modelMapper.map(villeDto, Ville.class);
    }

}
