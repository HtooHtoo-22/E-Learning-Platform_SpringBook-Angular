export interface User {
    id: number;
    name: string;
    email: string;
    password: string;
    city: string;
    gender: string;
    role: string;
    createdDate?: string; // Assuming createdDate is a string in ISO format (e.g., "2023-10-01T12:34:56Z")
    status: string;
    loginStatus: string;
    adminId?: number | null; // Use `number` for Integer, and allow null if it can be null
    adminName?: string | null; // Allow null if it can be null
  }