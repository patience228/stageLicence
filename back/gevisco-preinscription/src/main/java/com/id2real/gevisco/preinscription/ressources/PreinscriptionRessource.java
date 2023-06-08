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

import com.id2real.gevisco.preinscription.dtos.PreinscriptionDTO;
import com.id2real.gevisco.preinscription.entities.QEleve;
import com.id2real.gevisco.preinscription.entities.QPreinscription;
import com.id2real.gevisco.preinscription.services.PreinscriptionServiceInt;
import com.id2real.gevisco.preinscription.utils.PreinscriptionConstants;
import com.id2real.socle.utils.StringUtil;
import com.querydsl.core.BooleanBuilder;

@RestController
@RequestMapping(PreinscriptionConstants.API_PREINSCRIPTION_URL)
public class PreinscriptionRessource {


	@Autowired
	private PreinscriptionServiceInt preinscriptionService;
	
	 @GetMapping("/find/{id}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public PreinscriptionDTO find(@PathVariable("id") String id) {
	        return this.preinscriptionService.getDTO(id);
	    }

	    @GetMapping("/count")
	    @ResponseStatus(code = HttpStatus.OK)
	    public long count() {
	        return preinscriptionService.count();
	    }
	    
	    @GetMapping("/nbre")
	    @ResponseStatus(code = HttpStatus.OK)
	    public List<?> countbyAnnee() {
	        return preinscriptionService.CountByAnnee();
	    }
	    
	    @GetMapping("/nbre/{etat}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public long countNewbyAnnee(@PathVariable("etat") int etat) {
	        return preinscriptionService.CountNewByAnnee(etat);
	    }
	    
	    @GetMapping("/state/{etat}")
	    public List<PreinscriptionDTO> findPreinscriptionByEtat(@PathVariable("etat") int etat) {
	       return preinscriptionService.findByEtat(etat);
	    }

	    @PutMapping
	    @RequestMapping("/save")
	    @ResponseStatus(code = HttpStatus.CREATED)
	    public PreinscriptionDTO save(@RequestBody PreinscriptionDTO dto) {
	        return this.preinscriptionService.saveDTO(dto);
	    }

	    @PostMapping
	    @RequestMapping("/update")
	    @ResponseStatus(code = HttpStatus.OK)
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_UPDATE + "')")
	    public PreinscriptionDTO update(@RequestBody PreinscriptionDTO dto) {
//	        return AnneeScolaireDTO.updateDTO(dto.getId(), dto);
	        return preinscriptionService.saveDTO(dto);
	    }

	    @DeleteMapping("/{id}")
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_DELETE + "')")
	    public void delete(@PathVariable("id") String id) {
	    	preinscriptionService.delete(id);
	    }

	    @RequestMapping( method = RequestMethod.GET)
	    public List<PreinscriptionDTO> findAll() {
	    	/*
	    	 * @RequestParam int page, @RequestParam int size, @RequestParam Optional<String[]> sort,
	            @RequestParam Optional<String> direction, @RequestParam Optional<String> motcle
	    	 * 
	    	 
	        BooleanBuilder builder = new BooleanBuilder();

	        if (motcle.isPresent()) {
	            for (String mot : StringUtil.splitTextToWords(motcle.get())) {
	                builder.or(QPreinscription.preinscription.ecole_provenance.toUpperCase().containsIgnoreCase(mot.toUpperCase()));
	            }
	        }*/
	        return preinscriptionService.findAllDTO();
	    }

}
