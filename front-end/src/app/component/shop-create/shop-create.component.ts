import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ShopService, Shop } from '../../services/shop.service';

@Component({
  selector: 'app-shop-create',
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './shop-create.component.html',
  styleUrl: './shop-create.component.css'
})

export class ShopCreateComponent {

  shopForm: FormGroup 

  constructor(private fb: FormBuilder, private shopService: ShopService, private router: Router) {
    this.shopForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      city: ['', [Validators.required, Validators.minLength(3)]],
      address: ['', [Validators.required, Validators.minLength(3)]],
      postalCode: ['', [Validators.required, Validators.pattern('^[0-9]{5}$')]],
    })
  }

  onSubmit() {
    if (this.shopForm.valid) {
      const shop: Shop = this.shopForm.value;

      this.shopService.createShop(shop).subscribe({
        next: () => {
          alert('Shop created successfully');
          this.router.navigate(['/dashboard']);
        },
        error: (err) => {
          console.error('Error creating shop: ', err);
          alert('Failed to create shop');
        }
      });
    }
  }

}
