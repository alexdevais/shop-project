import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { UserService, User, AuthResponse } from '../../services/user.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm: FormGroup

  constructor(private fb: FormBuilder, private userService: UserService, private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],

    })
  }

  onSubmit(): void {
    if(this.loginForm.valid) {
      
      const user: User = this.loginForm.value;

      this.userService.login(user).subscribe({
        next: (token) => {

          this.userService.storeToken(token)
          
          this.router.navigate(['/shop-create'])
        },
        error: (err) => {
          alert('logging in failed')
          console.error('error loggin in',err)
        }
      })
    }
  }


}


