package org.ada.school.service;

import org.ada.school.dto.UserDto;
import org.ada.school.model.User;
import org.ada.school.repository.UserDocument;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserServiceHashMap
    implements UserService
{

    private final HashMap<String, UserDocument> usersMap = new HashMap<>();


    @Override
    public UserDocument create(UserDocument user )
    {
        usersMap.put( user.getId(), user );
        return user;
    }

    @Override
    public UserDocument findById( String id )
    {
        if ( usersMap.containsKey( id ) )
        {
            return usersMap.get( id );
        }
        return null;
    }

    @Override
    public List<UserDocument> all()
    {
        return new ArrayList<>( usersMap.values() );
    }

    @Override
    public boolean deleteById( String id )
    {
        return usersMap.remove( id ) != null;
    }

    @Override
    public UserDocument update( UserDto userDto, String id )
    {
        if ( usersMap.containsKey( id ) )
        {
            UserDocument user = usersMap.get( id );
            user.update( userDto );
            return user;
        }
        else
        {
            return null;
        }
    }


}
