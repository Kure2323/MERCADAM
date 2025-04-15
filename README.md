# Proyecto Mercadam - Sistema de Compra Online

Este proyecto es una simulación de un sistema de compra online llamado **Mercadam**. Permite a un cliente registrarse, iniciar sesión, añadir productos a la cesta y realizar un pedido con opciones de descuento.

## Descripción General

El proyecto está estructurado en varias clases que permiten gestionar clientes, productos y pedidos. El cliente tiene la capacidad de registrarse, añadir productos a su carrito, aplicar descuentos y finalizar su compra.

## Clases Principales

### **`AppZonaClientes`**

Esta clase es el punto de entrada de la aplicación. Se encarga de gestionar la autenticación de los clientes y la interacción con el sistema de compras.

- **`autenticacion(List<Cliente> clientes)`**  
  Verifica las credenciales de los usuarios. Si la autenticación es exitosa, se asigna al cliente y se inicia el proceso de compra.

- **`iniciarCompra()`**  
  Inicia un nuevo pedido y muestra los productos disponibles para la compra.

- **`imprimirProductos()`**  
  Muestra la lista de productos junto con sus precios y permite al cliente añadir productos al carrito.

- **`imprimirDespedida()`**  
  Muestra un mensaje de despedida al finalizar el pedido.

### **`Cliente`**

Representa a un cliente que puede interactuar con el sistema, creando pedidos y gestionando los productos que añade a su carrito.

- **`crearPedido()`**  
  Crea un nuevo pedido para el cliente.

- **`insertarProducto()`**  
  Permite al cliente añadir productos al carrito y verificar si el producto existe.

- **`opciones(Producto p)`**  
  Añade el producto a la cesta y permite al cliente elegir entre seguir comprando, aplicar promociones o finalizar el pedido.

- **`mostrarListaProd()`**  
  Muestra los productos en la cesta junto con su precio y el importe total.

### **`Mercadam`**

Gestión de clientes y generación de productos aleatorios para simular el sistema.

- **`generarClientes()`**  
  Crea clientes aleatorios con usuarios y contraseñas generados automáticamente.

- **`getClientes()`**  
  Devuelve una lista de los clientes registrados.

### **`Pedido`**

Representa un pedido realizado por un cliente. Permite aplicar promociones y gestionar los productos en la cesta.

- **`aplicarPromo3x2()`**  
  Aplica la promoción 3x2 a los productos que se repiten en múltiplos de 3.

- **`aplicarPromo10()`**  
  Aplica un descuento del 10% al importe total del pedido.

### **`Producto` (Enum)**

Enum que contiene los productos disponibles para la compra, cada uno con un precio asociado.

- **`MANZANAS`, `PAN`, `ARROZ`, etc.**  
  Representan los productos disponibles en el sistema, con precios asociados.

## Cómo Ejecutar el Proyecto

1. Clona este repositorio:
    ```bash
    git clone https://github.com/tu-usuario/mercadam.git
    ```

2. Abre el proyecto en **IntelliJ IDEA** o cualquier IDE compatible con **Java**.

3. Ejecuta la clase **`AppZonaClientes`**. Esta es la clase principal donde se inicia el proceso de compra.

4. Sigue las instrucciones que aparecen en la consola para autenticarte, añadir productos al carrito y finalizar tu pedido.

## Estructura del Proyecto

El proyecto está estructurado en las siguientes clases:

