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

import com.id2real.gevisco.evaluation.dtos.NoteDTO;
import com.id2real.gevisco.evaluation.services.NoteServiceInt;
import com.id2real.gevisco.evaluation.utils.EvaluationConstants;
import com.id2real.socle.utils.StringUtil;
import com.querydsl.core.BooleanBuilder;

@RestController
@RequestMapping(EvaluationConstants.API_EVALUATION_NOTE_URL)
public class NoteRessource {

	@Autowired
	private NoteServiceInt  noteService;
	
	 @GetMapping("/find/{id}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public NoteDTO find(@PathVariable("id") String id) {
	        return this.noteService.getDTO(id);
	    }

	    @GetMapping("/count")
	    @ResponseStatus(code = HttpStatus.OK)
	    public long count() {
	        return noteService.count();
	    }
	    
	    @GetMapping("/{libelle}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public List<?> NoteByLibelle(@PathVariable("libelle") String libelle) {
	        return noteService.NoteByLibelle(libelle);
	    }

	    @PutMapping
	    @RequestMapping("/save")
	    @ResponseStatus(code = HttpStatus.CREATED)
	   // @PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_CREATE + "')")
	    public NoteDTO save(@RequestBody NoteDTO dto) {
	        return this.noteService.saveDTO(dto);
	    }

	    @PostMapping
	    @RequestMapping("/update")
	    @ResponseStatus(code = HttpStatus.OK)
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_UPDATE + "')")
	    public NoteDTO update(@RequestBody NoteDTO dto) {
//	        return AnneeScolaireDTO.updateDTO(dto.getId(), dto);
	        return noteService.saveDTO(dto);
	    }

	    @DeleteMapping("/{id}")
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_DELETE + "')")
	    public void delete(@PathVariable("id") String id) {
	    	noteService.delete(id);
	    }

	    @RequestMapping( method = RequestMethod.GET)
	    public List<NoteDTO> findAll() {
	      /*  BooleanBuilder builder = new BooleanBuilder();

	        if (motcle.isPresent()) {
	            for (String mot : StringUtil.splitTextToWords(motcle.get())) {
	             // builder.or(QNote.note.m.toUpperCase().containsIgnoreCase(mot.toUpperCase()));
	            }
	        }*/
	        return noteService.findAllDTO();
	    }
}
