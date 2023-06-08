import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Affectation } from 'src/app/model/affectation';
import { Classe } from 'src/app/model/classe';
import { Annee } from 'src/app/model/annee';
import { environment } from 'src/environments/environment';
import { Eleve } from 'src/app/model/eleve';

@Injectable({
  providedIn: 'root'
})
export class AffectationService {

    constructor(private http: HttpClient) { }

    addAffectation(affectation:Affectation){
      return this.http.post<any>(environment.affectation_url+"/save",affectation)
      .toPromise();
  }

  updateAffectation(affectation:Affectation){
      return this.http.post<any>(environment.affectation_url+"/update",affectation)
      .toPromise();
  }

  deleteAffectation(affectation:Affectation){
      return this.http.delete<any>(environment.affectation_url+"/"+affectation.id)
      .toPromise();
  }

  getAffectations() {
      return this.http.get<any>(environment.affectation_url)
      .toPromise();
  }

  getAffectationById(affectation:Affectation) {
      return this.http.get<any>(environment.affectation_url+"/find/"+affectation.id)
      .toPromise();
  }

  getAffectationsCountByClasse(classe:Classe) {
      return this.http.get<any>(environment.affectation_url+"/nbre/"+classe.id)
      .toPromise();
  }

  getAffectationsEleveCountByClasse(classe:Classe) {
    return this.http.get<any>(environment.affectation_url+"/nb/"+classe.id)
    .toPromise();
}

  getAffectationsCountByClasseAndAnnee(classe:Classe,annee:Annee) {
    return this.http.get<any>(environment.affectation_url+"/nbre/"+classe.id+"/"+annee.id)
    .toPromise();
}

getAffectationsByClasseAndAnnee(classe:Classe,annee:Annee) {
    console.log(classe);
    return this.http.get<any>(environment.affectation_url+"/"+classe.id+"/"+annee.id)
    .toPromise();

}

getAffectationsByAnnee(annee:Annee,eleve:Eleve) {
console.log(eleve,annee)
    return this.http.get<any>(environment.affectation_url+"/class/"+annee.id+"/"+eleve.id)
    .toPromise();

}



getAffectationsCount() {
    return this.http.get<any>(environment.affectation_url+"/count")
    .toPromise();
}

  getaffectsall() {
    return this.http.get<any>(environment.affectation_url+"/all")
    .toPromise();
}


}
