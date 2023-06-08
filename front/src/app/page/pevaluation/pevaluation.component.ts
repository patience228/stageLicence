import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { Annee } from 'src/app/model/annee';
import { Evaluation } from 'src/app/model/evaluation';
import { AnneeService } from 'src/app/service/anneeservice';
import { DateService } from 'src/app/service/date.service';
import { EvaluationserviceService } from 'src/app/service/evaluation/evaluationservice.service';

@Component({
  selector: 'app-pevaluation',
  templateUrl: './pevaluation.component.html',
  styleUrls: ['./pevaluation.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class PevaluationComponent implements OnInit {

    evaluationDialog: boolean;as

    evaluations: Evaluation[];

    evaluation: Evaluation;

    selectedEvaluations: Evaluation[];

    submitted: boolean;

    disable: boolean;

    cols: any[];

    anneeDatabase: any[];

    annees: SelectItem[]=[];

    periodes: SelectItem[]=[];

    types: SelectItem[]=[];

    a:Annee;
    n:number;
    an: SelectItem[]=[];
    annee:Annee[];



    constructor(private anneeService: AnneeService,private evaluationService: EvaluationserviceService, private messageService: MessageService,
        private confirmationService: ConfirmationService, private dateService : DateService) { }

       async ngOnInit(): Promise<void> {

        this.anneeDatabase=  await this.anneeService.getAnnees();

        this.evaluations=[];
        this.evaluationService.getEvaluations().then(data =>{ this.evaluations = data;console.log(data)});

        this.annee=[];
            this.anneeService.getAnnees().then(data =>{ this.annee = data;console.log(data)
                                                        this.n=data.length-1;console.log(this.n);
                                                        this.a=data[this.n];console.log(new Date(this.a.dateFin));
                                                        this.an.push({label:this.a.libelle , value: this.a})


                                                        });

        this.cols = [
            {field: 'id', header: 'Identifiant'},
            {field: 'libelle', header: 'Libellé'},
            {field: 'periode', header: 'Période'},
            {field: 'debutEval', header: 'Date de début'},
            {field: 'finEval', header: 'Date de fin'},
            {field: 'annee', header: 'Année scolaire'}
        ];

         this.periodes = [
            {label: 'Trimestre 1', value: 'Trimestre 1'},
            {label: 'Trimestre 2', value: 'Trimestre 2'},
            {label: 'Trimestre 3', value: 'Trimestre 3'}


        ];

        this.types = [
            {label: 'devoir', value: 'devoir'},
            {label: 'examen', value: 'examen'}


        ];

        this.anneeDatabase.forEach(data=>{
            this.annees.push({label:data.libelle , value: data})
          })

      }



      openNew() {
        this.evaluation = {};
        this.evaluation.debutEval=new Date()
        this.evaluation.finEval=new Date()
        this.submitted = false;
        this.evaluationDialog = true;
        this.disable=true;
    }

    deleteSelectedEvaluations() {
        this.confirmationService.confirm({
            message: 'Êtes-vous sûr de supprimer '+this.evaluation.libelle+' du '+this.evaluation.periode+'?',
            header: 'Confirmer',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {

        this.selectedEvaluations.forEach(s=>{
                        this.evaluationService.deleteEvaluation(s)
                })
                this.evaluations = this.evaluations.filter(val => !this.selectedEvaluations.includes(val));
                this.selectedEvaluations= null;
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Evaluations supprimées', life: 3000});
            }
        });
    }

    editEvaluation(evaluation: Evaluation) {
        this.evaluation = {...evaluation};
        this.evaluation.debutEval=new Date(evaluation.debutEval)
        this.evaluation.finEval=new Date(evaluation.finEval)
        this.evaluationDialog = true;
        this.disable=false;
    }

    deleteEvaluation(evaluation: Evaluation) {
        this.confirmationService.confirm({
            message: 'Êtes-vous sûr de supprimer ?',
            header: 'Confirmer',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
               this.evaluationService.deleteEvaluation(evaluation)
                this.evaluations = this.evaluations.filter(val => val.id !== evaluation.id);
                this.evaluation = {};
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Evaluation supprimée', life: 3000});
            }
        });
    }

    hideDialog() {
        this.evaluationDialog = false;
        this.submitted = false;
    }

    verification(){
        if(this.evaluation.libelle.length ==0 ||
          this.evaluation.periode == null ||
          this.evaluation.debutEval == null ||
          this.evaluation.finEval == null ||
          this.evaluation.annee == null)
       {
        this.disable = true;
       }
       else
        this.disable =false;
    }

     async saveEvaluation() {
        this.submitted = true;

        if(this.dateService.conpareTwoDates(this.evaluation.debutEval,this.evaluation.finEval)<1)
        {
            this.messageService.add({ severity: 'error', summary: ' Date invalide', detail: 'La date de début ne peut pas etre supérieure ou égale à la date de fin', life: 10000});
            return;
        }

        (this.evaluation.debutEval as any)=this.evaluation.debutEval?.getTime();
        (this.evaluation.finEval as any)=this.evaluation.finEval?.getTime();


        if (this.evaluation.libelle.trim()) {
            if (this.evaluation.id) {
             this.evaluationService.updateEvaluation(this.evaluation).then(x=>{
              this.evaluationService.getEvaluations().then(data =>{ this.evaluations = data;console.log(data)});
          })

               // this.evaluations[this.findIndexById(this.evaluation.id)] = this.evaluation;
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Modification effectuée avec succès', life: 3000});
            } else {
               this.evaluationService.addEvaluation(this.evaluation).then(x=>{
                  this.evaluationService.getEvaluations().then(data =>{ this.evaluations = data;console.log(data)});
              })


                this.messageService.add({severity:  'success', summary: 'Succès', detail: 'Enregistrement effectué avec succès', life: 3000});
            }

           this.evaluations= await this.evaluationService.getEvaluations();
            this.evaluations = [...this.evaluations];
            this.evaluationDialog = false;
            this.evaluation = {};
        }
    }





}
