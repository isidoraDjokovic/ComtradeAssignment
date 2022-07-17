package com.dora.assignment.repository;

import com.dora.assignment.entity.HelloWorld;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface  SpringDataHelloWorldRepository extends CrudRepository<HelloWorld,Long> {

    @Query("SELECT * FROM helloworld WHERE language_code = :languageCode")
    Optional<HelloWorld> findByLanguageCode(@Param("languageCode") String languageCode);
}
