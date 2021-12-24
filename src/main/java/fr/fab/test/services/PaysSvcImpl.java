package fr.fab.test.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.fab.test.dto.PaysDto;
import fr.fab.test.models.Pays;
import fr.fab.test.repository.PaysRepository;

@Service
public class PaysSvcImpl implements PaysSvc {

    @Autowired
    private PaysRepository paysRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PaysDto> list() {
        return StreamSupport.stream(paysRepository.findAll().spliterator(),false).map(this::toDto).toList();
    }

    @Override
    public PaysDto byId(Long id) {
        return toDto(paysRepository.getById(id));
    }

    @Override
    public PaysDto save(PaysDto paysDto) {
        return toDto(paysRepository.save(toEntity(paysDto)));
    }

    @Override
    public void delete(PaysDto paysDto) {
        paysRepository.delete(toEntity(paysDto));
        
    }

    @Override
    public void delete(Long id) {
        paysRepository.deleteById(id);
    }

    @Override
    public List<Pays> entityList() {
        return paysRepository.findAll();
    }

    @Override
    public Pays entityById(Long id) {
        return paysRepository.getById(id);
    }

    @Override
    public Pays save(Pays pays) {
        return paysRepository.save(pays);
    }

    @Override
    public void delete(Pays pays) {
        paysRepository.delete(pays);
    }

    private PaysDto toDto(Pays pays){
        return modelMapper.map(pays, PaysDto.class);
    }
    private Pays toEntity(PaysDto paysDto){
        return modelMapper.map(paysDto, Pays.class);
    }

    
}
