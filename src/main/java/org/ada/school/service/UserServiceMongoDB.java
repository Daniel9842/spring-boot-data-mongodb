package org.ada.school.service;

import org.ada.school.dto.UserDto;
import org.ada.school.model.User;
import org.ada.school.repository.UserDocument;
import org.ada.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceMongoDB
        implements UserService
{

    private final UserRepository userRepository;

    public UserServiceMongoDB(@Autowired UserRepository userRepository )
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDocument create(UserDocument user) {
        return userRepository.save(user);
    }

    @Override
    public UserDocument findById( String id )
    {
        Optional<UserDocument> userDocOptional = userRepository.findById(id);
        if(userDocOptional.isPresent()){
            return userDocOptional.get();
        }
        return null;
    }

    @Override
    public List<UserDocument> all()
    {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteById( String id )
    {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public UserDocument update(UserDto userDto, String id) {
        if(userRepository.existsById(id)){
            UserDocument userDoc =  userRepository.findById(id).get();
            userDoc.update(userDto);
            userRepository.save(userDoc);
            return userDoc;
        }
        return null;

    }

}

