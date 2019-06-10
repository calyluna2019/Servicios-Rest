package com.AppWeb.AppWeb.Persistencia;

import com.AppWeb.AppWeb.Dominio.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioEmpleados extends JpaRepository<Empleado, Long> {
}
