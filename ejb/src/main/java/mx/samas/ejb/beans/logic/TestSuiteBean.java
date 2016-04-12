/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.beans.exceptions.AppException;
import mx.samas.ejb.entities.Test;

/**
 *
 * @author neftaly
 */
@Stateless
public class TestSuiteBean {

    private static final Logger LOG = Logger.getLogger(TestSuiteBean.class.getName());
    

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;
//    public createTestFromDTO(TestEntryDTO t)

    public List<Test> findAll() throws AppException{
        return em.createNamedQuery("Test.findAll").getResultList();

    }

}
