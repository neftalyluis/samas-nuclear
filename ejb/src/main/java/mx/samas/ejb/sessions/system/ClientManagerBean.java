/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.sessions.system;

import javax.annotation.security.DeclareRoles;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author neftaly
 */
@Stateless
@Local(ClientManagerLocal.class)
@Remote(ClientManagerRemote.class)
@DeclareRoles({"ComplianceContrallor", "Administrator"})
public class ClientManagerBean implements ClientManagerLocal {


}
