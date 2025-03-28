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