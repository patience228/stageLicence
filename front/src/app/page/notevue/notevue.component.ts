import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MenuItem, MessageService, SelectItem } from 'primeng/api';
import { Table } from 'primeng/table';
import { Affectation } from 'src/app/model/affectation';
import { Annee } from 'src/app/model/annee';
import { Classe } from 'src/app/model/classe';
import { Eleve } from 'src/app/model/eleve';
import { Note } from 'src/app/model/note';
import { AffectationService } from 'src/app/service/affectation/affectation.service';
import { AnneeService } from 'src/app/service/anneeservice';
import { ClasseserviceService } from 'src/app/service/classe/classeservice.service';
import { EleveserviceService } from 'src/app/service/eleve/eleveservice.service';
import { EvaluationserviceService } from 'src/app/service/evaluation/evaluationservice.service';
import { MatiereserviceService } from 'src/app/service/matiere/matiereservice.service';
import { MytranslateService } from 'src/app/service/mytranslate.service';
import * as FileSaver from 'file-saver';
import { NoteService } from 'src/app/service/note/note.service';

@Component({
  selector: 'app-notevue',
  templateUrl: './notevue.component.html',
  styleUrls: ['./notevue.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class NotevueComponent implements OnInit {

    eleveDialog: boolean;as
    notesDialog: boolean;
    notes: Note[];

    eleves: Eleve[];

    eleve: Eleve;

    selectedEleves: Eleve[];
    selectedAffectations: Affectation[];

    submitted: boolean;

    disable: boolean;

    cols: any[];

    anne:Annee;
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

    affectations:Affectation[];
    affectation:Affectation;
    affectationss:Affectation[];

    classeDatabase: any[];

    classes: SelectItem[]=[];

    classe: Classe;

    evaluationDatabase: any[];

    matieres: SelectItem[]=[];
    matiereDatabase: any[];
    matiere: number;

    evaluations: SelectItem[]=[];

    anneeDatabase: any[];

    annees: SelectItem[]=[];

    note:Note;

    bul1Dialog:boolean;
    bul2Dialog:boolean;
    bul3Dialog:boolean;

    s:string="devoir";
    s2:string="examen";
    s3:string="Trimestre 1";

    @ViewChild('dt') dt: ElementRef;
    items: MenuItem[];
    exportColumns:any[];

    dev=[];
    dev1=[];

    q;
    q1;



    constructor(private eleveService: EleveserviceService, private messageService: MessageService,
        private confirmationService: ConfirmationService, private router : Router,private matiereService:MatiereserviceService,
         private affectationService:AffectationService,private classeService:ClasseserviceService,private translate:MytranslateService,
         private noteService:NoteService,private anneeService:AnneeService, private evaluationService:EvaluationserviceService) { }

         async ngOnInit(): Promise<void> {
             this.classe={};
             this.anne={};
             this.matiere= await this.matiereService.getMatieresCount();

             this.annee=[];
                this.annee= await this.anneeService.getAnnees();
                this.n=this.annee.length-1;
                this.a=this.annee[this.n];console.log(this.a);


            this.evaluationDatabase=  await  this.evaluationService.getEvaluationByAnnee(this.a);
            this.matiereDatabase=  await  this.matiereService.getMatieres();


            this.evaluationDatabase.forEach(data=>{
                this.evaluations1.push({label:data.libelle+' '+data.periode , value: data})
              })


              this.classeDatabase=  await this.classeService.getClasses();


              this.classeDatabase.forEach(data=>{
                  this.classes.push({label:data.libelle , value: data})
                });

              this.anneeDatabase=  await  this.anneeService.getAnnees();


              this.anneeDatabase.forEach(data=>{
                  this.annees.push({label:data.libelle , value: data})

                })
                console.log(this.annees);




       // this.affectations.find()



       this.cols = [

        {field: 'nom', header: 'Nom'},
        {field: 'prenom', header: 'Prénoms'},
        {field: 'sexe', header: 'Sexe'},
        {field: 'classe', header: 'Classe'},
        {field: 'annee', header: 'Année scolaire'},



    ];


        this.exportColumns = this.cols.map(col => ({title: col.header, dataKey: col.field}));

        this.items = [
          {label:  this.translate.instant('En PDF'), icon: 'pi pi-file-pdf', command: () => {
              this.exportPdf();
              }
          },
          {label:  this.translate.instant('En CSV'), icon: 'pi pi-file-o', command: () => {
                  if(this.selectedAffectations)
                      (this.dt as any).exportCSV({selectionOnly:true});
                  else
                      (this.dt as any).exportCSV();
              }
          },
          {label:  this.translate.instant('En Exel'), icon: 'pi-file-excel', command: () => {
              this.exportExcel();
              }
          }
      ];






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

       this.anneeDatabase.forEach(x=>{
        if(x.libelle==this.anne.libelle){
            this.anne=x;

        }
   })

         this.affectationss= await this.affectationService.getAffectationsByClasseAndAnnee((this.classe as any).libelle,(this.anne as any).libelle)

         console.log(this.affectationss)


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

    //bulletin1
     async  bul1(affectation: Affectation){

        this.affectation = {...affectation};
        console.log(this.affectation);
        this.i =this.affectationss.findIndex(x => x.id === this.affectation.id)
        console.log(this.i);
        console.log(this.affectationss[this.i]);
        this.l=this.affectationss.length;console.log(this.l);
        this.eleve=this.affectation.eleve;
        console.log(this.eleve)

        this.matiereDatabase=  await  this.matiereService.getMatieres();
        this.notes=[];
        this.noteService.getNotes().then(data =>{ this.notes = data;console.log(data)});

       this.noteService.getNoteByLibelle(this.s).then(data=>{this.dev=data;console.log(this.dev)})
       this.noteService.getNoteByLibelle(this.s2).then(data=>{this.dev1=data;console.log(this.dev1)})

      

        this.bul1Dialog = true;

      }








    hideDialog() {
        this.eleveDialog = false;
        this.submitted = false;
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

    saveNote(){

        this.note.eleve=this.affectation.eleve;



    if (this.note.evaluation.libelle.trim()) {
        if (this.note.id) {
         this.noteService.updateNote(this.note).then(x=>{
            this.noteService.getNotes().then(data =>{ this.notes = data;console.log(data)})
         })

        this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Modification effectuée avec succès', life: 3000});

           this.notes = [...this.notes];
           this.note1Dialog = false;
           this.note = {};
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


exportPdf() {
    let data=[];
    if(this.selectedAffectations){
        this.selectedAffectations.forEach(x=>{
            data.push({annee:x.annee.libelle,
            classe:x.classe.libelle,
        nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
        })
    }
    else{
        this.affectationss.forEach(x=>{
            data.push({annee:x.annee.libelle,
            classe:x.classe.libelle,
          nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
        })
    }
       // data=this.preinscriptionsValid
    import("jspdf").then(jsPDF => {
        import("jspdf-autotable").then(x => {
            const doc = new jsPDF.default('p','pt');
            (doc as any).autoTable(this.exportColumns, data);
            doc.save();
        })
    })
}

exportExcel() {
    let data=[];
    if(this.selectedAffectations){
        this.selectedAffectations.forEach(x=>{
            data.push({annee:x.annee.libelle,
            classe:x.classe.libelle,
            nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
        })
    }
    else{
        this.affectationss.forEach(x=>{
            data.push({annee:x.annee.libelle,
           classe:x.classe.libelle,
          nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
        })
    }
    import("xlsx").then(xlsx => {
        const worksheet = xlsx.utils.json_to_sheet(data);
        const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
        const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });

        let name=prompt(this.translate.instant("Nom du ficher"),this.translate.instant("Preinscriptions_")+Date.now)
        if(!name)
            name="Infos_"+ new Date().getTime()
        this.saveAsExcelFile(excelBuffer, name);
    });
}

saveAsExcelFile(buffer: any, fileName: string): void {
    let EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
    let EXCEL_EXTENSION = '.xlsx';
    const data: Blob = new Blob([buffer], {
        type: EXCEL_TYPE
    });
    FileSaver.saveAs(data, fileName + '_export_' + new Date().getTime() + EXCEL_EXTENSION);
}



}
