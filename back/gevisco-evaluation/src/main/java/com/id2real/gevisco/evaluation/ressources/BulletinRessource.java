package com.id2real.gevisco.evaluation.ressources;

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

import com.id2real.gevisco.evaluation.dtos.BulletinDTO;
import com.id2real.gevisco.evaluation.services.BulletinServiceInt;
import com.id2real.gevisco.evaluation.utils.EvaluationConstants;
import com.id2real.socle.utils.StringUtil;
import com.querydsl.core.BooleanBuilder;

@RestController
@RequestMapping(EvaluationConstants.API_EVALUATION_BULLETIN_URL)
public class BulletinRessource {

	@Autowired
	private BulletinServiceInt  bulletinService;
	
	 @GetMapping("/find/{id}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public BulletinDTO find(@PathVariable("id") String id) {
	        return this.bulletinService.getDTO(id);
	    }

	    @GetMapping("/count")
	    @ResponseStatus(code = HttpStatus.OK)
	    public long count() {
	        return bulletinService.count();
	    }

	    @PutMapping
	    @RequestMapping("/save")
	    @ResponseStatus(code = HttpStatus.CREATED)
	   // @PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_CREATE + "')")
	    public BulletinDTO save(@RequestBody BulletinDTO dto) {
	        return this.bulletinService.saveDTO(dto);
	    }

	    @PostMapping
	    @RequestMapping("/update")
	    @ResponseStatus(code = HttpStatus.OK)
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_UPDATE + "')")
	    public BulletinDTO update(@RequestBody BulletinDTO dto) {
//	        return AnneeScolaireDTO.updateDTO(dto.getId(), dto);
	        return bulletinService.saveDTO(dto);
	    }

	    @DeleteMapping("/{id}")
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_DELETE + "')")
	    public void delete(@PathVariable("id") String id) {
	    	bulletinService.delete(id);
	    }

	    @RequestMapping(params = {"page", "size"}, method = RequestMethod.GET)
	    public Page<BulletinDTO> findAll(@RequestParam int page, @RequestParam int size, @RequestParam Optional<String[]> sort,
	            @RequestParam Optional<String> direction, @RequestParam Optional<String> motcle) {
	        BooleanBuilder builder = new BooleanBuilder();

	        if (motcle.isPresent()) {
	            for (String mot : StringUtil.splitTextToWords(motcle.get())) {
	             // builder.or(QBulleti.bulletin.libelle.toUpperCase().containsIgnoreCase(mot.toUpperCase()));
	            }
	        }
	        return bulletinService.findAllDTO(builder.getValue(), page, size, direction.orElse(null), sort.orElse(null));
	    }

}
