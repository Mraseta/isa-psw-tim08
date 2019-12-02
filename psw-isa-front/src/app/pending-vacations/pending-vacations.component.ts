import { Component, OnInit } from '@angular/core';
import { VacationService } from '../services/vacation/vacation.service';

@Component({
  selector: 'app-pending-vacations',
  templateUrl: './pending-vacations.component.html',
  styleUrls: ['./pending-vacations.component.css']
})
export class PendingVacationsComponent implements OnInit {
  pendingRequests = [];
  loaded = false;

  constructor(private vacationService: VacationService) { }

  ngOnInit() {
    this.getRequests();
  }

  getRequests() {
    this.vacationService.getPending()
      .subscribe(
        (data) => {
          console.log(data);
          this.pendingRequests = Object.assign([], (data));
          this.loaded = true;
        }
      )
  }

  prihvati(id) {
    /*this.vacationService.acceptRequest(id)
      .subscribe(
        () => {
          this.getRequests();
        }
      )*/
  }

  odbij(id) {
    /*this.vacationService.denyRequest(id)
    .subscribe(
      () => {
        this.getRequests();
      }
    )*/
  }
}
