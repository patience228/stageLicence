import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Utilisateur } from 'src/app/model/utilisateur';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurserviceService {



    constructor(private http: HttpClient) { }

    addUtilisateur(utilisateur:Utilisateur){
      return this.http.post<any>(environment.utilisateur_url+"/save",utilisateur)
      .toPromise();
  }

  updateUtilisateur(utilisateur:Utilisateur){
      return this.http.post<any>(environment.utilisateur_url+"/update",utilisateur)
      .toPromise();
  }

  deleteUtilisateur(utilisateur:Utilisateur){
      return this.http.delete<any>(environment.utilisateur_url+"/"+utilisateur.id)
      .toPromise();
  }

  getUtilisateurs() {
      return this.http.get<any>(environment.root_url+"gevisco/api/security/user?page=0&size=10")
      .toPromise();
  }



  getUtilisateurById(utilisateur:Utilisateur) {
      return this.http.get<any>(environment.utilisateur_url+"/"+utilisateur.id)
      .toPromise();
  }

  getUtilisateursCount() {
      return this.http.get<any>(environment.utilisateur_url+"/count")
      .toPromise();
  }


}
