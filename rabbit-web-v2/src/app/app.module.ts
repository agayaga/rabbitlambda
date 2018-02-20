import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { HttpModule } from '@angular/http';

import { DataService } from './services/data.service';
import { LoggerService } from './utils/logger/logger.service';

import { AppComponent } from './app.component';

import { MatButtonModule } from '@angular/material';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    MatButtonModule
  ],
  providers: [DataService, LoggerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
