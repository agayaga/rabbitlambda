import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public questions: Array<{}>;
  public step: number = 0;
  ngOnInit(): void {
    this.questions = [
      {
      id: 1,
      type: "open",
      q: "What's your name",
      answers: [ ],
      correct_answer: [ ]
      },
      {
      id: 2,
      type: "single",
      q: "21 * 2?",
      answers: [
      "42",
      "43",
      "45",
      "67"
      ],
      correct_answer: [
      0
      ]
      },
      {
      id: 2,
      type: "multiple",
      q: "21 * 2?",
      answers: [
      "42",
      "42",
      "45",
      "45"
      ],
      correct_answer: [
      0,
      1
      ]
      }
      ];
  }

  goNext() {
    this.step++;
  }

  goBack() {
    this.step--;
  }
}
