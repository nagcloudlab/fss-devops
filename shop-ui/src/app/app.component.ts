import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'shop-ui';

  constructor(private httpClient: HttpClient) {}

  products = [];

  ngOnInit() {
    this.httpClient
      .get('http://104.211.116.249:8081/products')
      .subscribe((data: any) => {
        this.products = data;
      });
  }
}
