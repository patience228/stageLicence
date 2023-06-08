package com.id2real.gevisco.inscription.ressources;



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

import com.id2real.gevisco.inscription.dtos.InscriptionDTO;
import com.id2real.gevisco.inscription.entities.QInscription;
import com.id2real.gevisco.inscription.services.InscriptionServiceInt;
import com.id2real.gevisco.inscription.utils.InscriptionConstants;
import com.id2real.gevisco.preinscription.dtos.EleveDTO;
import com.id2real.socle.utils.StringUtil;
import com.querydsl.core.BooleanBuilder;

@RestController
@RequestMapping(InscriptionConstants.API_INSCRIPTION_URL)
public class InscriptionResource {


	@Autowired
	private InscriptionServiceInt inscriptionService;
	
	 @GetMapping("/find/{id}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public InscriptionDTO find(@PathVariable("id") String id) {
	        return this.inscriptionService.getDTO(id);
	    }

	    @GetMapping("/count")
	    @ResponseStatus(code = HttpStatus.OK)
	    public long count() {
	        return inscriptionService.count();
	    }
	    
	    @GetMapping("/nbre")
	    @ResponseStatus(code = HttpStatus.OK)
	    public List<?> countbyAnnee() {
	        return inscriptionService.CountByAnnee();
	    }
	    
	    
	    @GetMapping("/all")
	    public List<InscriptionDTO> findElevebyall() {
	       return inscriptionService.findEleveBYall();
	    }
	    
	    @GetMapping("/matricule")
	    @ResponseStatus(code = HttpStatus.OK)
	    public String matricule() {
	        return inscriptionService.matricule();
	    }

	    @PutMapping
	    @RequestMapping("/save")
	    @ResponseStatus(code = HttpStatus.CREATED)
	   // @PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_CREATE + "')")
	    public InscriptionDTO save(@RequestBody InscriptionDTO dto) {
	        return this.inscriptionService.saveDTO(dto);
	    }

	    @PostMapping
	    @RequestMapping("/update")
	    @ResponseStatus(code = HttpStatus.OK)
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_UPDATE + "')")
	    public InscriptionDTO update(@RequestBody InscriptionDTO dto) {
//	        return AnneeScolaireDTO.updateDTO(dto.getId(), dto);
	        return inscriptionService.saveDTO(dto);
	    }

	    @DeleteMapping("/{id}")
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_DELETE + "')")
	    public void delete(@PathVariable("id") String id) {
	    	inscriptionService.delete(id);
	    }
	    
	   @RequestMapping( method = RequestMethod.GET)
	       public List<InscriptionDTO> findAll() {
		   /*
		    * @RequestParam int page, @RequestParam int size, @RequestParam Optional<String[]> sort,
	            @RequestParam Optional<String> direction, @RequestParam Optional<String> motcle
		    * 
		    
	        BooleanBuilder builder = new BooleanBuilder();

	        if (motcle.isPresent()) {
	            for (String mot : StringUtil.splitTextToWords(motcle.get())) {
	                builder.or(QInscription.inscription.derniere_classe.toUpperCase().containsIgnoreCase(mot.toUpperCase()));
	            }
	        }*/
	        return inscriptionService.findAllDTO();
	    }



}
