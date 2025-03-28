export interface Article {
  id: number;
  title: string;
  content: string;
  author: string;
  createdAt: Date;
}

export interface Theme {
  id: number;
  title: string;
  description: string;
}

export interface User {
  username: string,
  email: string,
  password?: string;
  subscribedThemes: number[]
};