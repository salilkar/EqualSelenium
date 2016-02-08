package com.equal.dao.user;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.equal.common.Config;
import com.equal.dao.base.XlsHelper;
import com.equal.dao.base.XlsReader;
import com.equal.dao.user.IUserDAO;
import com.equal.dto.UserDTO;


public class UserXlsDAO implements IUserDAO {


    //    private XlsReader xls = new XlsReader("src\\test\\resour�es\\Login.xls", "ValidData");
    private XlsReader xls = new XlsReader("Login.xlsx", "ValidData");

    // @Override
    public List<UserDTO> findListById(String id) {

        List<Map<String, String>> testData = xls.getDataListById(id);
        if (testData != null && !testData.isEmpty()) {
            List<UserDTO> userData = new ArrayList<UserDTO>();
            for (Map<String, String> dataItem : testData) {
                UserDTO userDTO = new UserDTO();
                XlsHelper.fillObject(userDTO, dataItem);
                userData.add(userDTO);
            }
            return userData;
        }
        return null;
    }

    //@Override
    public UserDTO findById(String id) {
        Map<String, String> testData = xls.getDataById(id);

        UserDTO userDTO = new UserDTO();
        XlsHelper.fillObject(userDTO, testData);

        return userDTO;
    }


}
