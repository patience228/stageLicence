import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Annee } from 'src/app/model/annee';
import { Evaluation } from 'src/app/model/evaluation';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EvaluationserviceService {

    constructor(private http: HttpClient) { }

    addEvaluation(evaluation:Evaluation){
      return this.http.post<any>(environment.evaluation_url+"/save",evaluation)
      .toPromise();
  }

  updateEvaluation(evaluation:Evaluation){
      return this.http.post<any>(environment.evaluation_url+"/update",evaluation)
      .toPromise();
  }

  deleteEvaluation(evaluation:Evaluation){
      return this.http.delete<any>(environment.evaluation_url+"/"+evaluation.id)
      .toPromise();
  }

  getEvaluations() {
      return this.http.get<any>(environment.evaluation_url)
      .toPromise();
  }

  getEvaluationById(evaluation:Evaluation) {
      return this.http.get<any>(environment.evaluation_url+"/find/"+evaluation.id)
      .toPromise();
  }

  getEvaluationByAnnee(annee:Annee) {
    return this.http.get<any>(environment.evaluation_url+"/eval/"+annee.id)
    .toPromise();
}

  getEvaluationsCount() {
      return this.http.get<any>(environment.evaluation_url+"/count")
      .toPromise();
  }


}
