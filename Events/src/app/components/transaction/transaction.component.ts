import { Component, OnInit , Input} from '@angular/core';
import { AccountServiceService } from 'src/app/services/account-service.service';
import { Transaction} from 'src/app/components/types/Transaction';


@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {
@Input() transaction: Transaction;
  constructor(
  	private service: AccountServiceService) { }

  ngOnInit(): void {
  }

}
