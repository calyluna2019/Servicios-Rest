package com.AppWeb.AppWeb.Controller;

import com.AppWeb.AppWeb.Dominio.Empleado;
import com.AppWeb.AppWeb.Persistencia.RepositorioEmpleados;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmpleadosControlador {

    private final RepositorioEmpleados repositorioEmpleados;

    public EmpleadosControlador(RepositorioEmpleados repositorioEmpleados) {
        this.repositorioEmpleados = repositorioEmpleados;
    }

    @GetMapping("/empleados")
    public List<Empleado> obtenerTodo() {
        return repositorioEmpleados.findAll();
    }

    @PostMapping("/empleados")
    public Empleado grabarEmpleado(@RequestBody Empleado empleado) {
        return repositorioEmpleados.save(empleado);
    }

    @GetMapping("/empleados/{id}")
    public Empleado obtenerEmpleado(@PathVariable Long id) {
        return repositorioEmpleados.findById(id)
                .orElseThrow(()-> new EmpleadoNoEncontradoException(id));
    }

    @PutMapping("empleados/{id}")
    public Empleado actualizarEmpleado(@RequestBody Empleado empleado, @PathVariable Long id) {
        return repositorioEmpleados.findById(id).map(e -> {
            e.setNombre(empleado.getNombre());
            e.setCargo(empleado.getCargo());
            return repositorioEmpleados.save(e);
        }).orElseGet(()-> {
            empleado.setId(id);
            return repositorioEmpleados.save(empleado);
        })
    }

    @DeleteMapping("/empleados/{id}")
    public void borrarEmpleado(@PathVariable Long id) {
        if (repositorioEmpleados.findById(id).isPresent()) {
            repositorioEmpleados.deleteById(id);
        } else throw new EmpleadoNoEncontradoException(id);

        /*
        repositorioEmpleados.findById(id)
                .map(empleado -> {
                    repositorioEmpleados.deleteById(id);
                    return empleado;
                })
                .orElseThrow(()-> new EmpleadoNoEncontradoException(id));*/
    }
}
