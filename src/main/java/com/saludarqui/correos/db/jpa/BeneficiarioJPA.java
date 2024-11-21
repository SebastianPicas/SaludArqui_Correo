package com.saludarqui.correos.db.jpa;

import com.saludarqui.correos.db.orm.AfiliadoORM;
import com.saludarqui.correos.db.orm.BeneficiarioORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeneficiarioJPA extends JpaRepository<BeneficiarioORM, Long> {

    Optional<BeneficiarioORM> findByAfliliadoORM(AfiliadoORM afiliado);


}
