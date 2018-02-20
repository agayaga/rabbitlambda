import { Injectable } from '@angular/core';
import { Http, RequestOptions, } from '@angular/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';

import { LoggerService } from '../utils/logger/logger.service';
import { HttpClient } from '@angular/common/http';
const GET_QUESTIONS_URL = 'https://heozkamkfc.execute-api.us-east-2.amazonaws.com/latest/db';
const SAVE_QUESTIONS_URL = 'https://heozkamkfc.execute-api.us-east-2.amazonaws.com/latest/submit';

@Injectable()
export class DataService {

  constructor (
    private loggerService:LoggerService,
    private http:HttpClient
  ){}

  getQuestions(): Observable<any> {
    this.loggerService.log(`fetching questions`);
    return this.http.get(GET_QUESTIONS_URL)
        .map(response => response)
        .do((res)=>{
            this.loggerService.log(`GOt Respons: ${ JSON.stringify(res) }`);
          })
        .catch((error:any) =>{
          this.loggerService.log(`An Error occured: ${JSON.stringify(error)}`);
          return Observable.throw(`An Error occured: ${JSON.stringify(error)}`)
        });
  }

  saveForm(formData:any): Observable<any> {
    let bodyString:string = JSON.stringify(formData);
    const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
        })
      };
    this.loggerService.log(`form data #: ${JSON.stringify(formData) }`);
    return this.http.post(SAVE_QUESTIONS_URL, bodyString, httpOptions )
        .map(response => response)
        .do((response)=>{
              this.loggerService.log(`Got response #: ${ JSON.stringify(response) }`);
          })
        .catch((error:any) =>{
          this.loggerService.log(`An Error occured: ${JSON.stringify(error)}`);
          return Observable.throw(`An Error occured: ${JSON.stringify(error)}`)
        });
  }
}
