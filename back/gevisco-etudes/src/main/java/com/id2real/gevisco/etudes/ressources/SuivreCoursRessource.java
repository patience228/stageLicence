package com.id2real.gevisco.etudes.ressources;

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

import com.id2real.gevisco.etudes.dtos.SuivreCoursDTO;
import com.id2real.gevisco.etudes.services.SuivreCoursServiceInt;
import com.id2real.gevisco.etudes.utils.EtudesConstants;
import com.id2real.socle.utils.StringUtil;
import com.querydsl.core.BooleanBuilder;

@RestController
@RequestMapping(EtudesConstants.API_ETUDES_COURS_URL)
public class SuivreCoursRessource {


	@Autowired
	private SuivreCoursServiceInt  suivreCoursService;
	
	 @GetMapping("/find/{id}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public SuivreCoursDTO find(@PathVariable("id") String id) {
	        return this.suivreCoursService.getDTO(id);
	    }

	    @GetMapping("/count")
	    @ResponseStatus(code = HttpStatus.OK)
	    public long count() {
	        return suivreCoursService.count();
	    }

	    @PutMapping
	    @RequestMapping("/save")
	    @ResponseStatus(code = HttpStatus.CREATED)
	   // @PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_CREATE + "')")
	    public SuivreCoursDTO save(@RequestBody SuivreCoursDTO dto) {
	        return this.suivreCoursService.saveDTO(dto);
	    }

	    @PostMapping
	    @RequestMapping("/update")
	    @ResponseStatus(code = HttpStatus.OK)
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_UPDATE + "')")
	    public SuivreCoursDTO update(@RequestBody SuivreCoursDTO dto) {
//	        return AnneeScolaireDTO.updateDTO(dto.getId(), dto);
	        return suivreCoursService.saveDTO(dto);
	    }

	    @DeleteMapping("/{id}")
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_DELETE + "')")
	    public void delete(@PathVariable("id") String id) {
	    	suivreCoursService.delete(id);
	    }

	    @RequestMapping(params = {"page", "size"}, method = RequestMethod.GET)
	    public Page<SuivreCoursDTO> findAll(@RequestParam int page, @RequestParam int size, @RequestParam Optional<String[]> sort,
	            @RequestParam Optional<String> direction, @RequestParam Optional<String> motcle) {
	        BooleanBuilder builder = new BooleanBuilder();

	        if (motcle.isPresent()) {
	            for (String mot : StringUtil.splitTextToWords(motcle.get())) {
	             //   builder.or(QAffecta..matricule.toUpperCase().containsIgnoreCase(mot.toUpperCase()));
	            }
	        }
	        return suivreCoursService.findAllDTO(builder.getValue(), page, size, direction.orElse(null), sort.orElse(null));
	    }

}
