import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Preinscription } from 'src/app/model/preinscription';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PreinscriptionserviceService {


    constructor(private http: HttpClient) { }

    addPreinscription(preinscription:Preinscription){
        return this.http.post<any>(environment.preinscription_url+"/save",preinscription)
        .toPromise();
    }

    updatePreinscription(preinscription:Preinscription){
        return this.http.post<any>(environment.preinscription_url+"/update",preinscription)
        .toPromise();
    }

deletePreinscription(preinscription:Preinscription){
        return this.http.delete<any>(environment.preinscription_url+"/"+preinscription.id)
        .toPromise();
    }

    getPreinscriptions() {
        return this.http.get<any>(environment.preinscription_url)
        .toPromise();
    }

    getPreinscriptionById(preinscription:Preinscription) {
        return this.http.get<any>(environment.preinscription_url+"/find/"+preinscription.id)
        .toPromise();
    }

    getPreinscriptionsCount() {
        return this.http.get<any>(environment.preinscription_url+"/count")
        .toPromise();
    }

    getPrenscription(id:any) {
        return this.http.get<any>(environment.preinscription_url+"/find/"+id)
        .toPromise();
    }

    getPreinscriptionsbyEtat(etat:number) {
        return this.http.get<any>(environment.preinscription_url+"/state/"+etat)
        .toPromise();
    }

    getPreinscriptionsbyAnnee() {
        return this.http.get<any>(environment.preinscription_url+"/nbre")
        .toPromise();
    }

    getPreinscriptionsForSelectedNiveaubyParameter(selectedNiveauId:string):Observable<any> {
        let params1=new HttpParams().set('niveauId',selectedNiveauId);
        return this.http.get(environment.preinscription_url,{params:params1});

    }



    getBase64(file): Observable<string> {
        return new Observable<string>(sub => {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => {
            sub.next(reader.result.toString());
            sub.complete();
            };
            reader.onerror = error => {
            sub.error(error);
            };
        })
    }


}
