import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { UserService } from '../../services/user.service';
import { User } from '../../services/user.service';

@Component({
  selector: 'create-account',
  imports: [RouterModule, ReactiveFormsModule],
  standalone: true,
  templateUrl: './create-account.component.html',
  styleUrl: './create-account.component.css'
})
export class CreateAccountComponent {

  accountForm: FormGroup

  constructor(private fb: FormBuilder, private userService: UserService, private router: Router) {
    this.accountForm = this.fb.group({
      firstName: ['', [Validators.required, Validators.minLength(2)]],
      lastName: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],

    })
  }



  onSubmit(): void {
    if (this.accountForm.valid) {
      
      const user: User = this.accountForm.value;

      this.userService.createUser(this.accountForm.value).subscribe({
        next: () => {
          alert('user created');
          this.router.navigate(['/login'])
        },
        error: (err) => {
          console.error('error creating user: ', err);
          alert('Failed to create user')
        }
      })
    }
  }

}
