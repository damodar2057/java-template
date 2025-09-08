package com.demo.shared.base;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.JpaRepository;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {}
