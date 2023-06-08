import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ConfirmationService, MenuItem, MessageService, SelectItem } from 'primeng/api';
import { Annee } from 'src/app/model/annee';
import * as FileSaver from 'file-saver';
import { Scolarite } from 'src/app/model/scolarite';
import { AnneeService } from 'src/app/service/anneeservice';
import { NiveauService } from 'src/app/service/niveauService';
import { ScolariteserviceService } from 'src/app/service/scolarite/scolariteservice.service';
import { MytranslateService } from 'src/app/service/mytranslate.service';

@Component({
  selector: 'app-pscolarite',
  templateUrl: './pscolarite.component.html',
  styleUrls: ['./pscolarite.component.scss'],
  providers: [MessageService, ConfirmationService]
})
export class PscolariteComponent implements OnInit {

    scolariteDialog: boolean;as

    scolarites: Scolarite[];

    scolarite: Scolarite;

    selectedScolarites: Scolarite[];

    submitted: boolean;

    disable: boolean;

    cols: any[];

    niveauDatabase: any[];

    niveaux: SelectItem[]=[];

    anneeDatabase: any[];

    annees: SelectItem[]=[];

    a:Annee;
    n:number;
    an: SelectItem[]=[];
    annee:Annee[];

    @ViewChild('dt') dt: ElementRef;
    items: MenuItem[];
    exportColumns:any[];



    constructor(private niveauService: NiveauService,private anneeService: AnneeService,private scolariteService: ScolariteserviceService, private messageService: MessageService,
        private confirmationService: ConfirmationService,private translate:MytranslateService) { }

       async ngOnInit(): Promise<void> {

        this.niveauDatabase=  await this.niveauService.getNiveaux();
        this.anneeDatabase=  await this.anneeService.getAnnees();
        this.annee=[];
        this.anneeService.getAnnees().then(data =>{ this.annee = data;console.log(data)
                                                    this.n=data.length-1;console.log(this.n);
                                                    this.a=data[this.n];console.log(new Date(this.a.dateFin));
                                                    this.an.push({label:this.a.libelle , value: this.a})


                                                    });

        this.scolarites=[];
        this.scolariteService.getScolarites().then(data =>{ this.scolarites = data;console.log(data)});

        this.cols = [

            {field: 'montant', header: 'Montant'},
            {field: 'niveau', header: 'Niveau'},
            {field: 'annee', header: 'Année scolaire'}
        ];

        this.exportColumns = this.cols.map(col => ({title: col.header, dataKey: col.field}));

        this.items = [
          {label:  this.translate.instant('En PDF'), icon: 'pi pi-file-pdf', command: () => {
              this.exportPdf();
              }
          },
          {label:  this.translate.instant('En CSV'), icon: 'pi pi-file-o', command: () => {
                  if(this.selectedScolarites)
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

        this.anneeDatabase.forEach(data=>{
            this.annees.push({label:data.libelle , value: data})
          })

      }





      openNew() {
        this.scolarite = {};
        this.submitted = false;
        this.scolariteDialog = true;
        this.disable=true;
    }

    deleteSelectedScolarites() {
        this.confirmationService.confirm({
            message: 'Êtes-vous sûr de supprimer?',
            header: 'Confirmer',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {

        this.selectedScolarites.forEach(s=>{
                        this.scolariteService.deleteScolarite(s)
                })
                this.scolarites = this.scolarites.filter(val => !this.selectedScolarites.includes(val));
                this.selectedScolarites= null;
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Scolarités supprimées', life: 3000});
            }
        });
    }

    editScolarite(scolarite: Scolarite) {
        this.scolarite = {...scolarite};
        this.scolariteDialog = true;
        this.disable=false;
    }

    deleteScolarite(scolarite: Scolarite) {
        this.confirmationService.confirm({
            message: 'Êtes-vous sûr de supprimer ?',
            header: 'Confirmer',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
               this.scolariteService.deleteScolarite(scolarite)
                this.scolarites = this.scolarites.filter(val => val.id !== scolarite.id);
                this.scolarite = {};
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Scolarité supprimée', life: 3000});
            }
        });
    }

    hideDialog() {
        this.scolariteDialog = false;
        this.submitted = false;
    }

    verification(){
        if(this.scolarite.montantScolarite ==0 ||
          this.scolarite.niveau == null ||
          this.scolarite.annee == null)
       {
        this.disable = true;
       }
       else
        this.disable =false;
    }

     async saveScolarite() {
        this.submitted = true;



        if (this.scolarite.niveau) {
            if (this.scolarite.id) {
             this.scolariteService.updateScolarite(this.scolarite).then(x=>{
              this.scolariteService.getScolarites().then(data =>{ this.scolarites = data;console.log(data)});
          })

                //this.scolarites[this.findIndexById(this.scolarite.id)] = this.scolarite;
                this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Modification effectuée avec succès', life: 3000});
            } else {
               this.scolariteService.addScolarite(this.scolarite).then(x=>{
                  this.scolariteService.getScolarites().then(data =>{ this.scolarites = data;console.log(data)});
              })


                this.messageService.add({severity:  'success', summary: 'Succès', detail: 'Enregistrement effectué avec succès', life: 3000});
            }

           this.scolarites= await this.scolariteService.getScolarites();
            this.scolarites = [...this.scolarites];
            this.scolariteDialog = false;
            this.scolarite = {};
        }
    }

    findIndexById(id: string): number {
        let index = -1;
        for (let i = 0; i < this.scolarites.length; i++) {
            if (this.scolarites[i].id === id) {
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
        if(this.selectedScolarites){
            this.selectedScolarites.forEach(x=>{
                data.push({annee:x.annee.libelle,niveau:x.niveau.libelle,
                    montant:x.montantScolarite})
            })
        }
        else{
            this.scolarites.forEach(x=>{
                data.push({annee:x.annee.libelle,niveau:x.niveau.libelle,
                    montant:x.montantScolarite})
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
        if(this.selectedScolarites){
            this.selectedScolarites.forEach(x=>{
                data.push({annee:x.annee.libelle,niveau:x.niveau.libelle,
                montant:x.montantScolarite})
            })
        }
        else{
            this.scolarites.forEach(x=>{
                data.push({annee:x.annee.libelle,niveau:x.niveau.libelle,
                    montant:x.montantScolarite})
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
