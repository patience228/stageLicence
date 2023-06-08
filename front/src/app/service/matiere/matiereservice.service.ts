import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Matiere } from 'src/app/model/matiere';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MatiereserviceService {

    constructor(private http: HttpClient) { }

    addMatiere(matiere:Matiere){
      return this.http.post<any>(environment.matiere_url+"/save",matiere)
      .toPromise();
  }

  updateMatiere(matiere:Matiere){
      return this.http.post<any>(environment.matiere_url+"/update",matiere)
      .toPromise();
  }

  deleteMatiere(matiere:Matiere){
      return this.http.delete<any>(environment.matiere_url+"/"+matiere.id)
      .toPromise();
  }

  getMatieres() {
      return this.http.get<any>(environment.matiere_url)
      .toPromise();
  }

  getMatiereById(matiere:Matiere) {
      return this.http.get<any>(environment.matiere_url+"/find/"+matiere.id)
      .toPromise();
  }

  getMatieresCount() {
      return this.http.get<any>(environment.matiere_url+"/count")
      .toPromise();
  }

}
