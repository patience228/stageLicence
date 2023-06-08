package com.id2real.gevisco;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.id2real.TestUtils;
import com.id2real.gevisco.preinscription.dtos.AnneeScolaireDTO;
import com.id2real.gevisco.preinscription.dtos.EleveDTO;
import com.id2real.gevisco.preinscription.dtos.NiveauDTO;
import com.id2real.gevisco.preinscription.dtos.PreinscriptionDTO;
import com.id2real.gevisco.preinscription.services.AnneeScolaireServiceInt;
import com.id2real.gevisco.preinscription.services.EleveServiceInt;
import com.id2real.gevisco.preinscription.services.NiveauServiceInt;
import com.id2real.gevisco.preinscription.services.PreinscriptionServiceInt;
import com.id2real.gevisco.preinscription.utils.PreinscriptionConstants;

import com.id2real.socle.account.dtos.ProfilDTO;
import com.id2real.socle.account.dtos.UtilisateurDTO;
import com.id2real.socle.account.services.ProfilServiceInt;
import com.id2real.socle.account.services.UtilisateurServiceInt;
import com.id2real.socle.account.utils.SecConstante;
import com.id2real.socle.core.services.PaysServiceInt;
import com.id2real.socle.core.services.VilleServiceInt;
import com.id2real.socle.core.utils.CoreConstants;
import com.id2real.socle.tiers.dtos.AdresseDTO;
import com.id2real.socle.tiers.dtos.EmailDTO;
import com.id2real.socle.tiers.dtos.PersonneMoraleDTO;
import com.id2real.socle.tiers.dtos.PersonnePhysiqueDTO;
import com.id2real.socle.tiers.dtos.TelephoneDTO;
import com.id2real.socle.tiers.services.AdresseServiceInt;
import com.id2real.socle.tiers.services.EmailTiersServiceInt;
import com.id2real.socle.tiers.services.PersonneMoraleServiceInt;
import com.id2real.socle.tiers.services.PersonnePhysiqueServiceInt;
import com.id2real.socle.tiers.services.ProfessionServiceInt;
import com.id2real.socle.tiers.services.ReferentielTiersServiceInt;
import com.id2real.socle.tiers.services.TelephoneServiceInt;
import com.id2real.socle.tiers.utils.Genre;
import com.id2real.socle.tiers.utils.GroupeSanguin;
import com.id2real.socle.tiers.utils.RhesusSanguin;
import com.id2real.socle.tiers.utils.TiersConstants;
import com.id2real.socle.tiers.utils.TypeAdresse;
import com.id2real.socle.tiers.utils.TypeTelephone;
import com.id2real.socle.tiers.utils.TypeTiers;
import com.id2real.socle.utils.Fileutil;
import com.id2real.socle.utils.dtos.DefaultDTO;

//import liquibase.pro.packaged.el;

