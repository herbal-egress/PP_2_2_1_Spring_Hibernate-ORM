package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public void addUserWithCar(User user, Car car) { // метод добавления юзера с машиной
        userDao.addUserWithCar(user, car);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) { // получаем юзера и связанную с ним машину по id юзера
        return userDao.getUserById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByCar(String model, int series) {  // достаём юзера, владеющего машиной по ее модели и серии.
        return userDao.getUserByCar(model, series);
    }
}