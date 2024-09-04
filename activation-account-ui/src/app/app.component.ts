import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ActivateAccountComponent } from './activate-account/activate-account.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ActivateAccountComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'activation-account-ui';
}
