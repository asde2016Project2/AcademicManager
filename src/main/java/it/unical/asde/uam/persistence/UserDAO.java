/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.persistence;

import it.unical.asde.uam.model.User;

/**
 *
 * @author Gezahegn
 */
public interface UserDAO {

    void create(User u);

    User retrieve(String username);

    boolean exists(String username);

    Long numberOfUsers();

    void update(User u);

    void delete(User u);
}
