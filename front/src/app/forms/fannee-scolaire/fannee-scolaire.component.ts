import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { NiveauService } from 'src/app/service/niveauService';


@Component({
  selector: 'app-fannee-scolaire',
  templateUrl: './fannee-scolaire.component.html',
  styleUrls: ['./fannee-scolaire.component.scss']
})
export class FanneeScolaireComponent implements OnInit {
    niveaux: any[];

    filteredNiveaux: any[];

    selectedNiveauAdvanced: any[];

  constructor(private niveauService: NiveauService, private messageService: MessageService,
    private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
    this.niveauService.getNiveaux().then(niveaux => {
        this.niveaux = niveaux;
  });

  }


  filterNiveau(event) {
    const filtered: any[] = [];
    const query = event.query;
    for (let i = 0; i < this.niveaux.length; i++) {
        const niveau = this.niveaux[i];
        if (niveau.libelle.toLowerCase().indexOf(query.toLowerCase()) == 0) {
            filtered.push(niveau);
        }
    }

    this.filteredNiveaux = filtered;
}

}
