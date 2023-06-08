import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Inscription } from 'src/app/model/inscription';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InscriptionserviceService {

    constructor(private http: HttpClient) { }

 addInscription(inscription:Inscription){
      return this.http.post<any>(environment.inscription_url+"/save",inscription)
      .toPromise();
  }

  updateInscription(inscription:Inscription){
      return this.http.post<any>(environment.inscription_url+"/update",inscription)
      .toPromise();
  }

  deleteInscription(inscription:Inscription){
      return this.http.delete<any>(environment.inscription_url+"/"+inscription.id)
      .toPromise();
  }

  getInscriptions() {
      return this.http.get<any>(environment.inscription_url)
      .toPromise();
  }

  getInscriptionById(inscription:Inscription) {
      return this.http.get<any>(environment.inscription_url+"/find/"+inscription.id)
      .toPromise();
  }

  getInscriptionByAnnee() {
    return this.http.get<any>(environment.inscription_url+"/nbre")
    .toPromise();
}


  getInscription(id:any) {
    return this.http.get<any>(environment.inscription_url+"/find/"+id)
    .toPromise();
}

  getInscriptionsCount() {
      return this.http.get<any>(environment.inscription_url+"/count")
      .toPromise();
  }

  getMatricule() {
    return this.http.get<any>(environment.inscription_url+"/matricule")
    .toPromise();
}

getinscrisall() {
    return this.http.get<any>(environment.inscription_url+"/all")
    .toPromise();
}

}
