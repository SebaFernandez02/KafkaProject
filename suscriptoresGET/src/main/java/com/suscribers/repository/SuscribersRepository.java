package com.suscribers.repository;

import com.suscribers.model.Suscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SuscribersRepository extends JpaRepository<Suscriber, UUID> {
}
