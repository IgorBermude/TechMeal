package br.bom.techmeal.academic.repository;

import br.bom.techmeal.academic.entity.ControleContas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ControleContasRepository extends JpaRepository<ControleContas, Integer> {



}
