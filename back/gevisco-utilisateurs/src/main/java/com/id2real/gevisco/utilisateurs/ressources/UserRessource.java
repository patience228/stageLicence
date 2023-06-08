package com.id2real.gevisco.utilisateurs.ressources;

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

import com.id2real.gevisco.utilisateurs.dtos.UserDTO;
import com.id2real.gevisco.utilisateurs.entities.QUser;
import com.id2real.gevisco.utilisateurs.services.UserServiceInt;
import com.id2real.gevisco.utilisateurs.utils.UtilisateursConstants;
import com.id2real.socle.utils.StringUtil;
import com.querydsl.core.BooleanBuilder;



@RestController
@RequestMapping(UtilisateursConstants.API_UTILISATEURS_CAISSIER_URL)
public class UserRessource {


	@Autowired
	private UserServiceInt userService;
	
	 @GetMapping("/find/{id}")
	    @ResponseStatus(code = HttpStatus.OK)
	    public UserDTO find(@PathVariable("id") String id) {
	        return this.userService.getDTO(id);
	    }

	    @GetMapping("/count")
	    @ResponseStatus(code = HttpStatus.OK)
	    public long count() {
	        return userService.count();
	    }

	    @PutMapping
	    @RequestMapping("/save")
	    @ResponseStatus(code = HttpStatus.CREATED)
	    public UserDTO save(@RequestBody UserDTO dto) {
	        return this.userService.saveDTO(dto);
	    }

	    @PostMapping
	    @RequestMapping("/update")
	    @ResponseStatus(code = HttpStatus.OK)
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_UPDATE + "')")
	    public UserDTO update(@RequestBody UserDTO dto) {
//	        return AnneeScolaireDTO.updateDTO(dto.getId(), dto);
	        return userService.saveDTO(dto);
	    }

	    @DeleteMapping("/{id}")
	    //@PreAuthorize("hasAnyAuthority('" + CoreConstants.SUPERADMIN_ROLE + "','" + TiersConstants.FC_TIERS_DELETE + "')")
	    public void delete(@PathVariable("id") String id) {
	    	userService.delete(id);
	    }

	    @RequestMapping(method = RequestMethod.GET)
	    public List<UserDTO> findAll() {
	    	/*
	    	 * @RequestParam int page, @RequestParam int size, @RequestParam Optional<String[]> sort,
	            @RequestParam Optional<String> direction, @RequestParam Optional<String> motcle
	    	 * 
	    	
	        BooleanBuilder builder = new BooleanBuilder();

	        if (motcle.isPresent()) {
	            for (String mot : StringUtil.splitTextToWords(motcle.get())) {
	                builder.or(QUser.user.utilisateur.firstName.toUpperCase().containsIgnoreCase(mot.toUpperCase()));
	            }
	        } */
	        return userService.findAllDTO();
	    }
}
