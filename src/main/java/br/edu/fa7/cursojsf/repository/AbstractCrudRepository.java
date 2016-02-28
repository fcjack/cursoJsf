package br.edu.fa7.cursojsf.repository;

import br.edu.fa7.cursojsf.model.AbstractEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by jackson on 2/28/16.
 */
public abstract class AbstractCrudRepository<T extends AbstractEntity> implements GenericRepository<T> {

    @Inject
    private EntityManager entityManager;

    @Override
    public T findById(Integer id) {
        Criteria criteria = getCurrentSession().createCriteria(getRecordType());
        criteria.add(Restrictions.idEq(id));

        T object = getRecordType().cast(criteria.uniqueResult());
        getCurrentSession().evict(object);

        return object;
    }

    @Override
    public void save(T entity) {
        getCurrentSession().saveOrUpdate(entity);
        getCurrentSession().flush();
    }

    @Override
    public void remove(Integer id) {
        Session session = getCurrentSession();

        Criteria criteria = session.createCriteria(getRecordType());
        criteria.add(Restrictions.idEq(id));

        session.delete(criteria.uniqueResult());
        session.flush();
    }

    @Override
    public List<T> findAll() {
        Criteria criteria = getCurrentSession().createCriteria(getRecordType());
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    public Session getCurrentSession() {
        Session session = (Session) entityManager.getDelegate();
        if (!session.isOpen()) session = session.getSessionFactory().openSession();
        return session;
    }

    public Class<T> getRecordType() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
