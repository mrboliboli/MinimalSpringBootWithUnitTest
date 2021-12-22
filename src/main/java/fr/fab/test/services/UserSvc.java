package fr.fab.test.services;

import java.util.List;

import fr.fab.test.dto.UserDto;

public interface UserSvc {
    
    public List<UserDto> list();
    public UserDto byId(Long id);
    public UserDto save(UserDto userDto);
    public void delete(UserDto userDto);
    public void delete(Long id);

}
