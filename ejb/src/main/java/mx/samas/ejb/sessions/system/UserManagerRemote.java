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
public interface UserManagerRemote {
    public String greet();

    public boolean createUser(User u);

    public User getUser(Long uid);

    public User getUser(String us);

    public boolean updateUser(User u);

    public boolean removeUser(User u);

}
