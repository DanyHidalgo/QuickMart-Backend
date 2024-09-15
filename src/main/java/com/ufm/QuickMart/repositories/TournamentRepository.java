package com.ufm.QuickMart.repositories;

import com.ufm.QuickMart.entities.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<Torneo, Long> {
}
