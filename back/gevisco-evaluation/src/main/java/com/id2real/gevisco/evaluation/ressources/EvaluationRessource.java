package com.id2real.gevisco.evaluation.ressources;

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

import com.id2real.gevisco.evaluation.dtos.EvaluationDTO;
import com.id2real.gevisco.evaluation.services.EvaluationServiceInt;
import com.id2real.gevisco.evaluation.utils.EvaluationConstants;
import com.id2real.socle.utils.StringUtil;
import com.querydsl.core.BooleanBuilder;

@RestController
@RequestMapping(EvaluationConstants.API_EVALUATION_URL)
public class EvaluationRessource {

	@Autowired
	private EvaluationServiceInt evaluationService;
	
	 @GetMapping("/find/{id}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public EvaluationDTO find(@PathVariable("id") String id) {
	        return this.evaluationService.getDTO(id);
	    }

	    @GetMapping("/count")
	    @ResponseStatus(code = HttpStatus.OK)
	    public long count() {
	        return evaluationService.count();
	    }

	    @PutMapping
	    @RequestMapping("/save")
	    @ResponseStatus(code = HttpStatus.CREATED)
	   // @PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_CREATE + "')")
	    public EvaluationDTO save(@RequestBody EvaluationDTO dto) {
	        return this.evaluationService.saveDTO(dto);
	    }
	    
	    @GetMapping("/eval/{id}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public List<EvaluationDTO> findbyAnnee(@PathVariable("id") String id) {
	        return this.evaluationService.findByAnnee(id);
	    }

	    @PostMapping
	    @RequestMapping("/update")
	    @ResponseStatus(code = HttpStatus.OK)
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_UPDATE + "')")
	    public EvaluationDTO update(@RequestBody EvaluationDTO dto) {
//	        return AnneeScolaireDTO.updateDTO(dto.getId(), dto);
	        return evaluationService.saveDTO(dto);
	    }

	    @DeleteMapping("/{id}")
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_DELETE + "')")
	    public void delete(@PathVariable("id") String id) {
	    	evaluationService.delete(id);
	    }

	    @RequestMapping(method = RequestMethod.GET)
	    public List<EvaluationDTO> findAll() {
	    	/*
	    	 * @RequestParam int page, @RequestParam int size, @RequestParam Optional<String[]> sort,
	            @RequestParam Optional<String> direction, @RequestParam Optional<String> motcle
	    	 * 
	    	
	        BooleanBuilder builder = new BooleanBuilder();

	        if (motcle.isPresent()) {
	            for (String mot : StringUtil.splitTextToWords(motcle.get())) {
	             // builder.or(QEvaluatio.evaluation.libelle.toUpperCase().containsIgnoreCase(mot.toUpperCase()));
	            }
	        } */
	        return evaluationService.findAllDTO();
	    }
}
