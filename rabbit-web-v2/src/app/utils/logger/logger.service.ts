import { Injectable } from '@angular/core';

@Injectable()
export class LoggerService {
  log(msg:any){
    console.log(`Logger: ${typeof(msg) === 'object' ? JSON.stringify(msg) :  msg}`);
  }
}
