import { Component, OnInit } from '@angular/core';
import { DataService } from './services/data.service';
import { LoggerService } from './utils/logger/logger.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public questions: Array<{}>;
  public answers: Array<{}>;
  public step: number = 0;
  constructor (private dataService:DataService, private logger:LoggerService){}
  
  ngOnInit(): void {
    this.dataService.getQuestions().subscribe( data => {
      this.questions =  data;
     });
  }

  goNext() {
    this.step++;
  }

  goBack() {
    this.step--;
  }

  save() {
    const savedObj =  this.questions.reduce((newArr:Array<{}>, item:any) => {
      newArr.push({ id:item.id, answer: item.answervalue });
      return newArr;
    }, []);
    this.dataService.saveForm(savedObj).subscribe( res => {
      this.logger.log(`GOt Respons: ${ res }`);
    });
  }
}
