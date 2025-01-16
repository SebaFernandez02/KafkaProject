package com.usuarios.repository;

import com.usuarios.dto.SuscriberDto;
import com.usuarios.model.Suscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SuscribersRepository extends JpaRepository<Suscriber, UUID> {
}
