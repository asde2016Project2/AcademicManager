/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unical.asde.uam.controllers.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 *
 * @author Francesco Bruno
 */
public class BaseController {

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    protected SessionLocaleResolver localeResolver;
}
