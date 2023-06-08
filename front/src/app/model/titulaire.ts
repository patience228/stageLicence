import { Annee } from "./annee";
import { Classe } from "./classe";
import { Professeur } from "./professeur";

export interface Titulaire {
    id?:string;
    professeur?:Professeur;
    annee?:Annee;
    classe?:Classe;
    active?:boolean;
}
