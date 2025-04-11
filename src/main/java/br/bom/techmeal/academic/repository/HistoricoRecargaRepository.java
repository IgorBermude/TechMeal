package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.HistoricoRecarga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRecargaRepository extends JpaRepository<HistoricoRecarga, Integer> {}

