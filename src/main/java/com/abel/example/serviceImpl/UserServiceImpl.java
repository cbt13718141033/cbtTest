package com.abel.example.serviceImpl;

import com.abel.example.bean.MatterHandlerVO;
import com.abel.example.bean.User;
import com.abel.example.bean.UserVO;
import com.abel.example.bean.WincurservicehandlerVO;
import com.abel.example.dao.UserJpaDao;
import com.abel.example.service.UserService;
import com.abel.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author abel
 * @ClassName UserServiceImpl
 * @date 2016年11月10日
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserJpaDao userJpaDao;
    @Value("${httpHeaderPath}")
    private String httpHeaderPath;
    @Value("${matterHandlerPath}")
    private String matterHandlerPath;
    @Value("${wincurservicehandlerPath}")
    private String wincurservicehandlerPath;
    /**
     * @param username
     * @return
     */
    @Override
    public User getUserByUserName(String username) {

        //读取matterhandler.ashx文件并且转化为MatterHandlerVO对象
        MatterHandlerVO matterHandlerVO = new MatterHandlerVO();
        CommonUtil.ashxStreamToObjectList(httpHeaderPath+matterHandlerPath,null,matterHandlerVO);
        matterHandlerVO.setfields();
        //读取wincurservicehandler.ashx文件并且转化为WincurservicehandlerVO对象
        //WincurservicehandlerVO wincurservicehandlerVO = new WincurservicehandlerVO();
        //CommonUtil.ashxStreamToObject(httpHeaderPath+wincurservicehandlerPath,null,wincurservicehandlerVO);



        return userJpaDao.getByUsernameIs(username);
    }

    @Override
    public List<User> getByUsernameContaining(String username) {

        return userJpaDao.findByUsernameContaining(username);
    }

    @Override
    public User saveUser(User user) {
        return userJpaDao.save(user);
    }

    @Override
    public int removeUser(Long id) {
       try {
           userJpaDao.delete(id);
       }catch (Exception e) {
           return 0;
       }
        return 1;
    }

    @Override
    public User updateUser(User user) {

        return userJpaDao.save(user);
    }

    @Override
    public User getUserById(Long id) {

        return  userJpaDao.findOne(id);
    }

    @Override
    public List<User> listUsers() {

        return userJpaDao.findAll();
    }
}
