package fr.fab.test.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.fab.test.dto.UserDto;
import fr.fab.test.models.User;
import fr.fab.test.repository.UserRepository;

@Service
public class UserSvcImpl implements UserSvc {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDto> list() {
        List<User> poo = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(poo::add);
       
        return StreamSupport.stream(userRepository.findAll().spliterator(),false).map(this::toDto).toList();
    }

    @Override
    public UserDto byId(Long id) {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()){
            return toDto(opt.get());
        } else {
            return null;
        }
    }

    @Override
    public UserDto save(UserDto userDto) {
        return toDto(userRepository.save(toEntity(userDto)));
    }

    @Override
    public void delete(UserDto userDto) {
        userRepository.delete(toEntity(userDto));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    
    private UserDto toDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    private User toEntity(UserDto dto){
        return modelMapper.map(dto, User.class);
    }

}
