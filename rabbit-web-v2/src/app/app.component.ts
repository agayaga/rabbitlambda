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
  public isBusy = false;
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
  public user = {
    email:'',
    pass:'',
    token:'',
  };

  constructor (private dataService:DataService, private logger:LoggerService){}
  
  ngOnInit(): void {
    this.generate();
   
  }
  getQuestions() {
    this.isBusy = true;
    this.dataService.getQuestions(this.user.token).subscribe( data => {
      this.questions =  data;
      this.isBusy = false;
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
  
  login() {
    this.logger.log('login');
    this.dataService.login(this.user.token).subscribe( res => {
      this.user.token = res.userid;
      if (this.user.token) {
        this.getQuestions();
      }
      this.logger.log(`GOt Respons: ${ JSON.stringify(res) }`);
    });
  }

  save() {
    this.isBusy = true;    
    const savedObj =  this.questions.reduce((newArr:Array<{}>, item:any) => {
      newArr.push({ id:item.id, answer: item.answervalue });
      return newArr;
    }, []);
    this.dataService.saveForm(savedObj, this.user.token).subscribe( res => {
      this.summary = `From total of ${ res.total_questions } questions you nailed ${ res.correct_answers}`;
      this.logger.log(`Got Respons: ${ JSON.stringify(res) }`);
      this.isBusy = false; 
    });
  }

  generate() {
    this.colorPick = Math.floor(Math.random() * this.possibleColors.length);
  }
}
