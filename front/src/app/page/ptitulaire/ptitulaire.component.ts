import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ConfirmationService, MenuItem, MessageService, SelectItem } from 'primeng/api';
import { Titulaire } from 'src/app/model/titulaire';
import * as FileSaver from 'file-saver';
import { AnneeService } from 'src/app/service/anneeservice';
import { ClasseserviceService } from 'src/app/service/classe/classeservice.service';
import { ProfesseurserviceService } from 'src/app/service/professeur/professeurservice.service';
import { TitulaireService } from 'src/app/service/titulaire/titulaire.service';
import { MytranslateService } from 'src/app/service/mytranslate.service';

@Component({
  selector: 'app-ptitulaire',
  templateUrl: './ptitulaire.component.html',
  styleUrls: ['./ptitulaire.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class PtitulaireComponent implements OnInit {

   titulaireDialog: boolean;as

   titulaires: Titulaire[];

   titulaire: Titulaire;

    selectedTitulaires: Titulaire[];

    submitted: boolean;

    disable: boolean;

    cols: any[];

    classeDatabase: any[];

    classes: SelectItem[]=[];

    anneeDatabase: any[];

    annees: SelectItem[]=[];

    professeurDatabase: any[];

    professeurs: SelectItem[]=[];

    @ViewChild('dt') dt: ElementRef;
    items: MenuItem[];
    exportColumns:any[];



    constructor(private classeService: ClasseserviceService,private anneeService: AnneeService,private professeurService: ProfesseurserviceService,
        private messageService: MessageService,private titulaireService:TitulaireService,
        private confirmationService: ConfirmationService,private translate:MytranslateService) { }

       async ngOnInit(): Promise<void> {

        this.classeDatabase=  await this.classeService.getClasses();
        this.anneeDatabase=  await this.anneeService.getAnnees();
        this.professeurDatabase=  await this.professeurService.getProfesseurs();

        this.titulaires=[];
        this.titulaireService.getTitulaires().then(data =>{ this.titulaires = data;console.log(data)});

        this.cols = [

            {field: 'professeur', header: 'Professeur'},
            {field: 'classe', header: 'Classe'},
            {field: 'annee', header: 'Année scolaire'}
        ];

        this.exportColumns = this.cols.map(col => ({title: col.header, dataKey: col.field}));

        this.items = [
          {label:  this.translate.instant('En PDF'), icon: 'pi pi-file-pdf', command: () => {
              this.exportPdf();
              }
          },
          {label:  this.translate.instant('En CSV'), icon: 'pi pi-file-o', command: () => {
                  if(this.selectedTitulaires)
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

        this.anneeDatabase.forEach(data=>{
            this.annees.push({label:data.libelle , value: data})
          })

          this.professeurDatabase.forEach(data=>{
            this.professeurs.push({label:data.nom +' '+data.prenom , value: data})
          })

      }






      openNew() {
        this.titulaire = {};
        this.submitted = false;
        this.titulaireDialog = true;
        this.disable=true;
    }

    deleteSelectedTitulaires() {
        this.confirmationService.confirm({
            message: 'Êtes-vous sûr de supprimer?',
            header: 'Confirmer',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {

        this.selectedTitulaires.forEach(s=>{
                        this.titulaireService.deleteTitulaire(s)
                })
                this.titulaires = this.titulaires.filter(val => !this.selectedTitulaires.includes(val));
                this.selectedTitulaires= null;
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Titulaires supprimés', life: 3000});
            }
        });
    }

    editTitulaire(titulaire: Titulaire) {
        this.titulaire = {...titulaire};
        this.titulaireDialog = true;
        this.disable=false;
    }

    deleteTitulaire(titulaire: Titulaire) {
        this.confirmationService.confirm({
            message: 'Êtes-vous sûr de supprimer ?',
            header: 'Confirmer',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
               this.titulaireService.deleteTitulaire(titulaire)
                this.titulaires = this.titulaires.filter(val => val.id !== titulaire.id);
                this.titulaire = {};
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Titulaire supprimé', life: 3000});
            }
        });
    }

    hideDialog() {
        this.titulaireDialog = false;
        this.submitted = false;
    }

    verification(){
        if(this.titulaire.professeur ==null ||
          this.titulaire.classe == null ||
          this.titulaire.annee == null)
       {
        this.disable = true;
       }
       else
        this.disable =false;
    }

     async saveTitulaire() {
        this.submitted = true;



        if (this.titulaire.professeur) {
            if (this.titulaire.id) {
             this.titulaireService.updateTitulaire(this.titulaire).then(x=>{
              this.titulaireService.getTitulaires().then(data =>{ this.titulaires = data;console.log(data)});
          })

               // this.titulaires[this.findIndexById(this.titulaire.id)] = this.titulaire;
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Modification effectuée avec succès', life: 3000});
            } else {
               this.titulaireService.addTitulaire(this.titulaire).then(x=>{
                  this.titulaireService.getTitulaires().then(data =>{ this.titulaires = data;console.log(data)});
              })


                this.messageService.add({severity:  'success', summary: 'Succès', detail: 'Enregistrement effectué avec succès', life: 3000});
            }

           this.titulaires= await this.titulaireService.getTitulaires();
            this.titulaires = [...this.titulaires];
            this.titulaireDialog = false;
            this.titulaire = {};
        }
    }

    findIndexById(id: string): number {
        let index = -1;
        for (let i = 0; i < this.titulaires.length; i++) {
            if (this.titulaires[i].id === id) {
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
        if(this.selectedTitulaires){
            this.selectedTitulaires.forEach(x=>{
                data.push({annee:x.annee.libelle,
                classe:x.classe.libelle,
                professeur:x.professeur.nom+' '+x.professeur.prenom})
            })
        }
        else{
            this.titulaires.forEach(x=>{
                data.push({annee:x.annee.libelle,
                    classe:x.classe.libelle,
                    professeur:x.professeur.nom+' '+x.professeur.prenom})
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
        if(this.selectedTitulaires){
            this.selectedTitulaires.forEach(x=>{
                data.push({annee:x.annee.libelle,
                    classe:x.classe.libelle,
                    professeur:x.professeur.nom+' '+x.professeur.prenom})
            })
        }
        else{
            this.titulaires.forEach(x=>{
                data.push({annee:x.annee.libelle,
                    classe:x.classe.libelle,
                    professeur:x.professeur.nom+' '+x.professeur.prenom})
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
