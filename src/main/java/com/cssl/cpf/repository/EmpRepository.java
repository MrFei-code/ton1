package com.cssl.cpf.repository;

import com.cssl.cpf.domain.Emp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmpRepository extends MongoRepository<Emp,String> {
}
