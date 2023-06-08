import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Titulaire } from 'src/app/model/titulaire';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TitulaireService {


    constructor(private http: HttpClient) { }

    addTitulaire(titulaire:Titulaire){
      return this.http.post<any>(environment.titulaire_url+"/save",titulaire)
      .toPromise();
  }

  updateTitulaire(titulaire:Titulaire){
      return this.http.post<any>(environment.titulaire_url+"/update",titulaire)
      .toPromise();
  }

  deleteTitulaire(titulaire:Titulaire){
      return this.http.delete<any>(environment.titulaire_url+"/"+titulaire.id)
      .toPromise();
  }

  getTitulaires() {
      return this.http.get<any>(environment.titulaire_url)
      .toPromise();
  }

  getTitulaireById(titulaire:Titulaire) {
      return this.http.get<any>(environment.titulaire_url+"/find/"+titulaire.id)
      .toPromise();
  }

  getTitulairesCount() {
      return this.http.get<any>(environment.titulaire_url+"/count")
      .toPromise();
  }

}
