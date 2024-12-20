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
  constructor(private httpClient: HttpClient) {
    // this.httpClient.get('http://localhost:3000/products').subscribe((data: any) => {
    //   this.products = data;
    // });
  }
  addToCart(product:any) {
    this.cart.push(product.id);
  }

  placeNewOrder(){
    // this.httpClient.post('http://localhost:3000/orders', {products: this.cart}).subscribe((data: any) => {
    //   console.log(data);
    // });
    this.cart = [];
  }

}
