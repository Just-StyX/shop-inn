import { Component } from '@angular/core';
import { NgIf } from '@angular/common';
import { skipUntil } from 'rxjs';
import { CodeInputModule } from 'angular-code-input';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/services/authentication.service';

@Component({
  selector: 'app-activate-account',
  standalone: true,
  imports: [NgIf, CodeInputModule],
  templateUrl: './activate-account.component.html',
  styleUrl: './activate-account.component.scss',
})
export class ActivateAccountComponent {
  message: string = '';
  isActivated: boolean = true;
  isSubmitted: boolean = false;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {}

  private confirmAccount(token: string) {
    this.authenticationService
      .confirm({
        token,
      })
      .subscribe({
        next: () => {
          this.message =
            'Your account has been successfully activated.\nNow you can proceed to login';
          this.isSubmitted = true;
        },
        error: () => {
          this.message = 'Token has been expired or invalid';
          this.isSubmitted = true;
          this.isActivated = false;
        },
      });
  }

  redirectToLogin() {
    this.router.navigate(['login']);
  }

  onCodeCompleted(token: string) {
    this.confirmAccount(token);
  }

  protected readonly skipUntil = skipUntil;
}
