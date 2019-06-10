package com.AppWeb.AppWeb.Controller;

public class EmpleadoNoEncontradoException extends RuntimeException {

    public EmpleadoNoEncontradoException(Long id){
        super("empleado con ID: "+ id +" no existe");
    }

}
