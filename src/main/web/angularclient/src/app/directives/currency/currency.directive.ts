import {Directive, ElementRef, HostListener} from '@angular/core';

@Directive({
  selector: '[appCurrency]'
})
export class CurrencyDirective {

  constructor(private element: ElementRef) {
  }

  @HostListener('keydown', ['$event'])
  onKeyDown(event) {
    const key = event.key;
    if (key.length > 1 || (key >= '0' && key <= '9') || (key === '.')) {
      return;
    } else {
      alert('invalid input');
      event.preventDefault();
    }
  }

  @HostListener('input', ['$event'])
  onInputChange(event) {
    const initialValue = this.element.nativeElement.value;
    let num = initialValue.replace(/^0+/, '');
    num = num.match(/(0|[1-9][0-9]*)(\.\d{1,2})?/)[0];
    num = Math.min(num, 99999.99);
    this.element.nativeElement.value = num;
    if (initialValue !== this.element.nativeElement.value) {
      alert('invalid input');
      event.stopPropagation();
    }
  }

}
