import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ConfirmationService, MenuItem, MessageService } from 'primeng/api';
import { Preinscription } from 'src/app/model/preinscription';
import { DateService } from 'src/app/service/date.service';
import * as FileSaver from 'file-saver';
import { MytranslateService } from 'src/app/service/mytranslate.service';
import { PreinscriptionserviceService } from 'src/app/service/preinscription/preinscriptionservice.service';

@Component({
  selector: 'app-preinscriptionvalide',
  templateUrl: './preinscriptionvalide.component.html',
  styleUrls: ['./preinscriptionvalide.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class PreinscriptionvalideComponent implements OnInit {


    preinscription: Preinscription;
    preinscriptionsValid: Preinscription[];
    selectedPreinscriptionValid: Preinscription[];

    cols: any[];
    @ViewChild('dt') dt: ElementRef;
    items: MenuItem[];
    exportColumns:any[];


    constructor(private preinscriptionService: PreinscriptionserviceService,private translate:MytranslateService,
         private dateService:DateService,private messageService: MessageService,private confirmationService: ConfirmationService) { }

        ngOnInit():void {

        this.preinscriptionsValid=[];
        this.preinscriptionService.getPreinscriptionsbyEtat(2).then(data =>{ this.preinscriptionsValid = data;console.log(data)});


        this.cols = [


            {field: 'nom', header: 'Nom'},
            {field: 'prenom', header: 'Prénoms'},
            {field: 'sexe', header: 'Sexe'},

            {field: 'ecoleProvenance', header: 'Ecole de Provenance'},
            {field: 'classeAnterieure', header: 'Classe Antérieure'},
            {field: 'moyenne', header: 'Moyenne'},
           // {field: 'datePreinscription', header: 'Date de Préinscription'},
            {field: 'niveau', header: 'Niveau'},

            {field: 'annee', header: 'Année'},


        ];

        this.exportColumns = this.cols.map(col => ({title: col.header, dataKey: col.field}));

        this.items = [
          {label:  this.translate.instant('En PDF'), icon: 'pi pi-file-pdf', command: () => {
              this.exportPdf();
              }
          },
          {label:  this.translate.instant('En CSV'), icon: 'pi pi-file-o', command: () => {
                  if(this.selectedPreinscriptionValid)
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




    validatePreinscription(preinscription: Preinscription){
        this.preinscriptionService.getPreinscriptionById(preinscription)
        preinscription.etat=2;

        this.preinscriptionService.updatePreinscription(preinscription)
        this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Validation effectuée avec succès', life: 3000});
    }

    deletePreinscription(preinscription: Preinscription) {
        this.confirmationService.confirm({
            message: 'Êtes-vous sûr de supprimer cette préinscription ?',
            header: 'Confirmer',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
               this.preinscriptionService.deletePreinscription(preinscription)
                this.preinscriptionsValid = this.preinscriptionsValid.filter(val => val.id !== preinscription.id);
                this.preinscription = {};
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Préinscription supprimée', life: 3000});
            }
        });
    }

    exportPdf() {
        let data=[];
        if(this.selectedPreinscriptionValid){
            this.selectedPreinscriptionValid.forEach(x=>{
                data.push({annee:x.annee.libelle,niveau:x.niveau.libelle,
                ecoleProvenance:x.ecoleProvenance,classeAnterieure:x.classeAnterieure,
                moyenne:x.moyenne,nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
            })
        }
        else{
            this.preinscriptionsValid.forEach(x=>{
                data.push({annee:x.annee.libelle,niveau:x.niveau.libelle,
                ecoleProvenance:x.ecoleProvenance,classeAnterieure:x.classeAnterieure,
                moyenne:x.moyenne,nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
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
        if(this.selectedPreinscriptionValid){
            this.selectedPreinscriptionValid.forEach(x=>{
                data.push({annee:x.annee.libelle,niveau:x.niveau.libelle,
                ecoleProvenance:x.ecoleProvenance,classeAnterieure:x.classeAnterieure,
                moyenne:x.moyenne,nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
            })
        }
        else{
            this.preinscriptionsValid.forEach(x=>{
                data.push({annee:x.annee.libelle,niveau:x.niveau.libelle,
                ecoleProvenance:x.ecoleProvenance,classeAnterieure:x.classeAnterieure,
                moyenne:x.moyenne,nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
            })
        }
        import("xlsx").then(xlsx => {
            const worksheet = xlsx.utils.json_to_sheet(data);
            const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
            const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });

            let name=prompt(this.translate.instant("Nom du ficher"),this.translate.instant("Preinscriptions_")+Date.now)
            if(!name)
                name="Preinscriptions_"+ new Date().getTime()
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
