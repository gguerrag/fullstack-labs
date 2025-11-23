import { Injectable } from '@angular/core';
import { User, UserRole } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // Lista estática simulando BD
  private users: User[] = [
    {
      id: 1,
      nombre: 'Admin',
      apellido: 'Sistema',
      email: 'admin@lab.com',
      password: 'Admin123*',
      rol: 'ADMIN'
    },
    {
      id: 2,
      nombre: 'Paciente',
      apellido: 'Ejemplo',
      email: 'paciente@lab.com',
      password: 'Paciente123*',
      rol: 'PATIENT'
    }
  ];

  private currentUser: User | null = null;

  login(email: string, password: string): boolean {
    const user = this.users.find(
      u => u.email === email && u.password === password
    );
    if (user) {
      this.currentUser = user;
      return true;
    }
    return false;
  }

  logout(): void {
    this.currentUser = null;
  }

  getCurrentUser(): User | null {
    return this.currentUser;
  }

  getRole(): UserRole | null {
    return this.currentUser?.rol ?? null;
  }

  getAllUsers(): User[] {
    return this.users;
  }

  addUser(user: Omit<User, 'id'>): void {
    const newUser: User = {
      ...user,
      id: this.users.length + 1
    };
    this.users.push(newUser);
  }
}
