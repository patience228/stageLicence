package com.id2real.gevisco.preinscription.ressources;


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

import com.id2real.gevisco.preinscription.dtos.AnneeScolaireDTO;
import com.id2real.gevisco.preinscription.entities.QAnnee_scolaire;
import com.id2real.gevisco.preinscription.services.AnneeScolaireServiceInt;
import com.id2real.gevisco.preinscription.utils.PreinscriptionConstants;
import com.id2real.socle.utils.StringUtil;
import com.querydsl.core.BooleanBuilder;

import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition.Undefined;

@RestController
@RequestMapping(PreinscriptionConstants.API_PREINSCRIPTION_ANNEE_URL)
//@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS + "','" + TiersConstants.FC_TIERS_READ + "')")
public class AnneeScolaireRessource {
	
	@Autowired
	private AnneeScolaireServiceInt anneeScolaireService;
	
	 @GetMapping("/find/{id}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public AnneeScolaireDTO find(@PathVariable("id") String id) {
	        return this.anneeScolaireService.getDTO(id);
	    }

	    @GetMapping("/count")
	    @ResponseStatus(code = HttpStatus.OK)
	    public long count() {
	        return anneeScolaireService.count();
	    }

	    @PutMapping
	    @RequestMapping("/save")
	    @ResponseStatus(code = HttpStatus.CREATED)
	   // @PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_CREATE + "')")
	    public AnneeScolaireDTO save(@RequestBody AnneeScolaireDTO dto) {

	        return this.anneeScolaireService.saveDTO(dto);
	    }

	    @PostMapping
	    @RequestMapping("/update")
	    @ResponseStatus(code = HttpStatus.OK)
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_UPDATE + "')")
	    public AnneeScolaireDTO update(@RequestBody AnneeScolaireDTO dto) {
//	        return AnneeScolaireDTO.updateDTO(dto.getId(), dto);
	        return anneeScolaireService.saveDTO(dto);
	    }

	    @DeleteMapping("/{id}")
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_DELETE + "')")
	    public void delete(@PathVariable("id") String id) {
	        anneeScolaireService.delete(id);
	    }

	    @RequestMapping(method = RequestMethod.GET)
	    public List<AnneeScolaireDTO> findAll() {
	     /*
	      * params = {"page", "size"}, 
	      * 
	      *@RequestParam int page, @RequestParam int size, @RequestParam Optional<String[]> sort,
	            @RequestParam Optional<String> direction, @RequestParam Optional<String> motcle
	    	BooleanBuilder builder = new BooleanBuilder();

	        if (motcle.isPresent()) {
	            for (String mot : StringUtil.splitTextToWords(motcle.get())) {
	                builder.or(QAnnee_scolaire.annee_scolaire.libelle.toUpperCase().containsIgnoreCase(mot.toUpperCase()));
	            }
	        }
	        builder.getValue(), page, size, direction.orElse(null), sort.orElse(null)*/
	    	System.out.println(anneeScolaireService.findAllDTO());
	        return anneeScolaireService.findAllDTO();
	    }




}
