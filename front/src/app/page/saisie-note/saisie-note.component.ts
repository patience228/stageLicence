import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MenuItem, MessageService, SelectItem } from 'primeng/api';
import { Affectation } from 'src/app/model/affectation';
import { Classe } from 'src/app/model/classe';
import { Eleve } from 'src/app/model/eleve';
import { AffectationService } from 'src/app/service/affectation/affectation.service';
import { ClasseserviceService } from 'src/app/service/classe/classeservice.service';
import { EleveserviceService } from 'src/app/service/eleve/eleveservice.service';
import { AnneeService } from 'src/app/service/anneeservice';
import { Note } from 'src/app/model/note';
import { NoteService } from 'src/app/service/note/note.service';
import { Annee } from 'src/app/model/annee';
import { EvaluationserviceService } from 'src/app/service/evaluation/evaluationservice.service';
import { MatiereserviceService } from 'src/app/service/matiere/matiereservice.service';
import { Table } from 'primeng/table';
import { MytranslateService } from 'src/app/service/mytranslate.service';

@Component({
  selector: 'app-saisie-note',
  templateUrl: './saisie-note.component.html',
  styleUrls: ['./saisie-note.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class SaisieNoteComponent implements OnInit {


    noteDialog: boolean;as
     notesDialog: boolean;

    eleveDialog: boolean;

    eleves: Eleve[];

    eleve: Eleve;

    selectedEleves: Eleve[];
    selectedAffectations: Affectation[];

    submitted: boolean;

    disable: boolean;

    cols: any[];

    affectations:Affectation[];
    affectationss:Affectation[];
    affectation:Affectation;

    classeDatabase: any[];

    classes: SelectItem[]=[];
    classe:Classe;

    note:Note;
    notes:Note[];
    evaluationDatabase: any[];

    matieres: SelectItem[]=[];
    matiereDatabase: any[];

    evaluations: SelectItem[]=[];

    a:Annee;
    b:Annee;
    n:number;
    annee:Annee[];
    values=[];
    i:number;
    l:number;


    note1Dialog:boolean;
  




    evaluations1: SelectItem[]=[];

    @ViewChild('dt') table: Table;
    rowGroupMetadata: any;






    constructor(private eleveService: EleveserviceService, private messageService: MessageService,
        private confirmationService: ConfirmationService, private router : Router,
         private affectationService:AffectationService,private classeService:ClasseserviceService,
         private anneeService: AnneeService,private noteService:NoteService,private evaluationService:EvaluationserviceService,
         private matiereService:MatiereserviceService) { }

         async ngOnInit(): Promise<void> {

            this.classe={};

                this.annee=[];
                this.annee= await this.anneeService.getAnnees();
                this.n=this.annee.length-1;
                this.a=this.annee[this.n];console.log(this.a);





                this.evaluationDatabase=  await  this.evaluationService.getEvaluationByAnnee(this.a);
                this.matiereDatabase=  await  this.matiereService.getMatieres();

                this.evaluationDatabase.forEach(data=>{
                    this.evaluations1.push({label:data.libelle+' '+data.periode , value: data})
                  })



                this.evaluationDatabase.forEach(a=>{
                    this.evaluations.push({label:a.libelle+' '+a.periode , value: a})
                  });


                this.matiereDatabase.forEach(data=>{
                    this.matieres.push({label:data.libelle, value: data})
                  });

                for (let i = 0; i < this.matiereDatabase.length; i++) {
                    const rowData = this.matiereDatabase[i];

                    this.values.push({value:" "})
                    };




            this.classeDatabase=  await this.classeService.getClasses();


            this.classeDatabase.forEach(data=>{
                this.classes.push({label:data.libelle , value: data})
              });


        this.eleves=[];
        this.eleveService.getElevesInscrire().then(data =>{ this.eleves = data;console.log(data)});

        this.affectationService.getAffectations().then(data =>{ this.affectations = data;console.log(data)});


       // this.affectations.find()


        this.cols = [
            {field: 'id', header: 'Identifiant'},
            {field: 'eleve', header: 'Eleve'},
            {field: 'classe', header: 'Classe'},
            {field: 'annee', header: 'Année scolaire'},



        ];



        this.classeDatabase.forEach(x=>{
            if(x.libelle==this.classe.libelle){
                this.classe=x;

            }
       })
        this.annee=[];
        this.annee= await this.anneeService.getAnnees();
        this.n=this.annee.length-1;console.log(this.n);
        this.a=this.annee[this.n];console.log(new Date(this.a.dateFin));
            console.log(this.classe.libelle);


      }


      Note(affectation:Affectation){
        this.affectation = {...affectation};
        console.log(this.affectation);
        this.i =this.affectationss.findIndex(x => x.id === this.affectation.id)
        console.log(this.i);
        console.log(this.affectationss[this.i]);
        this.l=this.affectationss.length;console.log(this.l);
        this.eleve=this.affectation.eleve;
        console.log(this.eleve)
        this.note={};
        this.noteDialog = true;
        this.disable=true;
      }


       left(){
        this.i=this.i-1;
        console.log(this.i)

        this.affectation=this.affectationss[this.i]
        console.log(this.affectationss[this.i] )
        this.eleve=this.affectation.eleve;
        console.log(this.eleve)
    }

      right(){
        this.i=this.i+1;

        console.log(this.i)

        this.affectation=this.affectationss[this.i]
        console.log(this.affectationss[this.i] )
        this.eleve=this.affectation.eleve;
        console.log(this.eleve)

    }


     async  Research(){

       this.classeDatabase.forEach(x=>{
            if(x.libelle==this.classe.libelle){
                this.classe=x;

            }
       })
        let annee=[];
        annee= await this.anneeService.getAnnees();
            let n=annee.length-1;console.log(n);
            let a=annee[n];console.log(new Date(a.dateFin));
            console.log(this.classe.libelle)



         this.affectationss= await this.affectationService.getAffectationsByClasseAndAnnee((this.classe as any).libelle,a)

         console.log(this.affectationss)


    }



      eleveNote(id: any){
        this.router.navigate(['note', id]);
      }


      datailsNote(id: any){
        this.router.navigate(['noteDetail', id]);
      }
//liste des notes
      detailsNote(affectation: Affectation){
        this.affectation = {...affectation};
        console.log(this.affectation);
        this.i =this.affectationss.findIndex(x => x.id === this.affectation.id)
        console.log(this.i);
        console.log(this.affectationss[this.i]);
        this.l=this.affectationss.length;console.log(this.l);
        this.eleve=this.affectation.eleve;
        console.log(this.eleve)

        this.notes=[];
        this.noteService.getNotes().then(data =>{ this.notes = data;console.log(data)});
        this.notesDialog = true;


      }




    hideDialog() {
        this.noteDialog = false;
        this.submitted = false;
    }

    verification(){
        if(this.note.evaluation ==null
         )
       {
        this.disable = true;
       }
       else
        this.disable =false;
    }


    saveNote(){

        this.note.eleve=this.affectation.eleve;



    if (this.note.evaluation.libelle.trim()) {
        if (this.note.id) {
         this.noteService.updateNote(this.note).then(x=>{
            this.noteService.getNotes().then(data =>{ this.notes = data;console.log(data)})
         })

        this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Modification effectuée avec succès', life: 3000});

          /* this.notes = [...this.notes];
           this.noteDialog = false;
           this.note = {};*/
        } else {

            for(let i=0; i<this.matiereDatabase.length; i++){
                this.note.matiere=this.matiereDatabase[i];
                this.note.notes=(this.values[i]as any).value;
                console.log(this.note.notes)

                this.noteService.addNote(this.note)

            }




            this.messageService.add({severity:  'success', summary: 'Succès', detail: 'Enregistrement effectué avec succès', life: 3000});
        }



    }



}




  list(){
    this.router.navigate(['page/note']);
  }

  //fonction editer les notes

  editNote(note:Note){
      this.note={...note}
      this.note1Dialog = true;
      this.disable=false;

  }

  hide1Dialog() {
    this.note1Dialog = false;
    this.submitted = false;
}


verif(){
    if(this.note.evaluation ==null ||
        this.note.matiere ==null ||
        this.note.notes ==null)
   {
    this.disable = true;
   }
   else
    this.disable =false;
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
