package com.id2real.gevisco.etudes.ressources;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import com.id2real.gevisco.etudes.dtos.EnseignementDTO;
import com.id2real.gevisco.etudes.dtos.EnseignementUpdateDTO;
import com.id2real.gevisco.etudes.entities.Classe;
import com.id2real.gevisco.etudes.entities.Matiere;
import com.id2real.gevisco.etudes.services.EnseignementServiceInt;
import com.id2real.gevisco.etudes.utils.EtudesConstants;
import com.id2real.socle.utils.StringUtil;
import com.querydsl.core.BooleanBuilder;

@RestController
@RequestMapping(EtudesConstants.API_ETUDES_ENSEIGNEMENT_URL)
public class EnseignementRessource {



	@Autowired
	private EnseignementServiceInt  enseignementService;
	
	 @GetMapping("/find/{id}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public EnseignementDTO find(@PathVariable("id") String id) {
	        return this.enseignementService.getDTO(id);
	    }

	    @GetMapping("/count")
	    @ResponseStatus(code = HttpStatus.OK)
	    public long count() {
	        return enseignementService.count();
	    }

	    @PutMapping
	    @RequestMapping("/save")
	    @ResponseStatus(code = HttpStatus.CREATED)
	   // @PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_CREATE + "')")
	    public EnseignementDTO save(@RequestBody EnseignementDTO dto) {
	        return this.enseignementService.saveDTO(dto);
	    }

	    @PostMapping
	    @RequestMapping("/update")
	    @ResponseStatus(code = HttpStatus.OK)
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_UPDATE + "')")
	    public EnseignementDTO update(@RequestBody EnseignementDTO dto) {
//	        return AnneeScolaireDTO.updateDTO(dto.getId(), dto);
	        return enseignementService.saveDTO(dto);
	    }
	    

	    @PostMapping
	    @RequestMapping("/update/matiere")
	    @ResponseStatus(code = HttpStatus.OK)
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_UPDATE + "')")
	    public EnseignementDTO updateMatiere(@RequestBody EnseignementUpdateDTO dto) {
//	        return AnneeScolaireDTO.updateDTO(dto.getId(), dto);
	        return enseignementService.updateDTO(dto);
	    }

	    @DeleteMapping("/{id}")
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_DELETE + "')")
	    public void delete(@PathVariable("id") String id) {
	    	enseignementService.delete(id);
	    }
	    
	    @DeleteMapping("/{id}/{idC}/{idM}")
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_DELETE + "')")
	    public void remove(@PathVariable("id") String id,@PathVariable("idC") String idC,@PathVariable("idM") String idM) {
	    	enseignementService.removeEnseignement(id,idC,idM);
	    }
	    
	    
	    @GetMapping("/classes/{id}/{idM}")
	    public Set<Classe> findClasseProfesseur(@PathVariable("id") String id,@PathVariable("idM") String idM) {
	       return enseignementService.findClasseProfesseur(id,idM);
	    }
	    
	    @GetMapping("/matiere/{id}")
	    public Set<Matiere> findMatiereByProfesseurId(@PathVariable("id") String id) {
	       return enseignementService.findMatiereProfesseur(id);
	    }
	 

	    @RequestMapping( method = RequestMethod.GET)
	    public List<EnseignementDTO> findAll() {
	    	/*
	    	 * @RequestParam int page, @RequestParam int size, @RequestParam Optional<String[]> sort,
	            @RequestParam Optional<String> direction, @RequestParam Optional<String> motcle
	    	 * 
	    	
	        BooleanBuilder builder = new BooleanBuilder();

	        if (motcle.isPresent()) {
	            for (String mot : StringUtil.splitTextToWords(motcle.get())) {
	               // builder.or(Qen..matricule.toUpperCase().containsIgnoreCase(mot.toUpperCase()));
	            }
	        } */
	        return enseignementService.findAllDTO();
	    }

}
