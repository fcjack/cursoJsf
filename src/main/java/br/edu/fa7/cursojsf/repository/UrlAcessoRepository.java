package br.edu.fa7.cursojsf.repository;

import br.edu.fa7.cursojsf.model.UrlAcesso;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.NoResultException;

public class UrlAcessoRepository extends AbstractCrudRepository<UrlAcesso> {

    public UrlAcesso findByUrl(String url) {
        try {
            Criteria criteria = getCurrentSession().createCriteria(UrlAcesso.class);
            criteria.add(Restrictions.eq("url", url));
            return (UrlAcesso) criteria.uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
