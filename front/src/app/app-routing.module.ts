import {RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {DashboardDemoComponent} from './demo/view/dashboarddemo.component';
import {FormLayoutDemoComponent} from './demo/view/formlayoutdemo.component';
import {InvalidStateDemoComponent} from './demo/view/invalidstatedemo.component';
import {FloatLabelDemoComponent} from './demo/view/floatlabeldemo.component';
import {PanelsDemoComponent} from './demo/view/panelsdemo.component';
import {OverlaysDemoComponent} from './demo/view/overlaysdemo.component';
import {MediaDemoComponent} from './demo/view/mediademo.component';
import {MenusDemoComponent} from './demo/view/menusdemo.component';
import {MessagesDemoComponent} from './demo/view/messagesdemo.component';
import {MiscDemoComponent} from './demo/view/miscdemo.component';
import {EmptyDemoComponent} from './demo/view/emptydemo.component';
import {ChartsDemoComponent} from './demo/view/chartsdemo.component';
import {FileDemoComponent} from './demo/view/filedemo.component';
import {DocumentationComponent} from './demo/view/documentation.component';
import {AppMainComponent} from './app.main.component';
import {AppNotfoundComponent} from './pages/app.notfound.component';
import {AppErrorComponent} from './pages/app.error.component';
import {AppAccessdeniedComponent} from './pages/app.accessdenied.component';
import {AppLoginComponent} from './pages/app.login.component';
import {InputDemoComponent} from './demo/view/inputdemo.component';
import {ButtonDemoComponent} from './demo/view/buttondemo.component';
import {TableDemoComponent} from './demo/view/tabledemo.component';
import {ListDemoComponent} from './demo/view/listdemo.component';
import {TreeDemoComponent} from './demo/view/treedemo.component';
import {DisplayComponent} from './utilities/display.component';
import {ElevationComponent} from './utilities/elevation.component';
import {FlexboxComponent} from './utilities/flexbox.component';
import {GridComponent} from './utilities/grid.component';
import {IconsComponent} from './utilities/icons.component';
import {WidgetsComponent} from './utilities/widgets.component';
import {SpacingComponent} from './utilities/spacing.component';
import {TypographyComponent} from './utilities/typography.component';
import {TextComponent} from './utilities/text.component';
import {AppCrudComponent} from './pages/app.crud.component';
import {AppCalendarComponent} from './pages/app.calendar.component';
import {AppTimelineDemoComponent} from './pages/app.timelinedemo.component';
import {AppInvoiceComponent} from './pages/app.invoice.component';
import {AppHelpComponent} from './pages/app.help.component';
import { FanneeScolaireComponent } from './forms/fannee-scolaire/fannee-scolaire.component';
import { PanneescolaireComponent } from './page/panneescolaire/panneescolaire.component';
import { PniveauComponent } from './page/pniveau/pniveau.component';
import { LoginComponent } from './page/login/login.component';
import { AuthGuard } from './service/auth.guard';
import { NoauthGuard } from './service/noauth.guard';
import { PclasseComponent } from './page/pclasse/pclasse.component';
import { PmatiereComponent } from './page/pmatiere/pmatiere.component';
import { PprofesseurComponent } from './page/pprofesseur/pprofesseur.component';
import { PpreinscriptionComponent } from './page/ppreinscription/ppreinscription.component';
import { PinscriptionComponent } from './page/pinscription/pinscription.component';
import { PevaluationComponent } from './page/pevaluation/pevaluation.component';
import { PscolariteComponent } from './page/pscolarite/pscolarite.component';
import { PutilisateurComponent } from './page/putilisateur/putilisateur.component';
import { PtitulaireComponent } from './page/ptitulaire/ptitulaire.component';
import { EnseignementComponent } from './page/enseignement/enseignement.component';
import { PreinscriptionvalideComponent } from './page/preinscriptionvalide/preinscriptionvalide.component';
import { PreinscriptionAcceptComponent } from './page/preinscription-accept/preinscription-accept.component';
import { PrecuComponent } from './page/precu/precu.component';
import { PeleveComponent } from './page/peleve/peleve.component';
import { PaffectationComponent } from './page/paffectation/paffectation.component';
import { RecuFComponent } from './page/recu-f/recu-f.component';
import { EleveDetailsComponent } from './page/eleve-details/eleve-details.component';
import { IncriptionAncienComponent } from './page/incription-ancien/incription-ancien.component';
import { SaisieNoteComponent } from './page/saisie-note/saisie-note.component';
import { PnoteComponent } from './page/pnote/pnote.component';
import { DetailsNotesComponent } from './page/details-notes/details-notes.component';
import { PbulletinComponent } from './page/pbulletin/pbulletin.component';
import { NotevueComponent } from './page/notevue/notevue.component';

@NgModule({
    imports: [
        RouterModule.forRoot([
            {
                path: '', component: AppMainComponent,
                canActivate:[AuthGuard],
                children: [
                    {path: '', component: DashboardDemoComponent},
                    {path: 'uikit/formlayout', component: FormLayoutDemoComponent},
                    {path: 'uikit/floatlabel', component: FloatLabelDemoComponent},
                    {path: 'uikit/invalidstate', component: InvalidStateDemoComponent},
                    {path: 'uikit/input', component: InputDemoComponent},
                    {path: 'uikit/button', component: ButtonDemoComponent},
                    {path: 'uikit/table', component: TableDemoComponent},
                    {path: 'uikit/list', component: ListDemoComponent},
                    {path: 'uikit/tree', component: TreeDemoComponent},
                    {path: 'uikit/panel', component: PanelsDemoComponent},
                    {path: 'uikit/overlay', component: OverlaysDemoComponent},
                    {path: 'uikit/menu', component: MenusDemoComponent},
                    {path: 'uikit/media', component: MediaDemoComponent},
                    {path: 'uikit/message', component: MessagesDemoComponent},
                    {path: 'uikit/misc', component: MiscDemoComponent},
                    {path: 'uikit/charts', component: ChartsDemoComponent},
                    {path: 'uikit/file', component: FileDemoComponent},
                    {path: 'utilities/display', component: DisplayComponent},
                    {path: 'utilities/elevation', component: ElevationComponent},
                    {path: 'utilities/flexbox', component: FlexboxComponent},
                    {path: 'utilities/grid', component: GridComponent},
                    {path: 'utilities/icons', component: IconsComponent},
                    {path: 'utilities/widgets', component: WidgetsComponent},
                    {path: 'utilities/spacing', component: SpacingComponent},
                    {path: 'utilities/typography', component: TypographyComponent},
                    {path: 'utilities/text', component: TextComponent},
                    {path: 'pages/crud', component: AppCrudComponent},
                    {path: 'pages/calendar', component: AppCalendarComponent},
                    {path: 'pages/timeline', component: AppTimelineDemoComponent},
                    {path: 'pages/invoice', component: AppInvoiceComponent},
                    {path: 'pages/help', component: AppHelpComponent},
                    {path: 'pages/empty', component: EmptyDemoComponent},
                    {path: 'documentation', component: DocumentationComponent},

                    {path: 'page/annee', component: PanneescolaireComponent},
                    {path: 'page/niveau', component: PniveauComponent},
                    {path: 'page/classe', component: PclasseComponent},
                    {path: 'page/matiere', component: PmatiereComponent},
                    {path: 'page/professeur', component: PprofesseurComponent},
                    {path: 'page/preinscription', component: PpreinscriptionComponent},
                    {path: 'page/preinscriptionValid', component: PreinscriptionvalideComponent},
                    {path: 'page/preinscriptionAcept', component: PreinscriptionAcceptComponent},
                    {path: 'page/inscription', component: PinscriptionComponent},
                    {path: 'page/evaluation', component: PevaluationComponent},
                    {path: 'page/scolarite', component: PscolariteComponent},
                    {path: 'page/user', component: PutilisateurComponent},
                    {path: 'page/titulaire', component: PtitulaireComponent},
                    {path: 'recu/:id', component: PrecuComponent},
                    {path: 'recuFrais/:id', component: RecuFComponent},
                    {path: 'details/:id', component: EleveDetailsComponent},
                    {path: 'note/:id', component: PnoteComponent},
                    {path: 'noteDetail/:id', component: DetailsNotesComponent},
                    {path: 'page/eleve', component: PeleveComponent},
                    {path: 'page/affectation', component: PaffectationComponent},
                    {path: 'page/inscriAncien', component: IncriptionAncienComponent},
                    {path: 'page/enseignement', component: EnseignementComponent},
                    {path: 'page/note', component: SaisieNoteComponent},
                    {path: 'page/bulletin', component: PbulletinComponent},
                    {path: 'page/noteVue', component: NotevueComponent},
                    {path: 'form/annee', component: FanneeScolaireComponent},

                ]
            },
            {path: 'error', component: AppErrorComponent},
            {path: 'access', component: AppAccessdeniedComponent},
            {path: 'notfound', component: AppNotfoundComponent},
             {path: 'login', component: LoginComponent, canActivate:[NoauthGuard] },
            {path: '**', redirectTo: '/notfound'},
        ], {scrollPositionRestoration: 'enabled'})
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
