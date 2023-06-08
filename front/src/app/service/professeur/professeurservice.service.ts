import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Professeur } from 'src/app/model/professeur';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProfesseurserviceService {


    constructor(private http: HttpClient) { }

    addProfesseur(professeur:Professeur){
      return this.http.post<any>(environment.professeur_url+"/save",professeur)
      .toPromise();
  }

  updateProfesseur(professeur:Professeur){
      return this.http.post<any>(environment.professeur_url+"/update",professeur)
      .toPromise();
  }

  deleteProfesseur(professeur:Professeur){
      return this.http.delete<any>(environment.professeur_url+"/"+professeur.id)
      .toPromise();
  }

  getProfesseurs() {
      return this.http.get<any>(environment.professeur_url)
      .toPromise();
  }

  getProfesseurById(professeur:Professeur) {
      return this.http.get<any>(environment.professeur_url+"/find/"+professeur.id)
      .toPromise();
  }

  getProfesseursCount() {
      return this.http.get<any>(environment.professeur_url+"/count")
      .toPromise();
  }


}