@RunWith(SpringRunner.class)
@AutoConfigureRestDocs
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PreinscriptionApiDocumentationIntegrationTest {


   // @Rule
    //public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation(TestUtils.TARGET_DIRECTORY);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NiveauServiceInt niveauService;

    @Autowired
    private AnneeScolaireServiceInt anneeScolaireService;

    @Autowired
    private EleveServiceInt eleveService;
  
    @Autowired
    private PreinscriptionServiceInt preinscriptionService;
    @Autowired
    private AdresseServiceInt adresseService;
    @Autowired
    private EmailTiersServiceInt emailTiersService;
    @Autowired
    private TelephoneServiceInt telephoneService;

    @Autowired
    private ProfilServiceInt profilService;
    @Autowired
    private ProfessionServiceInt professionService;
    @Autowired
    private UtilisateurServiceInt utilisateurService;

    @Autowired
    ResourceLoader resourceLoader;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String accessToken;

    // Define class default attributes
    public final String DEFAULT_USERNAME = "patience";
    public final String DEFAULT_EMAIL = "akousiabi@gmail.com";
    public final String DEFAULT_PASSWORD = "patience01";
    public final String LOGIN_URL = "/api/security/auth/login?username=" + this.DEFAULT_USERNAME
            + "&password=" + this.DEFAULT_PASSWORD;


    private String autorizationHeader() throws Exception {
        if (accessToken != null) {
            System.out.println("Already logged In");
            return "Bearer " + accessToken;
        }

        MvcResult result = mockMvc
                .perform(post(new URI(this.LOGIN_URL)).contentType(MediaType.APPLICATION_JSON)
                ).andDo(print()).andExpect(status().isOk()).andReturn();

        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        if (jsonObject.has("accessToken") && jsonObject.has("refreshToken")) {
            accessToken = (String) jsonObject.get("accessToken");
            return "Bearer " + accessToken;
        }
        throw new Exception("httpHeaders not defined");
    }

    @Test
    public void test01_createUser() throws Exception {

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setEmail(this.DEFAULT_EMAIL);
        utilisateurDTO.setActive(true);
        utilisateurDTO.setPassword(this.DEFAULT_PASSWORD);
        utilisateurDTO.setConfirmPassword(this.DEFAULT_PASSWORD);
        utilisateurDTO.setUsername(this.DEFAULT_USERNAME);
        utilisateurDTO.setFirstName("paty");
        utilisateurDTO.setLastName("patience");

        Set<ProfilDTO> profils = new HashSet<>();
        profils.add(this.profilService.getDTO(CoreConstants.SUPERADMIN_ROLE));
        utilisateurDTO.setProfils(profils);

        this.mockMvc.perform(post(SecConstante.API_UTILISATEUR_URL + "/save").contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(utilisateurDTO))
        )
                .andExpect(status().isCreated());
            
              
    }
    
    @Test
    public void test02_createPreinscription() throws Exception {
    	
    	 AnneeScolaireDTO anneeScolaireDTO = new AnneeScolaireDTO();
         anneeScolaireDTO.setLibelle("Annee scolaire 2019-2020");
       //  anneeScolaireDTO.setDateDebut(Instant.now());
       //  anneeScolaireDTO.setDateFin(Instant.now());
         anneeScolaireDTO=this.anneeScolaireService.saveDTO(anneeScolaireDTO);
    	
         NiveauDTO niveauDTO = new NiveauDTO();
         niveauDTO.setLibelle("sixieme");
         niveauDTO=this.niveauService.saveDTO(niveauDTO);
         
         EleveDTO eleveDTO = new EleveDTO();
         eleveDTO.setMatricule("EPL-001");
         eleveDTO.setNom("kodjo");
         eleveDTO.setPrenom("lolo");
         eleveDTO.setSexe("masculin");
         eleveDTO.setAdresse("adidogome");
         eleveDTO.setNationalite("Togolaise");
         eleveDTO.setLieuNaissance("Lome");
        // eleveDTO.setDateNaissance(Instant.now());
         eleveDTO.setNomParent("TOTO");
         eleveDTO.setPrenomParent("Abalo");
         eleveDTO.setProfessionParent("Ingenieur");
         eleveDTO.setAdresseParent("Lome");
         eleveDTO.setTelephoneParent("0022890345678");
         eleveDTO.setImage("dfgdgdffgd");
         eleveDTO=this.eleveService.saveDTO(eleveDTO);
         
        PreinscriptionDTO preinscriptionDTO = new PreinscriptionDTO();
        preinscriptionDTO.setFrais(3500);
        preinscriptionDTO.setMoyenne(12);;
        preinscriptionDTO.setClasseAnterieure("sixieme");
        preinscriptionDTO.setEcoleProvenance("providence");
      //  preinscriptionDTO.setDatePreinscription(Instant.now());
        preinscriptionDTO.setAnnee(anneeScolaireDTO);
        preinscriptionDTO.setNiveau(niveauDTO);
        preinscriptionDTO.setEleve(eleveDTO);

        this.mockMvc.perform(put(PreinscriptionConstants.API_PREINSCRIPTION_URL + "/save").contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, this.autorizationHeader())
                .content(this.objectMapper.writeValueAsString(preinscriptionDTO)))
                .andExpect(status().isCreated())
                .andDo(document("create-Preinscription"));
    }


    @Test
    public void test03_updatePreinscription() throws Exception {
        NiveauDTO niveauDTO = new NiveauDTO();
        niveauDTO.setLibelle("sixieme");
        niveauDTO=this.niveauService.saveDTO(niveauDTO);
    	
    	 PreinscriptionDTO p =this.preinscriptionService.findFirstDTO();
    	p.setMoyenne(13);
    	p.setNiveau(niveauDTO);

        this.mockMvc.perform(post(PreinscriptionConstants.API_PREINSCRIPTION_URL + "/update").contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, this.autorizationHeader())
                .content(this.objectMapper.writeValueAsString(p))
        )
                .andExpect(status().isOk())
                .andDo(document("update-Preinscription"));
    }

    @Test
    public void test04_listPreinscription() throws Exception {
    	 AnneeScolaireDTO anneeScolaireDTO = new AnneeScolaireDTO();
         anneeScolaireDTO.setLibelle("Annee scolaire 2019-2020");
       //  anneeScolaireDTO.setDateDebut(Instant.now());
       //  anneeScolaireDTO.setDateFin(Instant.now());
         anneeScolaireDTO=this.anneeScolaireService.saveDTO(anneeScolaireDTO);
    	
         NiveauDTO niveauDTO = new NiveauDTO();
         niveauDTO.setLibelle("sixieme");
         niveauDTO=this.niveauService.saveDTO(niveauDTO);
         
         EleveDTO eleveDTO = new EleveDTO();
         eleveDTO.setMatricule("EPL-001");
         eleveDTO.setNom("kodjo");
         eleveDTO.setPrenom("lolo");
         eleveDTO.setSexe("masculin");
         eleveDTO.setAdresse("adidogome");
         eleveDTO.setNationalite("Togolaise");
         eleveDTO.setLieuNaissance("Lome");
        // eleveDTO.setDateNaissance(Instant.now());
         eleveDTO.setNomParent("TOTO");
         eleveDTO.setPrenomParent("Abalo");
         eleveDTO.setProfessionParent("Ingenieur");
         eleveDTO.setAdresseParent("Lome");
         eleveDTO.setTelephoneParent("0022890345678");
         eleveDTO.setImage("dfgdgdffgd");
         eleveDTO=this.eleveService.saveDTO(eleveDTO);
         
        PreinscriptionDTO preinscriptionDTO = new PreinscriptionDTO();
        preinscriptionDTO.setFrais(3500);
        preinscriptionDTO.setMoyenne(12);;
        preinscriptionDTO.setClasseAnterieure("sixieme");
        preinscriptionDTO.setEcoleProvenance("providence");
      //  preinscriptionDTO.setDatePreinscription(Instant.now());
        preinscriptionDTO.setAnnee(anneeScolaireDTO);
        preinscriptionDTO.setNiveau(niveauDTO);
        preinscriptionDTO.setEleve(eleveDTO);
        
    	this.preinscriptionService.findFirstDTO();
    	 
    	 this.mockMvc.perform(get(PreinscriptionConstants.API_PREINSCRIPTION_URL + "?page=0&size=10" ).contentType(MediaType.APPLICATION_JSON)
                 .header(HttpHeaders.AUTHORIZATION, this.autorizationHeader()))
                 .andExpect(status().isOk())
                 .andDo(document("list-Preinscription"));
    }
    
    
    @Test
    public void test05_deletePreinscription() throws Exception {
      	 AnneeScolaireDTO anneeScolaireDTO = new AnneeScolaireDTO();
         anneeScolaireDTO.setLibelle("Annee scolaire 2019-2020");
       //  anneeScolaireDTO.setDateDebut(Instant.now());
       //  anneeScolaireDTO.setDateFin(Instant.now());
         anneeScolaireDTO=this.anneeScolaireService.saveDTO(anneeScolaireDTO);
    	
         NiveauDTO niveauDTO = new NiveauDTO();
         niveauDTO.setLibelle("sixieme");
         niveauDTO=this.niveauService.saveDTO(niveauDTO);
         
         EleveDTO eleveDTO = new EleveDTO();
         eleveDTO.setMatricule("EPL-001");
         eleveDTO.setNom("kodjo");
         eleveDTO.setPrenom("lolo");
         eleveDTO.setSexe("masculin");
         eleveDTO.setAdresse("adidogome");
         eleveDTO.setNationalite("Togolaise");
         eleveDTO.setLieuNaissance("Lome");
        // eleveDTO.setDateNaissance(Instant.now());
         eleveDTO.setNomParent("TOTO");
         eleveDTO.setPrenomParent("Abalo");
         eleveDTO.setProfessionParent("Ingenieur");
         eleveDTO.setAdresseParent("Lome");
         eleveDTO.setTelephoneParent("0022890345678");
         eleveDTO.setImage("dfgdgdffgd");
         eleveDTO=this.eleveService.saveDTO(eleveDTO);
         
        PreinscriptionDTO preinscriptionDTO = new PreinscriptionDTO();
        preinscriptionDTO.setFrais(3500);
        preinscriptionDTO.setMoyenne(12);;
        preinscriptionDTO.setClasseAnterieure("sixieme");
        preinscriptionDTO.setEcoleProvenance("providence");
      //  preinscriptionDTO.setDatePreinscription(Instant.now());
        preinscriptionDTO.setAnnee(anneeScolaireDTO);
        preinscriptionDTO.setNiveau(niveauDTO);
        preinscriptionDTO.setEleve(eleveDTO);
      
    	preinscriptionDTO = this.preinscriptionService.saveDTO(preinscriptionDTO);
        this.mockMvc.perform(delete(PreinscriptionConstants.API_PREINSCRIPTION_URL + "/" + preinscriptionDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, this.autorizationHeader())
        //                .content(this.objectMapper.writeValueAsString(refTiers))
        )
                .andExpect(status().isOk())
                .andDo(document("delete-preinscription"));
    }
    
    
    //annee
    
    @Test
    public void test06_createAnneeScolaire() throws Exception {

        AnneeScolaireDTO anneeScolaireDTO = new AnneeScolaireDTO();
        anneeScolaireDTO.setLibelle("Annee scolaire 2019-2020");
       // anneeScolaireDTO.setDateDebut(Instant.now());
       // anneeScolaireDTO.setDateFin(Instant.now());
       
        anneeScolaireDTO = this.anneeScolaireService.saveDTO(anneeScolaireDTO);

        this.mockMvc.perform(post(PreinscriptionConstants.API_PREINSCRIPTION_ANNEE_URL + "/save").contentType(MediaType.APPLICATION_JSON)
        		 .header(HttpHeaders.AUTHORIZATION, this.autorizationHeader())
                 .content(this.objectMapper.writeValueAsString(anneeScolaireDTO)))
        
                .andExpect(status().isCreated())
                .andDo(document("create-anneeScolaire")
                );
    }
    
    @Test
    public void test07_updateAnneeScolaire() throws Exception {

    	AnneeScolaireDTO anneetoUpdate = new AnneeScolaireDTO();
    	anneetoUpdate.setLibelle("Annee scolaire 2020-2021");
    	//anneetoUpdate.setDateDebut(Instant.now());
    	//anneetoUpdate.setDateFin(Instant.now());
        anneetoUpdate = this.anneeScolaireService.saveDTO(anneetoUpdate);

        this.mockMvc.perform(post(PreinscriptionConstants.API_PREINSCRIPTION_ANNEE_URL + "/update").contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, this.autorizationHeader())
                .content(this.objectMapper.writeValueAsString(anneetoUpdate))
        )
                .andExpect(status().isOk())
                .andDo(document("update-anneeScolaire"));
    }
    
    @Test
    public void test08_listAnneeScolaire() throws Exception {
    	AnneeScolaireDTO annees = new AnneeScolaireDTO();
    	annees.setLibelle("Annee scolaire 2020-2021");
    	//annees.setDateDebut(Instant.now());
    	//annees.setDateFin(Instant.now());
        this.anneeScolaireService.saveDTO(annees);
        
        this.mockMvc.perform(get(PreinscriptionConstants.API_PREINSCRIPTION_ANNEE_URL + "?page=0&size=10").contentType(MediaType.APPLICATION_JSON)
                //                .content(this.objectMapper.writeValueAsString(refTiers))
                .header(HttpHeaders.AUTHORIZATION, this.autorizationHeader())
        )
                .andExpect(status().isOk())
                .andDo(document("list-anneeScolaire"));
    }

    @Test
    public void test10_deleteAnneeScolaire() throws Exception {
    	AnneeScolaireDTO anneeTodelete = new AnneeScolaireDTO();
    	anneeTodelete.setLibelle("Annee scolaire 2020-2021");
    	//anneeTodelete.setDateDebut(Instant.now());
    	//anneeTodelete.setDateFin(Instant.now());
    	anneeTodelete = this.anneeScolaireService.saveDTO(anneeTodelete);
    	
        this.mockMvc.perform(delete(PreinscriptionConstants.API_PREINSCRIPTION_ANNEE_URL + "/" + anneeTodelete.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, this.autorizationHeader())
        //                .content(this.objectMapper.writeValueAsString(refTiers))
        )
                .andExpect(status().isOk())
                .andDo(document("delete-anneeScolaire"));
    }
    
    
    //niveau
    
    @Test
    public void test11_createNiveau() throws Exception {
        NiveauDTO niveauDTO = new NiveauDTO();
        niveauDTO.setLibelle("sixieme");

        this.mockMvc.perform(put(PreinscriptionConstants.API_PREINSCRIPTION_NIVEAU_URL + "/save").contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, this.autorizationHeader())
                .content(this.objectMapper.writeValueAsString(niveauDTO)))
                .andExpect(status().isCreated())
                .andDo(document("create-niveau"));
    }

    @Test
    public void test12_updateNiveau() throws Exception {

    	NiveauDTO niveauToUpdate = new NiveauDTO();
    	niveauToUpdate.setLibelle("sixieme");
    	niveauToUpdate = this.niveauService.saveDTO(niveauToUpdate);

        this.mockMvc.perform(post(PreinscriptionConstants.API_PREINSCRIPTION_NIVEAU_URL + "/update").contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, this.autorizationHeader())
                .content(this.objectMapper.writeValueAsString(niveauToUpdate))
        )
                .andExpect(status().isOk())
                .andDo(document("update-niveau"));
    }

    @Test
    public void test13_listNiveau() throws Exception {
        NiveauDTO niveaux = new NiveauDTO();
        niveaux.setLibelle("sixieme");
        this.niveauService.saveDTO(niveaux);
        this.mockMvc.perform(get(PreinscriptionConstants.API_PREINSCRIPTION_NIVEAU_URL + "?page=0&size=10").contentType(MediaType.APPLICATION_JSON)
                //                .content(this.objectMapper.writeValueAsString(refTiers))
                .header(HttpHeaders.AUTHORIZATION, this.autorizationHeader())
        )
                .andExpect(status().isOk())
                .andDo(document("list-niveau"));
    }

    @Test
    public void test14_deleteNiveau() throws Exception {
    	NiveauDTO niveauTodelete = new NiveauDTO();
    	niveauTodelete.setLibelle("sixieme");
    	niveauTodelete = this.niveauService.saveDTO(niveauTodelete);
        this.mockMvc.perform(delete(PreinscriptionConstants.API_PREINSCRIPTION_NIVEAU_URL + "/" + niveauTodelete.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, this.autorizationHeader())
        //                .content(this.objectMapper.writeValueAsString(refTiers))
        )
                .andExpect(status().isOk())
                .andDo(document("delete-niveau"));
    }
    
    
 //eleve
    
    @Test
    public void test15_createEleve() throws Exception {
    
        EleveDTO eleveDTO = new EleveDTO();
        eleveDTO.setMatricule("EPL-001");
        eleveDTO.setNom("kodjo");
        eleveDTO.setPrenom("lolo");
        eleveDTO.setSexe("masculin");
        eleveDTO.setAdresse("adidogome");
        eleveDTO.setNationalite("Togolaise");
        eleveDTO.setLieuNaissance("Lome");
       // eleveDTO.setDateNaissance(Instant.now());
        eleveDTO.setNomParent("TOTO");
        eleveDTO.setPrenomParent("Abalo");
        eleveDTO.setProfessionParent("Ingenieur");
        eleveDTO.setAdresseParent("Lome");
        eleveDTO.setTelephoneParent("0022890345678");
        eleveDTO.setImage("");

      
       
        this.mockMvc.perform(put(PreinscriptionConstants.API_PREINSCRIPTION_ELEVE_URL + "/save").contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(eleveDTO))
                .header(HttpHeaders.AUTHORIZATION, this.autorizationHeader()))
                .andExpect(status().isCreated())
                .andDo(document("create-eleve"));
    }
    
    @Test
    public void test16_updateEleve() throws Exception {

    	EleveDTO eleveToUpdate = new EleveDTO();
    	eleveToUpdate.setNom("SIABI");
    	eleveToUpdate.setPrenom("patience");
    	eleveToUpdate.setSexe("masculin");
    	eleveToUpdate.setAdresse("adidogome");
        eleveToUpdate.setNationalite("Togolaise");
        eleveToUpdate.setLieuNaissance("Lome");
       // eleveToUpdate.setDateNaissance(Instant.now());
        eleveToUpdate.setNomParent("TOTO");
        eleveToUpdate.setPrenomParent("Abalo");
        eleveToUpdate.setProfessionParent("Ingenieur");
        eleveToUpdate.setAdresseParent("Lome");
        eleveToUpdate.setTelephoneParent("0022890345678");
        eleveToUpdate.setImage("");
    	eleveToUpdate = this.eleveService.saveDTO(eleveToUpdate);

        this.mockMvc.perform(post(PreinscriptionConstants.API_PREINSCRIPTION_ELEVE_URL + "/update").contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, this.autorizationHeader())
                .content(this.objectMapper.writeValueAsString(eleveToUpdate))
        )
                .andExpect(status().isOk())
                .andDo(document("update-eleve"));
    }

    @Test
    public void test17_listEleve() throws Exception {
    	EleveDTO eleves = new EleveDTO();
    	eleves.setMatricule("EPL-001");
    	eleves.setNom("SIABI");
    	eleves.setPrenom("patience");
    	eleves.setSexe("masculin");
    	eleves.setAdresse("adidogome");
    	eleves.setNationalite("Togolaise");
    	eleves.setLieuNaissance("Lome");
    	//eleves.setDateNaissance(Instant.now());
    	eleves.setNomParent("TOTO");
    	eleves.setPrenomParent("Abalo");
    	eleves.setProfessionParent("Ingenieur");
        eleves.setAdresseParent("Lome");
        eleves.setTelephoneParent("0022890345678");
        eleves.setImage("");
        this.eleveService.saveDTO(eleves);
        this.mockMvc.perform(get(PreinscriptionConstants.API_PREINSCRIPTION_ELEVE_URL + "?page=0&size=10").contentType(MediaType.APPLICATION_JSON)
                //                .content(this.objectMapper.writeValueAsString(refTiers))
                .header(HttpHeaders.AUTHORIZATION, this.autorizationHeader())
        )
                .andExpect(status().isOk())
                .andDo(document("list-eleve"));
    }

    @Test
    public void test18_deleteEleve() throws Exception {
    	EleveDTO eleveTodelete = new EleveDTO();
    	eleveTodelete.setMatricule("EPL-001");
    	eleveTodelete.setNom("SIABI");
    	eleveTodelete.setPrenom("patience");
    	eleveTodelete.setSexe("masculin");
    	eleveTodelete.setAdresse("adidogome");
    	eleveTodelete.setNationalite("Togolaise");
    	eleveTodelete.setLieuNaissance("Lome");
    	//eleveTodelete.setDateNaissance(Instant.now());
    	eleveTodelete.setNomParent("TOTO");
    	eleveTodelete.setPrenomParent("Abalo");
    	eleveTodelete.setProfessionParent("Ingenieur");
    	eleveTodelete.setAdresseParent("Lome");
    	eleveTodelete.setTelephoneParent("0022890345678");
    	eleveTodelete.setImage("");
    	eleveTodelete = this.eleveService.saveDTO(eleveTodelete);
        this.mockMvc.perform(delete(PreinscriptionConstants.API_PREINSCRIPTION_ELEVE_URL + "/" + eleveTodelete.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, this.autorizationHeader())
        //                .content(this.objectMapper.writeValueAsString(refTiers))
        )
                .andExpect(status().isOk())
                .andDo(document("delete-eleve"));
    }

 
}
