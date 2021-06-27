package com.budan.springappblog.repository;

import com.budan.springappblog.model.ActivateCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivateCodeRepository extends CrudRepository<ActivateCode, String> {
}