import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { Annee } from 'src/app/model/annee';
import { Eleve } from 'src/app/model/eleve';
import { Note } from 'src/app/model/note';
import { AnneeService } from 'src/app/service/anneeservice';
import { EleveserviceService } from 'src/app/service/eleve/eleveservice.service';
import { EvaluationserviceService } from 'src/app/service/evaluation/evaluationservice.service';
import { MatiereserviceService } from 'src/app/service/matiere/matiereservice.service';
import { NoteService } from 'src/app/service/note/note.service';

@Component({
  selector: 'app-pnote',
  templateUrl: './pnote.component.html',
  styleUrls: ['./pnote.component.scss'],
  providers: [MessageService, ConfirmationService]
})

export class PnoteComponent implements OnInit {
    evaluationDatabase: any[];

    matieres: SelectItem[]=[];
    matiereDatabase: any[];

    evaluations: SelectItem[]=[];

    id: string ;
    eleve: Eleve ;
    note:Note;
    disable:boolean;

    a:Annee;
    b:Annee;
    n:number;
    annee:Annee[];

    name = 'Dynamic Add Fields';
    name1 = 'Dynamic Add Fields';
    values = [] ;

     notess:Note[]=[];

     notes:Note[];

  constructor(private noteService: NoteService,private evaluationService: EvaluationserviceService, private messageService: MessageService,
    private confirmationService: ConfirmationService, private eleveService : EleveserviceService,
    private route: ActivatedRoute,private router: Router, private matiereService: MatiereserviceService,
    private anneeService:AnneeService) { }


    async ngOnInit(): Promise<void> {

        //this.values=[{value:""}]

        this.annee=[];
        this.annee= await this.anneeService.getAnnees();
        this.n=this.annee.length-1;
        this.a=this.annee[this.n];console.log(this.a);



        this.evaluationDatabase=  await  this.evaluationService.getEvaluationByAnnee(this.a);
        this.matiereDatabase=  await  this.matiereService.getMatieres();

        this.note={};
        this.disable=true;

        this.evaluationDatabase.forEach(a=>{
            this.evaluations.push({label:a.libelle+' '+a.periode , value: a})
          })


        this.matiereDatabase.forEach(data=>{
            this.matieres.push({label:data.libelle, value: data})
          })

        for (let i = 0; i < this.matiereDatabase.length; i++) {
            const rowData = this.matiereDatabase[i];

            this.values.push({value:" "})
            }







      this.id = this.route.snapshot.params['id'];



      this.eleveService.getEleve(this.id)
      .then(data => {
          console.log(data)
          this.eleve = data;
        }, error => console.log(error));

    }

    list(){
        this.router.navigate(['page/note']);
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


    removevalue(i){
        this.values.splice(i,1) ;

      }

      addvalue(){
        this.values.push({value:"" });

      }


    saveNote(){

        this.note.eleve=this.eleve;

      /*  this.values.forEach(data=>{
            let a;
            data.value1.forEach(d => {
                a=d.value
            });
            this.notess.push({matiere:data.value,notes:a})

        })*/




       /* this.values.forEach(data=>{
            let a;
            data.value1.forEach(d => {
                a=d.value
            });
            this.notess.push({matiere:data.value,notes:a})

        })*/



        console.log(this.notess);

    if (this.note.evaluation.libelle.trim()) {
        if (this.note.id) {
         this.noteService.updateNote(this.note).then(x=>{
            this.noteService.getNotes().then(data =>{ this.notes = data;console.log(data)})
         })
           // this.niveaux[this.findIndexById(this.niveau.id)] = this.niveau;
            this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Modification effectuée avec succès', life: 3000});
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

}
