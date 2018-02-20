import { Component, OnInit } from '@angular/core';
import { DataService } from './services/data.service';
import { LoggerService } from './utils/logger/logger.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  public questions: Array<{}>;
  public answers: Array<{}>;
  public summary: String;
  public step: number = 0;
  public colorPick: number;
  public possibleColors = [
    'darksalmon',
    'hotpink',
    'lightskyblue',
    'goldenrod',
    'peachpuff',
    'mediumspringgreen',
    'cornflowerblue',
    'blanchedalmond',
    'lightslategrey'
  ];
  
  constructor (private dataService:DataService, private logger:LoggerService){}
  
  ngOnInit(): void {
    this.generate();
    this.dataService.getQuestions().subscribe( data => {
      this.questions =  data;
     });
  }

  goNext() {
    this.generate();
    this.step++;
  }

  goBack() {
    this.generate();
    this.step--;
  }

  save() {
    const savedObj =  this.questions.reduce((newArr:Array<{}>, item:any) => {
      newArr.push({ id:item.id, answer: item.answervalue });
      return newArr;
    }, []);
    this.dataService.saveForm(savedObj).subscribe( res => {
      this.summary = `From total of ${ res.total_questions } questions you nailed ${ res.correct_answers}`;
      this.logger.log(`GOt Respons: ${ JSON.stringify(res) }`);
    });
  }

  generate() {
    this.colorPick = Math.floor(Math.random() * this.possibleColors.length);
  }
}
