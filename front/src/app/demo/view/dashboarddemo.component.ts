import {Component, OnInit} from '@angular/core';
import {MenuItem, SelectItem} from 'primeng/api';
import { Annee } from 'src/app/model/annee';
import { PreinscriptionAcceptComponent } from 'src/app/page/preinscription-accept/preinscription-accept.component';
import { AnneeService } from 'src/app/service/anneeservice';
import { ClasseserviceService } from 'src/app/service/classe/classeservice.service';
import { EleveserviceService } from 'src/app/service/eleve/eleveservice.service';
import { MatiereserviceService } from 'src/app/service/matiere/matiereservice.service';
import { PreinscriptionserviceService } from 'src/app/service/preinscription/preinscriptionservice.service';
import { ProfesseurserviceService } from 'src/app/service/professeur/professeurservice.service';

@Component({
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboarddemo.scss'],
})
export class DashboardDemoComponent implements OnInit {

    chart1: any;

    chartOptions1: any;

    chart2: any;

    chartOptions2: any;

    prof:number;
    eleve:number;
    classe:number;
    matiere:number;

    annees:Annee[];
    annee:string;
    Preinscription:number[];


    lineData: any;

    barData: any;

    pieData: any;

    polarData: any;

    radarData: any;

    chartsOptions: any;

    chartsOptions2: any;

    constructor(private professeurService: ProfesseurserviceService,private classeService: ClasseserviceService,
        private matiereService: MatiereserviceService,private eleveService: EleveserviceService,
        private anneeService: AnneeService, private preinscriptionService:PreinscriptionserviceService) { }

    async ngOnInit() {
       this.prof= await this.professeurService.getProfesseursCount();
       this.classe= await this.classeService.getClassesCount();
       this.matiere= await this.matiereService.getMatieresCount();

       this.eleveService.getElevesInscrire().then(data =>{ this.eleve = data.length;});



       this.lineData = {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [
            {
                label: 'First Dataset',
                data: [65, 59, 80, 81, 56, 55, 40],
                fill: false,
                backgroundColor: 'rgb(255, 205, 86)',
                borderColor: 'rgb(255, 205, 86)'
            },
            {
                label: 'Second Dataset',
                data: [28, 48, 40, 19, 86, 27, 90],
                fill: false,
                backgroundColor: 'rgb(75, 192, 192)',
                borderColor: 'rgb(75, 192, 192)'
            }
        ]
    };

    this.barData = {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [
            {
                label: 'My First dataset',
                backgroundColor: 'rgb(255, 99, 132)',
                borderColor: 'rgb(255, 99, 132)',
                data: [65, 59, 80, 81, 56, 55, 40]
            },
            {
                label: 'My Second dataset',
                backgroundColor: 'rgb(54, 162, 235)',
                borderColor: 'rgb(54, 162, 235)',
                data: [28, 48, 40, 19, 86, 27, 90]
            }
        ]
    };





    this.annees = await this.anneeService.getAnnees();


    this.Preinscription= await this.preinscriptionService.getPreinscriptionsbyAnnee();

    this.pieData = {
        labels:this.annees ,
        datasets: [
            {
                data: this.Preinscription,
                backgroundColor: [
                    'rgb(54, 162, 235)',
                    'rgb(255, 99, 132)',
                    'rgb(255, 205, 86)',
                    'rgb(75, 192, 192)'
                ]
            }]
    };

    this.polarData = {
        datasets: [{
            data: [
                11,
                16,
                7,
                3
            ],
            backgroundColor: [
                'rgb(54, 162, 235)',
                'rgb(255, 99, 132)',
                'rgb(255, 205, 86)',
                'rgb(75, 192, 192)'
            ],
            label: 'My dataset'
        }],
        labels: [
            'Blue',
            'Purple',
            'Orange',
            'Green'
        ]
    };

    this.radarData = {
        labels: ['Eating', 'Drinking', 'Sleeping', 'Designing', 'Coding', 'Cycling', 'Running'],
        datasets: [
            {
                label: 'My First dataset',
                backgroundColor: 'rgba(54, 162, 235,0.2)',
                borderColor: 'rgba(54, 162, 235,1)',
                pointBackgroundColor: 'rgba(54, 162, 235,1)',
                pointBorderColor: '#fff',
                pointHoverBackgroundColor: '#fff',
                pointHoverBorderColor: 'rgba(54, 162, 235,1)',
                data: [65, 59, 90, 81, 56, 55, 40]
            },
            {
                label: 'My Second dataset',
                backgroundColor: 'rgba(255, 99, 132,0.2)',
                borderColor: 'rgba(255, 99, 132,1)',
                pointBackgroundColor: 'rgba(255, 99, 132,1)',
                pointBorderColor: '#fff',
                pointHoverBackgroundColor: '#fff',
                pointHoverBorderColor: 'rgba(255, 99, 132,1)',
                data: [28, 48, 40, 19, 96, 27, 100]
            }
        ]
    };

    this.chartsOptions = {
        legend: {
            display: true,
            labels: {
                fontColor: '#A0A7B5'
            }
        },
        responsive: true,
        scales: {
            yAxes: [{
                ticks: {
                    fontColor: '#A0A7B5'
                },
                gridLines: {
                    color:  'rgba(160, 167, 181, .3)',
                }
            }],
            xAxes: [{
                ticks: {
                    fontColor: '#A0A7B5'
                },
                gridLines: {
                    color:  'rgba(160, 167, 181, .3)',
                }
            }],
        }
    };

    this.chartsOptions2 = {
        legend: {
            display: true,
            labels: {
                fontColor: '#A0A7B5'
            }
        },
        responsive: true
    };
//ancien



        this.chart1 = {
            labels: ['8Sun', '9Mon', '10Thu', '11Wed', '12Fri', '13Sat', '14Sun'],
            datasets: [
                {
                    label: 'Revenue',
                    data: [12, 19, 3, 5, 2, 3, 9],
                    borderColor: [
                        '#FFA928',
                    ],
                    borderWidth: 4,
                    fill: true,
                    backgroundColor: [
                        'rgba(255, 169, 40, .2)'
                    ],
                }
            ]
        };

        this.chartOptions1 = {
            legend: {
                display: false,
            },
            maintainAspectRatio: false,
            hover: {
                mode: 'index'
            },
            scales: {
                xAxes: [{
                    display: false,
                }],
                yAxes: [{
                    display: false,
                }]
            }
        };

        this.chart2 = {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [
                {
                    label: 'Revenue',
                    data: [12, 19, 3, 5, 2, 3, 9],
                    borderColor: [
                        getComputedStyle(document.body).getPropertyValue('--primary-color') || '#2c84d8',
                    ],
                    borderWidth: 4,
                    fill: true,
                    backgroundColor: [
                        getComputedStyle(document.body).getPropertyValue('--primary-lighter-color') || '#2c84d8',
                    ],
                }
            ]
        };

        this.chartOptions2 = {
            legend: {
                display: false,
            },
            maintainAspectRatio: false,
            hover: {
                mode: 'index'
            },
            scales: {
                xAxes: [{
                    display: true,
                    gridLines: {
                        color:    'transparent',
                    },
                    ticks: {
                        fontColor: '#BFC2C6'
                    }
                }],
                yAxes: [{
                    display: true,
                    gridLines: {
                        color:  'rgba(191, 194, 198, .45)',
                        borderDash:[5, 10],
                    },
                    ticks: {
                        fontColor:  '#BFC2C6',
                        min: 0,
                        stepSize: 5,
                    }
                }]
            }
        };
    }
}
