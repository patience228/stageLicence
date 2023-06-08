import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ConfirmationService, MenuItem, MessageService, SelectItem } from 'primeng/api';
import { Affectation } from 'src/app/model/affectation';
import { Classe } from 'src/app/model/classe';
import { Niveau } from 'src/app/model/niveau';
import * as FileSaver from 'file-saver';
import { AffectationService } from 'src/app/service/affectation/affectation.service';
import { ClasseserviceService } from 'src/app/service/classe/classeservice.service';
import { NiveauService } from 'src/app/service/niveauService';
import { MytranslateService } from 'src/app/service/mytranslate.service';

@Component({
  selector: 'app-pclasse',
  templateUrl: './pclasse.component.html',
  styleUrls: ['./pclasse.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class PclasseComponent implements OnInit {

    classeDialog: boolean;as
    affectationDialog: boolean;


    classes: Classe[];

    classe: Classe;

    selectedClasses: Classe[];

    submitted: boolean;

    disable: boolean;

    cols: any[];

    niveauDatabase: any[];
    niveauId: any[];

    niveaux: SelectItem[]=[];
    selectedDrop: SelectItem;

    nbre:number;
    affectations:number[];

    @ViewChild('dt') dt: ElementRef;
    items: MenuItem[];
    exportColumns:any[];

    constructor(private niveauService: NiveauService,private classeService: ClasseserviceService, private messageService: MessageService,
      private confirmationService: ConfirmationService,private affectationService:AffectationService,private translate:MytranslateService) { }

     async ngOnInit(): Promise<void> {

        this.niveauDatabase=  await this.niveauService.getNiveaux();

      this.classes=[];
      this.classeService.getClasses().then(data =>{ this.classes = data;console.log(data)});




      this.cols = [

          {field: 'libelle', header: 'Libellé'},
          {field: 'niveau', header: 'Niveau'}
      ];

      this.exportColumns = this.cols.map(col => ({title: col.header, dataKey: col.field}));

      this.items = [
        {label:  this.translate.instant('En PDF'), icon: 'pi pi-file-pdf', command: () => {
            this.exportPdf();
            }
        },
        {label:  this.translate.instant('En CSV'), icon: 'pi pi-file-o', command: () => {
                if(this.selectedClasses)
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

      this.niveauDatabase.forEach(data=>{
        this.niveaux.push({label:data.libelle , value: data})
      })

    }





    openNew() {
      this.classe = {};
      this.submitted = false;
      this.classeDialog = true;
      this.disable=true;
  }

  deleteSelectedClasses() {
      this.confirmationService.confirm({
          message: 'Êtes-vous sûr de supprimer?',
          header: 'Confirmer',
          icon: 'pi pi-exclamation-triangle',
          accept: () => {

      this.selectedClasses.forEach(s=>{
                      this.classeService.deleteClasse(s)
              })
              this.classes = this.classes.filter(val => !this.selectedClasses.includes(val));
              this.selectedClasses = null;
              this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Classes supprimées', life: 3000});
          }
      });
  }

  editClasse(classe: Classe) {
      this.classe = {...classe};
      this.classeDialog = true;
      this.disable=false;
  }

  async Effectif(classe: Classe){
    this.classe = classe;
    this.affectations=[];
    this.affectations= await this.affectationService.getAffectationsEleveCountByClasse(classe);
    console.log(this.affectations);
      this.affectationDialog = true;
  }

  deleteClasse(classe: Classe) {
      this.confirmationService.confirm({
          message: 'Êtes-vous sûr de supprimer ' + classe.libelle + '?',
          header: 'Confirmer',
          icon: 'pi pi-exclamation-triangle',
          accept: () => {
             this.classeService.deleteClasse(classe)
              this.classes = this.classes.filter(val => val.id !== classe.id);
              this.classe = {};
              this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Classe supprimée', life: 3000});
          }
      });
  }

  hideDialog() {
      this.classeDialog = false;
      this.submitted = false;
  }

  verification(){
      if(this.classe.libelle.length==0 ||
        this.classe.niveau == null)
     {
      this.disable = true;
     }
     else
      this.disable =false;
  }

   async saveClasse() {
      this.submitted = true;



      if (this.classe.libelle.trim()) {
          if (this.classe.id) {
           this.classeService.updateClasse(this.classe).then(x=>{
            this.classeService.getClasses().then(data =>{ this.classes = data;console.log(data)});
        })

            //  this.classes[this.findIndexById(this.classe.id)] = this.classe;
              this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Modification effectuée avec succès', life: 3000});
          } else {
             this.classeService.addClasse(this.classe).then(x=>{
                this.classeService.getClasses().then(data =>{ this.classes = data;console.log(data)});
            })


              this.messageService.add({severity:  'success', summary: 'Succès', detail: 'Enregistrement effectué avec succès', life: 3000});
          }

         this.classes= await this.classeService.getClasses();
          this.classes = [...this.classes];
          this.classeDialog = false;
          this.classe = {};
      }
  }

  findIndexById(id: string): number {
      let index = -1;
      for (let i = 0; i < this.classes.length; i++) {
          if (this.classes[i].id === id) {
              index = i;
              break;
          }
      }
      return index;
  }

  createId(): string {
      let id = 'EPL_';
      const chars='0123456789';
      for (let i = 0; i < 8; i++) {
          id += chars.charAt(Math.floor(Math.random() * chars.length));
      }
      return id;
  }


  exportPdf() {
    let data=[];
    if(this.selectedClasses){
        this.selectedClasses.forEach(x=>{
            data.push({niveau:x.niveau.libelle,
            libelle:x.libelle})
        })
    }
    else{
        this.classes.forEach(x=>{
            data.push({niveau:x.niveau.libelle,
            libelle:x.libelle})
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
    if(this.selectedClasses){
        this.selectedClasses.forEach(x=>{
            data.push({niveau:x.niveau.libelle,
            libelle:x.libelle})
        })
    }
    else{
        this.classes.forEach(x=>{
            data.push({niveau:x.niveau.libelle,
            libelle:x.libelle})
        })
    }
    import("xlsx").then(xlsx => {
        const worksheet = xlsx.utils.json_to_sheet(data);
        const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
        const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });

        let name=prompt(this.translate.instant("Nom du ficher"),this.translate.instant("Preinscriptions_")+Date.now)
        if(!name)
            name="Classes_"+ new Date().getTime()
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
