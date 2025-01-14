package com.hiberus.cursos.consultadorclases.model;

import com.hiberus.cursos.consultadorclases.dto.AlumnoDTO;
import com.hiberus.cursos.consultadorclases.dto.ProfesorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String asignatura;
    private String nivel;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Profesor> profesores;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Alumno> alumnos;
}
