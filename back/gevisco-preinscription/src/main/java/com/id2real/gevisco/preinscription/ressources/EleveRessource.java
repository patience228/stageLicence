package com.id2real.gevisco.preinscription.ressources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.id2real.gevisco.preinscription.dtos.EleveDTO;
import com.id2real.gevisco.preinscription.dtos.PreinscriptionDTO;
import com.id2real.gevisco.preinscription.entities.QEleve;
import com.id2real.gevisco.preinscription.services.EleveServiceInt;
import com.id2real.gevisco.preinscription.utils.PreinscriptionConstants;
import com.id2real.socle.utils.StringUtil;
import com.querydsl.core.BooleanBuilder;

@RestController
@RequestMapping(PreinscriptionConstants.API_PREINSCRIPTION_ELEVE_URL)
public class EleveRessource {


	@Autowired
	private EleveServiceInt eleveService;
	
	 @GetMapping("/find/{id}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public EleveDTO find(@PathVariable("id") String id) {
	        return this.eleveService.getDTO(id);
	    }

	    @GetMapping("/count")
	    @ResponseStatus(code = HttpStatus.OK)
	    public long count() {
	        return eleveService.count();
	    }

	    
	    @GetMapping("/eleve")
	    public List<EleveDTO> findEleveInscrire() {
	       return eleveService.findEleveInInscription();
	    }
	    
	
	  
	    
	    @PutMapping
	    @RequestMapping("/save")
	    @ResponseStatus(code = HttpStatus.CREATED)
	   // @PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_CREATE + "')")
	    public EleveDTO save(@RequestBody EleveDTO dto) {
	        return this.eleveService.saveDTO(dto);
	    }

	    @PostMapping
	    @RequestMapping("/update")
	    @ResponseStatus(code = HttpStatus.OK)
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_UPDATE + "')")
	    public EleveDTO update(@RequestBody EleveDTO dto) {
//	        return AnneeScolaireDTO.updateDTO(dto.getId(), dto);
	        return eleveService.saveDTO(dto);
	    }

	    @DeleteMapping("/{id}")
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_DELETE + "')")
	    public void delete(@PathVariable("id") String id) {
	    	eleveService.delete(id);
	    }

	    @RequestMapping( method = RequestMethod.GET)
	    public List<EleveDTO> findAll() {
	    	/*
	    	 * @RequestParam int page, @RequestParam int size, @RequestParam Optional<String[]> sort,
	            @RequestParam Optional<String> direction, @RequestParam Optional<String> motcle
	        BooleanBuilder builder = new BooleanBuilder();

	        if (motcle.isPresent()) {
	            for (String mot : StringUtil.splitTextToWords(motcle.get())) {
	                builder.or(QEleve.eleve.matricule.toUpperCase().containsIgnoreCase(mot.toUpperCase()));
	            }
	        }*/
	        return eleveService.findAllDTO();
	    }

}
