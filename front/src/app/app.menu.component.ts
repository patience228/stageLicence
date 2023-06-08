import {Component, OnInit} from '@angular/core';
import {AppMainComponent} from './app.main.component';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html',
})
export class AppMenuComponent implements OnInit {

    model: any[];

    constructor(public appMain: AppMainComponent) {}

    ngOnInit() {
        this.model = [
            {label: 'Tableau de bord', icon: 'pi pi-home', routerLink: ['/']},
            {
                label: 'Année scolaire', icon: 'pi pi-calendar-plus', routerLink: ['/page'],
                items: [
                    {label: 'Gestion des  années', icon: 'pi pi-id-card', routerLink: ['/page/annee']}

                ]
            },

            {
                label: 'Niveau', icon: 'pi pi-sort-numeric-down', routerLink: ['/page'],
                items: [

                    {label: 'Gestion des  niveaux', icon: 'pi pi-check-square', routerLink: ['/page/niveau']},

                ]
            },

            {
                label: 'Préinscription', icon: 'pi pi-align-left', routerLink: ['/page'],
                items: [

                    {label: 'Gestion des  préinscriptions', icon: 'pi pi-align-justify', routerLink: ['/page/preinscription']},
                ]
            },

            {
                label: 'Inscription', icon: 'pi pi-align-left', routerLink: ['/page'],
                items: [
                    {label: 'Inscription des  préinscrits', icon: 'pi pi-save', routerLink: ['/page/preinscriptionAcept']},
                    {label: 'Inscription ', icon: 'pi pi-save', routerLink: ['page/inscriAncien']},
                    {label: 'Liste des  inscriptions', icon: 'pi pi-align-justify', routerLink: ['/page/inscription']},
                ]
            },
           /* {
                label: 'Login', icon: 'pi pi-align-left',routerLink: ['/login'],

            },*/

            {
                label: 'Classe', icon: 'pi pi-th-large', routerLink: ['/page'],
                items: [
                    {label: 'Gestion des classes', icon: 'pi pi-ticket', routerLink: ['/page/classe']},
                    {label: 'Gestion des affectations', icon: 'pi pi-eject', routerLink: ['/page/affectation']},
                ]
            },

            {
                label: 'Elève', icon: 'pi pi-user-plus', routerLink: ['/page'],
                items: [
                    {label: 'Gestion des  élèves', icon: 'pi pi-id-card', routerLink: ['/page/eleve']},

                ]
            },


            {
                label: 'Matière', icon: 'pi pi-clone', routerLink: ['/page'],
                items: [
                    {label: 'Gestion des matières', icon: 'pi pi-copy', routerLink: ['/page/matiere']},
                    {label: 'Gestion des enseignements', icon: 'pi pi-star-o', routerLink: ['/page/enseignement']},

                ]
            },

            {
                label: 'Professeur', icon: 'pi pi-id-card', routerLink: ['/page'],
                items: [
                    {label: 'Gestion des professeurs', icon: 'pi pi-table', routerLink: ['/page/professeur']},
                    {label: 'Gestion des titulaires', icon: 'pi pi-users', routerLink: ['/page/titulaire']},

                ]
            },

            {
                label: 'Utilisateur', icon: 'pi pi-users', routerLink: ['/page'],
                items: [
                    {label: 'Gestion des utilisateurs', icon: 'pi pi-user-plus', routerLink: ['/page/user']},

                ]
            },


            {
                label: 'Evaluation', icon: 'pi pi-bookmark', routerLink: ['/page'],
                items: [
                    {label: 'Gestion des évaluations', icon: 'pi pi-file', routerLink: ['/page/evaluation']},
                    {label: 'Gestion des notes', icon: 'pi pi-pencil', routerLink: ['/page/note']},
                    {label: 'Notes', icon: 'pi pi-mobile', routerLink: ['/page/noteVue']},
                    {label: 'Bulletin', icon: 'pi pi-star-io', routerLink: ['/page/bulletin']},
                ]
            },


            {
                label: 'Scolarite', icon: 'pi pi-circle-off', routerLink: ['/page'],
                items: [
                    {label: 'Gestion de la scolarité', icon: 'pi pi-tablet', routerLink: ['/page/scolarite']},

                ]
            },

            {
                label: 'UI Kit', icon: 'pi pi-star-o', routerLink: ['/uikit'],
                items: [
                    {label: 'Gestion des  élèves', icon: 'pi pi-id-card', routerLink: ['/uikit/formlayout']},
                    {label: 'Input', icon: 'pi pi-check-square', routerLink: ['/uikit/input']},
                    {label: 'Float Label', icon: 'pi pi-bookmark', routerLink: ['/uikit/floatlabel']},
                    {label: 'Invalid State', icon: 'pi pi-exclamation-circle', routerLink: ['/uikit/invalidstate']},
                    {label: 'Button', icon: 'pi pi-mobile', routerLink: ['/uikit/button'], class: 'rotated-icon'},
                    {label: 'Tree', icon: 'pi pi-share-alt', routerLink: ['/uikit/tree']},
                    {label: 'Panel', icon: 'pi pi-tablet', routerLink: ['/uikit/panel']},
                    {label: 'Overlay', icon: 'pi pi-clone', routerLink: ['/uikit/overlay']},
                    {label: 'Media', icon: 'pi pi-image', routerLink: ['/uikit/media']},
                    {label: 'Menu', icon: 'pi pi-bars', routerLink: ['/uikit/menu']},
                    {label: 'Message', icon: 'pi pi-comment', routerLink: ['/uikit/message']},
                    {label: 'File', icon: 'pi pi-file', routerLink: ['/uikit/file']},
                    {label: 'Chart', icon: 'pi pi-chart-bar', routerLink: ['/uikit/charts']},
                    {label: 'Misc', icon: 'pi pi-circle-off', routerLink: ['/uikit/misc']},
                    {label: 'Table', icon: 'pi pi-circle-off', routerLink: ['/uikit/table']}
                ]
            },
            {
                label: 'Utilities', icon: 'pi pi-compass', routerLink: ['utilities'],
                items: [
                    {label: 'Display', icon: 'pi pi-desktop', routerLink: ['utilities/display']},
                    {label: 'Elevation', icon: 'pi pi-external-link', routerLink: ['utilities/elevation']},
                    {label: 'FlexBox', icon: 'pi pi-directions', routerLink: ['utilities/flexbox']},
                    {label: 'Icons', icon: 'pi pi-search', routerLink: ['utilities/icons']},
                    {label: 'Text', icon: 'pi pi-pencil', routerLink: ['utilities/text']},
                    {label: 'Widgets', icon: 'pi pi-star-o', routerLink: ['utilities/widgets']},
                    {label: 'Grid System', icon: 'pi pi-th-large', routerLink: ['utilities/grid']},
                    {label: 'Spacing', icon: 'pi pi-arrow-right', routerLink: ['utilities/spacing']},
                    {label: 'Typography', icon: 'pi pi-align-center', routerLink: ['utilities/typography']}
                ]
            },
            {
                label: 'Pages', icon: 'pi pi-briefcase', routerLink: ['/pages'],
                items: [
                    {label: 'Crud', icon: 'pi pi-pencil', routerLink: ['/pages/crud']},
                    {label: 'Calendar', icon: 'pi pi-calendar-plus', routerLink: ['/pages/calendar']},
                    {label: 'Timeline', icon: 'pi pi-fw pi-calendar', routerLink: ['/pages/timeline']},
                    {label: 'Landing', icon: 'pi pi-globe', url: 'assets/pages/landing.html', target: '_blank'},
                    {label: 'Login', icon: 'pi pi-sign-in', routerLink: ['/login']},
                    {label: 'Invoice', icon: 'pi pi-dollar', routerLink: ['/pages/invoice']},
                    {label: 'Help', icon: 'pi pi-question-circle', routerLink: ['/pages/help']},
                    {label: 'Error', icon: 'pi pi-times-circle', routerLink: ['/error']},
                    {label: 'Not Found', icon: 'pi pi-exclamation-circle', routerLink: ['/notfound']},
                    {label: 'Access Denied', icon: 'pi pi-lock', routerLink: ['/access']},
                    {label: 'Empty', icon: 'pi pi-circle-off', routerLink: ['/pages/empty']}
                ]
            },
            {
                label: 'Hierarchy', icon: 'pi pi-align-left',
                items: [
                    {
                        label: 'Submenu 1', icon: 'pi pi-align-left',
                        items: [
                            {
                                label: 'Submenu 1.1', icon: 'pi pi-align-left',
                                items: [
                                    {label: 'Submenu 1.1.1', icon: 'pi pi-align-left'},
                                    {label: 'Submenu 1.1.2', icon: 'pi pi-align-left'},
                                    {label: 'Submenu 1.1.3', icon: 'pi pi-align-left'},
                                ]
                            },
                            {
                                label: 'Submenu 1.2', icon: 'pi pi-align-left',
                                items: [
                                    {label: 'Submenu 1.2.1', icon: 'pi pi-align-left'}
                                ]
                            },
                        ]
                    },
                    {
                        label: 'Submenu 2', icon: 'pi pi-align-left',
                        items: [
                            {
                                label: 'Submenu 2.1', icon: 'pi pi-align-left',
                                items: [
                                    {label: 'Submenu 2.1.1', icon: 'pi pi-align-left'},
                                    {label: 'Submenu 2.1.2', icon: 'pi pi-align-left'},
                                ]
                            },
                            {
                                label: 'Submenu 2.2', icon: 'pi pi-align-left',
                                items: [
                                    {label: 'Submenu 2.2.1', icon: 'pi pi-align-left'},
                                ]
                            },
                        ]
                    }
                ]
            },
            {
                label: 'Start', icon: 'pi pi-download',
                items: [
                    {
                        label: 'Buy Now', icon: 'pi pi-shopping-cart', url: ['https://www.primefaces.org/store']
                    },
                    {
                        label: 'Documentation', icon: 'pi pi-info-circle', routerLink: ['/documentation']
                    }
                ]
            },
        ];
    }
}
