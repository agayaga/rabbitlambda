import { Injectable } from '@angular/core';
import { Http, RequestOptions, } from '@angular/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';

import { LoggerService } from '../utils/logger/logger.service';
const GET_QUESTIONS_URL = 'https://heozkamkfc.execute-api.us-east-2.amazonaws.com/latest/db';
const SAVE_QUESTIONS_URL = 'https://heozkamkfc.execute-api.us-east-2.amazonaws.com/latest/db';

@Injectable()
export class DataService {

  constructor (
    private loggerService:LoggerService,
    private http:Http
  ){}

  getQuestions(): Observable<any> {
    this.loggerService.log(`fetching current Room`);
    return this.http.get(GET_QUESTIONS_URL)
        .map(response => response.json())
        .do((res)=>{
            this.loggerService.log(`GOt Respons: ${ res }`);
          })
        .catch((error:any) =>{
          this.loggerService.log(`An Error occured: ${error}`);
          return Observable.throw(`An Error occured: ${error}`)
        });
  }

  saveForm(formData:any): Observable<any> {
    let bodyString = JSON.stringify(formData);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    this.loggerService.log(`form data #: ${JSON.stringify(formData) }`);
    return this.http.post(SAVE_QUESTIONS_URL, bodyString )
        .map(response => response.json())
        .do((response)=>{
              this.loggerService.log(`Got response #: ${ response }`);
          })
        .catch((error:any) =>{
          this.loggerService.log(`An Error occured: ${error}`);
          return Observable.throw(`An Error occured: ${error}`)
        });
  }
}
