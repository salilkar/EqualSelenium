package com.equal.dao.user;

import java.util.List;

import com.equal.dto.UserDTO;


/**
 * User: Salil Kar
 */
public interface IUserDAO {

    public List<UserDTO> findListById(String id);

    public UserDTO findById(String id);
}
