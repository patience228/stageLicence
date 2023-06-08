package com.id2real.gevisco.etudes.ressources;

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

import com.id2real.gevisco.etudes.dtos.AffectationDTO;
import com.id2real.gevisco.etudes.services.AffectationServiceInt;
import com.id2real.gevisco.etudes.utils.EtudesConstants;
import com.id2real.gevisco.inscription.dtos.InscriptionDTO;
import com.id2real.socle.utils.StringUtil;
import com.querydsl.core.BooleanBuilder;

@RestController
@RequestMapping(EtudesConstants.API_ETUDES_AFFECTATION_URL)
public class AffectationRessource {


	@Autowired
	private AffectationServiceInt affectationService;
	
	 @GetMapping("/find/{id}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public AffectationDTO find(@PathVariable("id") String id) {
	        return this.affectationService.getDTO(id);
	    }

	    @GetMapping("/count")
	    @ResponseStatus(code = HttpStatus.OK)
	    public long count() {
	        return affectationService.count();
	    }
	    
	    @GetMapping("/nbre/{classe}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public long countByClasse(@PathVariable("classe") String classe) {
	        return affectationService.CountEleveByClasseAndAnnee(classe);
	    }
	    
	    @GetMapping("/nb/{classe}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public List<?> ElevecountByClasse(@PathVariable("classe") String classe) {
	        return affectationService.EleveByClasseAndAnnee(classe);
	    }
	    
	    @GetMapping("/nbre/{classe}/{annee}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public long countByClasseAndAnnee(@PathVariable("classe") String classe, @PathVariable("annee") String annee) {
	        return affectationService.CountEleveByClasse(classe, annee);
	    }
	    
	    @GetMapping("/all")
	    public List<AffectationDTO> findElevebyall() {
	       return affectationService.findEleveBYall();
	    }
	    
	    @GetMapping("/{classe}/{annee}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public List<AffectationDTO> findAffectationByClasseAndAnnee(@PathVariable("classe") String classe, @PathVariable("annee") String annee) {
	    	System.err.println("==========================");
	    	System.err.println(classe);
	    	System.err.println(annee);
	       return affectationService.findAffectationByClasseAndAnnee(classe, annee);
	    }
	    
	    @GetMapping("/class/{annee}/{eleve}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public List<AffectationDTO>  findAffectationByAnnee( @PathVariable("annee") String annee, @PathVariable("eleve") String eleve) {
	    	System.err.println("==========================");
	    	System.err.println(eleve);
	    	System.err.println(annee);
	       return affectationService.findAffectationByAnnee(annee,eleve);
	    }

	    @PutMapping
	    @RequestMapping("/save")
	    @ResponseStatus(code = HttpStatus.CREATED)
	   // @PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_CREATE + "')")
	    public AffectationDTO save(@RequestBody AffectationDTO dto) {
	        return this.affectationService.saveDTO(dto);
	    }

	    @PostMapping
	    @RequestMapping("/update")
	    @ResponseStatus(code = HttpStatus.OK)
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_UPDATE + "')")
	    public AffectationDTO update(@RequestBody AffectationDTO dto) {
//	        return AnneeScolaireDTO.updateDTO(dto.getId(), dto);
	        return affectationService.saveDTO(dto);
	    }

	    @DeleteMapping("/{id}")
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_DELETE + "')")
	    public void delete(@PathVariable("id") String id) {
	    	affectationService.delete(id);
	    }

	    @RequestMapping(method = RequestMethod.GET)
	    public List<AffectationDTO> findAll() {
	    	/*
	    	 *@RequestParam int page, @RequestParam int size, @RequestParam Optional<String[]> sort,
	            @RequestParam Optional<String> direction, @RequestParam Optional<String> motcle 
	    	 * 
	    	
	        BooleanBuilder builder = new BooleanBuilder();

	        if (motcle.isPresent()) {
	            for (String mot : StringUtil.splitTextToWords(motcle.get())) {
	               //builder.or(Qaf.n.matricule.toUpperCase().containsIgnoreCase(mot.toUpperCase()));
	            }
	        } */
	        return affectationService.findAllDTO();
	    }

}
