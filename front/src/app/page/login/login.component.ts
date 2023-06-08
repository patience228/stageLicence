import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginServiceService } from 'src/app/service/login-service.service';
import { MytranslateService } from 'src/app/service/mytranslate.service';
import { NotificationService } from 'src/app/service/notification.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {


    log:{ username? , password?} ={};


  constructor(private loginService:LoginServiceService,public translate: MytranslateService,private router:Router,
    private notificationService: NotificationService) { }

  ngOnInit(): void {
    const sign_in_btn = document.querySelector("#sign-in-btn");
    const sign_up_btn = document.querySelector("#sign-up-btn");
    const container = document.querySelector(".container");

    sign_up_btn.addEventListener("click", () => {
      container.classList.add("sign-up-mode");
    });

    sign_in_btn.addEventListener("click", () => {
      container.classList.remove("sign-up-mode");
    });
  }

  login(){

    this.loginService.login(this.log).then(data=>{
        this.notificationService.showPrimeNgToast("success","",this.translate.instant("logged in successfully"))
        localStorage.setItem("token",data.accessToken)
        localStorage.setItem("refreshToken",data.refreshToken )
        setTimeout(() => {
        this.router.navigate(["/"])
        }, 100);

    });
  }

}
