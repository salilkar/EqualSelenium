package com.equal.dao.base;

import com.equal.dao.user.IUserDAO;
import com.equal.dao.user.UserXlsDAO;


public class DAORepository {

    public DAORepository() {
        userDAO = new UserXlsDAO();
        
    }

    private IUserDAO userDAO;

   

    public IUserDAO getUserDAO() {
        return userDAO;
    }

  

}
