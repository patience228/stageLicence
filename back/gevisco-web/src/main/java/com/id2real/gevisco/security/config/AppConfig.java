/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id2real.gevisco.security.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ngs.abs.bud.nomenclature.entities.Budget;
//import com.ngs.abs.bud.nomenclature.entities.LigneBudgetaire;
//import com.ngs.abs.bud.nomenclature.utils.BudNomenclatureConstant;
//import com.ngs.abs.cge.nomenclature.entities.Journal;
//import com.ngs.abs.cge.nomenclature.utils.CgeNomenclatureConstant;
//import com.ngs.core.codification.conf.NProperties;
//import com.ngs.nextframe.account.entities.Profil;
//import com.ngs.nextframe.account.utils.SecConstante;
import javax.annotation.PostConstruct;
import javax.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author rkoufionou
 */
@Configuration
public class AppConfig {

    @PostConstruct
    public void init() {

    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        return factory.createMultipartConfig();
    }

    @Bean
    public ObjectMapper configJackson() {

        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
