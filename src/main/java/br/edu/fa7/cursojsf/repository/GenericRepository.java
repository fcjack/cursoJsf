package br.edu.fa7.cursojsf.repository;

import br.edu.fa7.cursojsf.model.AbstractEntity;

import java.util.List;

/**
 * Created by jackson on 2/28/16.
 */
public interface GenericRepository<T extends AbstractEntity> {

    T findById(Integer id);

    void save(T entity);

    void remove(Integer id);

    List<T> findAll();

}
