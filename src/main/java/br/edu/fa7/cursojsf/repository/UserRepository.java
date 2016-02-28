package br.edu.fa7.cursojsf.repository;

import br.edu.fa7.cursojsf.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;

public class UserRepository extends AbstractCrudRepository<User> {

    public User findByUsernameAndPassword(String username, String password) {
        try {
            Criteria criteria = getCurrentSession().createCriteria(User.class);
            criteria.add(Restrictions.eq("username", username)).add(Restrictions.eq("password", password));

            return (User) criteria.uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
