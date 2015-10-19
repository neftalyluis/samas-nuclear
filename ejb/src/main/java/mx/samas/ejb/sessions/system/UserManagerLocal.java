/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.sessions.system;

import mx.samas.ejb.entities.User;

/**
 *
 * @author neftaly
 */
public interface UserManagerLocal {

    String greet();

    boolean createUser(User u);

    User getUser(Long uid);

    User getUser(String us);

    boolean updateUser(User u);

    boolean removeUser(User u);

}
