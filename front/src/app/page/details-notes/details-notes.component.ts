import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { Table } from 'primeng/table';
import { Eleve } from 'src/app/model/eleve';
import { Evaluation } from 'src/app/model/evaluation';
import { Note } from 'src/app/model/note';
import { EleveserviceService } from 'src/app/service/eleve/eleveservice.service';
import { EvaluationserviceService } from 'src/app/service/evaluation/evaluationservice.service';
import { MatiereserviceService } from 'src/app/service/matiere/matiereservice.service';
import { NoteService } from 'src/app/service/note/note.service';

@Component({
  selector: 'app-details-notes',
  templateUrl: './details-notes.component.html',
  styleUrls: ['./details-notes.component.scss'],
  providers: [MessageService, ConfirmationService],
  styles: [`
  @media screen and (max-width: 960px) {
      :host ::ng-deep .p-datatable.p-datatable-customers.rowexpand-table .p-datatable-tbody > tr > td:nth-child(6) {
          display: flex;
      }
  }

`],
})
export class DetailsNotesComponent implements OnInit {
    id: string ;
    eleve: Eleve ;

    noteDialog:boolean;
    note:Note;
    notes:Note[];
    evaluations:Evaluation[];

    disable:boolean;
    submitted:boolean;

    evaluationDatabase: any[];

    matieres: SelectItem[]=[];
    matiereDatabase: any[];

    evaluations1: SelectItem[]=[];

    @ViewChild('dt') table: Table;
    rowGroupMetadata: any;
    cols=[]

  constructor(private noteService: NoteService,private evaluationService: EvaluationserviceService, private messageService: MessageService,
    private confirmationService: ConfirmationService, private eleveService : EleveserviceService,
    private route: ActivatedRoute,private router: Router, private matiereService: MatiereserviceService) { }

    async ngOnInit(): Promise<void> {

        this.evaluationDatabase=  await this.evaluationService.getEvaluations();
        this.matiereDatabase=  await  this.matiereService.getMatieres();


        this.evaluationDatabase.forEach(data=>{
            this.evaluations1.push({label:data.libelle+' '+data.periode , value: data})
          })

            this.matiereDatabase.forEach(data=>{
            this.matieres.push({label:data.libelle, value: data})
          })


    this.notes=[];
    this.noteService.getNotes().then(data =>{ this.notes = data;console.log(data)});

    this.evaluations=[];
    this.evaluationService.getEvaluations().then(data =>{ this.evaluations = data;console.log(data)});

    this.id = this.route.snapshot.params['id'];

    this.cols = [
        { field: 'evaluation', header: 'Evaluation' },
        { field: 'periode', header: 'Période' },
        { field: 'annee', header: 'Année scolaire' }
    ];



    this.eleveService.getEleve(this.id)
    .then(data => {
        console.log(data)
        this.eleve = data;
      }, error => console.log(error));
  }




  list(){
    this.router.navigate(['page/note']);
  }

  editNote(note:Note){
      this.note={...note}
      this.noteDialog = true;
      this.disable=false;

  }

  hideDialog() {
    this.noteDialog = false;
    this.submitted = false;
}


verification(){
    if(this.note.evaluation ==null ||
        this.note.matiere ==null ||
        this.note.notes ==null)
   {
    this.disable = true;
   }
   else
    this.disable =false;
}


async  saveNote(){

    this.note.eleve=this.eleve;

if (this.note.evaluation.libelle.trim()) {
    if (this.note.id) {
     this.noteService.updateNote(this.note).then(x=>{
        this.noteService.getNotes().then(data =>{ this.notes = data;console.log(data)})
     })

        this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Modification effectuée avec succès', life: 3000});
    } else {

       this.noteService.addNote(this.note)

        this.messageService.add({severity:  'success', summary: 'Succès', detail: 'Enregistrement effectué avec succès', life: 3000});
    }

    this.notes= await this.noteService.getNotes();
          this.notes = [...this.notes];
          this.noteDialog = false;
          this.note = {};


}

}


onSort() {
    this.updateRowGroupMetaData();
}



updateRowGroupMetaData() {
    this.rowGroupMetadata = {};

    if (this.notes) {
        for (let i = 0; i < this.notes.length; i++) {
            const rowData = this.notes[i];
            const representativeName = rowData.evaluation.id;

            if (i === 0) {
                this.rowGroupMetadata[representativeName] = { index: 0, size: 1 };
                
            }
            else {
                const previousRowData = this.notes[i - 1];
                const previousRowGroup = previousRowData.evaluation.id;
                if (representativeName === previousRowGroup) {
                    this.rowGroupMetadata[representativeName].size++;
                }
                else {
                    this.rowGroupMetadata[representativeName] = { index: i, size: 1 };
                }
            }
        }
    }
}


}
