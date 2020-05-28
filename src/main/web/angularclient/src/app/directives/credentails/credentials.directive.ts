import {Directive, HostListener} from '@angular/core';

@Directive({
  selector: '[appCredentials]'
})
export class CredentialsDirective {

  constructor() {
  }

  @HostListener('keydown', ['$event'])
  onKeyDown(event) {
    const key = event.key;
    if (!key || key.length > 1 || (key >= 'a' && key <= 'z') || (key === '_') || (key === '-') || (key === '.') || (key >= '0' && key <= '9')) {
      return;
    }
    alert('invalid input');
    event.preventDefault();
  }
}
