# 👤 app-user-management — Gestión de usuarios (registro y autenticación)

## Descripción
Módulo responsable de registrar usuarios y autentificarlos; emite tokens JWT tras login. Implementado con Spring Boot y Spring Data JPA. El esquema de la BD se genera desde las entidades JPA.

## Arquitectura
- Entidad principal: `User` (`id`, `username`/`email`, `password` (hashed), `roles`).  
- Capas: Repository → Service → Controller.  
- Seguridad: Spring Security + filtro JWT stateless.

## Endpoints principales

### Registro
- Método: `POST /api/auth/register`
- Body (JSON):
```json
{
  "email": "juan@ejemplo.com",
  "password": "P@ssw0rd!",
  "name": "juan perez"
}
```
- Respuestas típicas:
  - `201 Created` (usuario creado)
  - `400 Bad Request` (validación)

### Login
- Método: `POST /api/auth/login`
- Body (JSON):
```json
{
  "email": "juan@ejemplo.com",
  "password": "P@ssw0rd!"
}
```
- Respuesta (200 OK):
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "expiresIn": 3600
}
```
- Errores: `401 Unauthorized` (credenciales inválidas)

## Ejemplos rápidos (curl)
Registro:
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"juan","email":"juan@e.com","password":"P@ssw0rd!"}'
```

Login:
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"juan","password":"P@ssw0rd!"}'
```
