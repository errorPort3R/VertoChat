package com.theironyard;

import com.theironyard.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jeffryporter on 7/24/16.
 */
public interface UserRepository extends CrudRepository<User, Integer>
{
    public User findByUsername(String username);
}
