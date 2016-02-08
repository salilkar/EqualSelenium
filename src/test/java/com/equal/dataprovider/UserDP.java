package com.equal.dataprovider;

import com.equal.common.BaseDP;
import com.equal.dao.user.IUserDAO;
import com.equal.dataprovider.base.DataProviderHelper;
import com.equal.dto.UserDTO;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDP extends BaseDP {

    private static IUserDAO userDao = daoRepository.getUserDAO();

    @DataProvider
    public static Object[][] firstUser() {
        return DataProviderHelper.toObject(userDao.findById("firstUser"));
    }

}
