package com.prefeitura.iptu.iptu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParcelaIPTURepository extends JpaRepository<ParcelaIPTU, Long> {
    List<ParcelaIPTU> findByIdCidadao(Integer idCidadao);
}
