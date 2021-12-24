package fr.fab.test.services;

import java.util.List;

import fr.fab.test.dto.PaysDto;
import fr.fab.test.models.Pays;

public interface PaysSvc {
    
    public List<PaysDto> list();
    public PaysDto byId(Long id);
    public PaysDto save(PaysDto paysDto);
    public void delete(PaysDto paysDto);
    public void delete(Long id);

    /**
     * pour test de la transaction sur le controller
     * @return
     */
    public List<Pays> entityList();
    /**
     * pour test de la transaction sur le controller
     * @return
     */
    public Pays entityById(Long id);
    /**
     * pour test de la transaction sur le controller
     * @return
     */
    public Pays save(Pays pays);
    /**
     * pour test de la transaction sur le controller
     * @return
     */
    public void delete(Pays pays);

}
