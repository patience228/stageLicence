import { Classe } from "./classe";
import { Matiere } from "./matiere";

export interface MatClasse {
    matiere?:Matiere;
    classe?:Classe[];
}
