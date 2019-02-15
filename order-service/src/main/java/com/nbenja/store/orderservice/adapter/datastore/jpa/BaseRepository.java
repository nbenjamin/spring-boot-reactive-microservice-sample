package com.nbenja.store.orderservice.adapter.datastore.jpa;

import java.io.Serializable;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends CrudRepository<T, ID> {

  Optional<T> findById(ID id);

  <S extends T> S save(S entity);
}
