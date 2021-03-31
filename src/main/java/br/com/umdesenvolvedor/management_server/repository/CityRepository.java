package br.com.umdesenvolvedor.management_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.umdesenvolvedor.management_server.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT c FROM City c WHERE c.uf = :uf AND c.name LIKE %:name%")
    List<City> findByUfLikeName(@Param("uf") String uf, @Param("name") String name);
}
