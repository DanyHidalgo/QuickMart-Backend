/* USO SQL
package com.ufm.QuickMart.repositories;

import com.ufm.QuickMart.entities.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    // Método para encontrar equipos por torneo
    List<Equipo> findByTorneoId(Long torneoId);
}

 */

package com.ufm.QuickMart.repositories;

import com.ufm.QuickMart.entities.Equipo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EquipoRepository extends MongoRepository<Equipo, String> {
    // Método para encontrar equipos por torneo
    List<Equipo> findByTorneoId(String torneoId);
}
