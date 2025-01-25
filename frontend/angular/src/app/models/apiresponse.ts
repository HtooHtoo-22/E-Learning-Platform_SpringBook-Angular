export interface ApiResponse<T> {
    httpStatus: string; // Matches HttpStatus in Java
    statusCode: number; // Matches the HTTP status code
    message: string;    // A user-friendly message from the backend
    status: string;     // A success or failure indicator
    data: T;            // The actual payload (generic type)
  }
  