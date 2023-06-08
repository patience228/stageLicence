import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ConfirmationService, MenuItem, MessageService, SelectItem } from 'primeng/api';
import { Affectation } from 'src/app/model/affectation';
import { Eleve } from 'src/app/model/eleve';
import * as FileSaver from 'file-saver';
import { AffectationService } from 'src/app/service/affectation/affectation.service';
import { ClasseserviceService } from 'src/app/service/classe/classeservice.service';
import { MytranslateService } from 'src/app/service/mytranslate.service';


@Component({
  selector: 'app-paffectation',
  templateUrl: './paffectation.component.html',
  styleUrls: ['./paffectation.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class PaffectationComponent implements OnInit {



    affectationDialog: boolean;as

    affectations: Affectation[];

    affectation: Affectation;

    selectedAffectations: Affectation[];

    submitted: boolean;

    disable: boolean;

    cols: any[];

    classeDatabase: any[];

    classes: SelectItem[]=[];

    eleve:Eleve;
    annee:Eleve;

    @ViewChild('dt') dt: ElementRef;
    items: MenuItem[];
    exportColumns:any[];


    constructor(private messageService: MessageService,private confirmationService: ConfirmationService, private affectationService: AffectationService,
        private classeService:ClasseserviceService, private translate:MytranslateService) { }

    async ngOnInit(): Promise<void> {

        this.classeDatabase=  await this.classeService.getClasses();

        this.affectations=[];
        this.affectationService.getAffectations().then(data =>{ this.affectations = data;console.log(data)});

        this.cols = [

            {field: 'nom', header: 'Nom'},
            {field: 'prenom', header: 'Prénoms'},
            {field: 'sexe', header: 'Sexe'},
            {field: 'annee', header: 'Année scolaire'},
            {field: 'classe', header: 'Classe'},


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

        this.classeDatabase.forEach(data=>{
            this.classes.push({label:data.libelle , value: data})
          })




      }


      editAffectation(affectation: Affectation) {
        this.affectation = {...affectation};
        this.eleve = {...affectation.eleve};
        this.affectationDialog = true;
        this.disable=false;
    }

    hideDialog() {
        this.affectationDialog = false;
        this.submitted = false;
    }

    verification(){
        if(
          this.affectation.classe == null
         // this.inscription.dateInscription == null ||


          //this.eleve.matricule.length== 0


        )
       {
        this.disable = true;
       }
       else
        this.disable =false;
    }




    async saveAffectation() {
        this.submitted = true;

        if (this.eleve.nom.trim()) {

            if (this.affectation.id) {

                this.affectationService.updateAffectation(this.affectation).then(x=>{
                    this.affectationService.getAffectations().then(data =>{ this.affectations = data;console.log(data)});
                });
                    this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Modification effectuée avec succès', life: 3000});

               }
               this.affectations= await this.affectationService.getAffectations();
               this.affectations = [...this.affectations];
               this.affectationDialog = false;
               this.affectation = {};

        }
    }


    exportPdf() {
        let data=[];
        if(this.selectedAffectations){
            this.selectedAffectations.forEach(x=>{
                data.push({annee:x.annee.libelle,classe:x.classe.libelle,
                nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
            })
        }
        else{
            this.affectations.forEach(x=>{
                data.push({annee:x.annee.libelle,classe:x.classe.libelle,
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
                data.push({annee:x.annee.libelle,classe:x.classe.libelle,
                    nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
            })
        }
        else{
            this.affectations.forEach(x=>{
                data.push({annee:x.annee.libelle,classe:x.classe.libelle,
                    nom:x.eleve.nom,prenom:x.eleve.prenom,sexe:x.eleve.sexe})
            })
        }
        import("xlsx").then(xlsx => {
            const worksheet = xlsx.utils.json_to_sheet(data);
            const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
            const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });

            let name=prompt(this.translate.instant("Nom du ficher"),this.translate.instant("Preinscriptions_")+Date.now)
            if(!name)
                name="Affectations_"+ new Date().getTime()
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
