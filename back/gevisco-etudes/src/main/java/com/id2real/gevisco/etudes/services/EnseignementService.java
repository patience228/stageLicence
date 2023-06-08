package com.id2real.gevisco.etudes.services;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.id2real.gevisco.etudes.dtos.ClasseDTO;
import com.id2real.gevisco.etudes.dtos.EnseignementDTO;
import com.id2real.gevisco.etudes.dtos.EnseignementUpdateDTO;
import com.id2real.gevisco.etudes.dtos.MatiereDTO;
import com.id2real.gevisco.etudes.dtos.ProfesseurDTO;
import com.id2real.gevisco.etudes.entities.Classe;
import com.id2real.gevisco.etudes.entities.Enseignement;
import com.id2real.gevisco.etudes.entities.Matiere;
import com.id2real.gevisco.etudes.entities.Professeur;
import com.id2real.gevisco.etudes.repositories.EnseignementRepository;
import com.id2real.socle.core.services.GenericService;

@Service
@Transactional
public class EnseignementService extends GenericService<Enseignement, String, EnseignementDTO> implements EnseignementServiceInt{

	
	public static final Logger LOG = LoggerFactory.getLogger(EnseignementService.class);

	@Autowired
	private EnseignementRepository repository;

	@Autowired
	private MatiereServiceInt matiere;
	
	@Autowired
	private ClasseServiceInt classe;
	
	@Autowired
	private ProfesseurServiceInt professeur;
	
	@Override
	protected JpaRepository<Enseignement, String> getRepository() {
		return repository;
	}

	@Override
	protected QuerydslPredicateExecutor<Enseignement> getQuerydslPredicateExecutor() {
		return repository;
	}

	@Override
	public EnseignementDTO getDTO(Enseignement t) {
		// TODO Auto-generated method stub
				return null;
	}

	@Override
	public Enseignement getObjectFromDTO(EnseignementDTO dto) {
		// TODO Auto-generated method stub
				return null;
	}

	@Override
	public void removeEnseignement( String professeur_id,String classe_id, String matiere_id) {
		Enseignement cc = repository.findByMatiereIDAndProfesseurIDAndClasseID(matiere_id,professeur_id,classe_id);
		System.err.println(cc);
		
		if(cc!=null) {
			System.err.println(cc.getId());
						this.delete(cc.getId());
			//cbon			
					}
		
	}

