import { Pageable } from "src/app/core/model/page/Pageable";
import { Loan } from "./Loan";

export class LoanPage {
    content: Loan[] = [];
    pageable: Pageable = new Pageable();
    totalElements: number = 0;
}
