package com.saludarqui.correos.db.jpa;

import com.saludarqui.correos.db.orm.BeneficiarioORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiarioJPA extends JpaRepository<BeneficiarioORM, Long> {

}
