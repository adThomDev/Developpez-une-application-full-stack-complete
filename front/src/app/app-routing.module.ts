import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ThemesComponent } from './pages/themes/themes.component';
import { UserProfileComponent } from './pages/user-profile/user-profile.component';
import { ArticlesComponent } from './pages/articles/articles.component';
import { ArticleCreationComponent } from './pages/article-creation/article-creation.component';
import { ArticleComponent } from './pages/article/article.component';

export const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'themes', component: ThemesComponent },
  { path: 'profile', component: UserProfileComponent },
  { path: 'articles', component: ArticlesComponent },
  { path: 'create-article', component: ArticleCreationComponent },
  { path: 'article/:id', component: ArticleComponent },
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
