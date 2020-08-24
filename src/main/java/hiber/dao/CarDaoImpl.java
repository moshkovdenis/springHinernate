package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.SQLWarningException;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class CarDaoImpl implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUser(int series, String carName) {
        TypedQuery<User> query = sessionFactory.openSession().createQuery("from User where car.name = :carName and car.series = :series");
        query.setParameter("carName", carName);
        query.setParameter("series", series);
        if (query.getMaxResults() != 1) {
           return query.getResultList().get(0);
        }
        return query.getSingleResult();
    }

}
