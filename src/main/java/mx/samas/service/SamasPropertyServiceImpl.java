/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class SamasPropertyServiceImpl implements SamasPropertyService {

    @Value("${samas.pip.directory}")
    private String pipDirectory;

    @Value("${samas.user.csv.directory}")
    private String usuarioCsvDirectory;

    @Override
    public String getPipDirectory() {
        return pipDirectory;
    }

    @Override
    public String getUsuarioCsvDirectory() {
        return usuarioCsvDirectory;
    }

}
