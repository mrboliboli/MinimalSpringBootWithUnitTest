package fr.fab.test.services;

import java.util.List;

import fr.fab.test.dto.VilleDto;

public interface VilleSvc {
    
    public List<VilleDto> list();
    public VilleDto byId(Long id);
    public VilleDto save(VilleDto villeDto);
    public void delete(VilleDto villeDto);
    public void delete(Long id);
}
