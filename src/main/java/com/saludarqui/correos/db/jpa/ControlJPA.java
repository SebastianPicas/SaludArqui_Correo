package com.saludarqui.correos.db.jpa;

import com.saludarqui.correos.db.orm.ControlORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControlJPA extends JpaRepository<ControlORM, Long> {

}
