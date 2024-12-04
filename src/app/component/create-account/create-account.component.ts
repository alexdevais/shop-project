import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { UserService } from '../../services/user.service';
import { User } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { PasswordValidators } from '../../validators/password-mismatch.validator';

@Component({
  selector: 'create-account',
  imports: [RouterModule, ReactiveFormsModule, CommonModule],
  standalone: true,
  templateUrl: './create-account.component.html',
  styleUrl: './create-account.component.css'
})
export class CreateAccountComponent {

  accountForm: FormGroup;
  emailError: string | null = null;
  submitted: boolean = false;

  constructor(private fb: FormBuilder, private userService: UserService, private router: Router) {
    this.accountForm = this.fb.group({
      firstName: ['', [Validators.required, Validators.minLength(2)]],
      lastName: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      passwordConfirmation: ['', [Validators.required, Validators.minLength(6)]]

    },
    {
      validators: PasswordValidators.passwordMatch()
    },
  )
}
  


  onSubmit(): void {
    this.submitted = true;

    if (this.accountForm.valid) {

      const user: User = this.accountForm.value;


      this.userService.createUser(this.accountForm.value).subscribe({
        next: () => {
          this.router.navigate(['/login'])
        },
        error: (err) => {
          
          if (err.status === 400 && err.error.error === 'Email is already in use') {

            this.emailError = 'This email is already in use.'

          } else if (err.status === 400 && err.error.error === 'Passwords do not match') {
            console.log('Passwords do not match')
          } else {
            console.error('error creating user: ', err);
          }
        }
      });
    }  
  }

}

