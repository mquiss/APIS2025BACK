
# 🛒 Sistema de E-Commerce - Backend Java Spring

Este repositorio corresponde al **backend** del Trabajo Práctico Obligatorio de la materia _Aplicaciones Interactivas_ (Primer Cuatrimestre 2025).

La aplicación simula un sistema de e-commerce que permite a los usuarios registrarse, iniciar sesión, navegar por un catálogo de productos, gestionar un carrito de compras y publicar productos.

## 📑 Índice

1. [🧰 Requisitos](#-requisitos)
2. [🚀 Instrucciones para levantar el proyecto](#-instrucciones-para-levantar-el-proyecto)
    - [1️⃣ Clonar y preparar el entorno](#1-clonar-y-preparar-el-entorno)
      - [✅ Opción A: IntelliJ IDEA](#-opción-a-intellij-idea)
      - [✅ Opción B: Visual Studio Code](#-opción-b-visual-studio-code)
    - [2️⃣ Asignar variables de entorno](#2-asignar-variables-de-entorno)
      - [✅ Opción A: IntelliJ IDEA](#-opción-a-intellij-idea-1)
      - [✅ Opción B: Visual Studio Code](#-opción-b-visual-studio-code-1)
    - [3️⃣ Iniciar servidor](#3-iniciar-servidor)
      - [✅ Opción A: IntelliJ IDEA](#-opción-a-intellij-idea-2)
      - [✅ Opción B: Visual Studio Code](#-opción-b-visual-studio-code-2)
3. [🚪 Acceso al proyecto](#-acceso-al-proyecto)
4. [📦 Funcionalidades del proyecto](#-funcionalidades-del-proyecto)
5. [👥 Integrantes del grupo](#-integrantes-del-grupo)

---

## 🧰 Requisitos

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
---

## 🚀 Instrucciones para levantar el proyecto


### 1️⃣ Clonar y preparar el entorno

```bash
git clone https://github.com/mquiss/APIS2025BACK
```

#### ✅ Opción A: IntelliJ IDEA

1. Abre IntelliJ IDEA.
2. Ve a File > Open y selecciona la carpeta APIS2025BACK.

#### ✅ Opción B: Visual Studio Code

1. Abre Visual Studio Code.
2. Instala las siguientes extensiones:
   - Extension Pack for Java
   - Spring Boot Extension Pack
3. Ve a File > Open y selecciona la carpeta APIS2025BACK.

---

### 2️⃣ Asignar variables de entorno

Este proyecto utiliza variables de entorno para proteger credenciales sensibles. Estas se referencian en `application.yml` como:

```yaml
spring:
  data:
    mongodb:
      uri: ${DB_URI} 
  security:
    user:
      name: ${SC_USERNAME}
      password: ${SC_PASSWORD}
```

- `DB_URI`: URI de conexión a la base de datos MongoDB alojada en MongoDB Atlas. Incluye las credenciales de acceso, el nombre de la base de datos y parámetros de configuración necesarios para la conexión remota.
- `SC_USERNAME`: Nombre de usuario predeterminado para la autenticación básica de Spring Security.
- `SC_PASSWORD`: Contraseña correspondiente al usuario definido en SC_USERNAME, utilizada para proteger rutas HTTP mediante autenticación básica.

#### ✅ Opción A: IntelliJ IDEA

1. Ve a **Run > Edit Configurations**, seleccionando la clase principal (`EcommerceApplication`)
![img_4.png](docs/screenshots/img_4.png)
![img_2.png](docs/screenshots/img_2.png)
2. Selecciona **Modify options** y activa las variables de entorno
![img.png](docs/screenshots/img.png)
3. En la sección **Environment variables**, agrega:
- Opción 1: Cadena completa
![img_5.png](docs/screenshots/img_5.png)
```
DB_URI=mongodb+srv://<username>:<password>@cluster.mongodb.net/...;SC_USERNAME=<username>;SC_PASSWORD=<password>
```
- Opción 2: Clave valor
![img_1.png](docs/screenshots/img_1.png)

| Name        | Value                                                   |
|-------------|---------------------------------------------------------|
| DB_URI      | mongodb+srv://username:password@cluster.mongodb.net/... |
| SC_USERNAME | username                                                |
| SC_PASSWORD | password                                                |

#### ✅ Opción B: Visual Studio Code

1. Abre una terminal (PowerShell) y ejecuta:

```bash
$env:DB_URI= "mongodb+srv://username:password@cluster.mongodb.net/..."
$env:SC_USERNAME="username"
$env:SC_PASSWORD="password"
```
> Esto solo funcionará mientras la terminal se encuentre abierta. Al no ser permanente, se debe repetir el proceso antes de empezar a trabajar en el proyecto.

> 🔐 Evita hardcodear valores sensibles en `application.yml`, ya que el archivo se encuentra en el repositorio remoto.

---

### 3️⃣ Iniciar servidor

#### ✅ Opción A: IntelliJ IDEA
1. Haz clic en el botón verde sobre la clase `EcommerceApplication`:
![img_9.png](docs/screenshots/img_9.png)

#### ✅ Opción B: Visual Studio Code

1. Abre **Spring Boot Dashboard** y ejecuta la aplicación:
![img_6.png](docs/screenshots/img_6.png)

> Esto levantará el backend en: [http://localhost:8080](http://localhost:8080)

---

## 📦 Funcionalidades del proyecto

- [X] Products
- [ ] Carts
- [ ] Users
- [ ] Categories
- [ ] Orders
- [ ] Auth

> Cada funcionalidad se trabajará en su respectiva rama `feature\nombre`

```bash
git branch feature\products
git checkout feature\products
```


Guía para la **estructura de carpetas** de cada funcionalidad:

![img_10.png](docs/screenshots/img_10.png)


## 🚪 Acceso al proyecto

- **Login (Spring Security):** [http://localhost:8080/login](http://localhost:8080/login)
> Ingresar los valores de las variables de entorno **SC_USERNAME** y **SC_PASSWORD** en el formulario para poder acceder a todas las rutas del proyecto.


## 👥 Integrantes del grupo

| Nombre y Apellido         | Legajo  |
| ------------------------- | ------- |
| Jonathan Mayán            | 1159922 |
| Maria Quispe              | 1179220 |
| Gianfranco Matias Attadia | 1127384 |
| Sebastian Porini          |         |
| Bruno Roude               | 1183894 |
