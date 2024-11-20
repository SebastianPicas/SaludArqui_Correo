package com.saludarqui.correos.db.jpa;

import com.saludarqui.correos.db.orm.AfiliadoORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AfiliadoJPA extends JpaRepository<AfiliadoORM, Long> {

}
