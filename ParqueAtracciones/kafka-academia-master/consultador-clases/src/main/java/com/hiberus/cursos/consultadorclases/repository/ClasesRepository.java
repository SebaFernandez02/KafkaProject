package com.hiberus.cursos.consultadorclases.repository;

import com.hiberus.cursos.consultadorclases.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasesRepository extends JpaRepository<Clase, Long> {

    Clase findByAsignaturaAndNivel(String asignatura, String nivel);
}
