import { Classe } from "./classe";
import { Matiere } from "./matiere";
import { Professeur } from "./professeur";

export interface Enseignement {
    id?:string;
    professeur?:Professeur;
    matiere?:Matiere;
    classe?:Classe[];
    active?:boolean;
}




