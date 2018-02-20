import { Injectable } from '@angular/core';
import { Http, RequestOptions, } from '@angular/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/from';

import { LoggerService } from '../utils/logger/logger.service';
import { HttpClient } from '@angular/common/http';

const API_URL =   'https://heozkamkfc.execute-api.us-east-2.amazonaws.com/latest';

@Injectable()
export class DataService {

  constructor (
    private loggerService:LoggerService,
    private http:HttpClient
  ){}

  getQuestions(token:string): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'authorization': token,
      })
    };

    this.loggerService.log(`fetching questions: ${ JSON.stringify(httpOptions) }`);
    return this.http.get(`${API_URL}/db`, httpOptions)
        .map(response => response)
        .do((res)=>{
            this.loggerService.log(`GOt Respons: ${ JSON.stringify(res) }`);
          })
        .catch((error:any) =>{
          this.loggerService.log(`An Error occured: ${JSON.stringify(error)}`);
          return Observable.throw(`An Error occured: ${JSON.stringify(error)}`)
        });
  }

  saveForm(formData:any, token:string): Observable<any> {
    let bodyString:string = JSON.stringify(formData);
    const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'authorization': token,
        })
      };
    this.loggerService.log(`form data #: ${JSON.stringify(formData) }`);
    return this.http.post(`${API_URL}/submit`, bodyString, httpOptions )
        .map(response => response)
        .do((response)=>{
              this.loggerService.log(`Got response #: ${ JSON.stringify(response) }`);
          })
        .catch((error:any) =>{
          this.loggerService.log(`An Error occured: ${JSON.stringify(error)}`);
          return Observable.throw(`An Error occured: ${JSON.stringify(error)}`)
        });
  }

  login(user:any): Observable<any> {
    let bodyString:string = JSON.stringify(user);
    const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
        })
      };
    this.loggerService.log(`Doing login now`);
      return Observable.from([{ userid: 'gh56HHG78KJuu' }]);
    // return this.http.post(`${API_URL}/logon`, bodyString, httpOptions )
    //     .map(response => response)
    //     .do((response)=>{
    //           this.loggerService.log(`Got response #: ${ JSON.stringify(response) }`);
    //       })
    //     .catch((error:any) =>{
    //       this.loggerService.log(`An Error occured: ${JSON.stringify(error)}`);
    //       return Observable.throw(`An Error occured: ${JSON.stringify(error)}`)
    //     });
  }
  
}
