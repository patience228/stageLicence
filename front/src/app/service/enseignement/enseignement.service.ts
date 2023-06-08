import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Classe } from 'src/app/model/classe';
import { Enseignement } from 'src/app/model/enseignement';
import { EnseignementUpdate } from 'src/app/model/enseignementUpdate';
import { Matiere } from 'src/app/model/matiere';
import { Professeur } from 'src/app/model/professeur';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EnseignementService {

    constructor(private http: HttpClient) { }

    addEnseignement(enseignement:Enseignement){
      return this.http.post<any>(environment.enseignement_url+"/save",enseignement)
      .toPromise();
  }

  updateEnseignement(enseignement:Enseignement){
      return this.http.post<any>(environment.enseignement_url+"/update",enseignement)
      .toPromise();
  }

  updateMatiereEnseignement(enseignement:EnseignementUpdate){
      console.log("======================================");
    return this.http.post<any>(environment.enseignement_url+"/update/matiere",enseignement)
    .toPromise();
}

  deleteEnseignement(professeur:Professeur,classe:Classe,matiere: Matiere){
      return this.http.delete<any>(environment.enseignement_url+"/"+professeur.id+"/"+classe.id+"/"+matiere.id)
      .toPromise();
  }

  getEnseignements() {
      return this.http.get<any>(environment.enseignement_url)
      .toPromise();
  }

  getEnseignementById(enseignement:Enseignement) {
      return this.http.get<any>(environment.enseignement_url+"/find/"+enseignement.id)
      .toPromise();
  }

  getEnseignementsCount() {
      return this.http.get<any>(environment.enseignement_url+"/count")
      .toPromise();
  }

  getMatierebyProfesseur(professeur:Professeur) {
    return this.http.get<any>(environment.enseignement_url+"/matiere/"+professeur.id)
    .toPromise();
}

getClassesbyProfesseurAndMatiere(professeur:Professeur,matiere: Matiere) {
    return this.http.get<any>(environment.enseignement_url+"/classes/"+professeur.id+"/"+matiere.id)
    .toPromise();
}

}
