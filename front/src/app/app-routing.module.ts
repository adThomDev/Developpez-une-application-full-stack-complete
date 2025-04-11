import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { RegisterComponent } from './pages/auth/register/register.component';
import { ThemesComponent } from './pages/themes/themes.component';
import { UserProfileComponent } from './pages/user-profile/user-profile.component';
import { ArticlesComponent } from './pages/articles/articles.component';
import { ArticleCreationComponent } from './pages/article-creation/article-creation.component';
import { ArticleComponent } from './pages/article/article.component';

import { AuthGuard } from './guards/auth.guard';
import { UnauthGuard } from './guards/unauth.guard';

export const routes: Routes = [
  { path: '', canActivate: [UnauthGuard], component: LandingPageComponent },
  { path: 'login', canActivate: [UnauthGuard], component: LoginComponent },
  {
    path: 'register',
    canActivate: [UnauthGuard],
    component: RegisterComponent,
  },
  { path: 'articles', canActivate: [AuthGuard], component: ArticlesComponent },
  { path: 'themes', canActivate: [AuthGuard], component: ThemesComponent },
  {
    path: 'profile',
    canActivate: [AuthGuard],
    component: UserProfileComponent,
  },
  {
    path: 'create-article',
    canActivate: [AuthGuard],
    component: ArticleCreationComponent,
  },
  {
    path: 'article/:id',
    canActivate: [AuthGuard],
    component: ArticleComponent,
  },
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
