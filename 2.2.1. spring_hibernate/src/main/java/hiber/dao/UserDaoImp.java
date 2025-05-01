package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public void addUserWithCar(User user, Car car) { // добавление юзера с машиной
        user.setCar(car);
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getUserById(Long id) { // получаем юзера и связанную с ним машину по id юзера
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public User getUserByCar(String model, int series) { // достаём юзера, владеющего машиной по модели и серии.
        Car car = sessionFactory.getCurrentSession().createQuery
                        ("FROM Car car LEFT OUTER JOIN FETCH car.user " +
                                "WHERE car.model=:model and car.series=:series", Car.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .uniqueResult();
        return car.getUser();
    }
}