	@Override
	public EnseignementDTO findClasseByProfesseurID(String id_pof,String id_matiere) {
		EnseignementDTO dto = new EnseignementDTO();
		
		//Set<ProfDTO> Prof = new HashSet<>();
		if(id_pof!= null && this.professeur.exists(id_pof) && id_matiere!= null && this.matiere.exists(id_matiere)) {
			Professeur t = this.professeur.find(id_pof);
			Matiere m = this.matiere.find(id_matiere);
		//	Professeur t = this.professeur.find(id);
			
			ProfesseurDTO p = new ProfesseurDTO();
			
			p.setId(t.getId());
			p.setActive(t.isActive());
			p.setNationalite(t.getNationalite());
			p.setLieuNaissance(t.getLieu_naissance());
			p.setDateNaissance(t.getDate_naissance());
			p.setNom(t.getNom());
			p.setPrenom(t.getPrenom());
			p.setAdresse(t.getAdresse());
			p.setSexe(t.getSexe());
			p.setTelephone(t.getTelephone());
			
			
			MatiereDTO s = new MatiereDTO();
			s.setId(m.getId());
			s.setLibelle(m.getLibelle());
			s.setCoefficient(m.getCoefficient());
			s.setType(m.getType());
			
			dto.setProfesseur(p);
			dto.setMatiere(s);
			

			repository.findClasseByProfesseurIDAndMatiereID(id_pof,id_matiere).forEach(prof -> {
				dto.addClasse(prof);
				
			});
			
			
			
		}
		return dto;
		
	}
	
	
/*
	@Override
	public EnseignementDTO findMatiereByProfesseurID(String id) {
		EnseignementDTO dto = new EnseignementDTO();
		
		//Set<ProfDTO> Prof = new HashSet<>();
		if(id!= null && this.professeur.exists(id)) {
			Professeur t = this.professeur.find(id);
		//	Professeur t = this.professeur.find(id);
			
			ProfesseurDTO p = new ProfesseurDTO();
			
			p.setId(t.getId());
			p.setNationalite(t.getNationalite());
			p.setLieuNaissance(t.getLieu_naissance());
			p.setDateNaissance(t.getDate_naissance());
			p.setNom(t.getNom());
			p.setPrenom(t.getPrenom());
			p.setAdresse(t.getAdresse());
			p.setSexe(t.getSexe());
			p.setTelephone(t.getTelephone());
			
			dto.setProfesseur(p);


			repository.findMatiereByProfesseurID(id).forEach(prof -> {
				dto.addMatiere(prof);
				
			});
			
		}
		return dto;
	}
	
*/

@Override
public EnseignementDTO saveDTO(EnseignementDTO t) {
	/*System.out.println("==============================Adhérent====================================");
	System.out.println(t.getAdherent().getNom());
	System.out.println("==================================================================");
	System.out.println("============================Professions======================================");
	System.out.println(t.getProfessions());
	System.out.println("==================================================================");
	*/
	
	if(t.getProfesseur()!= null && this.professeur.exists(t.getProfesseur().getId()) &&
			t.getMatiere()!= null && this.matiere.exists(t.getMatiere().getId()) &&
			t.getClasse()!= null && !t.getClasse().isEmpty()  ) {
		
		Professeur prof = this.professeur.getObjectFromDTO(t.getProfesseur());
		Matiere mat = this.matiere.getObjectFromDTO(t.getMatiere());
		for(ClasseDTO cr: t.getClasse()) {
			System.out.println("==================================================================");
			System.out.println(cr);
			System.out.println("==================================================================");

			Enseignement pa = repository.findByMatiereIDAndProfesseurIDAndClasseID(cr.getId(), t.getProfesseur().getId(),t.getMatiere().getId());
			System.out.println("==================================================================");
			System.out.println(pa);
			System.out.println("==================================================================");

			if(pa==null) {
				if(cr.getId()!= null && this.classe.exists(cr.getId())) {
					Enseignement cc = new Enseignement();
					cc.setProfesseur(prof);
					cc.setMatiere(mat);
					cc.setClasse(this.classe.getObjectFromDTO(cr));
					System.out.println("==================================================================");
					System.out.println(cc);
					System.out.println("==================================================================");
					//cc.setDateAjout(t.getDateAjout());
					cc = this.save(cc);
				}
			}
		}
		return this.findClasseByProfesseurID(t.getProfesseur().getId(),t.getMatiere().getId()); 
	}
	
	return null;
}



@Override
	public EnseignementDTO updateDTO(EnseignementUpdateDTO t) {
		/*System.out.println("==============================Adhérent====================================");
		System.out.println(t.getAdherent().getNom());
		System.out.println("==================================================================");
		System.out.println("============================Professions======================================");
		System.out.println(t.getProfessions());
		System.out.println("==================================================================");
		*/
	System.err.println(t.getProfesseur());
	System.err.println(t.getMatiereOld());
	System.err.println(t.getMatiere());

		if(	t.getProfesseur()!= null && this.professeur.exists(t.getProfesseur().getId()) &&
				t.getMatiereOld()!= null && this.matiere.exists(t.getMatiereOld().getId()) &&
				t.getMatiere()!= null && this.matiere.exists(t.getMatiere().getId())  ) {
			
			EnseignementDTO tmp= this.findClasseByProfesseurID(t.getProfesseur().getId(),t.getMatiereOld().getId());
			
			for(ClasseDTO cr: tmp.getClasse()) {
				System.out.println("==================================================================");
				System.out.println(cr);
				System.out.println("==================================================================");

				Enseignement pa = repository.findByMatiereIDAndProfesseurIDAndClasseID(cr.getId(), t.getProfesseur().getId(),t.getMatiereOld().getId());
				System.out.println("==================================================================");
				System.out.println(pa);
				System.out.println("==================================================================");

				if(pa!=null) {
					if(t.getMatiere().getId()!= null && this.matiere.exists(t.getMatiere().getId())) {
						pa.setMatiere(
								this.matiere.find(t.getMatiere().getId())
								);
						pa = this.save(pa);
					}
				}
			}
			return this.findClasseByProfesseurID(t.getProfesseur().getId(),t.getMatiere().getId()); 
		}
		
		return null;
	}

	
	
	public Set<Classe> findClasseNotInTableByProfesseurID(String id) {
		Set<Classe> classe = new HashSet<>();

		repository.findClasseNotInProfesseurID(id).forEach(prof -> {
			classe.add(prof);
		});
		return classe;
	}
	
	public Set<Matiere> findMatiereNotInTableByProfesseurID(String id) {
		Set<Matiere> matiere = new HashSet<>();

		repository.findMatiereNotInProfesseurID(id).forEach(prof -> {
			matiere.add(prof);
		});
		return matiere;
	}

	
	


	
	public Set<Classe> findClasseProfesseur(String id_prof,String id_mat) {
		Set<Classe> classe = new HashSet<>();

		repository.findClasseByProfesseurIDAndMatiereID(id_prof,id_mat).forEach(prof -> {
			classe.add(prof);
		});
		return classe;
	}
	
	@Override
		public Set<Matiere> findMatiereProfesseur(String id) {
				Set<Matiere> matiere = new HashSet<>();
		
				repository.findMatiereByProfesseurID(id).forEach(prof -> {
					matiere.add(prof);
				});
				return matiere;
			}



	
	

}
