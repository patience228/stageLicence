import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import {PrimeNGConfig} from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
    menuMode = 'sidebar';

    darkMode = 'light';

    topbarTheme = 'light';

    menuTheme = 'light';

    inputStyle = 'outlined';

    ripple: boolean;

    constructor(private primengConfig: PrimeNGConfig,private router:Router) { }

    ngOnInit() {
        if(localStorage.getItem("token"))
            this.router.navigate["/"]
        this.primengConfig.ripple = true;
    }
}
