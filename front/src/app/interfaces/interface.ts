export interface Article {
  id: number;
  title: string;
  content: string;
  author: string;
  createdAt: string;
  themeTitle: string;
  commentaries: Commentary[];
}

export interface Theme {
  id: number;
  title: string;
  description: string;
}

export interface Commentary {
  id: number;
  content: string;
  author: string;
}

export interface User {
  username: string;
  email: string;
  password?: string;
  subscribedThemes: number[];
}

export interface ArticleCreation {
  themeId: string;
  title: string;
  content: string;
}

export interface SessionInformation {
  token: string;
  type: string;
  id: number;
  username: string;
  email: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  email: string;
  username: string;
  password: string;
}
