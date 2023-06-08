package com.id2real.gevisco.utilisateurs.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.utilisateurs.dtos.UserDTO;
import com.id2real.gevisco.utilisateurs.entities.User;
import com.id2real.gevisco.utilisateurs.repositories.UserRepository;
import com.id2real.socle.account.services.UtilisateurServiceInt;
import com.id2real.socle.core.services.GenericService;

@Service
@Transactional
public class UserService extends GenericService<User, String, UserDTO> implements UserServiceInt{

	public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository repository;

	@Autowired
	private UtilisateurServiceInt user;
	
	@Override
	protected JpaRepository<User, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<User> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public UserDTO getDTO(User t) {
		UserDTO dto = new UserDTO();
		dto.setId(t.getId());
		dto.setActive(t.isActive());
		dto.setFonction(t.getFonction());
		dto.setUtilisateur(this.user.getDTO(t.getUtilisateur()));
		return dto;
	}

	@Override
	public User getObjectFromDTO(UserDTO dto) {
		User p = new User();
		System.err.println(dto.getUtilisateur().getId());

		if (dto.getId() != null && this.exists(dto.getId())) {
            p = this.find(dto.getId());
        }
		p.setId(dto.getId());
		p.setActive(dto.isActive());
		p.setFonction(dto.getFonction());

		if (dto.getUtilisateur() != null && dto.getUtilisateur().getId() != null) {
			System.err.println(dto);
			p.setUtilisateur(this.user.find(dto.getUtilisateur().getId()));
		}
		return p;
	}

}
