package fr.fab.test.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.fab.test.dto.VilleDto;
import fr.fab.test.models.Ville;
import fr.fab.test.repository.PaysRepository;
import fr.fab.test.repository.VilleRepository;

@Service
public class VilleSvcImpl implements VilleSvc {

    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private PaysRepository paysRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<VilleDto> list() {
        return StreamSupport.stream(villeRepository.findAll().spliterator(), false).map(this::toDto).toList();
    }

    @Override
    public VilleDto byId(Long id) {
        
        return toDto(villeRepository.findById(id).orElseThrow());
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
        // mapper personalisé pour mapper le pays vers paysId
        return modelMapper.typeMap(Ville.class, VilleDto.class)
            .addMapping(src -> src.getPays().getId(), VilleDto::setId_pays)
            .addMapping(Ville::getNom, VilleDto::setNom)
            .addMapping(Ville::getId, VilleDto::setId).map(ville);
    }
    private Ville toEntity(VilleDto villeDto){
        // return modelMapper.map(villeDto, Ville.class);
        Provider<Ville> p = v -> villeRepository.getById(villeDto.getId());
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        Ville v = modelMapper.typeMap(VilleDto.class, Ville.class)
        .setProvider(p)
        .addMapping(src -> paysRepository.getById(src.getId_pays()), Ville::setPays)
        .addMapping(VilleDto::getNom, Ville::setNom)
        .addMapping(VilleDto::getId, Ville::setId).map(villeDto);
        return v;
    }

}
