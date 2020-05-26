import {Directive, ElementRef, HostListener} from '@angular/core';

@Directive({
  selector: 'input[appNumeric]'
})
export class NumericDirective {

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
    let num = this.element.nativeElement.value;
    num = initialValue.replace(/^0+/, '');
    // if ()
    num = num.match(/(0|[1-9][0-9]*)/)[0];
    num = Math.min(num, 99999);
    this.element.nativeElement.value = num;
    if (initialValue !== this.element.nativeElement.value) {
      alert('invalid input');
      event.stopPropagation();
    }
  }
}
