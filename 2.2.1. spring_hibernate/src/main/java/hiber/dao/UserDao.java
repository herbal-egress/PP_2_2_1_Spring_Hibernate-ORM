package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   void addUserWithCar(User user, Car car); // метод добавления юзера с машиной
   User getUserById(Long id); // получаем юзера и связанную с ним машину по id юзера
   User getUserByCar(String model, int series); // достаём юзера, владеющего машиной по ее модели и серии.
}
