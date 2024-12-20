import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {CommonModule, CurrencyPipe} from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [CommonModule, CurrencyPipe],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'shop-ui-service';
  products:Array<any> = [
    {
      id: 1,
      name: 'Product 1',
      price: 100
    },
    {
      id: 2,
      name: 'Product 2',
      price: 200
    },
    {
      id: 3,
      name: 'Product 3',
      price: 300
    }
  ];
  cart:Array<any> = [];
  message = '';
  constructor(private httpClient: HttpClient) {
    this.httpClient.get('http://98.70.125.146:8081/products').subscribe((data: any) => {
      this.products = data;
    });
  }
  addToCart(product:any) {
    this.cart.push(product.id);
  }

  placeNewOrder(){
    this.httpClient.post('http://98.70.125.146:8082/order', this.cart).subscribe((data: any) => {
      this.message = data
      setTimeout(() => {
        this.message = '';
      }, 5000);
    });
    this.cart = [];
  }

}
