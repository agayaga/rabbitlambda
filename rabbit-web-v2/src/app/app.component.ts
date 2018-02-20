import { Component, OnInit } from '@angular/core';
import { DataService } from './services/data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public questions: Array<{}>;
  public answers: Array<{}>;
  public step: number = 0;
  constructor (private dataService:DataService){}
  
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
    //ToDo: enter code
  }
}
