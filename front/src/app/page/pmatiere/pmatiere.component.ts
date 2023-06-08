import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ConfirmationService, MenuItem, MessageService, SelectItem } from 'primeng/api';
import { Matiere } from 'src/app/model/matiere';
import * as FileSaver from 'file-saver';
import { MatiereserviceService } from 'src/app/service/matiere/matiereservice.service';
import { MytranslateService } from 'src/app/service/mytranslate.service';

@Component({
  selector: 'app-pmatiere',
  templateUrl: './pmatiere.component.html',
  styleUrls: ['./pmatiere.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class PmatiereComponent implements OnInit {


    matiereDialog: boolean;as

    matieres: Matiere[];

    matiere: Matiere;

    selectedMatieres: Matiere[];

    submitted: boolean;

    disable: boolean;

    cols: any[];

    types: SelectItem[]=[];
    selectedDrop: SelectItem;

    @ViewChild('dt') dt: ElementRef;
    items: MenuItem[];
    exportColumns:any[];


    constructor(private matiereService: MatiereserviceService, private messageService: MessageService,
      private confirmationService: ConfirmationService,private translate:MytranslateService) { }

    ngOnInit(): void {

      this.matieres=[];
      this.matiereService.getMatieres().then(data =>{ this.matieres = data;console.log(data)});

      this.cols = [

          {field: 'libelle', header: 'Libellé'},
          {field: 'coefficient', header: 'Coefficient'},
          {field: 'type', header: 'Type'}


      ];

      this.exportColumns = this.cols.map(col => ({title: col.header, dataKey: col.field}));

      this.items = [
        {label:  this.translate.instant('En PDF'), icon: 'pi pi-file-pdf', command: () => {
            this.exportPdf();
            }
        },
        {label:  this.translate.instant('En CSV'), icon: 'pi pi-file-o', command: () => {
                if(this.selectedMatieres)
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

      this.types = [
        {label: 'Obligatoire', value: 'Obligatoire'},
        {label: 'Facultative', value: 'Facultative'}

    ];
    }



    openNew() {
      this.matiere = {};
      this.submitted = false;
      this.matiereDialog = true;
      this.disable=true;
  }

  deleteSelectedMatieres() {
      this.confirmationService.confirm({
          message: 'Êtes-vous sûr de supprimer?',
          header: 'Confirmer',
          icon: 'pi pi-exclamation-triangle',
          accept: () => {

      this.selectedMatieres.forEach(s=>{
                      this.matiereService.deleteMatiere(s)
              })
              this.matieres = this.matieres.filter(val => !this.selectedMatieres.includes(val));
              this.selectedMatieres = null;
              this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Matières supprimées', life: 3000});
          }
      });
  }

  editMatiere(matiere: Matiere) {
      this.matiere = {...matiere};
      this.matiereDialog = true;
      this.disable=false;
  }

  deleteMatiere(matiere: Matiere) {
      this.confirmationService.confirm({
          message: 'Êtes-vous sûr de supprimer ' + matiere.libelle + '?',
          header: 'Confirmer',
          icon: 'pi pi-exclamation-triangle',
          accept: () => {
             this.matiereService.deleteMatiere(matiere)
              this.matieres = this.matieres.filter(val => val.id !== matiere.id);
              this.matiere = {};
              this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Matière supprimée', life: 3000});
          }
      });
  }

  hideDialog() {
      this.matiereDialog = false;
      this.submitted = false;
  }

  verification(){
      if(this.matiere.libelle.length==0 ||
        this.matiere.coefficient == null ||
        this.matiere.type ==null)
     {
      this.disable = true;
     }
     else
      this.disable =false;
  }

   async saveMatiere() {
      this.submitted = true;



      if (this.matiere.libelle.trim()) {
          if (this.matiere.id) {
           this.matiereService.updateMatiere(this.matiere).then(x=>{
                this.matiereService.getMatieres().then(data =>{ this.matieres = data;console.log(data)})
            })
              //this.matieres[this.findIndexById(this.matiere.id)] = this.matiere;
              this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Modification effectuée avec succès', life: 3000});
          } else {
             this.matiereService.addMatiere(this.matiere).then(x=>{
                this.matiereService.getMatieres().then(data =>{ this.matieres = data;console.log(data)})
            })

              this.messageService.add({severity:  'success', summary: 'Succès', detail: 'Enregistrement effectué avec succès', life: 3000});
          }

         this.matieres= await this.matiereService.getMatieres();
          this.matieres = [...this.matieres];
          this.matiereDialog = false;
          this.matiere = {};
      }
  }

  findIndexById(id: string): number {
      let index = -1;
      for (let i = 0; i < this.matieres.length; i++) {
          if (this.matieres[i].id === id) {
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
    if(this.selectedMatieres){
        this.selectedMatieres.forEach(x=>{
            data.push({type:x.type,coefficient:x.coefficient,
            libelle:x.libelle})
        })
    }
    else{
        this.matieres.forEach(x=>{
            data.push({type:x.type,coefficient:x.coefficient,
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
    if(this.selectedMatieres){
        this.selectedMatieres.forEach(x=>{
            data.push({type:x.type,coefficient:x.coefficient,
                libelle:x.libelle})
        })
    }
    else{
        this.matieres.forEach(x=>{
            data.push({type:x.type,coefficient:x.coefficient,
                libelle:x.libelle})
        })
    }
    import("xlsx").then(xlsx => {
        const worksheet = xlsx.utils.json_to_sheet(data);
        const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
        const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });

        let name=prompt(this.translate.instant("Nom du ficher"),this.translate.instant("Preinscriptions_")+Date.now)
        if(!name)
            name="Matières"+ new Date().getTime()
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
