import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ConfirmationService, MenuItem, MessageService } from 'primeng/api';
import { Niveau } from 'src/app/model/niveau';
import * as FileSaver from 'file-saver';
import { MytranslateService } from 'src/app/service/mytranslate.service';
import { NiveauService } from 'src/app/service/niveauService';

@Component({
  selector: 'app-pniveau',
  templateUrl: './pniveau.component.html',
  styleUrls: ['./pniveau.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class PniveauComponent implements OnInit {

  niveauDialog: boolean;as

  niveaux: Niveau[];

  niveau: Niveau;

  selectedNiveaux: Niveau[];

  submitted: boolean;

  disable: boolean;

  cols: any[];

  @ViewChild('dt') dt: ElementRef;
    items: MenuItem[];
    exportColumns:any[];

  constructor(private niveauService: NiveauService, private messageService: MessageService,
    private confirmationService: ConfirmationService,private translate:MytranslateService) { }

  ngOnInit(): void {
    this.niveaux=[];
    this.niveauService.getNiveaux().then(data =>{ this.niveaux = data;console.log(data)});

    this.cols = [

        {field: 'libelle', header: 'Libellé'}


    ];




    this.exportColumns = this.cols.map(col => ({title: col.header, dataKey: col.field}));

    this.items = [
      {label:  this.translate.instant('En PDF'), icon: 'pi pi-file-pdf', command: () => {
          this.exportPdf();
          }
      },
      {label:  this.translate.instant('En CSV'), icon: 'pi pi-file-o', command: () => {
              if(this.selectedNiveaux)
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

  openNew() {
    this.niveau = {};
    this.submitted = false;
    this.niveauDialog = true;
    this.disable=true;
}

deleteSelectedNiveaux() {
    this.confirmationService.confirm({
        message: 'Êtes-vous sûr de supprimer?',
        header: 'Confirmer',
        icon: 'pi pi-exclamation-triangle',
        accept: () => {

    this.selectedNiveaux.forEach(s=>{
                    this.niveauService.deleteNiveau(s)
            })
            this.niveaux = this.niveaux.filter(val => !this.selectedNiveaux.includes(val));
            this.selectedNiveaux = null;
            this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Niveaux supprimés', life: 3000});
        }
    });
}

editNiveau(niveau: Niveau) {
    this.niveau = {...niveau};
    this.niveauDialog = true;
    this.disable=false;
}

deleteNiveau(niveau: Niveau) {
    this.confirmationService.confirm({
        message: 'Êtes-vous sûr de supprimer ' + niveau.libelle + '?',
        header: 'Confirmer',
        icon: 'pi pi-exclamation-triangle',
        accept: () => {
           this.niveauService.deleteNiveau(niveau)
            this.niveaux = this.niveaux.filter(val => val.id !== niveau.id);
            this.niveau = {};
            this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Niveau supprimé', life: 3000});
        }
    });
}

hideDialog() {
    this.niveauDialog = false;
    this.submitted = false;
}

verification(){
    if(this.niveau.libelle.length==0 )
   {
    this.disable = true;
   }
   else
    this.disable =false;
}

 async saveNiveau() {
    this.submitted = true;



    if (this.niveau.libelle.trim()) {
        if (this.niveau.id) {
         this.niveauService.updateNiveau(this.niveau).then(x=>{
            this.niveauService.getNiveaux().then(data =>{ this.niveaux = data;console.log(data)})
         })
           // this.niveaux[this.findIndexById(this.niveau.id)] = this.niveau;
            this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Modification effectuée avec succès', life: 3000});
        } else {
           this.niveauService.addNiveau(this.niveau).then(x=>{
            this.niveauService.getNiveaux().then(data =>{ this.niveaux = data;console.log(data)})
         })

            this.messageService.add({severity:  'success', summary: 'Succès', detail: 'Enregistrement effectué avec succès', life: 3000});
        }

       this.niveaux= await this.niveauService.getNiveaux();
        this.niveaux = [...this.niveaux];
        this.niveauDialog = false;
        this.niveau = {};
    }
}

findIndexById(id: string): number {
    let index = -1;
    for (let i = 0; i < this.niveaux.length; i++) {
        if (this.niveaux[i].id === id) {
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
    if(this.selectedNiveaux){
        this.selectedNiveaux.forEach(x=>{
            data.push({
            libelle:x.libelle})
        })
    }
    else{
        this.niveaux.forEach(x=>{
            data.push({
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
    if(this.selectedNiveaux){
        this.selectedNiveaux.forEach(x=>{
            data.push({
            libelle:x.libelle})
        })
    }
    else{
        this.niveaux.forEach(x=>{
            data.push({
            libelle:x.libelle})
        })
    }
    import("xlsx").then(xlsx => {
        const worksheet = xlsx.utils.json_to_sheet(data);
        const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
        const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });

        let name=prompt(this.translate.instant("Nom du ficher"),this.translate.instant("Preinscriptions_")+Date.now)
        if(!name)
            name="Niveaux_"+ new Date().getTime()
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
