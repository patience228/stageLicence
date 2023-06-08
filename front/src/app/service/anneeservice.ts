import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Annee } from '../model/annee';

@Injectable()
export class AnneeService {

    constructor(private http: HttpClient) { }

    addAnnee(annee:Annee){
        return this.http.post<any>(environment.annee_url+"/save",annee)
        .toPromise();
    }

    updateAnnee(annee:Annee){
        return this.http.post<any>(environment.annee_url+"/update",annee)
        .toPromise();
    }

deleteAnnee(annee:Annee){
        return this.http.delete<any>(environment.annee_url+"/"+annee.id)
        .toPromise()

    }

    getAnnees() {
        return this.http.get<any>(environment.annee_url)
        .toPromise()

    }

    getAnneesById(annee:Annee) {
        return this.http.get<any>(environment.annee_url+"/find/"+annee.id)
        .toPromise()

    }

    getAnneesCount() {
        return this.http.get<any>(environment.annee_url+"/count")
        .toPromise()

    }


}
