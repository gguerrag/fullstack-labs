import { Component, OnInit } from '@angular/core';
import { LabsService } from 'src/app/core/services/labs.service';
import { Laboratory } from 'src/app/core/models/laboratory.model';

@Component({
  selector: 'app-labs',
  templateUrl: './labs.component.html'
})
export class LabsComponent implements OnInit {

  labs: Laboratory[] = [];

  constructor(private labsService: LabsService) {}

  ngOnInit(): void {
    this.labs = this.labsService.getLabs();
  }

  toggleEstado(id: number): void {
    this.labsService.toggleEstado(id);
  }
}
