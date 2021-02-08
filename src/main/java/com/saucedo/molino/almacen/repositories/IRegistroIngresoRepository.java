package com.saucedo.molino.almacen.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saucedo.molino.almacen.models.RegistroIngreso;
@Repository
public interface IRegistroIngresoRepository extends JpaRepository<RegistroIngreso,Long> {
}
