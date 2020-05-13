/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoir.awt.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author onkar
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(memoir.awt.service.CinemaFacadeREST.class);
        resources.add(memoir.awt.service.CredentialsFacadeREST.class);
        resources.add(memoir.awt.service.MemoirFacadeREST.class);
        resources.add(memoir.awt.service.PersonFacadeREST.class);
        resources.add(memoir.awt.service.RatingFacadeREST.class);
    }
    
}
