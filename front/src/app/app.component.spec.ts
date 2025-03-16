import { TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';

//TODO : pour skip les tests, mettre un x devant "describe" (ou "it" pour les TU)
xdescribe('AppComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterModule
      ],
      providers: [
        provideRouter([])
      ],
      declarations: [
        AppComponent
      ],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'MDD'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('MDD');
  });
});
