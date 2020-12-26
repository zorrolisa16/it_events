import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/layout/header/header.component';
import { NotFoundComponent } from './components/not-found-component/not-found-component.component';
import { AccountListComponent } from './components/account-list/account-list.component';
import { AccountComponent } from './components/account/account.component';
import { CreateAccountComponent } from './components/create-account/create-account.component'
import { SingleAccountComponent } from './components/single-account/single-account.component';
import { TransactionComponent } from './components/transaction/transaction.component';
import { FormsModule } from '@angular/forms';
import { CreateTransactionComponent } from './components/create-transaction/create-transaction.component';
import { FindUserComponent } from './components/find-user/find-user.component';
import { FilterComponent } from './components/filter/filter.component'; // -> imported filter pipe

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NotFoundComponent,
    AccountListComponent,
    AccountComponent,
    CreateAccountComponent, 
    FilterComponent,  
    SingleAccountComponent, TransactionComponent, CreateTransactionComponent, FindUserComponent, FilterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
