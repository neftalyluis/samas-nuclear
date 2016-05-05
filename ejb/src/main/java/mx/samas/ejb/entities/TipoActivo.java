/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;


/**
 *
 * @author neftaly
 *
 * Esta entidad se encarga de hacer un agrupamiento de Activos por sus
 * propiedades, muy importante para las fungibilidades
 *
 */
public enum TipoActivo {
    ACCION,
    BONO,
    DERIVADO,
    MONEDA
}
