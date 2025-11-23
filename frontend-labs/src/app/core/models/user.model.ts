export type UserRole = 'ADMIN' | 'PATIENT';

export interface User {
  id: number;
  nombre: string;
  apellido: string;
  email: string;
  password: string;
  rol: UserRole;
}
