package com.gamedev.equipment.infrastructure.output.persistence;

import com.gamedev.equipment.domain.model.Helmet;
import com.gamedev.equipment.domain.port.output.HelmetRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HelmetPersistenceAdapter implements HelmetRepositoryPort {

    private final HelmetJpaRepository helmetJpaRepository;
    private final HelmetPersistenceMapper helmetPersistenceMapper;

    @Override
    public List<Helmet> findAll(){

        List<HelmetJpa> jpaList = helmetJpaRepository.findAll();

        return jpaList.stream().map(jpa -> helmetPersistenceMapper.fromJpaToDomain(jpa)).toList();
    }

    @Override
    public Optional<Helmet> findById(Long id){
        Optional<HelmetJpa> jpa = helmetJpaRepository.findById(id);

        return jpa.map(jpa1 -> helmetPersistenceMapper.fromJpaToDomain(jpa1));
    }

    @Override
    public Helmet save(Helmet helmet){
        HelmetJpa jpa = helmetPersistenceMapper.fromDomainToJpa(helmet);
        HelmetJpa ar = helmetJpaRepository.save(jpa);
        return helmetPersistenceMapper.fromJpaToDomain(ar);
    }

    @Override
    public boolean existsByName(String name){
        return helmetJpaRepository.existsByName(name);
    }

    @Override
    public boolean existsById(Long id){
        return helmetJpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id){
        helmetJpaRepository.deleteById(id);
    }

}
